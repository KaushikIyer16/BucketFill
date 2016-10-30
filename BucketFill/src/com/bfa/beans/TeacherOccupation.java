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
 public class TeacherOccupation {
    public static int NUMBER_OF_DAYS=6,NUMBER_OF_HOURS=8;
    private String teacherId;
    private int hourOfTheDay,dayOfTheWeek;
    public  void set_teacherId(String nteacherId)
    {
        teacherId=nteacherId;
    }
    public void set_hourOfTheDay(int hourOfTheDay)
    {
        this.hourOfTheDay=hourOfTheDay;
    }
    public void set_dayOfTheWeek(int dayOfTheWeek)
    {
        this.dayOfTheWeek=dayOfTheWeek;
    }
    public int get_dayOfTheWeek()
    {
        return this.dayOfTheWeek;
    }
    public int get_hourOfTheDay()
    {
        return this.hourOfTheDay;
    }
    public String get_TeacherId()
    {
    	return this.teacherId;
    }
    
    
    
    
}
