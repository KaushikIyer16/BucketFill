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
public class TeacherSubject implements DBConnection {

    private String courseCode = null;
    private int teacherID = 0;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }
    
    public static ArrayList<TeacherSubject> getSubjectByTeacherID(int teacherID) {
        try {

            Connection myConnection = DBConnection.createConnection();

            Statement myStatement = myConnection.createStatement();
            PreparedStatement myPreStatement = myConnection.prepareStatement("SELECT s.Name FROM TeacherSubject t, Subject s WHERE t.TeacherID = ? AND s.CourseCode = t.CourseCode");
            myPreStatement.setInt(1, teacherID);
            
            ResultSet rs = myPreStatement.executeQuery();
            ArrayList<TeacherSubject> teacherSubjectList = new ArrayList<TeacherSubject>();

            for (int i = 0; rs.next(); i++) {
                TeacherSubject temp = new TeacherSubject();

                temp.courseCode = rs.getString(1);

                teacherSubjectList.add(temp);
            }
            myConnection.close();
            return teacherSubjectList;
        } catch (Exception e) {
            System.out.println(e + " Occured in get all details");
            return null;
        }
    }
}
