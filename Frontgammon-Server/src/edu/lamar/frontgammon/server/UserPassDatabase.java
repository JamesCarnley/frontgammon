/*
 * UserPassDatabase.java
 *
 * Created on October 29, 2007, 11:44 AM
 *
 * Copyright notice: http://www.gnu.org/licenses/gpl-3.0.txt
 *
 */

package edu.lamar.frontgammon.server;

import java.util.TreeMap;

/**
 *
 * @author Daniel Schultze
 */
public interface UserPassDatabase
{
    public TreeMap <String, String> getUserPassMap ();
    public void addUserPass (String user, String pass) throws Exception;
}
