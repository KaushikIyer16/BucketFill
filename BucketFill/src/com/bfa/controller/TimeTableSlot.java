/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.controller;

/**
 *
 * @author kaushiknsiyer
 */
public class TimeTableSlot {

    private int hour;
    private String subject, room, teacher;

    public TimeTableSlot() {
    }

    public TimeTableSlot(int hour, String subject, String room, String teacher) {
        this.hour = hour;
        this.subject = subject;
        this.room = room;
        this.teacher = teacher;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

}
