/*
 * Checker.java
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
 * Created on November 3, 2007, 8:56 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.lamar.frontgammon.game;

/**
 *
 * @author Nitesh
 */
import java.io.*;
public class Checker implements Serializable {

    public int ownerID;
    public int pointID;
    public int pos; 
    public int moveLeft;
    public int x;
    public int y;
    public int w;
    /** Creates a new instance of Checker */
    public Checker(int ownerID, int pointID, int position, int moveLeft) {        
        this.ownerID = ownerID;
        this.pointID = pointID;
        this.pos = position;
        this.moveLeft = moveLeft;
    }
    
    public boolean hitTest(int x, int y) {
        if(x >= this.x && x <= (this.x + w) && y >= this.y && y <= (this.y+w))
            return true;
        else
            return false;
    }
    
}
