/*
 * AccountManager.java
 *
 * Created on October 29, 2007, 11:43 AM
 *
 * Copyright notice: http://www.gnu.org/licenses/gpl-3.0.txt
 */

package edu.lamar.frontgammon.server;

import java.util.TreeMap;
import edu.lamar.frontgammon.server.Player;
/**
 *
 * @author dan
 */
public class AccountManager
{
    private static AccountManager am = null;
    private UserPassDatabase upDB;
    private TreeMap <String, String> upMap;
    
    /** Creates a new instance of AccountManager */
    private AccountManager ()
    {
        FlatFile ff;
        
        try
        {
            ff = new FlatFile ();
            init (ff);
        }
        catch (Exception e)
        {
            e.printStackTrace ();
        }
    }
    
    private AccountManager (UserPassDatabase upDB)
    {
        init (upDB);
    }
    
    private void init (UserPassDatabase upDB)
    {
        this.upDB = upDB;
        upMap = null;
    }
    
    public synchronized static AccountManager getAccountManager ()
    {
        if (am == null) 
            am = new AccountManager ();
        
        return am;
    }
    
    public synchronized static AccountManager getAccountManager (UserPassDatabase upDB)
    {
        if (am == null) 
            am = new AccountManager (upDB);
        
        return am;
    }
    
    public synchronized Player login (String user, String givenPass)
    {
        Player result = null;
        String storedPass;
        
        storedPass = getUpMap ().get (user);
        
        if (storedPass != null && storedPass.equals (givenPass))
        {
            result = new Player (user);
        }
        
        return result;
    }
    
    public synchronized boolean userExists (String givenUser)
    {
        return getUpMap().containsKey (givenUser);
    }
    
    public synchronized boolean registerUser (String user, String pass)
    {
        if (userExists (user))
            return false;
        
        try
        {
            upDB.addUserPass (user,pass);
        }
        catch (Exception ex)
        {
            ex.printStackTrace ();
            return false;
        }
        finally
        {
            invalidateUserList ();
        }
        
        return true;
    }
    
    private synchronized TreeMap<String, String> getUpMap ()
    {
        if (upMap == null)
            upMap = upDB.getUserPassMap ();
        
        return upMap;
    }
    
    private synchronized void invalidateUserList ()
    {
        upMap = null;
    }
}
