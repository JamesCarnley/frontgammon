/*
 * Main.java
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
