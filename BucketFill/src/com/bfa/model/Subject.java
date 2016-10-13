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
    public static void copyItemsToList(ResultSet resultSet, ArrayList<Subject> subjectList){
        try {
            for(int i=0;resultSet.next();i++)
            {
                Subject temp = new Subject();
                
                temp.courseCode = resultSet.getString(1);
                temp.name = resultSet.getString(2);
                temp.theory = resultSet.getInt(3);
                temp.lab = resultSet.getBoolean(4);
                temp.tutorial = resultSet.getBoolean(5);
                temp.elective = resultSet.getBoolean(6);
                temp.selfStudy = resultSet.getBoolean(7);
                
                subjectList.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Subject.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public static void copyItemsToList(ResultSet resultSet, Subject temp){
        try {                
                temp.courseCode = resultSet.getString(1);
                temp.name = resultSet.getString(2);
                temp.theory = resultSet.getInt(3);
                temp.lab = resultSet.getBoolean(4);
                temp.tutorial = resultSet.getBoolean(5);
                temp.elective = resultSet.getBoolean(6);
                temp.selfStudy = resultSet.getBoolean(7);
                
        } catch (SQLException ex) {
            Logger.getLogger(Subject.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public static ArrayList<Subject> getAllDetails(){
        try{
                     
            Connection myConnection = DBConnection.createConnection();
            Statement myStatement = myConnection.createStatement();
            String simpleQuery = "select * from Subject";
            ResultSet rs = myStatement.executeQuery(simpleQuery);            
            ArrayList<Subject> subjectList = new ArrayList<>(); 
            
            copyItemsToList(rs, subjectList);
            
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
                       
            Connection myConnection = DBConnection.createConnection();
            
            PreparedStatement myPreStatement = myConnection.prepareStatement("SELECT * FROM Subject WHERE CourseCode = ?");
            myPreStatement.setString(1, courseCode);
            
            ResultSet rs = myPreStatement.executeQuery();
            ArrayList<Subject> subjectList = new ArrayList<>(); 
            
            copyItemsToList(rs, subjectList);
            
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
        ArrayList<Subject> subjectList = new ArrayList<>();   
        
        try{
            
            PreparedStatement myPreStatement = myConnection.prepareStatement("SELECT * FROM Subject");
            rs = myPreStatement.executeQuery();
            switch(year)
            {
                case 2: for(int i=0;rs.next();i++)
                        {
                            if(Integer.parseInt(Character.toString(rs.getString(1).charAt(4))) == 3 || Integer.parseInt(Character.toString(rs.getString(1).charAt(4))) == 4){
                                Subject temp = new Subject();
                                copyItemsToList(rs, temp);
                                subjectList.add(temp);
                            }
                            
                        }
                        break;
                case 3: for(int i=0;rs.next();i++)
                        {
                            if(Integer.parseInt(Character.toString(rs.getString(1).charAt(4))) == 5 || Integer.parseInt(Character.toString(rs.getString(1).charAt(4))) == 6){
                                Subject temp = new Subject();
                                copyItemsToList(rs, temp);
                                subjectList.add(temp);
                            }
                            
                        }
                        break;
                case 4: for(int i=0;rs.next();i++)
                        {
                            if(Integer.parseInt(Character.toString(rs.getString(1).charAt(4))) == 7 || Integer.parseInt(Character.toString(rs.getString(1).charAt(4))) == 8){
                                Subject temp = new Subject();
                                copyItemsToList(rs, temp);
                                subjectList.add(temp);
                            }
                            
                        }
                        break;
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
    
    public static ArrayList<Subject> getTheorySubjects(int year){
        ArrayList<Subject> theoryList = Subject.getSubjectByYear(year);
        try{
            Iterator it = theoryList.iterator();
            while(it.hasNext()){
                Subject tmp = (Subject)it.next();
                if(tmp.getTheory()==0){
                    theoryList.remove(tmp);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return theoryList;
    }
    
    
    
}
