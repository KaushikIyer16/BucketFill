/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.beans;

import com.bfa.controller.TimeTableSlot;

/**
 *
 * @author kaushiknsiyer
 */
public class TimeTableBean {
    String className;
    TimeTableSlot timeTable[][];

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
}
