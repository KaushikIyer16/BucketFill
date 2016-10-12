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
public class Subject implements DBConnection{
    
    private String name = null, courseCode = null;
    private int theory = 0;
    private boolean lab, tutorial, elective, selfStudy;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public boolean hasLab() {
        return lab;
    }

    public void setLab(boolean lab) {
        this.lab = lab;
    }

    public boolean hasTutorial() {
        return tutorial;
    }

    public void setTutorial(boolean tutorial) {
        this.tutorial = tutorial;
    }

    public boolean hasElective() {
        return elective;
    }

    public void setElective(boolean elective) {
        this.elective = elective;
    }

    public boolean hasSelfStudy() {
        return selfStudy;
    }

    public void setSelfStudy(boolean selfStudy) {
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
    
    public static ArrayList<Subject> getAllDetails(){
        try{
            
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection myConnection=DriverManager.getConnection("jdbc:mysql://localhost:8889/bucketfill","root","root");
//            
            Connection myConnection = DBConnection.createConnection();
            Statement myStatement = myConnection.createStatement();
            String simpleQuery = "select * from Subject";
            ResultSet rs = myStatement.executeQuery(simpleQuery);            
            ArrayList<Subject> subjectList = new ArrayList<>(); 
            
            for(int i=0;rs.next();i++)
            {
                Subject temp = new Subject();
                
                temp.courseCode = rs.getString(1);
                temp.name = rs.getString(2);
                temp.theory = rs.getInt(3);
                temp.lab = rs.getBoolean(4);
                temp.tutorial = rs.getBoolean(5);
                temp.elective = rs.getBoolean(6);
                temp.selfStudy = rs.getBoolean(7);
                
                subjectList.add(temp);
            }
            myConnection.close();
            return subjectList;
        }
        catch(Exception e){
            System.out.println(e+" Occured in get all details");
            return null;
        }
    }
    public static ArrayList<Subject> getDetailsByCourseCode(String courseCode){
        try{
            
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection myConnection=DriverManager.getConnection("jdbc:mysql://localhost:8889/bucketfill","root","root");
//            
            Connection myConnection = DBConnection.createConnection();
            
            PreparedStatement myPreStatement = myConnection.prepareStatement("SELECT * FROM Subject WHERE CourseCode = ?");
            myPreStatement.setString(1, courseCode);
            
            ResultSet rs = myPreStatement.executeQuery();
            ArrayList<Subject> subjectList = new ArrayList<>(); 
            
            for(int i=0;rs.next();i++)
            {
                Subject temp = new Subject();
                
                temp.courseCode = rs.getString(1);
                temp.name = rs.getString(2);
                temp.theory = rs.getInt(3);
                temp.lab = rs.getBoolean(4);
                temp.tutorial = rs.getBoolean(5);
                temp.elective = rs.getBoolean(6);
                temp.selfStudy = rs.getBoolean(7);
                
                subjectList.add(temp);
            }
            myConnection.close();
            return subjectList;
        }
        catch(Exception e){
            System.out.println(e+" Occured in get details by id");
            return null;
        }
    }
    
    public static ArrayList<Subject> getSubjectByYear(int year){
        
        Connection myConnection = DBConnection.createConnection();
        ResultSet rs = null;
        
        try{
            
            PreparedStatement myPreStatement = myConnection.prepareStatement("SELECT * FROM Subject WHERE CourseCode LIKE ? OR ?");
            switch(year)
            {
                case 2: myPreStatement.setString(1, "%3%");
                        myPreStatement.setString(2, "%4%");
                        rs = myPreStatement.executeQuery();
                        break;
                        
                case 3: myPreStatement.setString(1, "%5%");
                        myPreStatement.setString(2, "%6%");
                        rs = myPreStatement.executeQuery();
                        break;
                        
                case 4: myPreStatement.setString(1, "%7%");
                        myPreStatement.setString(2, "%8%");
                        rs = myPreStatement.executeQuery();
                        break;
                        
                default: myPreStatement.setString(1, "%");
                         myPreStatement.setString(2, "%");
                         rs = myPreStatement.executeQuery();
            }
            ArrayList<Subject> subjectList = new ArrayList<>(); 
            
            for(int i=0;rs.next();i++)
            {
                Subject temp = new Subject();
                
                temp.courseCode = rs.getString(1);
                temp.name = rs.getString(2);
                temp.theory = rs.getInt(3);
                temp.lab = rs.getBoolean(4);
                temp.tutorial = rs.getBoolean(5);
                temp.elective = rs.getBoolean(6);
                temp.selfStudy = rs.getBoolean(7);
                
                subjectList.add(temp);
            }
            myConnection.close();
            return subjectList;
        }
        catch(Exception e)
        {
            System.out.println("Error in getSubjectDetailsByYear");
            return null;
        }
    }
    @Override
    public String toString() {
       return this.courseCode+" "+this.name+" "+this.theory+" "+this.elective;
    }
    
}
