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
    public static ArrayList<String> getTeacherBySubject(String subject) {
        try {
            String subjectName = "";
            Connection myConnection = DBConnection.createConnection();

            PreparedStatement myPreStatement = myConnection.prepareStatement("SELECT CourseCode FROM Subject WHERE Name LIKE ?");
            myPreStatement.setString(1, subject); 
            ResultSet rs = myPreStatement.executeQuery();
            for (int i = 0; rs.next(); i++) {
                subjectName = rs.getString(1);
            }
            myPreStatement = myConnection.prepareStatement("SELECT Name FROM Teacher WHERE ID IN(SELECT TeacherID FROM TeacherSubject WHERE CourseCode LIKE ?)");
            myPreStatement.setString(1, subjectName);
            rs = myPreStatement.executeQuery();
            
            ArrayList<String> teacherList = new ArrayList<>();
            for (int i = 0; rs.next(); i++) {
                teacherList.add(rs.getString(1));
            }
                
            myConnection.close();
            return teacherList;
        } catch (Exception e) {
            System.out.println(e + " Occured in get all details");
            return null;
        }
    }
    public static ArrayList<String> getCcpTeachers() {
        try {
            String subjectName = "";
            Connection myConnection = DBConnection.createConnection();

            PreparedStatement myPreStatement = myConnection.prepareStatement("SELECT Name FROM Teacher WHERE ID IN (SELECT TeacherID FROM TeacherSubject WHERE CourseCode LIKE ?)");
            myPreStatement.setString(1, "14CS1ICCCP"); 
            ResultSet rs = myPreStatement.executeQuery();
            
            ArrayList<String> teacherList = new ArrayList<>();
            for (int i = 0; rs.next(); i++) {
                teacherList.add(rs.getString(1));
            }
                
            myConnection.close();
            return teacherList;
        } catch (Exception e) {
            System.out.println(e + " Occured in get all details");
            return null;
        }
    }
    
    public static void insertDetails(int teacherID, String subjectName) {
        try {
            String courseCode = "";
            Connection myConnection = DBConnection.createConnection();

            PreparedStatement myPreStatement = myConnection.prepareStatement("SELECT CourseCode FROM Subject WHERE Name LIKE ?");
            myPreStatement.setString(1, subjectName); 
            ResultSet rs = myPreStatement.executeQuery();
            
            for (int i = 0; rs.next(); i++) {
                courseCode = rs.getString(1);
            }
            
            myPreStatement = myConnection.prepareStatement("INSERT INTO TeacherSubject VALUES (?,?)");
            myPreStatement.setInt(1, teacherID);
            myPreStatement.setString(2, courseCode);
            myPreStatement.execute();
            
            myConnection.close();
        } catch (Exception e) {
            System.out.println(e + " Occured in get all details");
        }
    }
    
    public static ArrayList<Integer> getElectiveTeacherIdFromYear(int year){
        try{
            Connection myConnection = DBConnection.createConnection();

            PreparedStatement myPreStatement = myConnection.prepareStatement("SELECT TeacherID FROM TeacherSubject where CourseCode in (SELECT CourseCode FROM Subject WHERE Elective>0)"); 
            ResultSet rs = myPreStatement.executeQuery();
            ArrayList<Integer> ElectiveTeacherIds = new ArrayList<>();
            while(rs.next()){
                ElectiveTeacherIds.add(rs.getInt(1));
            }
            return ElectiveTeacherIds;
        }catch(Exception e){
            System.out.println("Error occured in getElectiveTeacherIdFromYear");
            return null;
        }
    }
}
