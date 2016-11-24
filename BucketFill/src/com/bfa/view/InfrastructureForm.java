/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.view;

import com.bfa.controller.Allot;
import com.bfa.model.Lab;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
public class InfrastructureForm extends Application {
    int i;
    TextField labName[];
        TextField className[];
        Label labl ;
        ArrayList<String> labNames = new ArrayList<>();
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("INFRASTRUCTURE FORM");
        GridPane grid = new GridPane();
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER_LEFT);
        grid.setAlignment(Pos.TOP_CENTER);
        gp.add(new Label("enter all the fields"),0,22);
        grid.setHgap(10);
        grid.setVgap(15);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("INFRASTRUCTURE DETAILS");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 50));
        grid.add(scenetitle, 0, 0, 4, 1);
        
        Label id = new Label("NUMBER OF CLASSROOMS:");
        grid.add(id, 0, 3);
        TextField numberOfClasses = new TextField();
        grid.add(numberOfClasses, 1, 3);
       

        Label labs = new Label("NUMBER OF LABS:");
        grid.add(labs, 0, 5);

        TextField numberOfLabs = new TextField();
        grid.add(numberOfLabs, 1, 5);
        Label inf1 = new Label("enter all the fields");
        Label inf2 = new Label("enter all the fields");
        Button infSubmit = new Button();
        infSubmit.setText("CONFIRM");
        grid.add(infSubmit, 0, 8);
        Label nL = new Label("enter valid values. (0-20)");
        infSubmit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //btn1.setVisible(false);
                if(isValid(numberOfLabs.getText())||isValid(numberOfLabs.getText())){ 
                    numberOfLabs.setText("");
                    numberOfClasses.setText("");
                    grid.add(nL,0,22);
                    return;
                }
                infSubmit.setVisible(false);
                nL.setVisible(false);
                int noOfLabs = Integer.parseInt(numberOfLabs.getText().toString());
                int noOfClassrooms = Integer.parseInt(numberOfClasses.getText().toString());
                Allot.NUMBER_OF_ROOMS = noOfClassrooms;
                Allot.NUMBER_OF_LABS = noOfLabs;
                Label classes = new Label("ENTER THE CLASSROOM NAMES");
                Label labs = new Label("ENTER THE LAB NAMES");
                labName = new TextField[noOfLabs];
                className = new TextField[noOfClassrooms];
                grid.add(classes, 0, 10);
                for (i = 1; i <= noOfClassrooms; i++) {
                    Label classNo = new Label("CLASSROOM " + i + ":");
                    grid.add(classNo, 0, 10+i);
                    className[i-1] = new TextField();
                    grid.add(className[i-1], 1, 10+i);
                   
                }
                i+=10;
                int sum=0;
                System.out.println(i);
                grid.add(labs, 3, 10);
                 for (int j = 1; j <= noOfLabs; j++) {
                    Label labNo = new Label("LAB " + j + ":");
                    grid.add(labNo, 2, 10+j);
                    labName[j-1] = new TextField();
                    grid.add(labName[j-1], 3, 10+j);
                    sum=10+j;    
                }
                Button submitButton = new Button();
                submitButton.setText("SUBMIT");
                grid.add(submitButton, 1, sum+2);
                submitButton.setOnAction(new EventHandler<ActionEvent> (){
                
                @Override
                public void handle(ActionEvent event){
                 boolean flag1=true;
                 boolean flag2=true;
                for(int j = 0;j<noOfLabs;j++){
                    if(labName[j].getText().equals("")){
                        grid.add(inf1,0,22);
                        flag1=false;
                    }
                }
                for(int j = 0;j<noOfClassrooms;j++){
                    if(className[j].getText().equals("")){
                        grid.add(inf2,0,22);
                        flag2=false;
                    }      
                }
                if(flag1&&flag2){
                    inf1.setVisible(false);
                    inf2.setVisible(false);
                for(int m =0;m<noOfLabs;m++)
                    labNames.add(labName[m].getText());
                 
                //Lab.insertDetails(labNames);
                //TeacherForm tf = new TeacherForm();
                //tf.start(primaryStage);
                //primaryStage.close();
                }
                }});
            
            
            
            }});
       

        Scene scene = new Scene(grid);
        primaryStage.setFullScreen(true);
        //scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public boolean isValid(String  str){
        boolean b = false;
        if(str.length()==0||!Character.isDigit(str.charAt(0))||!Character.isDigit(str.charAt(0))||Integer.parseInt(str)>20)
            b=true; 
        return b;
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
