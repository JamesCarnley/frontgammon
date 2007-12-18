/*
 * Command.java
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
