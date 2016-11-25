/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.view;

import com.bfa.model.Teacher;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Bhargav
 */
public class TeacherForm extends Application {

    String name,designation;
    String[] subjects = new String[2];
    int hours,ID,noOfSubjects,g1;
    TextField subname,subname1,subname2;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setFullScreen(true);
        primaryStage.setTitle("TEACHER FORM");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(15);
        grid.setStyle("-fx-background-color: #2F63A3");
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("TEACHER DETAILS");
        //scenetitle.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 50));
        scenetitle.setFont(Font.font("Quantico", FontWeight.EXTRA_BOLD, 60));
        scenetitle.setFill(Color.web("#ffffff"));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label teacherName = new Label("TEACHER NAME:");
        teacherName.setFont(Font.font("Quantico", FontWeight.EXTRA_BOLD, 20));
        teacherName.setTextFill(Color.web("#ffffff"));
        grid.add(teacherName, 0, 1);

        TextField nameField = new TextField();
        grid.add(nameField, 0, 2);

        Label id = new Label("TEACHER ID:");
        id.setFont(Font.font("Quantico", FontWeight.EXTRA_BOLD, 20));
        id.setTextFill(Color.web("#ffffff"));
        grid.add(id, 0, 3);

        TextField idField = new TextField();
        grid.add(idField, 0, 4);
        Label des = new Label("DESIGNATION:");
        des.setFont(Font.font("Quantico", FontWeight.EXTRA_BOLD, 20));
        des.setTextFill(Color.web("#ffffff"));
        grid.add(des, 0, 5);
        final ComboBox desBox = new ComboBox();
        desBox.getItems().addAll(
                "PROFESSOR",
                "ASSOCIATE PROFESSOR",
                "ASSISTANT PROFESSOR"
        );
        desBox.setValue("NONE");
        grid.add(desBox, 0, 6);
        Label sub = new Label("NUMBER OF SUBJECTS:");
        sub.setFont(Font.font("Quantico", FontWeight.EXTRA_BOLD, 20));
        sub.setTextFill(Color.web("#ffffff"));
        grid.add(sub, 0, 7);
        final ComboBox subBox = new ComboBox();
        subBox.getItems().addAll(
                "1",
                "2"
        );
            subBox.setValue("0");
        grid.add(subBox, 0, 8);
        Button btn1 = new Button();
        btn1.setText("CONFIRM");
        btn1.setFont(Font.font("Quantico", FontWeight.EXTRA_BOLD, 15));
        //btn1.setTextFill(Color.web("#ffffff"));
        //TextField noofSub = new TextField();
        //grid.add(noofSub, 0, 8);
        //String s1=noofSub.getText();
        //System.out.println(s1);
        //int nos=Integer.parseInt(s1);
        grid.add(btn1, 1, 8);
        btn1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //btn1.setVisible(false);
                noOfSubjects = Integer.parseInt((String) subBox.getValue());
                if(noOfSubjects == 1){
                    Label subno = new Label("SUBJECT :");
                    subno.setFont(Font.font("Quantico", FontWeight.EXTRA_BOLD, 20));
                    subno.setTextFill(Color.web("#ffffff"));
                    grid.add(subno, 0, 9);
                    subname = new TextField();
                    grid.add(subname, 1, 9);
                }
                else if(noOfSubjects == 2){
                     Label subno1 = new Label("SUBJECT 1 :");
                     subno1.setFont(Font.font("Quantico", FontWeight.EXTRA_BOLD, 20));
                     subno1.setTextFill(Color.web("#ffffff"));
                    grid.add(subno1, 0, 9);
                    subname1 = new TextField();
                    grid.add(subname1, 1, 9);
                     Label subno2 = new Label("SUBJECT 2 :");
                     subno2.setFont(Font.font("Quantico", FontWeight.EXTRA_BOLD, 20));
                     subno2.setTextFill(Color.web("#ffffff"));
                    grid.add(subno2, 0, 10);
                    subname2 = new TextField();
                    grid.add(subname2, 1, 10);
                }
                else{
                    Label nv1 = new Label("ENTER A VALID NUMBER");
                    nv1.setFont(Font.font("Quantico", FontWeight.EXTRA_BOLD, 20));
                    nv1.setTextFill(Color.web("#ffffff"));
                    grid.add(nv1,0,20);
                    Timer timer = new Timer();
                 TimerTask delayedThreadStartTask = new TimerTask() {
                @Override
                public void run() {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            nv1.setVisible(false);     
                        }
                        }).start();
                    }
                };
                timer.schedule(delayedThreadStartTask,4000); 
                }
               
            }
        });

        Button btn = new Button("SUBMIT");
        btn.setFont(Font.font("Quantico", FontWeight.EXTRA_BOLD, 15));
        //des.setTextFill(Color.web("#ffffff"));
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.CENTER);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 0, 15);
        g1=16;
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                boolean t1=true,t2=true,t3=true,t4=true,t5=true;
                name = nameField.getText();
                
                
                Label label = new Label("ENTER A VALID NAME");
                label.setFont(Font.font("Quantico", FontWeight.EXTRA_BOLD, 20));
                label.setTextFill(Color.web("#ffffff"));
                Label label2 = new Label("SELECT A DESIGNATION");
                label2.setFont(Font.font("Quantico", FontWeight.EXTRA_BOLD, 20));
                label2.setTextFill(Color.web("#ffffff"));
                Label label1 = new Label("ENTER A VALID TEACHER ID");
                label1.setFont(Font.font("Quantico", FontWeight.EXTRA_BOLD, 20));
                label1.setTextFill(Color.web("#ffffff"));
                 if (isValidName(name)||name.equals("")) {
                    grid.add(label,0,17);
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
                timer.schedule(delayedThreadStartTask,4000); 
                    
                    t1=false;
                }
                
                
                if (isValidID(idField.getText())||idField.getText().equals("")) {
                   grid.add(label1,0,18);
                    Timer timer = new Timer();
                 TimerTask delayedThreadStartTask = new TimerTask() {
                @Override
                public void run() {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            label1.setVisible(false);     
                        }
                        }).start();
                    }
                };
                timer.schedule(delayedThreadStartTask,4000); 
                 
                   t2=false; 
                }
                designation = desBox.getValue().toString();
                if(designation.equals("NONE")){
                    grid.add(label2,0,19);
                    Timer timer = new Timer();
                 TimerTask delayedThreadStartTask = new TimerTask() {
                @Override
                public void run() {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            label2.setVisible(false);     
                        }
                        }).start();
                    }
                };
                timer.schedule(delayedThreadStartTask,4000); 
                t3=false;
                }
                else{
                if(designation.equals("ASSISTANT PROFESSOR"))
                    hours = 16;
                else if(designation.equals("ASSOCIATE PROFESSOR"))
                    hours = 12;
                else
                    hours = 8;
                }
                Label nv = new Label("SELECT A VALID NUMBER");
                nv.setFont(Font.font("Quantico", FontWeight.EXTRA_BOLD, 20));
                nv.setTextFill(Color.web("#ffffff"));
                if(!subBox.getValue().toString().equals("1")||!subBox.getValue().toString().equals("2")){
                    grid.add(nv,0,20);
                    Timer timer = new Timer();
                 TimerTask delayedThreadStartTask = new TimerTask() {
                @Override
                public void run() {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            nv.setVisible(false);     
                        }
                        }).start();
                    }
                };
                timer.schedule(delayedThreadStartTask,4000); 
                t4=false;
                }
                else{
                    noOfSubjects = Integer.parseInt(subBox.getValue().toString());
                if(noOfSubjects == 1){
                    subjects[0] = subname.getText();
                    if(isValidName(subjects[0])||subjects[0].equals("")){
                        Label notV = new Label("ENTER A VALID SUBJECT NAME");
                       notV.setFont(Font.font("Quantico", FontWeight.EXTRA_BOLD, 20));
                notV.setTextFill(Color.web("#ffffff"));
                         grid.add(notV,0,21);
                         Timer timer = new Timer();
                 TimerTask delayedThreadStartTask = new TimerTask() {
                @Override
                public void run() {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            notV.setVisible(false);     
                        }
                        }).start();
                    }
                };
                timer.schedule(delayedThreadStartTask,4000); 
                t5=false;
                    }
                }
                    
                if(noOfSubjects==2){
                    
                    subjects[0] = subname1.getText();
                    subjects[1] = subname2.getText();
                     if(isValidName(subjects[0])||isValidName(subjects[1])||subjects[0].equals("")||subjects[1].equals("")){
                        Label notV = new Label("ENTER A VALID SUBJECT NAME");
                        notV.setFont(Font.font("Quantico", FontWeight.EXTRA_BOLD, 20));
                        notV.setTextFill(Color.web("#ffffff"));
                         grid.add(notV,0,22);
                         Timer timer = new Timer();
                 TimerTask delayedThreadStartTask = new TimerTask() {
                @Override
                public void run() {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            notV.setVisible(false);     
                        }
                        }).start();
                    }
                };
                timer.schedule(delayedThreadStartTask,4000); 
                t5=false;
                    }
                    
                }
                }
                
                if(t1&&t2&&t3&&t4&&t5){
                    ID = Integer.parseInt(idField.getText());
                    //System.out.println(" Booyah!!!");
             
                    Button next = new Button("NEXT TEACHER");
                    Button done = new Button("NEXT FORM");
                    grid.add(next,0,20);
                    grid.add(done,1,20);
                     next.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                    //primaryStage.close();
                    //Teacher.insertDetails(name, ID, hours, subjects);    
                    start(primaryStage); 
                    }});
                     done.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                    SectionForm sf = new SectionForm();
                    sf.start(primaryStage);
                    //Teacher.insertDetails(name, ID, hours, subjects);    
                    //start(primaryStage); 
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
    
    public boolean isValidName(String n){
        Pattern p1 = Pattern.compile("[^a-z ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p1.matcher(n);
        boolean b = m.find();
        return b;
    } 
    public boolean isValidID(String n){
       Pattern p2 = Pattern.compile("[^0-9 ]", Pattern.CASE_INSENSITIVE);
                Matcher m2 = p2.matcher(n);
                boolean b2 = m2.find();
        return b2;
    } 

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {
        launch(args);
    }

}
