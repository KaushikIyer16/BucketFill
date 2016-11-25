/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.view;

import com.bfa.model.Subject;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Bhargav
 */
public class SubjectForm extends Application {
    
    TextField l[],t[],p[],s[], courseCode[],subjName[];
    ComboBox elect[];
    int set=2;
    int l1[], t1[], p1[], ss[],g1=0;
    boolean flag1=true,flag2=true,flag3=true,flag4=true,flag5=true,flag6=true;
    String electiveSubject[];
    String cc[], sn[];
                
    //ArrayList<String> courseCode = new ArrayList<>();
    //ArrayList<String> subjName = new ArrayList<>();
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("SECTION FORM");
        GridPane grid = new GridPane();
        grid.setId("pane");
        grid.setStyle("-fx-background-color: #2F63A3");
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(15);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("YEAR "+set);
        Text sceneMainTitle = new Text("SUBJECT FORM");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 40));
        sceneMainTitle.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 40));
        
        grid.add(scenetitle, 0, 9, 4, 1);
        grid.add(sceneMainTitle, 0, 3, 4, 1);

        Label subjectLabel = new Label("ENTER THE NUMBER OF SUBJECTS:");
        grid.add(subjectLabel, 0, 10);

        TextField noOfSubjects = new TextField();
        noOfSubjects.setPrefWidth(80);
        grid.add(noOfSubjects, 1, 10);
        Button setButton = new Button();
        //setButton.setStyle("-fx-background-color: aquamarine");
        setButton.setText("CONFIRM");
        grid.add(setButton,2,10);
        Label subjectNames = new Label("SUBJECTS:");
        setButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //btn1.setVisible(false);
                String []args = null;
                System.out.println(noOfSubjects.getText());
                if(noOfSubjects.getText().length()==0||!Character.isDigit(noOfSubjects.getText().charAt(0))){
                    Label label = new Label("ENTER A VALID NUMBER");
                     grid.add(label,0,30);
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
                timer.schedule(delayedThreadStartTask,3000);  
                }
                else{ 
                setButton.setVisible(false);
                grid.add(subjectNames,0,11);
                Label cCode = new Label("COURSE CODE");
                cCode.setPrefWidth(140);
                grid.add(cCode, 1, 12);
                Label subName = new Label("SUBJECT");
                subName.setPrefWidth(120);
                grid.add(subName, 2, 12);
                Label lecture = new Label("L");
                lecture.setPrefWidth(80);
                grid.add(lecture,3,12);
                Label theory = new Label("T");
                theory.setPrefWidth(80);
                grid.add(theory,4,12);
                Label practical = new Label("P");
                practical.setPrefWidth(80);
                grid.add(practical,5,12);
                Label self = new Label("S");
                self.setPrefWidth(80);
                grid.add(self,6,12);
                Label elective = new Label("ELECTIVE");
                grid.add(elective, 7, 12);
                int number = Integer.parseInt(noOfSubjects.getText().toString());
                l = new TextField[number];
                t = new TextField[number];
                p = new TextField[number];
                s = new TextField[number];
                l1= new int[number];
                t1= new int[number];
                p1= new int[number];
                ss= new int[number];
                electiveSubject= new String[number];
                cc= new String[number];
                sn= new String[number];
                
                elect = new ComboBox[number];
                courseCode = new TextField[number];
                subjName = new TextField[number];
                int i;
                for( i = 0; i < number; i++ ){
                    l[i] = new TextField();
                    l[i].setPrefWidth(80);
                    t[i] = new TextField();
                    t[i].setPrefWidth(80);
                    p[i] = new TextField();
                    p[i].setPrefWidth(80);
                    s[i] = new TextField();
                    s[i].setPrefWidth(80);
                    courseCode[i] = new TextField();
                    courseCode[i].setPrefWidth(140);
                    subjName[i] = new TextField();
                    subjName[i].setPrefWidth(120);
                    elect[i] = new ComboBox();
                    elect[i].getItems().addAll("YES", "NO");
                    elect[i].setValue("   ");
                    grid.add(courseCode[i],1,i+7+6);
                    grid.add(subjName[i],2,i+7+6);
                    grid.add(l[i],3,i+7+6);
                    grid.add(t[i],4,i+7+6);
                    grid.add(p[i],5,i+7+6);
                    grid.add(s[i],6,i+7+6);
                    grid.add(elect[i], 7, i+7+6);
                }
                Button submitButton = new Button();
                submitButton.setText("SUBMIT");
                grid.add(submitButton, 7, i+10+6);
                g1=i+11+6;
                 submitButton.setOnAction(new EventHandler<ActionEvent>() {
                     
                    @Override
                    public void handle(ActionEvent event) {
                    flag1=true;flag2=true;flag3=true;flag4=true;    
                    for(int i = 0; i<number;i++){
                        if(subjName[i].getText().equals("")||isValidName(subjName[i].getText())&&flag2){
                            Label nvn = new Label("ENTER A VALID SUBJECT NAME");
                             grid.add(nvn,0,++g1);
                                Timer timer = new Timer();
                                TimerTask delayedThreadStartTask = new TimerTask() {
                                 @Override
                                 public void run() {
                                new Thread(new Runnable() {
                                    @Override
                                 public void run() {
                                        nvn.setVisible(false);     
                                    }
                                    }).start();
                                }
                            };
                            timer.schedule(delayedThreadStartTask,3000);  
                            flag2 = false;
                            
                        }
                        if(courseCode[i].getText().equalsIgnoreCase("")||!isValidCourseCode(courseCode[i].getText())&&flag1){
                            
                             Label nvn1 = new Label("ENTER A VALID COURSE CODE");
                             grid.add(nvn1,0,++g1);
                            Timer timer = new Timer();
                            TimerTask delayedThreadStartTask = new TimerTask() {
                            @Override
                            public void run() {
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        nvn1.setVisible(false);     
                                    }
                                    }).start();
                                }
                            };
                            timer.schedule(delayedThreadStartTask,3000);  
                            flag1 = false;
                            
                        }
                       if(ltpsMatch(i)&&flag3){
                           Label nvn2 = new Label("ENTER VALID LTPS VALUES");
                             grid.add(nvn2,0,++g1);
                            Timer timer = new Timer();
                            TimerTask delayedThreadStartTask = new TimerTask() {
                            @Override
                            public void run() {
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        nvn2.setVisible(false);     
                                    }
                                    }).start();
                                }
                            };
                            timer.schedule(delayedThreadStartTask,3000);  
                            flag3 = false;
                       }
                       if(elect[i].getValue().toString().equals("   ")&&flag4){
                           Label nvn3 = new Label("ENTER YES/NO FOR ELECTIVE");
                             grid.add(nvn3,0,++g1);
                            Timer timer = new Timer();
                            TimerTask delayedThreadStartTask = new TimerTask() {
                            @Override
                            public void run() {
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        nvn3.setVisible(false);     
                                    }
                                    }).start();
                                }
                            };
                            timer.schedule(delayedThreadStartTask,3000);  
                            flag4 = false;
                       }
                        
                    }
                        if(flag1&&flag2&&flag3&&flag4){
                        for(int i = 0; i<number;i++){    
                        System.out.println(courseCode[i].getText());
                     l1[i]=Integer.parseInt(l[i].getText());
                     t1[i]=Integer.parseInt(t[i].getText());
                     p1[i]=Integer.parseInt(p[i].getText());
                     ss[i]=Integer.parseInt(s[i].getText());
                     electiveSubject[i]=elect[i].getValue().toString();
                     cc[i]=courseCode[i].getText().toUpperCase();
                     sn[i]=subjName[i].getText();
                    }    
                    
               //Subject.insertDetails(cc, sn, l1, t1, p1, ss, electiveSubject);
                System.out.println("BOOYAH! IT'S DONE!");
                set++;
                if(set<5){
                    start(primaryStage);
                }
                else{    
                TeacherForm tf = new TeacherForm();
                tf.start(primaryStage);
                        }
                    }
                }}); 
        
                }
             

       }}); 
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 16);
        Scene scene = new Scene(grid,Color.BLACK);
        scene.setFill(Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
       // scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());  
        primaryStage.show();
    }
     public boolean isValidCourseCode(String n){
        Pattern p1 = Pattern.compile("[0-9]{2}[a-z]{2}[0-9][a-z]{5}", Pattern.CASE_INSENSITIVE);
        Matcher m = p1.matcher(n);
        boolean b = m.find();
        return b;
    } 
    public boolean isValidName(String n){
       Pattern p2 = Pattern.compile("[^a-z ]", Pattern.CASE_INSENSITIVE);
                Matcher m2 = p2.matcher(n);
                boolean b2 = m2.find();
        return b2;
    }
    public boolean isValidNumber(String n){
        Pattern p2 = Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE);
                Matcher m2 = p2.matcher(n);
                boolean b2 = m2.find();
        return b2;
    }
    public boolean ltpsMatch(int n){
        if(l[n].getText().equals("")||t[n].getText().equals("")||p[n].getText().equals("")||s[n].getText().equals(""))
            return true;
        if(!isValidNumber(l[n].getText())||!isValidNumber(t[n].getText())||!isValidNumber(p[n].getText())||!isValidNumber(s[n].getText()))
            return true;
        return false;
    }

        

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
