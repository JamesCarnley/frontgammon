/*
 * ChatIF.java
 *
 * Created on September 15, 2007, 9:37 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package edu.lamar.cs.frontgammon.simplechat;

/**
 * This interface implements the abstract method used to display
 * objects onto the client or server UIs.
 *
 * @author Dr Robert Lagani&egrave;re
 * @author Dr Timothy C. Lethbridge
 * @version July 2000
 */
public interface ChatIF 
{
  /**
   * Method that when overriden is used to display objects onto
   * a UI.
   */
  public abstract void display(String message);
}