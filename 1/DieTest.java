/*
 * DieTest.java
 *
 * Created on September 6, 2007, 3:50 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.lamar.cs.frontgammon.gamepiece;

import edu.lamar.cs.frontgammon.gamepiece.*;

/**
 * Test class for edu.lamar.cs.frontgammon.gamepiece.Die.
 *
 * @author Daniel Schultze
 */
public class DieTest
{
    
    /** Creates a new instance of DieTest */
    public DieTest ()
    {
    }
    
    public static void main (String args[])
    {
        //Create a die
        Die die = new Die (6);
        
        System.out.println ("Rolling die: " + die.roll () + " " + die.roll () + " " + die.roll ());
        
        try
        {
            int smallDie = -2;
            
            //Creating a die that has too few sides
            System.out.println ("Creating a die of side: " + smallDie + ".");
            Die die2 = new Die (smallDie);
        }
        catch (RuntimeException e)
        {
            System.out.println ("Exception caught: " + e.toString ());
        }
    }
}
