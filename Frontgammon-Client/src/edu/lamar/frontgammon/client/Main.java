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
 * Created on September 22, 2007, 8:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package edu.lamar.frontgammon.client;

import edu.lamar.frontgammon.client.gui.SignIn;
import java.io.IOException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import edu.lamar.frontgammon.client.gui.*;

/**
 *
 * @author James
 * Modified By Nitesh
 *
 */
public class Main {

    MessageBox mBox;
    Client cli;
    GameGUI ggui;

    /** Creates a new instance of Main */
    public Main(String hostname, int port) {
        mBox = new MessageBox();
        ggui = new GameGUI();
        ggui.setVisible(false);
        cli = new Client(hostname, port);
        try {
            cli.openConnection();
            SignIn signin = new SignIn();
            cli.setSignIn(signin);
            cli.setGameGUI(ggui);
            signin.cli = cli;
            signin.msgBox = mBox;
            signin.ggui = ggui;
            signin.setVisible(true);

        } catch (IOException ex) {
            ggui.dispose();
            mBox.showMessage("Error", "Can't Open Connection to " + cli.getHost());
            while (mBox.isVisible()) {
                ;
            }
            mBox.dispose();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String hostname = "localhost"; //Default
        int port = 12345; //Default
        // Set the native look and feel. This takes away the "ugly Java look".
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

        if (args.length > 0) {
            hostname = args[0];
            if (args.length > 1) {
                port = Integer.parseInt(args[1]);
            }
        }

        Main cl = new Main(hostname, port);
    }
}
