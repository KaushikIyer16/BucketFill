package com.bfa.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mahesh
 */
public interface DBConnection {

    String CONNECTIONSTRING = "jdbc:mysql://localhost:8889/bucketfill", USERNAME = "root", PASSWORD = "root";

    public static Connection createConnection() {
        Connection myConnection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            myConnection = DriverManager.getConnection(CONNECTIONSTRING, USERNAME, PASSWORD);
            return myConnection;
        } catch (Exception e) {
            System.out.println("Error in connection class " + e);
            return null;
        }
    }
}
