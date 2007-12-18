/*
 * Command.java
 *
 * Created on November 1, 2007, 12:04 PM
 *
 * Copyright notice: http://www.gnu.org/licenses/gpl-3.0.txt
 */

package edu.lamar.frontgammon.protocol;

/**
 *
 * @author dan
 */
import java.io.*;
public enum Command implements Serializable 
{
    RollDice, ReRollDice, MoveFinished, DoubleCube, AskDouble, MoveChecker, Confirm, Unodo, Accepted, Rejected, Login, Denied, Refresh, Register, OK, Waiting,StartGame, Quit
}
