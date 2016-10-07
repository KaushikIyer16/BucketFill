package com.bfa.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mahesh
 */
public class DBConnection {
    public static Connection createConnection()
    {
        Connection myConnection;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            myConnection = DriverManager.getConnection("jdbc:mysql://localhost:8889/bucketfill", "root", "root");
            return myConnection;
        } 
        catch(Exception e)
        {
            System.out.println("Error in connection class: "+e);
            return null;
        }
    }
}
