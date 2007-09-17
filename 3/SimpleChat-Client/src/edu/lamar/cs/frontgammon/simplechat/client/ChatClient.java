/*
 * ChatClient.java
 *
 * Created on September 15, 2007, 9:38 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com

package edu.lamar.cs.frontgammon.simplechat.client;

import com.lloseng.ocsf.client.*;
import java.io.*;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class ChatClient extends AbstractClient {
    //Instance variables **********************************************
    
    /**
     * The interface type variable.  It allows the implementation of
     * the display method in the client.
     */
    ChatIF clientUI;
    String loginID;
    
    
    //Constructors ****************************************************
    
    /**
     * Constructs an instance of the chat client.
     *
     * @param host The server to connect to.
     * @param port The port number to connect on.
     * @param clientUI The interface type variable.
     */
    
    public ChatClient(String host, int port, ChatIF clientUI, String loginID)
    throws IOException {
        super(host, port); //Call the superclass constructor
        this.clientUI = clientUI;
        this.loginID = loginID;
        openConnection();
        sendToServer ("#login " + loginID + "\n");
    }
    
    
    //Instance methods ************************************************
    
    /**
     * This method handles all data that comes in from the server.
     *
     * @param msg The message from the server.
     */
    public void handleMessageFromServer(Object msg) {
        clientUI.display(msg.toString());
    }
    
    /**
     * This method handles all data coming from the UI
     *
     * @param message The message from the UI.
     */
    public void handleMessageFromClientUI(String message) {
        
        // Case doesn't matter
        message = message.toLowerCase();
        
        // All messages starting with # are commands
        if (message.startsWith("#")) {
            
            /* This process of parsing commands uses nested if elses.
             * There is really no clean way of doing this, so each if statement
             * has a slightly hidden corresponding else. This allows the command
             * processing to cascade and if none of the if statements catch then 
             * we find that the command is an error and we tell the user so.
            */
            
            String command = message.substring(1);
            
            if (command.equals("quit"))
                quit();
            else
            
            if (command.equals("logoff")) {
                try {
                    closeConnection();
                } catch (IOException ex) {
                    clientUI.display("Could not logoff of server: " + ex);
                }
            } else
            
            if (command.startsWith("sethost")) {
                String[] commandParts = command.split(" ");
                String host = commandParts[1];
                
                if (this.isConnected() == false)
                {
                    clientUI.display("Host set to " + host);
                    setHost(host);
                } else {
                    clientUI.display("Could not set host. You must log off of the current server first.");
                }
            } else
            
            if (command.startsWith("setport")) {
                String[] commandParts = command.split(" ");
                if (this.isConnected() == false)
                {
                    try {
                        int port = Integer.parseInt(commandParts[1]);
                        clientUI.display("Port set to " + port);
                        setPort(port);
                    } catch (NumberFormatException ex) {
                        clientUI.display("Setting port failed: Port was not an integer.");
                    }
                } else {
                    clientUI.display("Could not set port number. You must log off of the current server first.");
                }
            } else
            
            if (command.equals("login"))
            {
                if (isConnected() == false)
                {
                    try {
                        openConnection();
                    } catch (IOException ex) {
                        clientUI.display("Could not login to server: " + ex);
                    }
                } else {
                    clientUI.display("You must disconnect from the current server before attempting to connect to another.");
                }
            } else
            
            if (command.equals("gethost"))
                clientUI.display(getHost());
            else
            
            if (command.equals("getport"))
                clientUI.display(Integer.toString(getPort()));
            else
            {
                clientUI.display("Invalid command.");
            }
            
        } else {
            try {
                sendToServer(message);
            } catch(IOException e) {
                clientUI.display("Could not send message to server.  Terminating client.");
                quit();
            }
        }
    }
    
    // E49 - Responding to server shutdown
    protected void connectionException(Exception exception) {
        System.out.println("The server has shut down. The client will now quit.");
        quit();
    }
    
    /**
     * This method terminates the client.
     */
    public void quit() {
        try {
            closeConnection();
        } catch(IOException e) {}
        System.exit(0);
    }
}
//End of ChatClient class
