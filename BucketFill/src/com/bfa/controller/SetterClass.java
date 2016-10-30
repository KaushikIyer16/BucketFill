/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.controller;
import com.bfa.beans.TeacherOccupation;
import java.util.*;

/**
 *
 * @author Moin
 */
public class SetterClass {
    public List<TeacherOccupation> detailsSetter(List<TeacherOccupation> teacherobj )
	{
		TeacherOccupation obj=new TeacherOccupation();
		Scanner sc=new Scanner(System.in);
		for(int i=0;i<3;i++)
		{
			obj=new TeacherOccupation();
			teacherobj.add(obj);
			System.out.println("Enter id , day , hour");
			String id=sc.next();
			int day=sc.nextInt();
			int hour=sc.nextInt();
			obj.set_dayOfTheWeek(day);
			obj.set_teacherId(id);
			obj.set_hourOfTheDay(hour);
		}
		//System.out.println(teacherobj.size());
		//for(int i = 0;i<3;i++)
		//{ 
		//	obj=teacherobj.get(i);
		//	System.out.println(obj.get_TeacherId());
		//}
		return teacherobj;
	}
}
    