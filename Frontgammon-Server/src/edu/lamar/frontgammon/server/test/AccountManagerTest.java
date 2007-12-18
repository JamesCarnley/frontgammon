/*
 * AccountManagerTest.java
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
