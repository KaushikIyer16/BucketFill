/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mahesh
 */
public class SelfStudy {
    String courseCode;
    int credits, l, p;
    public static void insertDetails(String courseCode, int credits, int learning, int practical){
        Connection myConnection = DBConnection.createConnection();
        
        PreparedStatement myPreStatement;
        try {
            myPreStatement = myConnection.prepareStatement("INSERT INTO SelfStudy VALUES(?,?,?,?)");                
                myPreStatement.setString(1, courseCode);
                myPreStatement.setInt(2, credits);
                myPreStatement.setInt(3, learning);
                myPreStatement.setInt(4, practical);
                myPreStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Subject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
