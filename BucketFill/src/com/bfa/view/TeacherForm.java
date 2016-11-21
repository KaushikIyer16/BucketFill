/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.view;

import com.bfa.model.Teacher;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Bhargav
 */
public class TeacherForm extends Application {

    String name,designation;
    String[] subjects = new String[2];
    int hours,ID,noOfSubjects;
    TextField subname,subname1,subname2;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("TEACHER FORM");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(15);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("ENTER DETAILS FOR TEACHER");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label teacherName = new Label("TEACHER NAME:");
        grid.add(teacherName, 0, 1);

        TextField nameField = new TextField();
        grid.add(nameField, 0, 2);

        Label id = new Label("TEACHER ID:");
        grid.add(id, 0, 3);

        TextField idField = new TextField();
        grid.add(idField, 0, 4);
        Label des = new Label("DESIGNATION:");
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
        grid.add(sub, 0, 7);
        final ComboBox subBox = new ComboBox();
        subBox.getItems().addAll(
                "1",
                "2"
        );
            subBox.setValue("0");
        grid.add(subBox, 0, 8);
        Button btn1 = new Button();
        btn1.setText("Confirm");
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
                    grid.add(subno, 0, 9);
                    subname = new TextField();
                    grid.add(subname, 1, 9);
                }
                else{
                     Label subno1 = new Label("SUBJECT 1 :");
                    grid.add(subno1, 0, 9);
                    subname1 = new TextField();
                    grid.add(subname1, 1, 9);
                     Label subno2 = new Label("SUBJECT 2 :");
                    grid.add(subno2, 0, 10);
                    subname2 = new TextField();
                    grid.add(subname2, 1, 10);
                }
               
            }
        });

        Button btn = new Button("SUBMIT");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.CENTER);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 0, 15);
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
       
                name = nameField.getText();
                ID = Integer.parseInt(idField.getText());
                designation = desBox.getValue().toString();
                if(designation.equals("ASSISTANT PROFESSOR"))
                    hours = 16;
                else if(designation.equals("ASSOCIATE PROFESSOR"))
                    hours = 12;
                else
                    hours = 8;
                    
                if(noOfSubjects == 1)
                    subjects[0] = subname.getText();
                else{
                    subjects[0] = subname1.getText();
                    subjects[1] = subname2.getText();
                }
                    
                boolean flag1 = true;
                boolean flag2 = true;
                boolean flag3 = true;
                boolean flag4 = true;
                boolean flag5 = true;
                Pattern p = Pattern.compile("[^a-z ]", Pattern.CASE_INSENSITIVE);
                Matcher m = p.matcher(nameField.getText().toString());
                boolean b = m.find();

                if (b) {
                    flag1 = false;
                    nameField.setText("");
                }
                p = Pattern.compile("[^0-9 ]", Pattern.CASE_INSENSITIVE);
                m = p.matcher(idField.getText().toString());
                b = m.find();
                if (b) {
                    flag2 = false;
                    idField.setText("");
                }
                if (desBox.getValue().toString().equalsIgnoreCase("NONE")) {
                    flag3 = false;
                }
                if (subBox.getValue().toString().equalsIgnoreCase("0")) {
                    flag4 = false;

                }
             Teacher.insertDetails(name, ID, hours, subjects);
             
             primaryStage.close();   
            }});
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 16);
        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
