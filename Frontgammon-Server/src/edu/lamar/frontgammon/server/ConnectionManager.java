/*
 * ConnectionManager.java
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
 * Created on October 30, 2007, 12:04 PM
 *
 * Copyright notice: http://www.gnu.org/licenses/gpl-3.0.txt
 */

package edu.lamar.frontgammon.server;

import com.lloseng.ocsf.server.AbstractServer;
import com.lloseng.ocsf.server.ConnectionToClient;
import edu.lamar.frontgammon.protocol.Message;

/**
 *
 * @author dan
 */
public class ConnectionManager extends AbstractServer
{
    private MessageProcessor cp;
    
    /** Creates a new instance of ConnectionManager */
    public ConnectionManager (MessageProcessor cProc, int port)
    {
        super(port);
        cp = cProc;
    }

    protected void handleMessageFromClient (Object msg, ConnectionToClient client)
    {
        cp.handleMessageFromClient (client, (Message)msg);
    }
    
}
