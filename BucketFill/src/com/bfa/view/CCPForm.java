/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.view;

import com.bfa.controller.Occupancy;
import com.bfa.model.TeacherSubject;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Bhargav
 */
public class CCPForm extends Application {

    ArrayList<String> ccpTeachers = new ArrayList<>();
    CheckBox [][]slots;
    boolean [][] buffer;
    boolean[] teach;
    String teacherName;
    @Override
    public void start(Stage primaryStage) throws Exception {
        Occupancy.setDetails();
        primaryStage.setTitle("SECTION FORM");
        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color: white");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(15);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("CCP TEACHERS");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 50));
        grid.add(scenetitle, 0, 0, 4, 1);
        Label tList = new Label("SELECT TEACHERS FROM THE LIST:");
        grid.add(tList,0,3);
        ccpTeachers = TeacherSubject.getCcpTeachers();
        teach = new boolean[ccpTeachers.size()];
        ComboBox teacherList = new ComboBox();
        teacherList.setValue("SELECT");
        teacherList.setPrefWidth(100);
        for(int i =0; i<ccpTeachers.size(); i++){
            teacherList.getItems().add(ccpTeachers.get(i));
        }
        
        grid.add(teacherList, 1, 3);
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
        confirm.setOnAction(new EventHandler<ActionEvent> (){
                
                @Override
                public void handle(ActionEvent event){
                    teacherName = teacherList.getValue().toString();
                    if(teacherName.equals("SELECT"))                    
                    {
                     Label label = new Label("SELECT A TEACHER NAME");
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
                    buffer =Occupancy.getOccupancyMatrix(teacherName);
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
                        grid.add(time[i],i+1,7);
                    }
                    for(int i =0;i<6;i++){
                        grid.add(days[i],0,num+i);
                    }
                    slots = new CheckBox[6][6];
                    int i=0,j=0;
                  for( i =0;i<6;i++){
                      for( j =0;j<6;j++){
                          slots[i][j] = new CheckBox();
                          slots[i][j].setPrefWidth(100);
                          slots[i][j].setAlignment(Pos.CENTER_RIGHT);
                          if(buffer[i][j])
                              slots[i][j].setSelected(true);
                          
                          grid.add(slots[i][j],j+1,8+i);
                      }
                  }  
                    
                  Button submitButton = new Button("SUBMIT");
                  grid.add(submitButton,i,10+j);
                          
                submitButton.setOnAction(new EventHandler<ActionEvent> (){
                
                    @Override
                    public void handle(ActionEvent event){
                        int flag=0;
                        for(int i=0;i<6;i++){
                            for(int j=0;j<6;j++){
                                if(slots[i][j].isSelected())
                                    flag++;
                            }
                        }
                        if(flag==0)
                        {
                            Label label = new Label("CHECK ATLEAST ONE OF THE BOXES");
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
                        teach[ccpTeachers.indexOf(teacherName)] = true;    
                        for(int i =0;i<6;i++){
                            for(int j=0;j<6;j++){
                                if(slots[i][j].isSelected())
                                    buffer[i][j] = true;
                                else
                                    buffer[i][j] = false;
                            }
                        }

                        Occupancy.setOccupancyMatrix(teacherName,buffer);
                        int check=0;
                        for(int i =0;i<ccpTeachers.size();i++){
                            if(teach[i])
                                check++;
                        }
                        if(check==ccpTeachers.size())
                                System.out.println("HELLO WORLD");
                        } 
                        
                    }});
                
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
