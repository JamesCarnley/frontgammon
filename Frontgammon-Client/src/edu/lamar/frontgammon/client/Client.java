/*
 * Client.java
 *
 * Created on November 4, 2007, 1:55 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.lamar.frontgammon.client;
import com.lloseng.ocsf.client.AbstractClient;
import edu.lamar.frontgammon.client.gui.*;
import edu.lamar.frontgammon.game.*;

import edu.lamar.frontgammon.protocol.*;
import java.io.IOException;
/**
 *
 * @author Nitesh
 */
public class Client extends AbstractClient {
    
    public BoardAndIntelligence bgPanel = null;
    public void setBackgammonPanel(BoardAndIntelligence bgP) {
        bgPanel = bgP;
    }
    
    public GameGUI ggui = null;
    public void setGameGUI(GameGUI ggui) {
     this.ggui = ggui;
    }
    
    public SignIn signIn = null;
    public void setSignIn(SignIn signIn)
    {
        this.signIn = signIn;
    }
    
    public int playerID = -1;
    public int gameID = -1;    
    /**
     * Creates a new instance of Client
     */
    public Client(String host, int port) {
        super(host,port);
    }
    
       
    protected void connectionClosed() {
    System.out.println("Connection Closed");
  
  }

  protected void connectionException(Exception exception) {
    System.out.println("Server Closed Connection");
  }

  protected void connectionEstablished() {
    System.out.println("Successfully Connected");
  }

  private void loadData(Object[] param) {
      ggui.getGameState().setD1((Integer)param[0]);
      ggui.getGameState().setD2((Integer)param[1]);
      ggui.getGameState().setDoubleValue((Integer)param[2]);
      ggui.getGameState().setlastDoubleOwnerID((Integer)param[3]);
      ggui.getGameState().setturnID((Integer)param[4]);
      ggui.getGameState().setNotFirstMove((Boolean)param[5]);
      ggui.getGameState().setPipCount1((Integer)param[6]);
      ggui.getGameState().setPipCount2((Integer)param[7]);
      ggui.getGameState().setErrorCode((Integer)param[8]);
      ggui.getGameState().setPlayer1((String)param[9]);
      ggui.getGameState().setPlayer2((String)param[10]);
      int ind=0;
      for(int i=0;i<120;i+=4)
      {
          Checker ch = new Checker((Integer)param[i+11],(Integer)param[i+12],(Integer)param[i+13],(Integer)param[i+14]);       
          ggui.getGameState().setChecker(ch,ind++);
      }
  }
  
 protected void handleMessageFromServer(Object msg) {
         Message m = (Message)msg;
         if(m.cmd == Command.Waiting)
         {
             System.out.println("Access Granted \nWaiting For Opponent...");
              signIn.waitingOpponent();
         }
         else if(m.cmd == Command.StartGame)
         {
             System.out.println("Start Game Received");
             gameID = (Integer)m.params[0];
             playerID = (Integer)m.params[1];
             ggui.getGameState().setPlayer1((String)m.params[2]);
             ggui.getGameState().setPlayer2((String)m.params[3]);             
             signIn.startGame();
         }
         
         else if(m.cmd == Command.Denied)
         {
             System.out.println("Access Denied");
             signIn.invalidLogin();
         }
         else if(m.cmd == Command.DoubleCube)
         {
             System.out.println("Double Cube");
             loadData(m.params);
             ggui.updateDoubleDice();
         }
         else if(m.cmd == Command.Login)
         {
             
         }
         else if(m.cmd == Command.MoveChecker)
         {
             System.out.println("Move verification result");             
             loadData(m.params);
             System.out.println("Error Code " + ggui.gs.errorCode );
             ggui.updateAll();       
            
         }
         else if (m.cmd == Command.MoveFinished)
         {
             loadData(m.params);
             ggui.moveFinished();
         }
         
         else if(m.cmd == Command.Confirm)
         {
             loadData(m.params);
             ggui.updateTurn();
         }
         else if(m.cmd == Command.Refresh)
         {
             loadData(m.params);
             ggui.refresh();
             
         }
         else if(m.cmd == Command.AskDouble)
         {
             ggui.askDouble();
             
         }
         else if(m.cmd == Command.Register)
         {
             
         }
         else if(m.cmd == Command.Rejected)
         {
              ggui.reportWinonReject();
             
         }
         else if(m.cmd == Command.RollDice)
         {
             boolean notifyWin = false;
             System.out.println("Roll Dice");
             if(!ggui.getGameState().notFirstMove)//This is the winner of the dice roll
                 notifyWin = true;
             
             loadData(m.params);             
             ggui.updateDice();
             
             if(notifyWin && ggui.getGameState().notFirstMove)
                 ggui.informResultofDiceRoll();
         }
         else if(m.cmd == Command.Quit)
         {
             ggui.reportwin();
         //System.out.println("Quit");
         }
         else if(m.cmd == Command.ReRollDice)
         {
             loadData(m.params);
            ggui.reRollDice();
         }
     
 }
 
 
 
 

 
 
 
    
    
}
