/*
 * ServerConsole.java
 *
 * Created on September 15, 2007, 2:28 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.lamar.cs.frontgammon.simplechat.server;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Console for the Server.
 *
 * @author Daniel Schultze
 */
public class ServerConsole implements Runnable
{
    private boolean readyToQuit;
    private EchoServer server;
    
    // #quit
    private static final Pattern QUIT = Pattern.compile ("^\\#quit\\s*.*$");
    // #stop
    private static final Pattern STOP = Pattern.compile ("^\\#stop\\s*.*$");
    // #close
    private static final Pattern CLOSE = Pattern.compile ("^\\#close\\s*.*$");
    // #setport
    private static final Pattern SETPORT = Pattern.compile ("^\\#setport (\\d+)\\s*.*$");
    // #start
    private static final Pattern START = Pattern.compile ("^\\#start\\s*.*$");
    // #getport
    private static final Pattern GETPORT = Pattern.compile ("^\\#getport\\s*.*$");
    
    
    /** 
     * Creates a new instance of ServerConsole
     *
     * @param e the EchoServer being used.
     */
    public ServerConsole (EchoServer e)
    {
        server = e;
        readyToQuit = false;
    }
    
    /**
     * The main Thread loop for waiting for user input.
     */
    public void run ()
    {
        Scanner input = new Scanner (System.in);
        String line;
        
        while (!readyToQuit)
        {
            try
            {
                line = waitForInput (input);
                userInput (line);
            }
            catch (Exception e)
            {
                e.printStackTrace ();
                System.out.println (e);
            }
        }
    }
    
    /**
     * Method that waits for console input.
     */
    public String waitForInput (Scanner input)
    {
        return input.nextLine ();
    }
    
    /**
     * This method processes the user input.
     * 
     * @param input the user's input.
     */
    public void userInput (String input)
    {
        if (input.startsWith ("#"))
        {
            Matcher paramMatcher;
            
            if (QUIT.matcher (input).matches ())
            {
                commandQuit ();
            }
            else if (STOP.matcher (input).matches ())
            {
                commandStop ();
            }
            else if (CLOSE.matcher (input).matches ())
            {
                commandClose ();
            }
            else if ((paramMatcher = SETPORT.matcher (input)).matches ())
            {
                commandSetPort (paramMatcher.group (1));
            }
            else if (START.matcher (input).matches ())
            {
                commandStart ();
            }
            else if (GETPORT.matcher (input).matches ())
            {
                commandGetPort ();
            }
            else //No command was matched
            {
                userOutputToUI ("Command not found.\n");
            }
        }
        else //send a line of text
        {
            input += "\n";
            userOutput (input);
        }
    }
    
    /**
     * Sends string to user's UI.
     *
     * @param output string to send
     */
    public void userOutputToUI (String output)
    {
        System.out.print (output);
    }
    
    /**
     * Sends string to clients.
     *
     * @param output string to send
     */
    public void userOutputToClients (String output)
    {
        server.sendToAllClients (output);
    }
    
    /**
     * Sends string to user's UI and clients.
     *
     * @param output string to send
     */
    public void userOutput (String output)
    {
        output = "SERVER MSG> " + output;
        userOutputToUI (output);
        userOutputToClients (output);
    }
    
    private void commandStop ()
    {
        server.stopListening ();
    }
    
    private void commandStart ()
    {
        try
        {
            server.listen ();
        } catch (IOException ex)
        {
            ex.printStackTrace();
            userOutputToUI ("There was an error while starting to listen again: " + ex.toString () + "\n");
        }
    }
    
    /**
     * @param input port number to parse in string form.
     */
    private void commandSetPort (String input)
    {
        if (server.isListening ())
        {
            userOutputToUI ("Server is not stopped. The listening port cannot be changed.\n");
        }
        else
        {
            int port;
            
            try
            {
                port = Integer.parseInt (input);
                
                if (port < 0 || port > 65535)
                    throw new NumberFormatException ();
                
                server.setPort (port);
            }
            catch (NumberFormatException e)
            {
                userOutputToUI ("Bad number was specified, try 0-65535");
            }
        }
    }
    
    private void commandClose ()
    {
        try
        {
            server.close ();
        }
        catch (IOException ex)
        {
            //nothing is needed to be done.
        }
    }
    
    private void commandQuit ()
    {
        userOutput ("The server is quitting.\n");
        commandClose ();
        System.exit (0);
    }
    
    private void commandGetPort ()
    {
        int port;
        
        port = server.getPort ();
        
        userOutputToUI ("The current listening port is: " + port + ".\n");
    }
}
