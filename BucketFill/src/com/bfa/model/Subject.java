package com.bfa.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mahesh
 */
public class Subject implements DBConnection {

    private String name = null, courseCode = null;
    private int theory = 0;
    private int lab, tutorial, elective, selfStudy;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public boolean hasLab() {
        if(lab > 0)
            return true;
        else
            return false;
    }

    public int getLab() {
        return lab;
    }

    public int getTutorial() {
        return tutorial;
    }

    public int getElective() {
        return elective;
    }

    public int getSelfStudy() {
        return selfStudy;
    }

    public void setLab(int lab) {
        this.lab = lab;
    }

    public boolean hasTutorial() {
        if(tutorial > 0)
            return true;
        else
            return false;
    }

    public void setTutorial(int tutorial) {
        this.tutorial = tutorial;
    }

    public boolean hasElective() {
        if(elective > 0)
            return true;
        else
            return false;
    }

    public void setElective(int elective) {
        this.elective = elective;
    }

    public boolean hasSelfStudy() {
        if(selfStudy > 0)
            return true;
        else
            return false;
    }

    public void setSelfStudy(int selfStudy) {
        this.selfStudy = selfStudy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTheory() {
        return theory;
    }

    public void setTheory(int theory) {
        this.theory = theory;
    }

    public static void copyItemsToList(ResultSet resultSet, ArrayList<Subject> subjectList) {
        try {
            for (int i = 0; resultSet.next(); i++) {
                Subject temp = new Subject();

                temp.courseCode = resultSet.getString(1);
                temp.name = resultSet.getString(2);
                temp.theory = resultSet.getInt(3);
                temp.lab = resultSet.getInt(4);
                temp.tutorial = resultSet.getInt(5);
                temp.elective = resultSet.getInt(6);
                temp.selfStudy = resultSet.getInt(7);

                subjectList.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Subject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void copyItemsToList(ResultSet resultSet, Subject temp) {
        try {
            temp.courseCode = resultSet.getString(1);
            temp.name = resultSet.getString(2);
            temp.theory = resultSet.getInt(3);
            temp.lab = resultSet.getInt(4);
            temp.tutorial = resultSet.getInt(5);
            temp.elective = resultSet.getInt(6);
            temp.selfStudy = resultSet.getInt(7);

        } catch (SQLException ex) {
            Logger.getLogger(Subject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<Subject> getAllDetails() {
        try {

            Connection myConnection = DBConnection.createConnection();
            Statement myStatement = myConnection.createStatement();
            String simpleQuery = "select * from Subject";
            ResultSet rs = myStatement.executeQuery(simpleQuery);
            ArrayList<Subject> subjectList = new ArrayList<>();

            copyItemsToList(rs, subjectList);

            myConnection.close();
            return subjectList;
        } catch (Exception e) {
            System.out.println(e + " Occured in get all details");
            return null;
        }
    }

    public static ArrayList<Subject> getDetailsByCourseCode(String courseCode) {
        try {

            Connection myConnection = DBConnection.createConnection();

            PreparedStatement myPreStatement = myConnection.prepareStatement("SELECT * FROM Subject WHERE CourseCode = ?");
            myPreStatement.setString(1, courseCode);

            ResultSet rs = myPreStatement.executeQuery();
            ArrayList<Subject> subjectList = new ArrayList<>();

            copyItemsToList(rs, subjectList);

            myConnection.close();
            return subjectList;
        } catch (Exception e) {
            System.out.println(e + " Occured in get details by id");
            return null;
        }
    }

    public static ArrayList<Subject> getSubjectByYear(int year) {

        Connection myConnection = DBConnection.createConnection();
        ResultSet rs = null;
        ArrayList<Subject> subjectList = new ArrayList<>();

        try {

            PreparedStatement myPreStatement = myConnection.prepareStatement("SELECT * FROM Subject");
            rs = myPreStatement.executeQuery();
            switch (year) {
                case 2:
                    for (int i = 0; rs.next(); i++) {
                        if (Integer.parseInt(Character.toString(rs.getString(1).charAt(4))) == 3 || Integer.parseInt(Character.toString(rs.getString(1).charAt(4))) == 4) {
                            Subject temp = new Subject();
                            copyItemsToList(rs, temp);
                            subjectList.add(temp);
                        }

                    }
                    break;
                case 3:
                    for (int i = 0; rs.next(); i++) {
                        if (Integer.parseInt(Character.toString(rs.getString(1).charAt(4))) == 5 || Integer.parseInt(Character.toString(rs.getString(1).charAt(4))) == 6) {
                            Subject temp = new Subject();
                            copyItemsToList(rs, temp);
                            subjectList.add(temp);
                        }

                    }
                    break;
                case 4:
                    for (int i = 0; rs.next(); i++) {
                        if (Integer.parseInt(Character.toString(rs.getString(1).charAt(4))) == 7 || Integer.parseInt(Character.toString(rs.getString(1).charAt(4))) == 8) {
                            Subject temp = new Subject();
                            copyItemsToList(rs, temp);
                            subjectList.add(temp);
                        }

                    }
                    break;
            }
            myConnection.close();
            
            return subjectList;
        } catch (Exception e) {
            System.out.println("Error in getSubjectDetailsByYear "+e);
            return null;
        }
    }
    
    public static int[] getElectiveLtps(int year){
         Connection myConnection = DBConnection.createConnection();
        ResultSet rs = null;
        ArrayList<Subject> subjectList = new ArrayList<>();
        int[] maxLtps = new int[4];
        try {

            PreparedStatement myPreStatement = myConnection.prepareStatement("SELECT * FROM Subject WHERE Elective = 1");
            rs = myPreStatement.executeQuery();
            switch (year) {
                case 2:
                    for (int i = 0; rs.next(); i++) {
                        if (Integer.parseInt(Character.toString(rs.getString(1).charAt(4))) == 3 || Integer.parseInt(Character.toString(rs.getString(1).charAt(4))) == 4) {
                            Subject temp = new Subject();
                            copyItemsToList(rs, temp);
                            subjectList.add(temp);
                        }

                    }
                    break;
                case 3:
                    for (int i = 0; rs.next(); i++) {
                        if (Integer.parseInt(Character.toString(rs.getString(1).charAt(4))) == 5 || Integer.parseInt(Character.toString(rs.getString(1).charAt(4))) == 6) {
                            Subject temp = new Subject();
                            copyItemsToList(rs, temp);
                            subjectList.add(temp);
                        }

                    }
                    break;
                case 4:
                    for (int i = 0; rs.next(); i++) {
                        if (Integer.parseInt(Character.toString(rs.getString(1).charAt(4))) == 7 || Integer.parseInt(Character.toString(rs.getString(1).charAt(4))) == 8) {
                            Subject temp = new Subject();
                            copyItemsToList(rs, temp);
                            subjectList.add(temp);
                        }

                    }
                    break;
            }
            myConnection.close();
            for(Subject s : subjectList){
                if(s.theory > maxLtps[0])
                    maxLtps[0] = s.theory;
                if(s.tutorial > maxLtps[1])
                    maxLtps[1] = s.tutorial;
                if(s.lab > maxLtps[2])
                    maxLtps[2] = s.lab;
                if(s.selfStudy > maxLtps[3])
                    maxLtps[3] = s.selfStudy;
            }
            return maxLtps;
        } catch (Exception e) {
            System.out.println("Error in getSubjectDetailsByYear "+e);
            return null;
        }
    }
    
    public static void insertDetails(String[] courseCode, String[] name, int[] l, int[] t, int[] p, int[] s, String[] elective){
        Connection myConnection = DBConnection.createConnection();
        
        PreparedStatement myPreStatement;
        try {
            myPreStatement = myConnection.prepareStatement("INSERT INTO Subject VALUES(?,?,?,?,?,?,?)");
            for(int i = 0;i < name.length; i++){
                
                myPreStatement.setString(1, courseCode[i]);
                myPreStatement.setString(2, name[i]);
                myPreStatement.setInt(3, l[i]);
                myPreStatement.setInt(4, t[i]);
                myPreStatement.setInt(5, p[i]);
                myPreStatement.setInt(6, s[i]);
                if(elective[i].equalsIgnoreCase("YES"))
                    myPreStatement.setBoolean(7, true);
                else
                    myPreStatement.setBoolean(7, false);
                myPreStatement.execute();
                if(s[i] == 1){
                    SelfStudy.insertDetails(courseCode[i], (l[i]+(t[i]*2)+p[i]+s[i]), l[i], p[i]);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Subject.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
