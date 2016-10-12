/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.controller;

import com.bfa.model.Subject;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author kaushiknsiyer
 */
public class Graph {
//    now i have to build the graph .
//    so first i have to get the no of theory subjects
    
    private static void populateGraphForYear(int year){
        List<Subject> theoryList= Subject.getTheorySubjects(year);
        Iterator iterator = theoryList.iterator();
        while(iterator.hasNext()){
            Subject tmp = (Subject)iterator.next();
            
            System.out.println(tmp.getName()+"  "+tmp.getTheory());
        }
    }
    
    public static void main(String args[]){
        Graph.populateGraphForYear(2);
        Graph.populateGraphForYear(3);
    }
}
