/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.controller;
import com.bfa.model.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Bhargav
 */
public class Allot {
    private String[] daysOfWeek = {"monday","tuesday","wednesday","thursday","friday","saturday","sunday"};

//    1st index: number of classes, 2nd index: number of days, 3rd index: number of slots
    private TimeTableSlot[][][] TimeTable = new TimeTableSlot[6][6][6];
    
    public static int NUMBER_OF_LABS = 4;
    public static int NUMBER_OF_ROOMS = 4;
    
    
    ArrayList<Subject> getSubjectDetails=new ArrayList<>();
    ArrayList<Section> getSectionDetails=new ArrayList<>();
    Iterator sectionIterator;
    
    public static ArrayList<String> rooms = new ArrayList<>();
    
    void getAllDetails()
    {
//      the main randomize logic should run for a full week > in a day for a year > in a year for every class
        
        for (int day = 0; day < 6; day++) {
            System.out.println("Day: "+daysOfWeek[day]+"\n\n");
//            the number of rows signifies the number of time slots in a day and the columns is the number of rooms and labs available
            boolean [][]occupencyMatrix = new boolean[6][NUMBER_OF_ROOMS+NUMBER_OF_LABS];
            
            for (int year = 2; year <= 4; year++) {
                getSectionDetails = Section.getSectionByYear(year);
                sectionIterator = getSectionDetails.iterator();

                System.out.println("--> For year : "+year);
                System.out.println("Section Name || Semester || Subject || Teacher ");
                while(sectionIterator.hasNext()){

    //                set the class to the current class and whenever the class changes them referesh the
                    Section tempSection = (Section)sectionIterator.next();
                    System.out.println("      " + tempSection.getName() + "            " + tempSection.getSemester() + "           " + Section.getSubjectNameByTeacherID(tempSection.getTeacherID(), tempSection.getSemester()) + "       " + Teacher.getNameById(tempSection.getTeacherID()));
                }
            }
        }
    }
    public static void main(String args[])
    {
        Allot test=new Allot();
        test.getAllDetails();   
    }
    
    
}
