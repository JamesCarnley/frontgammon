/*
 * Server.java
 *
 * Created on November 1, 2007, 12:20 PM
 *
 * Copyright notice: http://www.gnu.org/licenses/gpl-3.0.txt
 */

package edu.lamar.frontgammon.server;

import com.lloseng.ocsf.server.ConnectionToClient;
import edu.lamar.frontgammon.game.Game;
import edu.lamar.frontgammon.game.GameState;
import edu.lamar.frontgammon.protocol.Command;
import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *
 * @author Daniel Schultze
 */
public class Server
{
    private AccountManager am;
    private MessageProcessor msgProc;
    private CyclicBarrier cBarrier;
    private PlayerAssociation pa;
    private ArrayList <Player> playerQueue = new ArrayList <Player> ();
    private Game g;
    private ConnectionManager connMan;
    
    /** Creates a new instance of Server */
    public Server ()
    {
        am = AccountManager.getAccountManager ();
        msgProc = new MessageProcessor (this);
    }
    
    public Player getPlayer (ConnectionToClient ctc)
    {
        throw new RuntimeException ("Not yet implemented");
    }
    
    public GameState rollDice (Player p)
    {
        Game g;
        GameState gs;
        
        g = getGameByPlayer (p);
        gs = g.rollDice (p.getId());
        
        return gs;
    }
    
    private Game getGameByPlayer (Player p)
    {
        return null;
    }
    
    public GameState doubleCube (Player p)
    {
        Game g;
        GameState gs;
        
        g = getGameByPlayer (p);
        gs = g.doubleCube (p.getId());
        
        return gs;
    }
    
    public GameState moveChecker (Player p, int checkerID, int from, int to)
    {
        Game g;
        GameState gs;
        
        g = getGameByPlayer (p);
        gs = g.moveChecker (p.getId(), checkerID, from, to);
        
        return gs;
    }
    
    public void login (ConnectionToClient ctc, String user, String pass)
    {
        Game g;
        Player p;
        
        p = am.login (user,pass);
        
        if (p == null)
        {
            msgProc.rejectLogin (ctc);
        }
        else
        {
            msgProc.acceptLogin (ctc);
            
            g = pa.getGame (ctc);
            if (g != null)
            {
                GameState state = g.getState ();
                refreshGameState (g);
            }
            else
            {
                awaitGameForPlayer (p);
                g = pa.getGame (p);
                registerCtcPlayerGame (ctc, p, g);
            }
        }
    }
    
    private void awaitGameForPlayer (Player p)
    {
        try
        {
            cBarrier.await ();
        }
        catch (InterruptedException ex)
        {
            ex.printStackTrace ();
        }
        catch (BrokenBarrierException ex)
        {
            ex.printStackTrace ();
        }
    }
    
    private void registerCtcPlayerGame (ConnectionToClient ctc, Player p, Game g)
    {
        pa.addMapping (ctc,p,g);
    }
    
    public void register (ConnectionToClient ctc, String user, String pass)
    {
        if (am.registerUser (user, pass))
        {
            login (ctc, user, pass);
        }
        else
        {
            msgProc.rejectRegister ();
        }
    }
    
    public void startSingleGame ()
    {
        cBarrier = new CyclicBarrier (2, new SingleGameBarrierHandler (this));
        msgProc = new MessageProcessor (this);
        connMan = new ConnectionManager (msgProc, 6000);
    }

    private void refreshGameState (Game g)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void quit(ConnectionToClient ctc, String string, String string0) {
        System.out.println("quit message from server");
    }
    
    public void rejected(ConnectionToClient ctc, String string, String string0) {
        System.out.println("Rejected message from server");
    }
    
    
    public class SingleGameBarrierHandler implements Runnable
    {
        private Server s;
        
        public SingleGameBarrierHandler (Server s)
        {
            this.s = s;
        }
        
        public void run ()
        {
            ConnectionToClient ctcA, ctcB;
            Player a, b;
            a = playerQueue.remove (0);
            b = playerQueue.remove (0);
            
            //s.g = new Game (playerQueue.remove (0), playerQueue.remove (0));
            ctcA = s.pa.getCtc (a);
            ctcB = s.pa.getCtc (b);
            s.pa.addMapping (ctcA, a, g);
            s.pa.addMapping (ctcB, b, g);
        }
    }
}
