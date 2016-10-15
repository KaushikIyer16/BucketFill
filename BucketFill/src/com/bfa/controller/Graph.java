/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.controller;

import com.bfa.model.Subject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author kaushiknsiyer
 */
public class Graph {

    static ArrayList<Subject> subjectGraph[] = new ArrayList[3];

    private static void populateGraphForYear(int year) {

        ArrayList<Subject> tmpList = Subject.getSubjectByYear(year);
        Graph.subjectGraph[0] = Graph.getTheorySubjects(tmpList);
        tmpList = Subject.getSubjectByYear(year);
        Graph.subjectGraph[1] = Graph.getTutorialSubjects(tmpList);
        tmpList = Subject.getSubjectByYear(year);
        Graph.subjectGraph[2] = Graph.getPracticalsubjects(tmpList);

    }

    private static void printGraph() {
        for (int i = 0; i < 3; i++) {
            Iterator it = Graph.subjectGraph[i].iterator();

            while (it.hasNext()) {
                Subject tmp = (Subject) it.next();
                System.out.println(tmp.getName() + "  " + tmp.getTheory());
            }

            System.out.println("--------------------------");
        }
    }

    public static ArrayList<Subject> getTheorySubjects(ArrayList<Subject> subjectList) {

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

    public static ArrayList<Subject> getTutorialSubjects(ArrayList<Subject> subjectList) {

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

    public static ArrayList<Subject> getPracticalsubjects(ArrayList<Subject> subjectList) {

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

//    public static void main(String args[]){
////        Graph.populateGraphForYear(2);
////        System.out.println("++++++++++++++++++++++++++++");
////        Graph.populateGraphForYear(3);
//          for (int i = 4; i <= 4; i++) {
//            Graph.populateGraphForYear(i);
//            Graph.printGraph();
//        }
//    }
}
