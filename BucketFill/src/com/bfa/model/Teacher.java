package com.bfa.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author mahesh
 */
public class Teacher implements DBConnection {

    private String name = null;
    private int id = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
    private int hours = 0;

    public static ArrayList<Teacher> getAllDetails() {
        try {

            Connection myConnection = DBConnection.createConnection();

            Statement myStatement = myConnection.createStatement();
            String simpleQuery = "select * from Teacher";
            ResultSet rs = myStatement.executeQuery(simpleQuery);
            ArrayList<Teacher> teacherList = new ArrayList<>();

            for (int i = 0; rs.next(); i++) {
                Teacher temp = new Teacher();

                temp.name = rs.getString(2);
                temp.id = rs.getInt(1);
                temp.hours = rs.getInt(3);

                teacherList.add(temp);
            }
            myConnection.close();
            return teacherList;
        } catch (Exception e) {
            System.out.println(e + " Occured in get all details");
            return null;
        }
    }

    public static ArrayList<Teacher> getDetailsById(int id) {
        try {

            Connection myConnection = DBConnection.createConnection();

            PreparedStatement myPreStatement = myConnection.prepareStatement("SELECT * FROM Teacher WHERE id = ?");
            myPreStatement.setInt(1, id);

            ResultSet rs = myPreStatement.executeQuery();
            ArrayList<Teacher> teacherList = new ArrayList<Teacher>();

            for (int i = 0; rs.next(); i++) {
                Teacher temp = new Teacher();

                temp.name = rs.getString(2);
                temp.id = rs.getInt(1);
                temp.hours = rs.getInt(3);

                teacherList.add(temp);
            }
            myConnection.close();
            return teacherList;
        } catch (Exception e) {
            System.out.println(e + " Occured in get details by id");
            return null;
        }
    }

    public static String getNameById(int id) {
        try {

            Connection myConnection = DBConnection.createConnection();

            PreparedStatement myPreStatement = myConnection.prepareStatement("SELECT Name FROM Teacher WHERE id = ?");
            myPreStatement.setInt(1, id);

            ResultSet rs = myPreStatement.executeQuery();
            String teacherName = null;

            while (rs.next()) {
                teacherName = rs.getString(1);
            }
            myConnection.close();
            return teacherName;
        } catch (Exception e) {
            System.out.println(e + " Occured in get details by id");
            return null;
        }
    }
    
    public static void insertDetails(String name, int ID, int hours, String[] subjects) {
        try {

            Connection myConnection = DBConnection.createConnection();
            PreparedStatement myPreStatement;
            //for(int i = 0; i < name.length; i++){
                myPreStatement = myConnection.prepareStatement("INSERT INTO Teacher VALUES(?,?,?)");
                myPreStatement.setInt(1, ID);
                myPreStatement.setString(2, name);
                myPreStatement.setInt(3, hours);
                myPreStatement.execute();
            //}
            //for(int i = 0; i < name.length; i++){
                myPreStatement = myConnection.prepareStatement("INSERT INTO TeacherSubject VALUES(?,?)");
                myPreStatement.setInt(1, ID);
                for(int j=0; j < subjects.length;j++){
                    PreparedStatement tempPreStatement = myConnection.prepareStatement("SELECT CourseCode FROM Subject WHERE Name LIKE ?");
                    tempPreStatement.setString(1, subjects[j]);
                    ResultSet tempRS = tempPreStatement.executeQuery();
                    String tempCC = "";
                    while (tempRS.next()) {
                        tempCC = tempRS.getString(1);
                    }
                    myPreStatement.setString(2, tempCC);
                    myPreStatement.execute();
                }
                
            //}
            
            myConnection.close();
        } catch (Exception e) {
            System.out.println(e + " Occured in insert details");
        }
    }
    
    public static String getTeacherBySectionAndSubject(String section,Subject subject){
        try {
            Connection myConnection = DBConnection.createConnection();
            PreparedStatement myPreStatement = myConnection.prepareStatement("SELECT Name FROM Teacher WHERE id = (SELECT DISTINCT TeacherID from TeacherSection where CourseCode = ? AND Section=?)");
            myPreStatement.setString(1, subject.getCourseCode());
            myPreStatement.setString(2, section);
            ResultSet rs = myPreStatement.executeQuery();
            
            String teacherName = null;
            while(rs.next()){
                teacherName =  rs.getString(1);
            }
            
            myConnection.close();
            return teacherName;
        } catch (Exception e) {
            System.out.println("Exception has occured in getTeacherBySectionAndSubject");
        }
        return null;
    }
}
