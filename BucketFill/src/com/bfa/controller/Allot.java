/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.controller;
import com.bfa.model.*;
import java.util.ArrayList;

/**
 *
 * @author Bhargav
 */
public class Allot {
    
    ArrayList<Subject> getSubjectDetails=new ArrayList<>();
    ArrayList<Section> getSectionDetails=new ArrayList<>();
    
    
    void getAllDetails()
    {
        for(int i=2;i<=4;i++)
        {
            getSubjectDetails = Subject.getSubjectByYear(i);
            getSectionDetails = Section.getSectionByYear(i);
            System.out.println(getSubjectDetails);
            System.out.println(getSectionDetails);
        }
    }
    public static void main(String args[])
    {
        Allot test=new Allot();
        test.getAllDetails();
        
    }
    
    
}
