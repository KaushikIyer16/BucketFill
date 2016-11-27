/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.controller;

import com.bfa.beans.ElectiveCombination;
import com.bfa.beans.SectionPriority;
import com.bfa.beans.TimeTableBean;
import com.bfa.model.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

/**
 *
 * @author Bhargav
 */
public class Allot {

    private String[] daysOfWeek = {"monday", "tuesday", "wednesday", "thursday",
        "friday", "saturday", "sunday"};
    private TreeSet<SectionPriority> sectionSet = new TreeSet<>();
    
    // this has to be removed in the final output
    public static int NUMBER_OF_LABS=3;
    public static int NUMBER_OF_ROOMS=4;
    public static int NUMBER_OF_HOURS = 6;
    
    public static int day=0;
    private int prevDay = -1;
    private int prevHour = -1;
    private boolean writtenToExcel = false;

    //    1st index: number of classes, 2nd index: number of days, 3rd index: number of slots
    private TimeTableSlot[][][] TimeTable = new TimeTableSlot[6][6][NUMBER_OF_HOURS];

//    the below list should contain the names of the rooms available
    public static ArrayList<String> rooms = new ArrayList<>();

    private void preProcessing(boolean[][] occupancyMatrix, int dayOfWeek) {
        //below preprocessing would be to set the total number of sections
        Section.setTotalNumberOfSections();
        
        
        // below preprocessing would be to fill in the ccp time slots
        ArrayList<Slot> slotList = Slot.getSlotsByLabNameAndDayOfWeek("CCP", daysOfWeek[dayOfWeek]);
        Iterator it = slotList.iterator();

        while (it.hasNext()) {

            Slot slot = (Slot) it.next();
//            System.out.println(slot.getDayOfWeek() + "     " + slot.getStartTime());

//            i need to somehow map the indeces in the occupancy matrix to the rooms/labs right now im assuing it to be the 5th index
            if (slot.getStartTime().compareTo(new Time(8, 0, 0)) >= 0
                    && slot.getStartTime().compareTo(new Time(10, 45, 0)) <= 0) {

//                System.out.println("this falls into the first slot");

                occupancyMatrix[0][5] = true;
                occupancyMatrix[1][5] = true;

            } else if (slot.getStartTime().compareTo(new Time(11, 15, 0)) >= 0
                    && slot.getStartTime().compareTo(new Time(13, 5, 0)) <= 0) {

//                System.out.println("this falls into the second slot");

                occupancyMatrix[2][5] = true;
                occupancyMatrix[3][5] = true;
            } else {
//                System.out.println("this falls into the third slot");

                occupancyMatrix[4][5] = true;
                occupancyMatrix[5][5] = true;
            }
        }

//        for (int i = 0; i < 6; i++) {
//            for (int j = 0; j < 8; j++) {
//                System.out.print(occupancyMatrix[i][j]+"  ");
//            }
//            System.out.println("");
//        }
    }

    public void getAllDetails() {
        // this array list holds all the year-hour combination for elective say if the third hour comes elective for 3rd year then it appears as 33
        ArrayList<ElectiveCombination> electiveHours = new ArrayList<>();
        Random random = new Random();
        for (int year = 2; year < 4; year++) {

            ArrayList<Section> getSectionDetails = Section.getSectionByYear(year);
            Iterator sectionIterator = getSectionDetails.iterator();

            while (sectionIterator.hasNext()) {

                Section tempSection = (Section) sectionIterator.next();
                if (!tempSection.getName().matches("[0-9]*")) {
//                    for now the priority is same as the year but we can have a map which can store this
                    sectionSet.add(new SectionPriority(tempSection, year));
                }

            }
        }
        
        // below statement initializes the graph for the given set of sections
        Graph.init(sectionSet);
        
        // below i will take the teacher time occupancy and populate them into a bean list
        Occupancy.initTeacherOccupancyList();
        
        
        // now i have to set the timings for the elective
        // first thing will be to setup the TimeTableBean
        TimeTableBean.initTimeTable(sectionSet);
        
        // i will get the ltps of elective's
        for (int year = 2; year < 4; year++) {
            int[] electiveLtps = Subject.getElectiveLtps(year);
            if(electiveLtps[0] == 0){
                continue;
            }
            // this is a list having all the sections for a particular year
            ArrayList<String> classForYear = new ArrayList<>();
            Iterator sectionIterator = sectionSet.iterator();
            while(sectionIterator.hasNext()){
                SectionPriority temp = (SectionPriority)sectionIterator.next();
                if (temp.getYear() == year) {
                    classForYear.add(temp.getYear()+temp.getSection());
//                    System.out.println(temp.getYear()+temp.getSection());
                }
                
                
            }
            
            //now i will get a random 
            while(electiveLtps[0]!=0 || electiveLtps[1]!=0 || electiveLtps[2]!=0 || electiveLtps[3]!=0 ){
                int randLtps;
                int currDay;
                int currHour;
                do{
                    randLtps = random.nextInt(3);
                }while(electiveLtps[randLtps] == 0);
                
                do {                    
                    currDay = random.nextInt(5);
                } while (currDay==prevDay);
                do{
                     currHour = random.nextInt(5);
                }while(randLtps!=0 && currHour%2 == 1);
                // now we have the ltps day and hour we will add this detail to all the sections and reduce it from ltps
                if (!Occupancy.areElectiveTeachersOccupied(year, currDay, currHour, randLtps) && !electiveHours.contains(new ElectiveCombination(currDay, currHour))) {
                    System.out.println("Value of currDay and currHour is "+currDay+"    =     "+currHour);
                    electiveLtps[randLtps]--;
                    Iterator classIterator = classForYear.iterator();
                    while(classIterator.hasNext()){
                        String className = (String)classIterator.next();
                        TimeTableSlot currTimeTable[][] = TimeTableBean.getTimeTableForSection(className);
                        if(randLtps == 1 || randLtps == 2){
                            currTimeTable[currDay][currHour] = new TimeTableSlot();
                            currTimeTable[currDay][currHour].setSubject("ELECTIVE");
                            currTimeTable[currDay][currHour].setIsOccupied(true);
                            currTimeTable[currDay][currHour+1] = new TimeTableSlot();
                            currTimeTable[currDay][currHour+1].setSubject("ELECTIVE");
                            currTimeTable[currDay][currHour+1].setIsOccupied(true);
                            electiveHours.add(new ElectiveCombination(currDay, currHour));
                            electiveHours.add(new ElectiveCombination(currDay, currHour+1));
                            
                            Occupancy.updateOccupancyForElective(year,currDay,currHour,randLtps);
                        }else{
                            currTimeTable[currDay][currHour] = new TimeTableSlot();
                            currTimeTable[currDay][currHour].setSubject("ELECTIVE");
                            currTimeTable[currDay][currHour].setIsOccupied(true);
                            Occupancy.updateOccupancyForElective(year,currDay,currHour,randLtps);
                            electiveHours.add(new ElectiveCombination(currDay, currHour));
                        }
                    }
                    
                    
                }
                
            }
            
        }
        
        
        //the main randomize logic should run for a full week > in a day for a year > in a year for every class
        for ( day = 0; day < 6; day++) {
//            System.out.println("Day: " + daysOfWeek[day] + "\n\n");
            Graph.prevSubject = null;
            //the number of rows signifies the number of time slots in a day and the columns is the number of rooms and labs available
            boolean[][] occupancyMatrix = new boolean[6][NUMBER_OF_ROOMS + NUMBER_OF_LABS];
            
            // here set the occupied time slots or any form of preprocessor before the randomization
            this.preProcessing(occupancyMatrix, day);
            
            Iterator sectionIterator = sectionSet.iterator();
            while(sectionIterator.hasNext()){
                SectionPriority tmp = (SectionPriority)sectionIterator.next();
//                System.out.println(tmp.getYear()+""+tmp.getSection());
                
                // get the time table for that particular section
                TimeTableSlot[][] currTimeTable = TimeTableBean.getTimeTableForSection(tmp.getYear()+tmp.getSection());
                
//                 now below get the graph for that class and get a room from the occupancy matrix and then fill them in the timetable variable and then remove them from the pool
                int hour = 1;
                while(hour <= 6){
                    
                    if ( currTimeTable[day][hour-1] != null && currTimeTable[day][hour-1].isIsOccupied()) {
                        
                        if(hour<6 && currTimeTable[day][hour] != null &&currTimeTable[day][hour].isIsOccupied() ){
                            Graph.setLtps(1);
                            hour+=2;
                        }else{
                            Graph.setLtps(0);
                            hour++;
                        }
//                        System.out.print("ELECTIVE"+"   "+Graph.getLtps()+" ---- ");
                        
                        continue;
                    } else {
                        Subject subject = Graph.getClassForHour(tmp.getYear(), tmp.getSection(), hour);
                        if (subject != null) {
 
//                            System.out.print(subject.getCourseCode()+"   "+Graph.getLtps()+" ---- ");
                            int retLtps = Graph.getLtps();
                            if (retLtps == 0) {
                                currTimeTable[day][hour-1] = new TimeTableSlot();
                                currTimeTable[day][hour-1].setSubject(subject.getCourseCode());
                                currTimeTable[day][hour-1].setIsOccupied(true);
                                hour+=1;
                                
                            } else {
                                currTimeTable[day][hour-1] = new TimeTableSlot();
                                currTimeTable[day][hour-1].setSubject(subject.getCourseCode());
                                currTimeTable[day][hour-1].setIsOccupied(true);
                                
                                currTimeTable[day][hour] = new TimeTableSlot();
                                currTimeTable[day][hour].setSubject(subject.getCourseCode());
                                currTimeTable[day][hour].setIsOccupied(true);
                                hour+=2;
                            }

                        }else{
                            break;
                        }

                    }
                                        
                }
//                System.out.println("");
            }
//            System.out.println("");
        }
        
//        TimeTableBean.printTimeTables();
        if(!writtenToExcel){
            ExcelWriter excelWriter = new ExcelWriter();
            excelWriter.writeTimeTable();
            writtenToExcel = true;
        }
        
    }

//    public static void main(String args[]) {
//        Allot test = new Allot();
//        test.getAllDetails();
//    }

}
