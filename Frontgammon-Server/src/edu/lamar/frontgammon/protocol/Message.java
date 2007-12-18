/*
 * Message.java
 *
 * Created on November 1, 2007, 12:10 PM
 *
 * Copyright notice: http://www.gnu.org/licenses/gpl-3.0.txt
 */

package edu.lamar.frontgammon.protocol;

/**
 *
 * @author dan
 */
import java.io.*;
import edu.lamar.frontgammon.game.*;
public class Message implements Serializable
{
    
    public Command cmd;
    public Object []params;    
    /** Creates a new instance of Message */
    public Message (Command cmd, Object []params)
    {
        this.cmd = cmd;
        this.params = params;
    }   
}
