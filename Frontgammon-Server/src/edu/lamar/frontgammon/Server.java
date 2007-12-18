/*
 * Server.java
 *
 * Created on November 5, 2007, 10:12 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.lamar.frontgammon;

/**
 *
 * @author Nitesh
 */
import com.lloseng.ocsf.server.*;
import edu.lamar.frontgammon.protocol.*;
import edu.lamar.frontgammon.game.*;
import edu.lamar.frontgammon.server.*;
import java.io.IOException;

import java.util.*;

public class Server extends AbstractServer {
    
    int gameID = 0;
    String[] winner= new String[3];
    
    MessageBox msgBox = new MessageBox();   ;
    
    Hashtable onGoingGames = new Hashtable();         
    
    private AccountManager am;
    ArrayList waitingList = new ArrayList();
    ArrayList waitingList2 = new ArrayList();
    ArrayList connList = new ArrayList();
    ArrayList connList2 = new ArrayList();
    int gameMode = 0; //0 : Non- Tournament Mode
                      //1 : Tournament Mode  
    
    
    public Hashtable getGames() {
        return onGoingGames;
    }
    
    
    /** Creates a new instance of Server */
    public Server(int port) {
        super(port);
        am = AccountManager.getAccountManager ();
        try {
            this.listen();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public Server(int port, int mode) {
        super(port);
        gameMode = mode;
        System.out.println("present game mode:"+ mode);
        am = AccountManager.getAccountManager ();
        try {
            this.listen();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
        Message mesg = (Message) msg;        
        Game g;
        int gID ,pID;
        Player[] p;
        Object[] param;
        
        switch (mesg.cmd)
            {
                case Register:
                    System.out.println("Registration Request Received");
                    register (client, mesg.params[2].toString (), mesg.params[3].toString ());     
                    System.out.println("Processed");
                    break;
                 case Login:
                     System.out.println("Login Request Received");
                    login (client, mesg.params[2].toString (), mesg.params[3].toString ());
                    System.out.println("Processed");
                    break;
                case RollDice:
                    Command cmd = Command.RollDice;
                    System.out.println("Roll Dice Request Received");
                    gID = (Integer)mesg.params[0]; 
                    pID = (Integer)mesg.params[1];                    
                    g = (Game)onGoingGames.get(gID);    
                    if(g == null)
                        System.out.println("No such Game found");
                    g.rollDice(pID);
                    g.getState().setMovedAmmount(0);
                    if(g.getState().notFirstMove)
                    {
                        System.out.println(g.getState().getDice1() + "  " + g.getState().getDice2());
                        sendGameState(g,cmd,pID);
                    }
                    else
                    {
                        
                        g.diceRollCount++;
                        int diceVal;
                        if(pID == 0)
                            diceVal = g.getState().d1;
                        else
                            diceVal = g.getState().d2;
                        if(g.diceRollCount==2)
                        {
                            if(g.lastdiceRollVal > diceVal)
                            {
                                g.getState().turnID = 1- pID;
                                g.getState().notFirstMove = true;
                            }
                            else if(g.lastdiceRollVal < diceVal)
                            {
                                g.getState().turnID = pID;
                                g.getState().notFirstMove = true;
                            }
                            else
                            {
                                //its a tie so let user roll again
                                g.diceRollCount=0;
                                cmd = Command.ReRollDice;
                               
                            }
                            
                            if(pID == 0)
                                g.getState().setD2(g.lastdiceRollVal);
                            else
                                g.getState().setD1(g.lastdiceRollVal);                                                         
                        }
                        else
                        {
                            if(pID == 0)
                            {
                                g.lastdiceRollVal = g.getState().d1;
                                g.getState().setD2(0);
                            }
                            else
                            {
                                g.lastdiceRollVal = g.getState().d2;
                                g.getState().setD1(0);
                            }
                                
                        }
                        
                         //g.getState().turnID = 0;//for testing purpose only
                        
                        sendGameState(g,cmd,pID);
                    }
                    System.out.println("Processed");
                    break;

               case DoubleCube:
                    System.out.println("Double Cube Request Received");
                    gID = (Integer)mesg.params[0]; 
                    pID = (Integer)mesg.params[1];                    
                    g = (Game)onGoingGames.get(gID);    
                    if(g == null)
                        System.out.println("No such Game found");
                    g.doubleCubeCount++;
                    if(g.doubleCubeCount == 2)
                    {
                        g.doubleCube (pID);
                        sendGameState(g,Command.DoubleCube,pID);
                        g.doubleCubeCount = 0;
                    }
                    else
                    {
                        try {
                            g.conn[1-pID].sendToClient(new Message(Command.AskDouble,new Object[1]));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    
                    System.out.println("Processed");                   
                    
                    break;

                case MoveChecker:

                    int from, to, checkerID;
                    System.out.println("Move Verification Request Received");
                    gID = (Integer)mesg.params[0]; 
                    pID = (Integer)mesg.params[1];                    
                    g = (Game)onGoingGames.get(gID);    
                    if(g == null)
                        System.out.println("No such Game found");
                    
                    checkerID = Integer.valueOf ((Integer)mesg.params[2]);
                    from = Integer.valueOf ((Integer)mesg.params[3]);
                    to = Integer.valueOf ((Integer)mesg.params[4]);
                    
                    System.out.println("checkerID:" + checkerID + "  fromPointID:"+ from + "  toPointID:" + to);
                    
                    g.moveChecker (pID, checkerID, from, to);
                    sendGameState(g,Command.MoveChecker,pID);
                    if(!g.isMovesLeft())                    
                    {                       
                        sendGameState(g,Command.MoveFinished,pID);
                    }
                    System.out.println("Processed");
                    break;
                    
            case Confirm:
                    System.out.println("Move Verification Request Received");
                    gID = (Integer)mesg.params[0]; 
                    pID = (Integer)mesg.params[1];                    
                    g = (Game)onGoingGames.get(gID);    
                    if(g == null)
                        System.out.println("No such Game found");
                    
                    g.changeTurn();
                    g.getState().setMovedAmmount(0);
                    
                    sendGameState(g,Command.Confirm,pID);
                 break;

                case Refresh:
                    break;
                    
            case Quit:
                System.out.println("Quit requested");
                //System.out.println("Double Cube Request Received");
                    gID = (Integer)mesg.params[0]; 
                    pID = (Integer)mesg.params[1];                    
                    g = (Game)onGoingGames.get(gID); 
                    if (pID == 0)
                        pID = 1;
                    else if (pID == 1)
                        pID = 0;
                    if(g == null)
                        System.out.println("No such Game found");
                  //  g.doubleCubeCount++;
                    //if(g.doubleCubeCount == 2)
                    //{
                      //  g.doubleCube (pID);
                        sendGameState(g,Command.Quit,pID);
                        Player[] players = new Player[2];
                        players = g.getPlayers();
                        System.out.println("Player ID"+players[pID].getName()+"GameID"+gID);
                        //g.doubleCubeCount = 0;
                       setwinner(players[pID].getName(),pID,gID,g);
                    //}
                    //else
                    //{
                        try {
                            g.conn[1-pID].sendToClient(new Message(Command.Quit,new Object[1]));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                   // }
                    
                    System.out.println("Processed");                   
                                        
                break;
            case Rejected:       
                    System.out.println("Double Cube Reject");
                    gID = (Integer)mesg.params[0]; 
                    pID = (Integer)mesg.params[1];                    
                    g = (Game)onGoingGames.get(gID); 
                    if (pID == 0)
                        pID = 1;
                    else if (pID == 1)
                        pID = 0;
                    if(g == null)
                        System.out.println("No such Game found");
                  //  g.doubleCubeCount++;
                    //if(g.doubleCubeCount == 2)
                    //{
                      //  g.doubleCube (pID);
                        sendGameState(g,Command.Rejected,pID);
                        Player[] players1 = new Player[2];
                        players1 = g.getPlayers();
                        System.out.println("Player ID"+players1[pID].getName()+"GameID"+gID);
                        //g.doubleCubeCount = 0;
                       setwinner(players1[pID].getName(),pID,gID,g);
                    //}
                    //else
                    //{
                        try {
                            g.conn[1-pID].sendToClient(new Message(Command.Rejected,new Object[1]));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                                       
                    
                default:
                    break;
            }
        

    }
    
    
    
    private Object[] getParamList(Game g) {
        Object[] param = new Object[131];
        param[0] = g.getState().d1;
        param[1] = g.getState().d2;
        param[2] = g.getState().doubleValue;
        param[3] = g.getState().lastDoubleOwnerID;
        param[4] = g.getState().turnID;
        param[5] = g.getState().notFirstMove;        
        g.getState().pipCount1 = g.getState().getPipCount(0);        
        param[6] = g.getState().pipCount1;
        g.getState().pipCount2 = g.getState().getPipCount(1);
        param[7] = g.getState().pipCount2;
        param[8] = g.getState().errorCode;
        param[9] = g.getState().player1;
        param[10] = g.getState().player2;
        int ind = 0;
        for(int i=0;i<120;i+=4)
        {
            param[11 + i] = g.getState().checkers[ind].ownerID;            
            param[12 + i] = g.getState().checkers[ind].pointID;
            param[13 + i] = g.getState().checkers[ind].pos;
            param[14 + i] = g.getState().checkers[ind++].moveLeft;           
        }
       
        return param;
    }
    
    private void sendGameState(Game g, Command cmd, int p)
    {
        Message msg1,msg2;
        msg1 = new Message(cmd, getParamList(g));
        //msg2 = new Message(Command.Refresh, getParamList(g));
        ConnectionToClient c1 = g.conn[0];
        ConnectionToClient c2 = g.conn[1];        
        
          
        try {
                     
            c1.sendToClient(msg1);
                c2.sendToClient(msg1);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    private void register (ConnectionToClient ctc, String user, String pass)
    {
        if (am.registerUser (user, pass))
            login (ctc, user, pass);        
        else
        {
            System.out.println("DENIED");
            Object[] obj = new Object[3];
            Message m = new Message(Command.Denied, obj);
            try {
                ctc.sendToClient(m);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void quit(ConnectionToClient ctc, String user, String pass)
    {
        System.out.println("Quit msg received");
    }
    
    private void rejected(ConnectionToClient ctc, String user, String pass)
    {
        System.out.println("Rejected msg received");
    }
    
    private void setwinner(String pname,int pID,int gID,Game g) 
     {
       //System.out.println("Winner player" +pID+"of game"+gID);
       //Player[] players = new Player[2];
       //players = g.getPlayers();
       System.out.println("Game:"+gID+" - "+pname+" wins ");
       winner[gID] = pname;
       System.out.println("testing entry to if loop, present gID = "+gID);
       if(gID>1)
       {
           System.out.println(" Tournament finished");
           msgBox.showMessage("Message to server\nTournament is finished\n"+pname+" won the tournament");
           g.status = pname+" wins";
       }
       else
       {
       if(gID==0)
       connList2.add(connList.get(pID+gID));
       else if(gID==1)
       connList2.add(connList.get(pID+gID+1));
           
       //System.out.println("---------------\nWinner List");
      // for(int i=0;i<3;i++)
        //   System.out.println(winner[i]);
       g.status = pname+" wins";
       onGoingGames.remove(g.gameID);
       
       if(onGoingGames.isEmpty() && gameMode==1)
       {
         System.out.println("First Round is done");
         msgBox.showMessage("   Message to server\nRound 1 finished\n Starting next round \n"+winner[0]+" vs "+winner[1]);
         Player p1=new Player(winner[0]);
         Player p2=new Player(winner[1]);
          
         waitingList2.add(p1);
         waitingList2.add(p2);
         System.out.println("----------------------------------\n new waiting list - size ="+waitingList.size());
         
         if(waitingList2.size() > 1)
            {
                
                System.out.println("Two Player Received");
                //This is non-tournament mode and we have at least two players
                p1 = (Player)waitingList2.get(0);
                p2 = (Player)waitingList2.get(1);
                ConnectionToClient c1 = (ConnectionToClient)connList2.get(0);  
                ConnectionToClient c2 = (ConnectionToClient)connList2.get(1);  
                
                Game g12 = new Game(p1,p2,c1,c2);
                
                // to set into table
                g12.getState().player1 = p1.getName();
                g12.getState().player2 = p2.getName();
                
                g12.gameID = gameID++;
                g12.status = "Game In Progress";
                
                
                
                
                onGoingGames.put(g12.gameID,g12);
                
                Object[] prms = new Object[4];
                prms[0] = g12.gameID;
                prms[1] = p1.getId();
                prms[2] = p1.getName();
                prms[3] = p2.getName();
                //prms[2] = getParamList(g);
                
                System.out.println("Game Created");
                
                Message mesg = new Message(Command.StartGame,prms);
                try {
                    
                    
                c1.sendToClient((Object)mesg);
                System.out.println("mesg sent to c1");
                prms[1] = p2.getId();
                mesg = new Message(Command.StartGame,prms);                
                c2.sendToClient((Object)mesg);
                System.out.println("mesg sent to c2");
                waitingList.remove(0);
                waitingList.remove(1);
                
                
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            else if(waitingList2.size() == 1)
            {
                Object[] prms = new Object[1];
                Message mesg = new Message(Command.Waiting,prms);
                try {
                    
                    System.out.println("Sending Wait Command to client");
                    ConnectionToClient c1 = (ConnectionToClient)connList2.get(0);
                    //ctc.sendToClient(mesg);
                    c1.sendToClient(mesg);
                 } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
           
         
         
         
       }
       else if(onGoingGames.isEmpty() && gameMode==0)
       {
         System.out.println("Single Game is Done");
         msgBox.showMessage("Message to server\nGame is finished");
           
       }
       }
    }
    
    private void login (ConnectionToClient ctc, String user, String pass)
    {
        Player p = am.login (user,pass);
        
        if (p == null)
        {
            System.out.println("LOGIN DENIED");
            Object[] obj = new Object[3];
            Message m = new Message(Command.Denied, obj);
            try {
                ctc.sendToClient(m);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else
        {
            System.out.println("GRANTED");
            //First insert this player in waitingList
            waitingList.add(p);
            System.out.println(waitingList.size());
            connList.add(ctc);
            //while(gameMode == 0 && waitingList.size() > 1)
            if(gameMode == 0 && waitingList.size() > 1)
            {
                
                System.out.println("Two Player Received");
                //This is non-tournament mode and we have at least two players
                Player p1 = (Player)waitingList.get(0);
                Player p2 = (Player)waitingList.get(1);
                ConnectionToClient c1 = (ConnectionToClient)connList.get(0);  
                ConnectionToClient c2 = (ConnectionToClient)connList.get(1);  
                
                Game g = new Game(p1,p2,c1,c2);
                
                g.getState().player1 = p1.getName();
                g.getState().player2 = p2.getName();
                
                g.gameID = gameID++;
                g.status = "Game In Progress";
                
                
                onGoingGames.put(g.gameID,g);
                
                Object[] prms = new Object[4];
                prms[0] = g.gameID;
                prms[1] = p1.getId();
                prms[2] = p1.getName();
                prms[3] = p2.getName();
                //prms[2] = getParamList(g);
                
                System.out.println("Game Created");
                
                Message mesg = new Message(Command.StartGame,prms);
                try {
                    
                    
                c1.sendToClient((Object)mesg);
                System.out.println("mesg sent to c1");
                prms[1] = p2.getId();
                mesg = new Message(Command.StartGame,prms);                
                c2.sendToClient((Object)mesg);
                System.out.println("mesg sent to c2");
                waitingList.remove(0);
                waitingList.remove(1);
                
                connList.remove(0);
                connList.remove(1);
                
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            else if(waitingList.size() == 1 && gameMode == 0)
            {
                Object[] prms = new Object[1];
                Message mesg = new Message(Command.Waiting,prms);
                try {
                    
                    System.out.println("Sending Wait Command to client");
                    ConnectionToClient c1 = (ConnectionToClient)connList.get(0);
                    //ctc.sendToClient(mesg);
                    c1.sendToClient(mesg);
                 } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            else if(gameMode == 1 && waitingList.size() > 3)
            {
                
                System.out.println("four Player entered in tournament");
                //This is non-tournament mode and we have at least two players
                Player p1 = (Player)waitingList.get(0);
                Player p2 = (Player)waitingList.get(1);
                                
                ConnectionToClient c1 = (ConnectionToClient)connList.get(0);  
                ConnectionToClient c2 = (ConnectionToClient)connList.get(1);  
                
                Game g = new Game(p1,p2,c1,c2);
                
                
                g.getState().player1 = p1.getName();
                g.getState().player2 = p2.getName();
                
                g.gameID = gameID++;
                g.status = "Game In Progress";
                
                onGoingGames.put(g.gameID,g);
                
                Object[] prms = new Object[4];
                prms[0] = g.gameID;
                prms[1] = p1.getId();
                prms[2] = p1.getName();
                prms[3] = p2.getName();
                //prms[2] = getParamList(g);
                
                System.out.println("Game 1 Created");
                                
                Message mesg = new Message(Command.StartGame,prms);
                try {
                    
                c1.sendToClient((Object)mesg);
                System.out.println("mesg sent to c1");
                prms[1] = p2.getId();
                mesg = new Message(Command.StartGame,prms);                
                c2.sendToClient((Object)mesg);
                System.out.println("mesg sent to c2");
                
                
                System.out.println("After starting game bn p1 and p2");
                }
                catch (IOException ex) 
                {
                    ex.printStackTrace();
                }
                
                System.out.println("before starting game bn p3 and p4");
                
///*                
                Player p3 = (Player)waitingList.get(2);
                Player p4 = (Player)waitingList.get(3);
                
                ConnectionToClient c3 = (ConnectionToClient)connList.get(2);  
                ConnectionToClient c4 = (ConnectionToClient)connList.get(3);  

                System.out.println("before game g bn p3 and p4");
                Game g1 = new Game(p3,p4,c3,c4);
                System.out.println("after game g bn p3 and p4");
             
                g1.getState().player1 = p3.getName();
                g1.getState().player2 = p4.getName();
               
                
                g1.gameID = gameID++;
                g1.status = "Game In Progress";
                
                onGoingGames.put(g1.gameID,g1);
                
                System.out.println("after ongoing bn p3 and p4");
                
                Object[] prms1 = new Object[4];
                prms1[0] = g1.gameID;
                prms1[1] = p3.getId();
                prms1[2] = p3.getName();
                prms1[3] = p4.getName();
                //prms[2] = getParamList(g);
                
                System.out.println("Game 2 Created");

                Message mesg1 = new Message(Command.StartGame,prms1);                
                try {
                
                c3.sendToClient((Object)mesg1);
                System.out.println("mesg sent to c3");
                prms1[1] = p4.getId();
                mesg1 = new Message(Command.StartGame,prms1);                
                c4.sendToClient((Object)mesg1);
                System.out.println("mesg sent to c4");
                
                waitingList.remove(0);
                waitingList.remove(1);
                waitingList.remove(2);
                waitingList.remove(3);
                /*
                connList.remove(0);
                connList.remove(1);
                connList.remove(2);
                connList.remove(3);
               */
                System.out.println("After starting game bn p3 and p4");
                }
                catch (IOException ex) 
                {
                    ex.printStackTrace();
                }
                
//*/
                
                
            }
            
            else if(gameMode==1 && waitingList.size()==1)
            {
                System.out.println("tournament selected");
                
                Object[] prms = new Object[1];
                Message mesg = new Message(Command.Waiting,prms);
                try {
                    
                    System.out.println("Tournament: Sending Wait Command to client");
                    ConnectionToClient c1 = (ConnectionToClient)connList.get(0);
                    c1.sendToClient(mesg);
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
            }
            
      //    /*
            else if(gameMode==1 && waitingList.size()==2)
            {
                System.out.println("tournament selected");
                
                Object[] prms = new Object[1];
                Message mesg = new Message(Command.Waiting,prms);
                try {
                    
                    System.out.println("Tournament: Sending Wait Command to client");
                   // ConnectionToClient c1 = (ConnectionToClient)connList.get(0);
                   // c1.sendToClient(mesg);
                    ConnectionToClient c2 = (ConnectionToClient)connList.get(1);
                    c2.sendToClient(mesg);
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
            }

            else if(gameMode==1 && waitingList.size()==3)
            {
                System.out.println("tournament selected");
                
                Object[] prms = new Object[1];
                Message mesg = new Message(Command.Waiting,prms);
                try {
                    
                    System.out.println("Tournament: Sending Wait Command to client");
                  //  ConnectionToClient c1 = (ConnectionToClient)connList.get(0);
                   // c1.sendToClient(mesg);
                   // ConnectionToClient c2 = (ConnectionToClient)connList.get(1);
                   // c2.sendToClient(mesg);
                    ConnectionToClient c3 = (ConnectionToClient)connList.get(2);
                    c3.sendToClient(mesg);
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
            }
     //*/
            
        }
    }
     
  
    
}
