/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.beans;

import java.util.Comparator;
import com.bfa.model.Section;
import java.util.Objects;

/**
 *
 * @author kaushiknsiyer
 * @description this class is a bean for storing the sections in a sorted manner
 * with the priority as the key
 */
public class SectionPriority implements Comparable<SectionPriority> {

    private int year;
    private String section;
    private int priority;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public SectionPriority(Section section, int priority) {
        this.year = section.getSemester();
        this.section = section.getName();
        this.priority = priority;
    }

    @Override
    public int compareTo(SectionPriority o) {
        if (this.getPriority() == o.getPriority() && this.getSection().equalsIgnoreCase(o.getSection()) && this.getYear() == o.getYear()) {
            return 0;
        } else if (this.getPriority() < o.getPriority()) {
            return -1;
        } else {
            return 1;
        }

    }

}
