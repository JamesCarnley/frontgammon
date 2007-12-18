/*
 * PlayerAssociation.java
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
 * Created on November 2, 2007, 10:07 AM
 *
 * Copyright notice: http://www.gnu.org/licenses/gpl-3.0.txt
 */

package edu.lamar.frontgammon.server;

import com.lloseng.ocsf.server.ConnectionToClient;
import edu.lamar.frontgammon.game.Game;
import java.util.TreeMap;

/**
 *
 * @author dan
 */
public class PlayerAssociation
{
    TreeMap <ConnectionToClient, Player> ctcToP;
    TreeMap <Player, ConnectionToClient> playerToCtc;
    TreeMap <Player, Game> playerToG;
    
    /** Creates a new instance of PlayerAssociation */
    public PlayerAssociation ()
    {
        ctcToP = new TreeMap <ConnectionToClient, Player> ();
        playerToCtc = new TreeMap <Player, ConnectionToClient> ();
        playerToG = new TreeMap <Player, Game> ();
    }
    
    public Player getPlayer (ConnectionToClient ctc)
    {
        Player p = ctcToP.get (ctc);
        
        return p;
    }
    
    public ConnectionToClient getCtc (Player p)
    {
        ConnectionToClient ctc;
        
        ctc = playerToCtc.get (p);
        
        return ctc;
    }
    
    public void addMapping (ConnectionToClient ctc, Player p, Game g)
    {
        ctcToP.put (ctc,p);
        playerToCtc.put (p, ctc);
        playerToG.put (p, g);
    }
    
    public Game getGame (Player p)
    {
        Game g = playerToG.get (p);
        
        return g;
    }
    
    public Game getGame (ConnectionToClient ctc)
    {
        Player p = getPlayer (ctc);
        Game g = getGame (p);
        
        return g;
    }
}
