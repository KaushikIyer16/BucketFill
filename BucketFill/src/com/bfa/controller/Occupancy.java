/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.controller;
import com.bfa.beans.TeacherOccupation;
import java.awt.BorderLayout;
import java.util.*;

/**
 *
 * @author Moin
 */
public class Occupancy {
    
    public static int NUMBER_OF_TEACHERS = 10, NUMBER_OF_DAYS = 6, NUMBER_OF_HOURS = 9;
    
    public static void main(String[] args) {
             boolean[][][] teacherOccupancy = new boolean[NUMBER_OF_TEACHERS][NUMBER_OF_DAYS][NUMBER_OF_HOURS];
        List<String> teacherId= new ArrayList<String>();   
        List<TeacherOccupation> teacherObj= new ArrayList<TeacherOccupation>();
        TeacherOccupation obj = new TeacherOccupation();
        SetterClass obj1=new SetterClass();
        teacherObj=obj1.detailsSetter(teacherObj);
        for(int i=0;i<teacherObj.size();i++)
        {
                   obj=teacherObj.get(i);
		   int day=obj.get_dayOfTheWeek();
		   int hour=obj.get_hourOfTheDay();
		          if(!(teacherId.contains(obj.get_TeacherId())))
		        	  teacherId.add(obj.get_TeacherId());
	           teacherOccupancy[teacherId.indexOf(obj.get_TeacherId())][day][hour]=true;
	
           
        }
        	   System.out.println(teacherId);
        	  // System.out.println(teacherOccupancy[1][3][3]);
                  //Input any proper value to get output
    }
}
             /* teacherName.add("nalini");
              teacherName.add("shwetha");
              teacherName.add("mamtha");
              teacherName.add("sowmya");
              teacherName.add("indira");
              teacherName.add("anitha");
              teacherName.add("chandrakala");
              
              teacherOccupancy[0][1][4]=true;
              teacherOccupancy[0][2][1]=true;
              teacherOccupancy[0][3][3]=true;
              
              teacherOccupancy[1][3][4]=true;
              teacherOccupancy[1][4][2]=true;
              teacherOccupancy[1][5][5]=true;
              
              teacherOccupancy[1][1][6]=true;
              teacherOccupancy[1][2][6]=true;
              teacherOccupancy[1][3][8]=true;
              
              teacherOccupancy[2][0][3]=true;
              teacherOccupancy[2][1][4]=true;
              teacherOccupancy[2][3][1]=true; 
              
              teacherOccupancy[3][0][6]=true;
              teacherOccupancy[3][1][0]=true;
              teacherOccupancy[3][2][4]=true;
              
              teacherOccupancy[3][0][8]=true;
              teacherOccupancy[3][3][1]=true;
              teacherOccupancy[3][5][3]=true;
              
              teacherOccupancy[4][3][1]=true;
              teacherOccupancy[4][4][0]=true;
              teacherOccupancy[4][5][1]=true;
              
              teacherOccupancy[5][2][6]=true;
              teacherOccupancy[5][3][0]=true;
              teacherOccupancy[5][4][6]=true;
              
              teacherOccupancy[6][2][1]=true;
              teacherOccupancy[6][3][6]=true;
              teacherOccupancy[6][5][0]=true;
             
              Scanner sc = new Scanner(System.in);
              System.out.println("Enter the name of a teacher");
              String myString = sc.next();
              myString=myString.toLowerCase();
              int indexOfTeacher=teacherName.indexOf(myString);
              System.out.println("Enter the hour ");
              int indexOfHour=sc.nextInt();
              indexOfHour-=1;
              if(indexOfHour>=9)
                  System.out.println("No such time slot exists");
              else
              {
                    System.out.println("Enter the day , 0-Monday .............6-Saturday");
                    int indexOfDay=sc.nextInt();
                    if(indexOfDay<=6)
                    {
                        try
                        {
                             if(teacherOccupancy[indexOfTeacher][indexOfDay][indexOfHour]==true)
                                    System.out.println("The specific teacher is occupied");
                             else
                            System.out.println("Teacher is free");
                        }
                        catch(ArrayIndexOutOfBoundsException e)
                        {
                                 System.out.println("No such teacher exists");   
                         }
                        
                    }
                    else
                        System.out.println("No proper Day given");
              }
    }
    
} */
