/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.beans;

/**
 *
 * @author Moin
 */
 public class TeacherOccupancy {
    public static int NUMBER_OF_DAYS=6,NUMBER_OF_HOURS=6;
    private String teacherName;
    private boolean[][] occupancy = new boolean[NUMBER_OF_DAYS][NUMBER_OF_HOURS];

    public static int getNUMBER_OF_DAYS() {
        return NUMBER_OF_DAYS;
    }

    public static void setNUMBER_OF_DAYS(int NUMBER_OF_DAYS) {
        TeacherOccupancy.NUMBER_OF_DAYS = NUMBER_OF_DAYS;
    }

    public static int getNUMBER_OF_HOURS() {
        return NUMBER_OF_HOURS;
    }

    public static void setNUMBER_OF_HOURS(int NUMBER_OF_HOURS) {
        TeacherOccupancy.NUMBER_OF_HOURS = NUMBER_OF_HOURS;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public boolean[][] getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(boolean[][] occupancy) {
        this.occupancy = occupancy;
    }

    public TeacherOccupancy(String teacherName,boolean[][] occupancy) {
        this.teacherName = teacherName;
        this.occupancy = occupancy;
    }
    
    
    
    
    
}
