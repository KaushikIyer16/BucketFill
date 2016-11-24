/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.beans;

import com.bfa.controller.Allot;
import com.bfa.controller.TimeTableSlot;
import com.bfa.model.Section;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

/**
 *
 * @author kaushiknsiyer
 */
public class TimeTableBean {
    String className;
    TimeTableSlot timeTable[][];

    public TimeTableBean(String className) {
        this.className = className;
        // first index is the number of days and the second is the number of hours per day
        this.timeTable = new TimeTableSlot[6][Allot.NUMBER_OF_HOURS];
    }
    
    
    public static ArrayList<TimeTableBean> timeTableDetails = new ArrayList<>();

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public TimeTableSlot[][] getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(TimeTableSlot[][] timeTable) {
        this.timeTable = timeTable;
    }
    
    public static void initTimeTable(TreeSet<SectionPriority> sectionPriority){
        Iterator iterator = sectionPriority.iterator();
        
        while(iterator.hasNext()){
            SectionPriority tmp = (SectionPriority)iterator.next();
            timeTableDetails.add(new TimeTableBean(tmp.getYear()+tmp.getSection()));
        }
    }
    
    public static TimeTableSlot[][] getTimeTableForSection(String section){
        Iterator it = timeTableDetails.iterator();
        
        while(it.hasNext()){
            TimeTableBean timeTableBean = (TimeTableBean)it.next();
            if(timeTableBean.getClassName().equals(section)){
                return timeTableBean.getTimeTable();
            }
        }
        return null;
    }
    
    public static void printTimeTables(){
        Iterator timeTableIterator = timeTableDetails.iterator();
        while(timeTableIterator.hasNext()){
            TimeTableBean currTimeTable = (TimeTableBean)timeTableIterator.next();
            System.out.println("------"+currTimeTable.getClassName()+"----------");
            TimeTableSlot[][] currSlot = currTimeTable.getTimeTable();
            
            for(int i=0;i<6;i++){
                for(int j=0;j<6;j++){
                    System.out.print(currSlot[i][j].getSubject()+"     ");
                }
                System.out.println("");
            }
        }
    }
}
