/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.controller;

import com.bfa.beans.SectionPriority;
import com.bfa.model.Subject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/**
 *
 * @author kaushiknsiyer
 */
public class Graph {

    
    static HashMap<String,ArrayList<Subject>[]> graph = new HashMap<>();

    private ArrayList<Subject>[] populateGraphForYear(int year) {
        ArrayList<Subject> subjectGraph[] = new ArrayList[3];
        
        ArrayList<Subject> tmpList = Subject.getSubjectByYear(year);
        subjectGraph[0] = this.getTheorySubjects(tmpList);
        tmpList = Subject.getSubjectByYear(year);
        subjectGraph[1] = this.getTutorialSubjects(tmpList);
        tmpList = Subject.getSubjectByYear(year);
        subjectGraph[2] = this.getPracticalsubjects(tmpList);
        
        return subjectGraph;
    }

    private static void printGraph() {
        Iterator gIt = graph.keySet().iterator();
        while(gIt.hasNext()){
            String className = gIt.next().toString();
            System.out.println(className+":>");
            ArrayList<Subject>[] sectionGraph = Graph.graph.get(className);
            for (int i = 0; i < 3; i++) {
                Iterator it = sectionGraph[i].iterator();

                while (it.hasNext()) {
                    Subject tmp = (Subject) it.next();
                    System.out.println(tmp.getName() + "  " + tmp.getTheory());
                }

                System.out.println("--------------------------");
            }
        }
        
    }
    
    
    public static void printGraphForClassName(String className) {
        
        System.out.println(className+":>");
        ArrayList<Subject>[] sectionGraph = Graph.graph.get(className);
        for (int i = 0; i < 3; i++) {
            Iterator it = sectionGraph[i].iterator();

            while (it.hasNext()) {
                Subject tmp = (Subject) it.next();
                System.out.println(tmp.getName() + "  " + tmp.getTheory());
            }

            System.out.println("--------------------------");
        }
        
    }
    
    public ArrayList<Subject> getTheorySubjects(ArrayList<Subject> subjectList) {

        try {
            Iterator it = subjectList.iterator();
            while (it.hasNext()) {
                Subject tmp = (Subject) it.next();
                if (tmp.getTheory() == 0) {
                    subjectList.remove(tmp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subjectList;
    }

    public ArrayList<Subject> getTutorialSubjects(ArrayList<Subject> subjectList) {

        try {
            Iterator it = subjectList.iterator();
            while (it.hasNext()) {
                Subject tmp = (Subject) it.next();
                if (!tmp.hasTutorial()) {
                    it.remove();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subjectList;
    }

    public ArrayList<Subject> getPracticalsubjects(ArrayList<Subject> subjectList) {

        try {
            Iterator it = subjectList.iterator();
            while (it.hasNext()) {
                Subject tmp = (Subject) it.next();
                if (!tmp.hasLab()) {
                    it.remove();
                }
            }
        } catch (Exception e) {

        }
        return subjectList;
    }

    public static void init(TreeSet<SectionPriority> sectionSet){

//            Graph.populateGraphForYear(year);

            Graph graphObj = new Graph();
            Iterator sectionIterator = sectionSet.iterator();
            while(sectionIterator.hasNext()){
                SectionPriority tmp = (SectionPriority)sectionIterator.next();
                graph.put(tmp.getYear()+tmp.getSection(), graphObj.populateGraphForYear(tmp.getYear()) );
            }
//            Graph.printGraph();
            
    }
}
