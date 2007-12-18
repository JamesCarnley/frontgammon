/*
 * CommandProcessor.java
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
 * Created on October 30, 2007, 12:06 PM
 *
 * Copyright notice: http://www.gnu.org/licenses/gpl-3.0.txt
 */

package edu.lamar.frontgammon.server;

import com.lloseng.ocsf.server.ConnectionToClient;
import edu.lamar.frontgammon.protocol.Message;

/**
 *
 * @author dan
 */
public class MessageProcessor
{
    private Server s;
    
    /** Creates a new instance of CommandProcessor */
    public MessageProcessor (Server s)
    {
        this.s = s;
    }
    
    public void handleMessageFromClient (ConnectionToClient ctc, Message msg)
    {
        Player p;
        
        p = s.getPlayer (ctc);
        
        switch (msg.cmd)
        {
            case RollDice:
                s.rollDice (p);
                break;
                
            case DoubleCube:
                s.doubleCube (p);
                break;
                
            case MoveChecker:
            {
                int from, to, checkerID;
                checkerID = Integer.valueOf ((Integer)msg.params[0]);
                from = Integer.valueOf ((Integer)msg.params[1]);
                to = Integer.valueOf ((Integer)msg.params[2]);
                
                s.moveChecker (p, checkerID, from, to);
            }
                break;
                
            case Login:
                s.login (ctc, msg.params[0].toString (), msg.params[1].toString ());
                break;
                
            case Register:
                s.register (ctc, msg.params[0].toString (), msg.params[1].toString ());
                break;
            case Rejected:
                s.rejected(ctc,msg.params[0].toString(), msg.params[1].toString());
            case Quit:
               s.quit (ctc,msg.params[0].toString(), msg.params[1].toString());
            case Refresh:
                break;
            
            default:
                break;
        }
    }

    void rejectLogin (ConnectionToClient ctc)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    void acceptLogin (ConnectionToClient ctc)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    void rejectRegister ()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
