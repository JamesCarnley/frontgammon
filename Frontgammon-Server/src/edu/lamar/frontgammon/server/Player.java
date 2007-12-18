/*
 * Player.java
 *
 * Created on October 29, 2007, 12:04 PM
 *
 * Copyright notice: http://www.gnu.org/licenses/gpl-3.0.txt
 */

package edu.lamar.frontgammon.server;

/**
 *
 * @author dan
 */
public class Player implements Comparable
{
    private String name;
    private int id;
    
    /** Creates a new instance of Player */
    public Player (String name)
    {
        this.name = name;
        this.setId(id);
    }

    public int compareTo (Object o)
    {
        return getName().compareToIgnoreCase (o.toString ());
    }

    public String getName ()
    {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
