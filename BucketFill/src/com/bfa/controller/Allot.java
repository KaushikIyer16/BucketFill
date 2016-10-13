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
    
    ArrayList<Subject> getSubjectDetails=new ArrayList<>();
    ArrayList<Section> getSectionDetails=new ArrayList<>();
    Iterator sectionIterator;
    
    void getAllDetails()
    {
        for(int i=2;i<=4;i++)
        {
            getSubjectDetails = Subject.getSubjectByYear(i);
            getSectionDetails = Section.getSectionByYear(i);
            sectionIterator = getSectionDetails.iterator();
            
            System.out.println("Section Name || Semester || Subject || Teacher --> for year : "+i);
            while(sectionIterator.hasNext()){
                Section tempSection = (Section)sectionIterator.next();
                System.out.println("      " + tempSection.getName() + "            " + tempSection.getSemester() + "           " + Section.getSubjectNameByTeacherID(tempSection.getTeacherID(), tempSection.getSemester()) + "       " + Teacher.getNameById(tempSection.getTeacherID()));
            }
            
            //System.out.println(getSubjectDetails);
            //System.out.println(getSectionDetails);
        }
    }
    public static void main(String args[])
    {
        Allot test=new Allot();
        test.getAllDetails();   
    }
    
    
}
