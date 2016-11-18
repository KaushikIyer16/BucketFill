package com.bfa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mahesh
 */
public class Lab implements DBConnection{
    private String name = null;
    private String dayOfWeek = null;
    private int totalSlots = 0, freeSlots = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    public int getFreeSlots() {
        return freeSlots;
    }

    public void setFreeSlots(int freeSlots) {
        this.freeSlots = freeSlots;
    }
    
    
    public static void insertDetails(ArrayList<Lab> labName){
        String[] days = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"};
        Connection myConnection = DBConnection.createConnection();
        
        PreparedStatement myPreStatement;
        try {
            myPreStatement = myConnection.prepareStatement("INSERT INTO Lab VALUES(?,?,?,?)");
            for(Lab l : labName){
                myPreStatement.setString(1, l.getName());
                for(int i=0; i<7; i++){
                    myPreStatement.setString(2, days[i]);
                    myPreStatement.setInt(3, 3);
                    myPreStatement.setInt(4, 3);
                    myPreStatement.execute();
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Subject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
