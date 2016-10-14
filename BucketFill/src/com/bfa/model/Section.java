package com.bfa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author mahesh
 */
public class Section implements DBConnection{
    

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    private int teacherID, semester;
    private String subject, name;
    
    /**
     *
     * @param year
     * @return semester name teacher-id
     */
    public static ArrayList<Section> getSectionByYear(int year)
    {
        
        
        Connection myConnection = DBConnection.createConnection();
        ResultSet rs = null;
        
        try{
            
            PreparedStatement myPreStatement = myConnection.prepareStatement("SELECT DISTINCT Name,Semester,TeacherID FROM Section WHERE Semester = ? OR Semester = ? ORDER BY Name");

            switch(year)
            {
                case 2: myPreStatement.setInt(1, 3);
                        myPreStatement.setInt(2, 4);
                        rs = myPreStatement.executeQuery();
                        break;
                        
                case 3: myPreStatement.setInt(1, 5);
                        myPreStatement.setInt(2, 6);
                        rs = myPreStatement.executeQuery();
                        break;
                        
                case 4: myPreStatement.setInt(1, 7);
                        myPreStatement.setInt(2, 8);
                        rs = myPreStatement.executeQuery();
                        break;
                        
                default: myPreStatement.setInt(1, 1);
                         myPreStatement.setInt(2, 2);
                         rs = myPreStatement.executeQuery();
            }
            
            ArrayList<Section> sectionList = new ArrayList<>(); 
            for(int i=0;rs.next();i++)
            {
                Section temp = new Section();
                
                temp.semester = rs.getInt(2);
                temp.name = rs.getString(1);
                temp.teacherID = rs.getInt(3);
            
                sectionList.add(temp);
            }
            myConnection.close();
            return sectionList;
        }
        catch(Exception e){
            System.out.println("Error in section by year "+e);
            return null;
        }
    }  
    
    public static String getSubjectNameByTeacherID(int id, int semester)
    {
        
        
        Connection myConnection = DBConnection.createConnection();
        ResultSet rs = null;
        
        try{
            
            PreparedStatement myPreStatement = myConnection.prepareStatement("SELECT Subject FROM Section WHERE TeacherID = ? AND Semester = ?");
            myPreStatement.setInt(1, id);
            myPreStatement.setInt(2, semester);
            rs = myPreStatement.executeQuery();
             
            String teacherName = null;
            while(rs.next())
            {
                teacherName = rs.getString(1);
            }
            myConnection.close();
            return teacherName;
        }
        catch(Exception e){
            System.out.println("Error in section by year "+e);
            return null;
        }
    }  
}
