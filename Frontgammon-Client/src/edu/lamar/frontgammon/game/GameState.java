/*
 * GameState.java
 *
 * This file is part of Frontgammon.
 *
 * Frontgammon is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Frontgammon is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Frontgammon.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Created on November 1, 2007, 12:32 PM
 *
 * Copyright notice: http://www.gnu.org/licenses/gpl-3.0.txt
 */
package edu.lamar.frontgammon.game;

/**
 *
 * @author Nitesh
 */
import java.io.*;

public class GameState implements Serializable {

    public int d1;

    public void setD1(int d1) {
        this.d1 = d1;
    }
    public int d2;

    public void setD2(int d2) {
        this.d2 = d2;
    }
    public int doubleValue = 0;

    public void setDoubleValue(int doubleValue) {
        this.doubleValue = doubleValue;
    }
    public int lastDoubleOwnerID = -1;

    public void setlastDoubleOwnerID(int lastDoubleOwnerID) {
        this.lastDoubleOwnerID = lastDoubleOwnerID;
    }
    public int errorCode;

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    public boolean notFirstMove;

    public void setNotFirstMove(boolean notFirstMove) {
        this.notFirstMove = notFirstMove;
    }
    public Checker[] checkers;

    public void setChecker(Checker ch, int i) {
        checkers[i] = ch;
    }
    public int pipCount1;

    public void setPipCount1(int pipCount1) {
        this.pipCount1 = pipCount1;
    }
    public int pipCount2;

    public void setPipCount2(int pipCount2) {
        this.pipCount2 = pipCount2;
    }
    public int turnID;

    public void setturnID(int turnID) {
        this.turnID = turnID;
    }
    public String player1 = "";

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }
    public String player2 = "";

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public String getPlayer(int pID) {
        if (pID == 0) {
            return this.player1;
        } else {
            return this.player2;
        }
    }

    public String getOpponent(int pID) {
        if (pID == 0) {
            return this.player2;
        } else {
            return this.player1;
        }
    }
    public int movedAmmount = 0;

    public void setMovedAmmount(int movedAmmount) {
        this.movedAmmount = movedAmmount;
    }

    /** Creates a new instance of GameState */
    public GameState() {
    }

    public GameState(boolean init) {
        if (init) {
            initGame();
        }
    }

    public void initGame() {
        pipCount1 = 167;
        pipCount2 = 167;
        turnID = 0;
        d1 = 0;
        d2 = 0;
        checkers = new Checker[30];
        checkers[0] = new Checker(0, 0, 0, 24);
        checkers[1] = new Checker(0, 0, 1, 24);
        checkers[2] = new Checker(0, 11, 0, 13);
        checkers[3] = new Checker(0, 11, 1, 13);
        checkers[4] = new Checker(0, 11, 2, 13);
        checkers[5] = new Checker(0, 11, 3, 13);
        checkers[6] = new Checker(0, 11, 4, 13);
        checkers[7] = new Checker(0, 16, 0, 8);
        checkers[8] = new Checker(0, 16, 1, 8);
        checkers[9] = new Checker(0, 16, 2, 8);
        checkers[10] = new Checker(0, 18, 0, 6);
        checkers[11] = new Checker(0, 18, 1, 6);
        checkers[12] = new Checker(0, 18, 2, 6);
        checkers[13] = new Checker(0, 18, 3, 6);
        checkers[14] = new Checker(0, 18, 4, 6);


        checkers[15] = new Checker(1, 23, 0, 24);
        checkers[16] = new Checker(1, 23, 1, 24);
        checkers[17] = new Checker(1, 12, 0, 13);
        checkers[18] = new Checker(1, 12, 1, 13);
        checkers[19] = new Checker(1, 12, 2, 13);
        checkers[20] = new Checker(1, 12, 3, 13);
        checkers[21] = new Checker(1, 12, 4, 13);
        checkers[22] = new Checker(1, 7, 0, 8);
        checkers[23] = new Checker(1, 7, 1, 8);
        checkers[24] = new Checker(1, 7, 2, 8);
        checkers[25] = new Checker(1, 5, 0, 6);
        checkers[26] = new Checker(1, 5, 1, 6);
        checkers[27] = new Checker(1, 5, 2, 6);
        checkers[28] = new Checker(1, 5, 3, 6);
        checkers[29] = new Checker(1, 5, 4, 6);

    }

    public int getOwner(int cID) {
        return checkers[cID].ownerID;
    }

    public int getDice1() {
        return d1;
    }

    public int getDice2() {
        return d2;
    }

    public void setDice(int dice1, int dice2) {
        d1 = dice1;
        d2 = dice2;
    }

    public void setDoubleDice(int id) {
        if (notFirstMove) {
            if (doubleValue == 64) {
                errorCode = 2;
            } //Double not permmitted max already reached                            
            else {
                if (doubleValue == 0) {
                    doubleValue = 2;
                } else {
                    doubleValue *= 2;
                }

                errorCode = 0;
                lastDoubleOwnerID = id;
            }
        } else {
            errorCode = 1;
        } //First Move not made yet
    }

    public int getDoubleDice() {
        return doubleValue;
    }

    public int getDoubleDiceOwner() {
        return lastDoubleOwnerID;
    }

    public int getCheckerCount(int pointID, int playerID) {
        int cnt = 0;
        for (int i = 0; i < 30; i++) {
            if (checkers[i].pointID == pointID) {
                if (pointID == 24) {
                    if (checkers[i].ownerID == playerID) {
                        cnt++;
                    }
                } else {
                    cnt++;
                }
            }
        }

        return cnt;

    }

    public int getTopElementOwnerID(int pointID) {
        int id = -100;
        int pos = -100;
        for (int i = 0; i < 30; i++) {
            if (checkers[i].pointID == pointID) {
                if (pos < checkers[i].pos) {
                    pos = checkers[i].pos;
                    id = checkers[i].ownerID;
                }
            }
        }

        return id;
    }

    public int getNextPosition(int pointID, int playerID) {
        int id = -1;
        int pos = -1;
        for (int i = 0; i < 30; i++) {
            if (checkers[i].pointID == pointID) {
                if (pos < checkers[i].pos) {
                    if (pointID == 24) {
                        if (playerID == checkers[i].ownerID) {
                            pos = checkers[i].pos;
                            id = checkers[i].ownerID;
                        }
                    } else {
                        pos = checkers[i].pos;
                        id = checkers[i].ownerID;
                    }
                }
            }
        }
        if (pos == -1) {
            return 0;
        } else {
            return (pos + 1);
        }
    }

    public void updateChecker(int checkerID, int newPointID, int stackPosition) {
        if (newPointID > 25) {
            checkers[checkerID].moveLeft = 0;
        } else {
            checkers[checkerID].moveLeft -= Math.abs(newPointID - checkers[checkerID].pointID);
        }

        movedAmmount += Math.abs(newPointID - checkers[checkerID].pointID);

        checkers[checkerID].pointID = newPointID;
        checkers[checkerID].pos = stackPosition;
    }

    public void hitOpponentChecker(int checkerID, int playerID) {
        int barID = 24;
        if (checkers[checkerID].ownerID == 1) {
            barID = 25;
        }
        checkers[checkerID].pointID = barID;
        checkers[checkerID].pos = getNextPosition(barID, playerID);
        checkers[checkerID].moveLeft = 24;
    }

    public boolean isAllCheckersHome(int playerID) {
        int startI, endI, homePointStartI, homePointEndI, homeI;
        int chkCount = 0;
        if (playerID == 1) {
            startI = 0;
            endI = 14;
            homePointStartI = 18;
            homePointEndI = 23;
            homeI = 25;
        } else {
            startI = 15;
            endI = 29;
            homePointStartI = 0;
            homePointEndI = 5;
            homeI = 26;
        }
        for (int i = startI; i <= endI; i++) {
            if ((checkers[i].pointID >= homePointStartI && checkers[i].pointID <= homePointEndI) || checkers[i].pointID == homeI) {
                chkCount++;
            }
        }
        if (chkCount == 15) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isWinner(int playerID) {
        int homeI;
        int chkCount = 0;
        if (playerID == 0) {
            homeI = 25;
        } else {
            homeI = 26;
        }
        for (int i = 0; i < 30; i++) {
            if (checkers[i].ownerID == playerID && checkers[i].pointID == homeI) {
                chkCount++;
            }
        }

        if (chkCount == 15) {
            return true;
        } else {
            return false;
        }
    }

    public int getPipCount(int playerID) {
        int startID;
        int cnt = 0;
        if (playerID == 0) {
            startID = 0;
        } else {
            startID = 15;
        }
        for (int i = startID; i < (startID + 15); i++) {
            cnt += checkers[i].moveLeft;
        }
        return cnt;
    }

    public Checker[] getCheckers() {
        return checkers;
    }

    public void updateTurn() {
        turnID = 1 - turnID;
    }

    public boolean isTurn(int playerID) {
        if (playerID == turnID) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isMovesLeft() {


        if (d1 == d2 && d1 != 0) {
            if (((4 * d1) - movedAmmount) == 0) {
                return false;
            } else {
                return true;
            }
        } else if ((d1 + d2 - movedAmmount) == 0 && d1 != 0) {
            return false;
        } else {
            return true;
        }
    }
}
