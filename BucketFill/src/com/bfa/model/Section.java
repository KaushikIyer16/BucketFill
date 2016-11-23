package com.bfa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author mahesh
 */
public class Section implements DBConnection {

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
    public static int TOTAL_NUMBER_OF_SECTIONS;

    /**
     *
     * @param year
     * @return semester name teacher-id
     */
    public static ArrayList<Section> getSectionByYear(int year) {

        Connection myConnection = DBConnection.createConnection();
        ResultSet rs = null;

        try {

            PreparedStatement myPreStatement = myConnection.prepareStatement("SELECT DISTINCT Name,Semester,TeacherID FROM Section WHERE Semester = ? OR Semester = ? ORDER BY Name");

            switch (year) {
                case 2:
                    myPreStatement.setInt(1, 3);
                    myPreStatement.setInt(2, 4);
                    rs = myPreStatement.executeQuery();
                    break;

                case 3:
                    myPreStatement.setInt(1, 5);
                    myPreStatement.setInt(2, 6);
                    rs = myPreStatement.executeQuery();
                    break;

                case 4:
                    myPreStatement.setInt(1, 7);
                    myPreStatement.setInt(2, 8);
                    rs = myPreStatement.executeQuery();
                    break;

                default:
                    myPreStatement.setInt(1, 1);
                    myPreStatement.setInt(2, 2);
                    rs = myPreStatement.executeQuery();
            }

            ArrayList<Section> sectionList = new ArrayList<>();
            for (int i = 0; rs.next(); i++) {
                Section temp = new Section();

                temp.semester = rs.getInt(2);
                temp.name = rs.getString(1);
                temp.teacherID = rs.getInt(3);

                sectionList.add(temp);
            }
            myConnection.close();
            return sectionList;
        } catch (Exception e) {
            System.out.println("Error in section by year " + e);
            return null;
        }
    }

    public static String getSubjectNameByTeacherID(int id, int semester) {
        /*Deprecated-->Please use the method in TeacherSubject.java*/
        Connection myConnection = DBConnection.createConnection();
        ResultSet rs = null;

        try {

            PreparedStatement myPreStatement = myConnection.prepareStatement("SELECT Subject FROM Section WHERE TeacherID = ? AND Semester = ?");
            myPreStatement.setInt(1, id);
            myPreStatement.setInt(2, semester);
            rs = myPreStatement.executeQuery();

            String teacherName = null;
            while (rs.next()) {
                teacherName = rs.getString(1);
            }
            myConnection.close();
            return teacherName;
        } catch (Exception e) {
            System.out.println("Error in section by year " + e);
            return null;
        }
    }

    public static void setTotalNumberOfSections() {
        for (int i = 2; i <= 4; i++) {
            TOTAL_NUMBER_OF_SECTIONS += Section.getNumberOfSectionsByYear(i);
        }
    }

    /**
     *
     * @param year
     * @return number of sections in a year as integer
     */
    public static int getNumberOfSectionsByYear(int year) {
        Connection myConnection = DBConnection.createConnection();
        ResultSet rs = null;

        try {

            PreparedStatement myPreStatement = myConnection.prepareStatement("SELECT COUNT(DISTINCT Name) FROM Section WHERE Name NOT IN (SELECT Name FROM Section WHERE Name = 1) AND (Semester = ? OR Semester = ?)");
            switch (year) {
                case 2:
                    myPreStatement.setInt(1, 3);
                    myPreStatement.setInt(2, 4);
                    rs = myPreStatement.executeQuery();
                    break;

                case 3:
                    myPreStatement.setInt(1, 5);
                    myPreStatement.setInt(2, 6);
                    rs = myPreStatement.executeQuery();
                    break;

                case 4:
                    myPreStatement.setInt(1, 7);
                    myPreStatement.setInt(2, 8);
                    rs = myPreStatement.executeQuery();
                    break;

                default:
                    myPreStatement.setInt(1, 1);
                    myPreStatement.setInt(2, 2);
                    rs = myPreStatement.executeQuery();
            }

            int noOfSections = 0;
            while (rs.next()) {
                noOfSections = rs.getInt(1);
            }
            myConnection.close();
            return noOfSections;
        } catch (Exception e) {
            System.out.println("Error in section by year " + e);
            return 0;
        }
    }

    public static void insertDetails(ArrayList<Subject> subjectList ,String[][] sectionMatrix, int year){
        
        Connection myConnection = DBConnection.createConnection();
        ResultSet rs = null;

        try {
            
            PreparedStatement myPreStatement = myConnection.prepareStatement("INSERT INTO Section VALUES((SELECT ID from Teacher where Name like ?),?,?,?)");
            //Important: check for elective before insert
            for(int i=0; i< subjectList.size(); i++){
                char section = 'A';
                String tempSubject = subjectList.get(i).getName();
                int tempTeacherID = 0;
                for(int j = 0; j<sectionMatrix[i].length; j++){
                    myPreStatement.setString(1, sectionMatrix[i][j]);
                    myPreStatement.setString(2, tempSubject);
                    if(year == 2)
                        myPreStatement.setInt(3, 3);
                    else if(year == 3)
                        myPreStatement.setInt(3, 5);
                    else
                        myPreStatement.setInt(3, 7);
                    myPreStatement.setString(4, String.valueOf(section++));
                    myPreStatement.execute();
                }
            }
            
            myConnection.close();
        } catch (Exception e) {
            System.out.println("Error in insert detail in section " + e);
        }
    }
}
