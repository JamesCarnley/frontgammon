/*
 * Die.java
 *
 * Created on September 6, 2007, 3:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.lamar.cs.frontgammon.gamepiece;

import java.lang.RuntimeException;
import java.util.Random;

/**
 * A class that represents a random die.
 *
 * @author Daniel Schultze
 */
public class Die
{
    private int sides;
    
    private Random randy;
    
    /** 
     * Creates a new instance of Die 
     *
     * @param side The number of sides for the die, minimum of 1 is required
     */
    public Die (int side)
    {
        if (side < 0)
            throw new RuntimeException ("A die needs more sides than 0. " + side + " is too small.");
        
        sides = side;
        randy = new Random ();
    }
    
    /**
     * Roll a die.
     *
     * @return the result of the roll.
     */
    public int roll ()
    {
        return randy.nextInt (sides) + 1;
    }
}
