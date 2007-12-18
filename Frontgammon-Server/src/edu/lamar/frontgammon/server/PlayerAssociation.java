/*
 * PlayerAssociation.java
 *
 * Created on November 2, 2007, 10:07 AM
 *
 * Copyright notice: http://www.gnu.org/licenses/gpl-3.0.txt
 */

package edu.lamar.frontgammon.server;

import com.lloseng.ocsf.server.ConnectionToClient;
import edu.lamar.frontgammon.game.Game;
import java.util.TreeMap;

/**
 *
 * @author dan
 */
public class PlayerAssociation
{
    TreeMap <ConnectionToClient, Player> ctcToP;
    TreeMap <Player, ConnectionToClient> playerToCtc;
    TreeMap <Player, Game> playerToG;
    
    /** Creates a new instance of PlayerAssociation */
    public PlayerAssociation ()
    {
        ctcToP = new TreeMap <ConnectionToClient, Player> ();
        playerToCtc = new TreeMap <Player, ConnectionToClient> ();
        playerToG = new TreeMap <Player, Game> ();
    }
    
    public Player getPlayer (ConnectionToClient ctc)
    {
        Player p = ctcToP.get (ctc);
        
        return p;
    }
    
    public ConnectionToClient getCtc (Player p)
    {
        ConnectionToClient ctc;
        
        ctc = playerToCtc.get (p);
        
        return ctc;
    }
    
    public void addMapping (ConnectionToClient ctc, Player p, Game g)
    {
        ctcToP.put (ctc,p);
        playerToCtc.put (p, ctc);
        playerToG.put (p, g);
    }
    
    public Game getGame (Player p)
    {
        Game g = playerToG.get (p);
        
        return g;
    }
    
    public Game getGame (ConnectionToClient ctc)
    {
        Player p = getPlayer (ctc);
        Game g = getGame (p);
        
        return g;
    }
}
