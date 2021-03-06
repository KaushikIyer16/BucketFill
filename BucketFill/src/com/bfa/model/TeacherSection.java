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
public class TeacherSection implements DBConnection {

    private String courseCode = null, section = null;
    private int teacherID = 0;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }
    public static void insertDetails(int tID, String sec, String subjectName){
        try {
            String courseCode = "";
            Connection myConnection = DBConnection.createConnection();

            PreparedStatement myPreStatement = myConnection.prepareStatement("SELECT CourseCode FROM Subject WHERE Name LIKE ?");
            myPreStatement.setString(1, subjectName); 
            ResultSet rs = myPreStatement.executeQuery();
            
            for (int i = 0; rs.next(); i++) {
                courseCode = rs.getString(1);
            }
            
            myPreStatement = myConnection.prepareStatement("INSERT INTO TeacherSection VALUES (?,?,?)");
            myPreStatement.setInt(1, tID);
            myPreStatement.setString(2, courseCode);
            myPreStatement.setString(3, sec);
            myPreStatement.execute();
            
            myConnection.close();
        } catch (Exception e) {
            System.out.println(e + " Occured in get all details");
        }
    }
    
}
