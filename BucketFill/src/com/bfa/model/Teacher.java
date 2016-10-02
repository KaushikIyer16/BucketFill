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
public class Teacher {
    
    String name = null;
    int id = 0, hours = 0;
    public static ArrayList getAllDetails(){
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection myConnection=DriverManager.getConnection("jdbc:mysql://localhost:8889/bucketfill","root","root");
            
            Statement myStatement = myConnection.createStatement();
            String simpleQuery = "select * from teachers";
            ResultSet rs = myStatement.executeQuery(simpleQuery);            
            ArrayList<Teacher> teacherList = new ArrayList<Teacher>(); 
            
            for(int i=0;rs.next();i++)
            {
                Teacher temp = new Teacher();
                
                temp.name = rs.getString(2);
                temp.id = rs.getInt(1);
                temp.hours = rs.getInt(3);
                
                teacherList.add(temp);
            }
            myConnection.close();
            return teacherList;
        }
        catch(Exception e){
            System.out.println(e+" Occured in get all details");
            return null;
        }
    }
    public static ArrayList getDetailsById(int id){
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection myConnection=DriverManager.getConnection("jdbc:mysql://localhost:8889/bucketfill","root","root");
            
            PreparedStatement myPreStatement = myConnection.prepareStatement("SELECT * FROM teachers WHERE id = ?");
            myPreStatement.setInt(1, id);
            
            ResultSet rs = myPreStatement.executeQuery();
            ArrayList<Teacher> teacherList = new ArrayList<Teacher>(); 
            
            for(int i=0;rs.next();i++)
            {
                Teacher temp = new Teacher();
                
                temp.name = rs.getString(2);
                temp.id = rs.getInt(1);
                temp.hours = rs.getInt(3);
                
                teacherList.add(temp);
            }
            myConnection.close();
            return teacherList;
        }
        catch(Exception e){
            System.out.println(e+" Occured in get details by id");
            return null;
        }
    }
}
