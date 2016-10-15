/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.controller;
import com.bfa.model.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Bhargav
 */
public class Allot {
    
    private String[] daysOfWeek = {"monday","tuesday","wednesday","thursday",
        "friday","saturday","sunday"};

//    1st index: number of classes, 2nd index: number of days, 3rd index: number of slots
    private TimeTableSlot[][][] TimeTable = new TimeTableSlot[6][6][6];
    
    public static int NUMBER_OF_LABS = 4;
    public static int NUMBER_OF_ROOMS = 4;
    
    
    ArrayList<Subject> getSubjectDetails=new ArrayList<>();
    ArrayList<Section> getSectionDetails=new ArrayList<>();
    Iterator sectionIterator;
    
    public static ArrayList<String> rooms = new ArrayList<>();
    
    private void preProcessing(boolean [][]occupancyMatrix,int dayOfWeek){
        // first preprocessing would be to fill in the ccp time slots
        ArrayList<Slot> slotList = Slot.getSlotsByLabNameAndDayOfWeek("CCP", daysOfWeek[dayOfWeek]);
        Iterator it = slotList.iterator();
        
        while(it.hasNext()){
            
            Slot slot = (Slot)it.next();
            System.out.println(slot.getDayOfWeek()+"     "+slot.getStartTime());
            
//            i need to somehow map the indeces in the occupancy matrix to the rooms/labs right now im assuing it to be the 5th index
            
            if (slot.getStartTime().compareTo(new Time(8,0,0))>=0 && slot.getStartTime().compareTo(new Time(10,45,0))<=0 ) {
                System.out.println("this falls into the first slot");
                
                occupancyMatrix[0][5] = true;occupancyMatrix[1][5] = true;
                
            } else if(slot.getStartTime().compareTo(new Time(11,15,0))>=0 && slot.getStartTime().compareTo(new Time(13, 5, 0))<=0 ){
                System.out.println("this falls into the second slot");
                
                occupancyMatrix[2][5] = true;occupancyMatrix[3][5] = true;
            }else{
                System.out.println("this falls into the third slot");
                
                occupancyMatrix[4][5] = true;occupancyMatrix[5][5] = true;
            }
        }
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(occupancyMatrix[i][j]+"  ");
            }
            System.out.println("");
        }
        
    }
    private void getAllDetails()
    {
        //the main randomize logic should run for a full week > in a day for a year > in a year for every class
        
        for (int day = 0; day < 6; day++) {
            System.out.println("Day: "+daysOfWeek[day]+"\n\n");
            //the number of rows signifies the number of time slots in a day and the columns is the number of rooms and labs available
            boolean [][]occupancyMatrix = new boolean[6][NUMBER_OF_ROOMS+NUMBER_OF_LABS];
            
            // here set the occupied time slots or any form of preprocessor before the randomization
            this.preProcessing(occupancyMatrix,day);
            
            for (int year = 2; year <= 4; year++) {
                getSectionDetails = Section.getSectionByYear(year);
                sectionIterator = getSectionDetails.iterator();

                System.out.println("--> For year : "+year);
                System.out.println("Section Name || Semester || Subject || Teacher ");
                while(sectionIterator.hasNext()){

                
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
