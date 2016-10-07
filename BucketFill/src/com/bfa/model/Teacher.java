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
public class Teacher implements DBConnection{
    
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
    
    public static ArrayList<Teacher> getAllDetails(){
        try{
            
            Connection myConnection = DBConnection.createConnection();
            
            Statement myStatement = myConnection.createStatement();
            String simpleQuery = "select * from Teacher";
            ResultSet rs = myStatement.executeQuery(simpleQuery);            
            ArrayList<Teacher> teacherList = new ArrayList<>(); 
            
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
    public static ArrayList<Teacher> getDetailsById(int id){
        try{
            
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection myConnection=DriverManager.getConnection("jdbc:mysql://localhost:8889/bucketfill","root","root");
            Connection myConnection = DBConnection.createConnection();
            
            PreparedStatement myPreStatement = myConnection.prepareStatement("SELECT * FROM Teacher WHERE id = ?");
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
