/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.controller;

import com.bfa.beans.Debug;
import com.bfa.beans.SectionPriority;
import com.bfa.model.Subject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
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
    
    @Debug
    public static void printGraph() {
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
    
    @Debug
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
    
    private static ArrayList<Subject>[] getGraphForSection(String sectionAndYear){
        return Graph.graph.get(sectionAndYear);
    }
    
    private static boolean isEmptyGraph(ArrayList<Subject>[] graph){
        return graph[0].isEmpty() && graph[1].isEmpty() && graph[2].isEmpty();
    }
    
    private static void updateGraph(ArrayList<Subject>[] graph,int n,int currSubject){
        if (n == 0) {
            int newTheory = graph[n].get(currSubject).getTheory() -1;
            if(newTheory == 0){
                graph[n].remove(currSubject);
            }else{
                graph[n].get(currSubject).setTheory(newTheory);
            }
            
        } else if(n == 1){
            int newTutorial = graph[n].get(currSubject).getTutorial() -1;
            if (newTutorial == 0) {
                graph[n].remove(currSubject);
            } else {
                graph[n].get(currSubject).setTutorial(newTutorial);
            }
            
        }else{
            int newPractical = graph[n].get(currSubject).getLab() -1;
            if (newPractical == 0) {
                graph[n].remove(currSubject);
            } else {
                graph[n].get(currSubject).setLab(newPractical);
            }
            
        }
    }
    
    public static void getClassForHour(int year,String section,int hour){
        ArrayList<Subject>[] sectionGraph = Graph.getGraphForSection(year+section);
        Random random = new Random();
        if (hour%2 == 1) {
            
        } else {
            
            int n = random.nextInt(3);
            boolean isEmptyGraph = Graph.isEmptyGraph(sectionGraph);
            while (!isEmptyGraph && sectionGraph[n].isEmpty() ) {                
                n = random.nextInt(3);
            }
            if(!isEmptyGraph){
                System.out.println(n);
                int currSubject = random.nextInt(sectionGraph[n].size());
                Subject subject = sectionGraph[n].get(currSubject);
                Graph.updateGraph(sectionGraph, n, currSubject);
                
                
            }
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
    
//    @Debug
//    public static void main(String[] args) {
//        
//        Graph.getClassForHour("2", "A", 1);
//    }
}
