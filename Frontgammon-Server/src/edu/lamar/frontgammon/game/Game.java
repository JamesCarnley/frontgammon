/*
 * Game.java
 *
 * Created on November 1, 2007, 12:33 PM
 *
 * Copyright : nitesh    not GPLed
 */

package edu.lamar.frontgammon.game;

import edu.lamar.frontgammon.*;
import edu.lamar.frontgammon.server.Player;
import com.lloseng.ocsf.server.*;
        
import java.util.Random;
/**
 *
 * @author nitesh
 */
public class Game
{
    Random r;
    GameState gs;
    Player[] players = new Player[2];
    public ConnectionToClient[] conn = new ConnectionToClient[2];
    
    
    public String status = "Waiting for Another Player";
    
    public int gameID;
    
    public int diceRollCount = 0;
    public int lastdiceRollVal = 0;
    public int doubleCubeCount = 0;
    
    /** Creates a new instance of Game */
    public Game (Player p1, Player p2, ConnectionToClient c1, ConnectionToClient c2)
    {
        players[0] = p1;
        players[1] = p2;
        players[0].setId(0);
        players[1].setId(1);
        conn[0] = c1;
        conn[1] = c2;
              
        gs = new GameState(true);
        r = new Random();
    }
    
    public boolean isMovesLeft() {
       return gs.isMovesLeft();
    }
    
    public void changeTurn() {
        gs.turnID = 1 - gs.turnID;        
    }
    
    public GameState rollDice (int p)
    {
        gs.setDice(r.nextInt(6) + 1 , r.nextInt(6) + 1);
        gs.setErrorCode(0); //Success
        return gs;
    }

    public GameState doubleCube (int p)
    {
        if((gs.getDoubleDiceOwner() == -1) || (gs.getDoubleDiceOwner() == (1 - p)))
        {
            gs.setDoubleDice(p);
            gs.setErrorCode(0); // Success
        }
        else
            gs.setErrorCode(9);// Not Double Dice Owner
        return gs;
    }

    public GameState moveChecker (int p, int checkerID, int from, int to)
    {
        if(p == gs.getOwner(checkerID))
        {
            int barID;
            if(p == 0)            
                barID = 24;
            else
                barID = 25;
            
            if(gs.getCheckerCount(barID,p) > 0)
            {
                //Player has checker in bar
                if(from == barID)
                {
                    verifyPosition(p,checkerID,from,to);
                }
                else
                    gs.setErrorCode(6); // Error Code 6 : Checker is on the bar cannot move other piece  
            }
            else if ( to == (barID + 2))
            {
                //check for homing validity
                //check if all the checkers are in home region
                if(gs.isAllCheckersHome(p))
                {
                    //Needs to implement rule of homing
                     verifyPosition(p,checkerID,from,to);
                }
                else
                    gs.setErrorCode(7); // Error Code 7 : Not all checkers are inside the home
            }
            else
            {                
               verifyPosition(p,checkerID,from,to);
            }
        }
        else
            gs.setErrorCode(3); // Error Code 3 : Checker not owned by current player
        return gs;
    }
    
    public Player[] getPlayers ()
    {
        return players;
    }

    public GameState getState ()
    {
        return gs;
    }
    
    public boolean checkIfValidToPlace(int checkerID, int playerID, int pointID) {
        if(gs.getCheckerCount(pointID, playerID) < 2)        
        {
            Checker[] ch = gs.getCheckers();
            for(int i=0;i<30;i++)
            {
                if(ch[i].pointID == pointID  && ch[i].ownerID != playerID)
                {
                    gs.hitOpponentChecker(i,1-playerID);
                    break;
                }
                
            }
           
            return true;        
        }
        else if(gs.getTopElementOwnerID(pointID) == playerID)            
                return true;
        else
            return false;      
    }
    
    public void verifyPosition(int p, int checkerID, int from,int  to)
    {
        
        //move validation
        
        int sign = 1;
        int d1 = gs.getDice1();
        int d2 = gs.getDice2();
        int movedAmnt = gs.movedAmmount;
        
        int twinDice = 1;
        if(p == 1)               
            sign = -1;                              
        if(d1 == d2)
            twinDice = 2;  

        if(from == 24 || from == 25) //trying to move from Bar
               from = -1;
       
        int validPos1 = from + sign * (d1 - movedAmnt);
        int validPos2 = from + sign * (d2 - movedAmnt);
        if(movedAmnt > 0 )
        {
            if(movedAmnt == d1)
                validPos1 = -1;
            if(movedAmnt == d2)
                validPos2 = -1;
            else
            {
                validPos1 = -1;
                validPos2 = -1;
            }
        }
       
        int validPos3 = from + (sign * (d1 + d2 - movedAmnt));
        int validPos4 = from + sign*(3*d1 - movedAmnt);
        int validPos5 = from + sign*(4*d1 - movedAmnt);
        
       //Trying to bearoff checker
       if(to == 26 || to == 27)
       {
           //Analayze Dice count and checkers left

           if((to == validPos1) || (to == validPos2)   || ( to == validPos3 ))
           {
               gs.updateChecker(checkerID,to,gs.getNextPosition(to,p));
               gs.setErrorCode(0); //Success
           }
           else if((twinDice == 2) && (( to == validPos4) ||  (to == validPos5)))
           {
               gs.updateChecker(checkerID,to,gs.getNextPosition(to,p));
               gs.setErrorCode(0); //Success
           }
           else
           {
              //now check if there is remaining checkers before this point in the home range
               int startID,endID;
               if(p == 0)
               {
                   startID = 5;
                   endID = from + 1;
               }
               else
               {
                   startID = from - 1;
                   endID = 18;
               }
               boolean allEmpty = true;
               for(int i=startID;i<=endID;i--)
                   if(gs.getCheckerCount(i,p) > 0)
                       allEmpty = false;

               if(allEmpty)
               {
                   //valid move
                   gs.updateChecker(checkerID,to,gs.getNextPosition(to,p));
                   gs.setErrorCode(0); //Success
               }
               else
                   gs.setErrorCode(8); // Error Code 8 : There are other checker left in previous point/s before this point                       
           }
       }
       else
       {
           if(to == validPos3)
           {
               if(checkIfValidToPlace(checkerID,p,to))
               {
                   gs.updateChecker(checkerID,to,gs.getNextPosition(to,p));
                   gs.setErrorCode(0); //Success                   
               }
               else
                   gs.setErrorCode(5); //Error Code 5 : Opponent has two or more checker in that point
           }
           else if(to == validPos1)
           {
               if(checkIfValidToPlace(checkerID,p,to))
               {
                   gs.updateChecker(checkerID,to,gs.getNextPosition(to,p));
                   gs.setErrorCode(0); //Success
               }
               else
                   gs.setErrorCode(5); //Error Code 5 : Opponent has two or more checker in that point
           }   
           else if(to == validPos2)
           {
               if(checkIfValidToPlace(checkerID,p,to))
               {
                   gs.updateChecker(checkerID,to,gs.getNextPosition(to,p));
                   gs.setErrorCode(0); //Success
               }
               else
                   gs.setErrorCode(5); //Error Code 5 : Opponent has two or more checker in that point
           }
           else if((twinDice == 2) && ( (to == validPos4)  || (to == validPos5) ) )
           {
               if(checkIfValidToPlace(checkerID,p,to))
               {
                   gs.updateChecker(checkerID,to,gs.getNextPosition(to,p));
                   gs.setErrorCode(0); //Success
               }
               else
                   gs.setErrorCode(5); //Error Code 5 : Opponent has two or more checker in that point
           }
           else           
               gs.setErrorCode(4); // Error Code 4 : Invalid Checker Placement
       }
            
    }
}
