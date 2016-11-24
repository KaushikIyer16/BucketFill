/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.controller;

import com.bfa.beans.Debug;
import com.bfa.beans.SectionPriority;
import com.bfa.beans.TeacherOccupancy;
import com.bfa.model.Subject;
import com.bfa.model.Teacher;
import java.util.ArrayList;
import java.util.Arrays;
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

    public static int getLtps() {
        return ltps;
    }

    public static void setLtps(int ltps) {
        Graph.ltps = ltps;
    }

    
    static HashMap<String,ArrayList<Subject>[]> graph = new HashMap<>();
    // this is used to know if the returned value from the getSubjectForHour is a lab or theory or tutorial
    private static int ltps = 0;
    
    public static Subject prevSubject;
    private static String prevSection = "";
    

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
    
    private static boolean teacherOccupied(int year, String section, int hour, Subject subject) {
        String teacherName = Teacher.getTeacherBySectionAndSubject(year+section, subject);
//        System.out.println("The teacher name is "+teacherName);
        TeacherOccupancy currOccupancy = Occupancy.teacherOccupancyDetails.get(teacherName);
        // hour is taken as hour-1 as the hour is from 1-6 and the indeces of the array is from 0-5
        try{
            if(currOccupancy.getOccupancy()[Allot.day][hour-1]){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
        
    }
    
    
//    public static Subject getElectiveForHour(int year,String section,int hour){
//        ArrayList<Subject>[] sectionGraph = Graph.getGraphForSection(year+section);
//        boolean isEmptyGraph = Graph.isEmptyGraph(sectionGraph);
//        Subject subject=null;
//        int currSubject = 0; 
//        Random random = new Random();
//        
//    }
    
    public static Subject getClassForHour(int year,String section,int hour){
        ArrayList<Subject>[] sectionGraph = Graph.getGraphForSection(year+section);
        boolean isEmptyGraph = Graph.isEmptyGraph(sectionGraph);
        Subject subject=null;
        int currSubject = 0; 
        Random random = new Random();
        
        if (hour%2 == 0 && !isEmptyGraph) {
            
            ltps = 0;
            // the count is to make sure that the search process does not go into a spin lock situation
            int count=0;
            do{
                if(sectionGraph[0].isEmpty()){
                    Subject foregoSubject = new Subject();
                    foregoSubject.setCourseCode("NONE");
                    ltps=0;
                    return foregoSubject;
                }
                try{
                    currSubject = random.nextInt(sectionGraph[0].size());
                    }catch(Exception e){
//                        System.out.println("val of curr is"+currSubject+" val of ltps size is "+sectionGraph[ltps].size()+" is it empty "+sectionGraph[ltps].isEmpty());
                        Subject foregoSubject = new Subject();
                            foregoSubject.setCourseCode("NONE");
                            ltps=0;
                            return foregoSubject;
                    }
                subject = sectionGraph[0].get(currSubject);
                count++;
                if (count==200) {
                    Subject foregoSubject = new Subject();
                    foregoSubject.setCourseCode("NONE");
                    ltps=0;
                    return foregoSubject;
                    
                    
                }
                
            }while(subject.hasElective() || !teacherOccupied(year, section, hour, subject) && (subject==prevSubject && hour!=1 && (year+section).equals(prevSection)|| ( prevSubject!=null && subject.getName().equals(prevSubject.getName() ) )) );
            Graph.updateGraph(sectionGraph, 0, currSubject);
            prevSubject=subject;
            prevSection = year+section;
            return subject;
            
        } else { 
            int count = 0;
            if(!isEmptyGraph){
                
                do{
                    ltps = random.nextInt(3);
                    if(ltps==0 && sectionGraph[0].isEmpty()){
                        Subject foregoSubject = new Subject();
                        foregoSubject.setCourseCode("NONE");
                        ltps=0;
                        return foregoSubject;
                        
                    }
                    while (!isEmptyGraph && sectionGraph[ltps].isEmpty() && ltps>0) {                
                        ltps = random.nextInt(3);
                    }try{
                        currSubject = random.nextInt(sectionGraph[ltps].size());
                        }catch(Exception e){
//                            System.out.println("val of curr is"+currSubject+" val of ltps size is "+sectionGraph[ltps].size()+" is it empty "+sectionGraph[ltps].isEmpty());
                            Subject foregoSubject = new Subject();
                            foregoSubject.setCourseCode("NONE");
                            ltps=0;
                            return foregoSubject;
                    }
                    subject = sectionGraph[ltps].get(currSubject);
                    count++;
                        if (count==200) {
                            Subject foregoSubject = new Subject();
                            foregoSubject.setCourseCode("NONE");
                            ltps=0;
                            return foregoSubject;
                        
                    }
                }while(subject.hasElective() || !teacherOccupied(year, section, hour, subject) && (subject==prevSubject && hour!=1 && (year+section).equals(prevSection) || ( prevSubject!=null && subject.getName().equals(prevSubject.getName() ) )) );
                
                Graph.updateGraph(sectionGraph, ltps, currSubject);
                
                
            }else{
                Subject foregoSubject = new Subject();
                foregoSubject.setCourseCode("NONE");
                return foregoSubject;
            }
            prevSubject = subject;
            prevSection = year+section;
            // before returning update the occupancy
            Occupancy.updateOccupancy(year,section,hour,subject);
            return subject;
        }
    }
    
    public ArrayList<Subject> getTheorySubjects(ArrayList<Subject> subjectList) {

        try {
            Iterator it = subjectList.iterator();
            while (it.hasNext()) {
                Subject tmp = (Subject) it.next();
                if (tmp.getTheory() == 0 ) {
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

//                   System.out.println(tmp.getClass()+" "+tmp.getSection()+" "+tmp.getYear());
//                   graphObj.populateGraphForYear(tmp.getYear());
            }
//            Graph.printGraph();
            
    }
    
//    @Debug
//    public static void main(String[] args) {
//        
//        Graph.getClassForHour("2", "A", 1);
//    }
}
