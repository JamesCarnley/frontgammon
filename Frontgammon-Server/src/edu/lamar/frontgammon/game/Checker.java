/*
 * Checker.java
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
