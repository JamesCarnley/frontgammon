/*
 * Main.java
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
 * Created on October 29, 2007, 11:40 AM
 *
 * Copyright notice: http://www.gnu.org/licenses/gpl-3.0.txt
 */

package edu.lamar.frontgammon;

/**
 * Place holder for main function.
 *
 * @author Nitesh
 */
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import edu.lamar.frontgammon.server.gui.SelectionGUI;

public class Main
{
    
    /** Creates a new instance of Main */
    private Main ()
    {
    }
    
    public static void main (String args[])
    {
          
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
                
       SelectionGUI serverGUI = new SelectionGUI();
       serverGUI.setVisible(true);
                
    }
    
}
