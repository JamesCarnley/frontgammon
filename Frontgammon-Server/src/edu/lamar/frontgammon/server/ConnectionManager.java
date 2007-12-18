/*
 * ConnectionManager.java
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
