/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.controller;
import com.bfa.beans.Debug;
import com.bfa.beans.TeacherOccupancy;
import com.bfa.model.Subject;
import com.bfa.model.Teacher;
import com.bfa.model.TeacherSubject;
import java.awt.BorderLayout;
import java.util.*;

/**
 *
 * @author Moin
 */
public class Occupancy {
    
        static int NUMBER_OF_TEACHERS , NUMBER_OF_DAYS = 6, NUMBER_OF_HOURS = 6;
        static boolean[][][] teacherOccupancy;
        static ArrayList<Teacher> teacherDetails= new ArrayList<Teacher>();
        static ArrayList<String> teacherNames= new ArrayList<String>();
        
        static HashMap<String,TeacherOccupancy> teacherOccupancyDetails = new HashMap<>();
        public static void setDetails(){     
        
        teacherDetails = Teacher.getAllDetails();
        Occupancy.NUMBER_OF_TEACHERS = teacherDetails.size();
        teacherOccupancy = new boolean[NUMBER_OF_TEACHERS][NUMBER_OF_DAYS][NUMBER_OF_HOURS];
        for(int i =0;i<teacherDetails.size();i++){
            teacherNames.add(teacherDetails.get(i).getName());
        }
        for(int i=0;i<NUMBER_OF_TEACHERS;i++){
            for(int j =0 ;j<NUMBER_OF_DAYS;j++){
                for(int k =0 ;k<NUMBER_OF_DAYS;k++){
                    teacherOccupancy[i][j][k]=false;
                }
            }
        }
        
        
              teacherOccupancy[20][1][4]=true;
              teacherOccupancy[20][2][1]=true;
              teacherOccupancy[20][3][3]=true;
              
              teacherOccupancy[19][3][4]=true;
              teacherOccupancy[19][4][2]=true;
              teacherOccupancy[19][5][5]=true;
              
              teacherOccupancy[21][1][5]=true;
              teacherOccupancy[21][2][5]=true;
              teacherOccupancy[21][3][3]=true;
              
        
        //List<TeacherOccupation> teacherObj= new ArrayList<TeacherOccupation>();
        //TeacherOccupation obj = new TeacherOccupancy();
        //SetterClass obj1=new SetterClass();
        //teacherObj=obj1.detailsSetter(teacherObj);
       /* for(int i=0;i<teacherObj.size();i++)
        {
                   obj=teacherObj.get(i);
		   int day=obj.get_dayOfTheWeek();
		   int hour=obj.get_hourOfTheDay();
		          if(!(teacherId.contains(obj.get_TeacherId())))
		        	  teacherId.add(obj.get_TeacherId());
	           teacherOccupancy[teacherId.indexOf(obj.get_TeacherId())][day][hour]=true;
	
           
        }
        	   System.out.println(teacherId);*/
        	  // System.out.println(teacherOccupancy[1][3][3]);
                  //Input any proper value to get output
    }
    public static boolean[][] getOccupancyMatrix(String name){
        System.out.println(teacherNames.indexOf(name));
        return teacherOccupancy[teacherNames.indexOf(name)];
    }
    public static void setOccupancyMatrix(String name, boolean[][] occupancy){
        teacherOccupancy[teacherNames.indexOf(name)]=occupancy; 
        /*for(int i=0;i<NUMBER_OF_TEACHERS;i++){
            System.out.println(teacherNames.get(i));
            for(int j =0 ;j<NUMBER_OF_DAYS;j++){
                for(int k =0 ;k<NUMBER_OF_DAYS;k++){
                    System.out.print(teacherOccupancy[i][j][k]+"   ");
                }
                System.out.println();
            }
            System.out.println();
        }*/  
    }

    public static void initTeacherOccupancyList() {
        
        // populating the teacher occupancy list with the bean type to keep related data together
        
        // remove this line in the final output
        teacherDetails = Teacher.getAllDetails();
        Occupancy.NUMBER_OF_TEACHERS = teacherDetails.size();
        teacherOccupancy = new boolean[NUMBER_OF_TEACHERS][NUMBER_OF_DAYS][NUMBER_OF_HOURS];
        
        for(int i=0;i<NUMBER_OF_TEACHERS;i++){
            for(int j =0 ;j<NUMBER_OF_DAYS;j++){
                for(int k =0 ;k<NUMBER_OF_DAYS;k++){
                    teacherOccupancy[i][j][k]=false;
                }
            }
        }
        
        
              teacherOccupancy[20][1][4]=true;
              teacherOccupancy[20][2][1]=true;
              teacherOccupancy[20][3][3]=true;
              
              teacherOccupancy[19][3][4]=true;
              teacherOccupancy[19][4][2]=true;
              teacherOccupancy[19][5][5]=true;
              
              teacherOccupancy[21][1][5]=true;
              teacherOccupancy[21][2][5]=true;
              teacherOccupancy[21][3][3]=true;
        
        
        for (int i = 0; i < teacherDetails.size(); i++) {
            teacherOccupancyDetails.put(teacherDetails.get(i).getName(), new TeacherOccupancy(teacherDetails.get(i).getName(), teacherOccupancy[i]));
        }
        
        // i am clearing the redundant list from the memory
        teacherNames = null;
        teacherDetails = null;
        teacherOccupancy = null;
    }
    
    @Debug
    public static void printTeacherOccupancyList(){
//        for(TeacherOccupancy teacher : teacherOccupancyDetails){
//            System.out.println("Teacher Name: "+teacher.getTeacherName());
//            
//            for(boolean[] row: teacher.getOccupancy() ){
//                for(boolean val: row){
//                    System.out.print(val);
//                }
//                System.out.println("");
//            }
//        }
    }

    public static void updateOccupancy(int year, String section, int hour, Subject subject) {
        String teacherName = Teacher.getTeacherBySectionAndSubject(year+section, subject);
        TeacherOccupancy currOccupancy = Occupancy.teacherOccupancyDetails.get(teacherName);
        
        try {
            if (Graph.getLtps() == 0) {
                currOccupancy.getOccupancy()[Allot.day][hour-1] = true;
            }else{
                currOccupancy.getOccupancy()[Allot.day][hour-1] = true;
                currOccupancy.getOccupancy()[Allot.day][hour] = true;
            }
            
        } catch (Exception e) {
        }
    }
    
    public static void updateOccupancyForElective(int year,int day,int hour,int ltps){
        ArrayList<Integer> ElectiveTeacherIds = TeacherSubject.getElectiveTeacherIdFromYear(year);
        
        for (int i = 0; i< ElectiveTeacherIds.size(); i++) {
            String teacherName = Teacher.getNameById(ElectiveTeacherIds.get(i));
            TeacherOccupancy currOccupancy = Occupancy.teacherOccupancyDetails.get(teacherName);
        
            try {
                if (ltps == 0) {
                    currOccupancy.getOccupancy()[day][hour] = true;
                }else{
                    currOccupancy.getOccupancy()[day][hour] = true;
                    currOccupancy.getOccupancy()[day][hour+1] = true;
                }

            } catch (Exception e) {
            }
        }
    }
    
    public static boolean areElectiveTeachersOccupied(int year,int day,int hour,int ltps){
        boolean isOccupied = false;
        ArrayList<Integer> ElectiveTeacherIds = TeacherSubject.getElectiveTeacherIdFromYear(year);
        
        for (int i = 0; i< ElectiveTeacherIds.size(); i++) {
            String teacherName = Teacher.getNameById(ElectiveTeacherIds.get(i));
            TeacherOccupancy currOccupancy = Occupancy.teacherOccupancyDetails.get(teacherName);
            
            isOccupied = isOccupied || currOccupancy.getOccupancy()[day][hour];
        }
        
        return isOccupied;
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
