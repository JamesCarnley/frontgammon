/*
 * AccountManagerTest.java
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
 * Created on November 2, 2007, 1:08 PM
 *
 * Copyright notice: http://www.gnu.org/licenses/gpl-3.0.txt
 */

package edu.lamar.frontgammon.server.test;

import edu.lamar.frontgammon.server.AccountManager;

/**
 *
 * @author dan
 */
public class AccountManagerTest
{
    
    /** Creates a new instance of AccountManagerTest */
    public AccountManagerTest ()
    {
    }
    
    public static void main (String[] args)
    {
        AccountManager am = AccountManager.getAccountManager ();
        String user = "testuser1";
        String pass = "test";
        System.out.println ("Register: " + am.registerUser (user, pass));
        System.out.println ("User Exists: " + am.userExists (user));
        System.out.println ("Login: " + am.login (user, pass));
    }
    
}
