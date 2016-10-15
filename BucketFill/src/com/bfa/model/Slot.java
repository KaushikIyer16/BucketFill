/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author kaushiknsiyer
 */
public class Slot {

    private String labName, className, subject, dayOfWeek;
    private Time startTime;

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    /**
     *
     * @param labName
     * @return labName, className, subject, dayOfWeek, startTime
     */
    public static ArrayList<Slot> getSlotsByLabName(String labName) {

        Connection myConnection = DBConnection.createConnection();
        ResultSet rs = null;

        try {

            PreparedStatement myPreStatement = myConnection.prepareStatement("SELECT * FROM Slot WHERE Name = ?");
            myPreStatement.setString(1, labName);

            rs = myPreStatement.executeQuery();
            ArrayList<Slot> slotList = new ArrayList<>();
            for (int i = 0; rs.next(); i++) {
                Slot temp = new Slot();

                temp.labName = rs.getString(1);
                temp.className = rs.getString(2);
                temp.subject = rs.getString(3);
                temp.startTime = rs.getTime(4);
                temp.dayOfWeek = rs.getString(5);

                slotList.add(temp);
            }
            myConnection.close();
            return slotList;
        } catch (Exception e) {
            System.out.println("Error in section by year " + e);
            return null;
        }
    }

    /**
     *
     * @param labName
     * @param dayOfWeek
     * @return labName, className, subject, dayOfWeek, startTime
     */
    public static ArrayList<Slot> getSlotsByLabNameAndDayOfWeek(String labName, String dayOfWeek) {
        ArrayList<Slot> slotList = new ArrayList<>();

        Connection mConnection = DBConnection.createConnection();
        ResultSet rs = null;

        try {
            PreparedStatement mPreparedStatement = mConnection.prepareStatement("SELECT * FROM slot WHERE Name = ? AND DayOfWeek = ?");
            mPreparedStatement.setString(1, labName);
            mPreparedStatement.setString(2, dayOfWeek);

            rs = mPreparedStatement.executeQuery();

            for (int i = 0; rs.next(); i++) {
                Slot temp = new Slot();

                temp.labName = rs.getString(1);
                temp.className = rs.getString(2);
                temp.subject = rs.getString(3);
                temp.startTime = rs.getTime(4);
                temp.dayOfWeek = rs.getString(5);

                slotList.add(temp);
            }

            mConnection.close();
            return slotList;

        } catch (Exception e) {
            System.out.println("Error in getting ccp lab slots by the day of the week ");
            return null;
        }
    }

    /**
     *
     * @return labName, className, subject, dayOfWeek, startTime
     */
    public static ArrayList<Slot> getAllSlotDetails() {

        Connection myConnection = DBConnection.createConnection();
        ResultSet rs = null;

        try {

            PreparedStatement myPreStatement = myConnection.prepareStatement("SELECT * FROM Slot");

            rs = myPreStatement.executeQuery();
            ArrayList<Slot> slotList = new ArrayList<>();
            for (int i = 0; rs.next(); i++) {
                Slot temp = new Slot();

                temp.labName = rs.getString(1);
                temp.className = rs.getString(2);
                temp.subject = rs.getString(3);
                temp.startTime = rs.getTime(4);
                temp.dayOfWeek = rs.getString(5);

                slotList.add(temp);
            }
            myConnection.close();
            return slotList;
        } catch (Exception e) {
            System.out.println("Error in section by year " + e);
            return null;
        }
    }
}
