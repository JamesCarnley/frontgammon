/*
 * FlatFile.java
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
 * Created on October 29, 2007, 11:46 AM
 *
 * Copyright notice: http://www.gnu.org/licenses/gpl-3.0.txt
 */

package edu.lamar.frontgammon.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author dan
 */
public class FlatFile implements UserPassDatabase
{
    private File f;
    
    /** Creates a new instance of FlatFile */
    public FlatFile () throws FileNotFoundException
    {
        f = new File ("passwd.txt");
        try {
            f.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public TreeMap<String, String> getUserPassMap ()
    {
        Scanner input = null;
        String line;
        String split[];
        TreeMap <String, String> userPass = new TreeMap <String, String> ();
        
        try
        {
            input = new Scanner (f);
            
            while (input.hasNextLine ())
            {
                line = input.nextLine ();
                split = line.split (" ");
                userPass.put (split[0], split[1]);
            }
        }
        catch (FileNotFoundException ex)
        {
            return null;
        }
        finally
        {
            if (input != null)
                input.close ();
        }
        
        return userPass;
    }
    
    public void addUserPass (String user, String pass) throws Exception
    {
        FileOutputStream foStream;
        PrintStream pStream;
        
        foStream = new FileOutputStream (f, true);
        pStream = new PrintStream (foStream);
        
        pStream.println (user + " " + pass);
        
        pStream.close ();
    }
    
}
