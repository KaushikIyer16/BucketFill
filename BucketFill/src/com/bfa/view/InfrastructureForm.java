/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.view;

import com.bfa.controller.Allot;
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
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("INFRASTRUCTURE FORM");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
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
        
        Button infSubmit = new Button();
        infSubmit.setText("CONFIRM");
        grid.add(infSubmit, 0, 8);
        
        infSubmit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //btn1.setVisible(false);
                if(numberOfLabs.getText().length()==0||numberOfClasses.getText().length()==0||!Character.isDigit(numberOfLabs.getText().charAt(0))||!Character.isDigit(numberOfClasses.getText().charAt(0))){
                    numberOfLabs.setText("");
                    numberOfClasses.setText("");
                    return;
                }
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
                System.out.println(i);
                grid.add(labs, 0, i++);
                 for (int j = 1; j <= noOfLabs; j++) {
                    Label labNo = new Label("LAB " + j + ":");
                    grid.add(labNo, 0, i);
                    labName[j-1] = new TextField();
                    grid.add(labName[j-1], 1, i++);

                }
                Button submitButton = new Button();
                submitButton.setText("SUBMIT");
                grid.add(submitButton, 1, i);
                submitButton.setOnAction(new EventHandler<ActionEvent> (){
                
                @Override
                public void handle(ActionEvent event){
                 boolean flag1=false;
                 boolean flag2=false;
                for(int j = 0;j<noOfLabs;j++){
                    if(labName[j].getText().equals("")){
                        grid.add(new Label("enter all the fields"),0,22);
                        return;
                    }
                }
                for(int j = 0;j<noOfClassrooms;j++){
                    if(className[j].getText().equals("")){
                        grid.add(new Label("enter all the fields"),0,22);
                        return;
                    }
                        
                }
                
                
                
                }});
            
            
            
            }});
       

        Scene scene = new Scene(grid, 300, 275);
        //scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
