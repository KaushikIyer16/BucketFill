/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.view;

import com.bfa.beans.TimeTableBean;
import com.bfa.controller.Allot;
import com.bfa.controller.Occupancy;
import com.bfa.controller.TimeTableSlot;
import com.bfa.model.Section;
import com.bfa.model.TeacherSubject;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Bhargav
 */
public class TimeTableDisplay extends Application {
    int yr=2;
    String tName;
    TimeTableSlot[][] finalTimeTable;
    @Override
    public void start(Stage primaryStage) throws Exception {
        
   //Occupancy.setDetails();
        primaryStage.setTitle("TIMETABLE DISPLAY");
        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color: #2F63A3");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(15);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle1 = new Text("TIMETABLE");
        scenetitle1.setFont(Font.font("Quantico", FontWeight.EXTRA_BOLD, 60));
        scenetitle1.setFill(Color.web("#ffffff"));
        Text scenetitle = new Text("YEAR "+yr);
        scenetitle.setFont(Font.font("Quantico", FontWeight.EXTRA_BOLD, 40));
        scenetitle.setFill(Color.web("#ffffff"));
        grid.add(scenetitle1,0,0,4,1);
        grid.add(scenetitle, 0, 2, 4, 1);
        Label taList = new Label("SELECT SECTION FROM THE LIST:");
        taList.setFont(Font.font("Quantico", FontWeight.EXTRA_BOLD, 20));
        taList.setTextFill(Color.web("#ffffff"));
        grid.add(taList,0,3);
        Label [][][]slots;
        
         
        Button nextYear = new Button("YEAR "+Integer.toString(1+yr));
        grid.add(nextYear,10,20);
        nextYear.setVisible(false);
        
         ArrayList<String> sectionList = new ArrayList<>();
         ArrayList<Section> section = new ArrayList<>();
        section = Section.getSectionByYear(yr);
        System.out.println(section.size());
        Allot alt = new Allot();
        alt.getAllDetails();
        for(int i =0; i<section.size(); i++){
            if(!sectionList.contains(section.get(i).getName())&&!Character.isDigit(section.get(i).getName().charAt(0)))
                sectionList.add(section.get(i).getName());
        }
       
        ComboBox tList = new ComboBox();
        tList.setValue("SELECT");
        tList.setPrefWidth(100);
        for(int i =0; i<sectionList.size(); i++){
            
            tList.getItems().add(sectionList.get(i));
        }
        slots = new Label[sectionList.size()][6][6];
        grid.add(tList, 1, 3);
        Label nothing = new Label("");
        nothing.setPrefWidth(100);
        grid.add(nothing,2,3);
        Button confirm = new Button("CONFIRM");
        confirm.setPrefWidth(100);
        grid.add(confirm, 3,3);
         Label nothing1 = new Label("");
        nothing1.setPrefWidth(100);
        grid.add(nothing1,4,3);
         Label nothing2 = new Label("");
        nothing2.setPrefWidth(100);
        grid.add(nothing2,5,3);
         Label nothing3 = new Label("");
        nothing3.setPrefWidth(100);
        grid.add(nothing3,6,3);
        int num = 8;
        for(int gh = 0;gh<sectionList.size();gh++){
                        
                            for(int i1=0;i1<6;i1++){
                                for(int j1=0;j1<6;j1++){
                                    slots[gh][i1][j1] = new Label(" ");
                                    grid.add(slots[gh][i1][j1],j1+1,8+i1);
                                }
                                    
                            }
                        
                            
                    }
        confirm.setOnAction(new EventHandler<ActionEvent> (){
                
                @Override
                public void handle(ActionEvent event){
                    tName = tList.getValue().toString();
                    if(tName.equals("SELECT"))                    
                    {
                     Label label = new Label("SELECT A SECTION NAME");
                     grid.add(label,0,20);
                     Timer timer = new Timer();
                     TimerTask delayedThreadStartTask = new TimerTask() {
                     @Override
                     public void run() {
                     new Thread(new Runnable() {
                        @Override
                        public void run() {
                            label.setVisible(false);     
                        }
                        }).start();
                    }
                };
                timer.schedule(delayedThreadStartTask,6000);             
            }
                    
                    else{          
                    finalTimeTable = TimeTableBean.getTimeTableForSection(Integer.toString(yr)+tName);
                    Label []time =new Label[6];
                    time[0] = new Label("8:55 - 9:50");
                    time[1]= new Label("9:50 - 10:45");
                    time[2]= new Label("11:15 - 12:10");
                    time[3]= new Label("12:10 - 1:05");
                    time[4]= new Label("2:00 - 2:55");
                    time[5]= new Label("2:55 - 3:50");
                    Label []days = new Label[6];
                    days[0]= new Label("MONDAY");
                    days[1]= new Label("TUESDAY");
                    days[2]= new Label("WEDNESDAY");
                    days[3]= new Label("THURSDAY");
                    days[4]= new Label("FRIDAY");
                    days[5]= new Label("SATURDAY");
                    for(int i =0;i<6;i++){
                        time[i].setTextFill(Color.web("#ffffff"));
                        time[i].setFont(Font.font("Quantico", FontWeight.EXTRA_BOLD, 20));
                        grid.add(time[i],i+1,7);
                    }
                    for(int i =0;i<6;i++){
                        days[i].setTextFill(Color.web("#ffffff"));
                        days[i].setFont(Font.font("Quantico", FontWeight.EXTRA_BOLD, 20));
                        grid.add(days[i],0,num+i);
                    }
                    int sectionChar = tName.charAt(0)-'A';
                    int i=0,j=0;
                    for(int gh = 0;gh<sectionList.size();gh++){
                        
                            for(int i1=0;i1<6;i1++){
                                for(int j1=0;j1<6;j1++){
                                    slots[gh][i1][j1].setVisible(false);
                                }
                                    
                            }
                        
                            
                    }
                  for( i =0;i<6;i++){
                      for( j =0;j<6;j++){
                          //slots[i][j] = new Label(finalTimeTable[i][j].getSubject());
                          
                              slots[sectionChar][i][j].setText(finalTimeTable[i][j].getSubject());
                              slots[sectionChar][i][j].setTextFill(Color.web("#ffffff"));
                              slots[sectionChar][i][j].setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 15));
                              slots[sectionChar][i][j].setPrefWidth(120);
                              slots[sectionChar][i][j].setVisible(true);
                              
                         
                      }
                  }
                  if(yr<3)
                      nextYear.setVisible(true);
                    } 
                    
                }});
                 nextYear.setOnAction(new EventHandler<ActionEvent> (){
                
                @Override
                public void handle(ActionEvent event){
                    yr++;
                    try {
                        start(primaryStage);
                    } catch (Exception ex) {
                        Logger.getLogger(TimeTableDisplay.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }});
        
        
     
       
         final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 16);
        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    

    
}
