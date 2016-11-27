/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.controller;

import com.bfa.beans.TimeTableBean;
import static com.bfa.beans.TimeTableBean.timeTableDetails;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author mahesh
 */
public class ExcelWriter {
    public void writeTimeTable(){
        FileOutputStream fOutStream  = null;
        try {
            File testFile  = new File("TimeTable.xlsx");
            XSSFWorkbook myWorkBook = new XSSFWorkbook();
            String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
            String[] times = {"8:55-9:50", "9:50-10:45", "11:15-12:10", "12:10-13:05", "14:00-14:55", "14:55-15:50"};
            Iterator timeTableIterator = timeTableDetails.iterator();
            int rowNum = 1, i=0, cellNum = 0;
            while(timeTableIterator.hasNext()){
                TimeTableBean currTimeTable = (TimeTableBean)timeTableIterator.next();
                XSSFSheet mySheet = myWorkBook.createSheet(currTimeTable.getClassName());
                Row row = mySheet.createRow(0);
                Cell cell = row.createCell(0);
                cell.setCellValue(currTimeTable.getClassName());
                int j = 0;
                TimeTableSlot[][] currSlot = currTimeTable.getTimeTable();
                for(TimeTableSlot[] a: currSlot){
                    row = mySheet.createRow(rowNum++);
                    cell = row.createCell(0);
                    cell.setCellValue(days[j++]);
                    //System.out.println(days[j-1]);
                    Row secondRow = mySheet.createRow(rowNum++);
                    row = mySheet.createRow(rowNum++);
                    for(TimeTableSlot p : a){
                        if(p!=null){
                            int timeSlot = 0;
                            cell = row.createCell(cellNum);
                            Cell secondCell = secondRow.createCell(cellNum++);
                            secondCell.setCellValue(times[timeSlot++]);
                            cell.setCellValue(p.getSubject());
                        }
                        //System.out.println(p.subjectName+"----"+p.teacherName);
                    }
                    cellNum = 0;
                    //System.out.println("\n");
                }
                rowNum = 1;
            }   
            fOutStream = new FileOutputStream(testFile);
            myWorkBook.write(fOutStream);
            System.out.println("Success");
        } catch (Exception ex) {
            Logger.getLogger(ExcelWriter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fOutStream.close();
            } catch (IOException ex) {
                Logger.getLogger(ExcelWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
