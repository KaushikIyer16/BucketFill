/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
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
public class WelcomePage extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        Text bucketFill = new Text("BUCKETFILL");
        Label nextLine = new Label("     TIME TABLE GENERATOR");
        nextLine.setFont(Font.font("Quantico", FontWeight.EXTRA_BOLD, 30));
        nextLine.setTextFill(Color.web("#ffffff"));
        Label l1 = new Label("");
        Label l2 = new Label("");
        Label l3 = new Label("");
        Label l4 = new Label("");
        bucketFill.setFont(Font.font("Quantico", FontWeight.EXTRA_BOLD, 80));
        bucketFill.setFill(Color.web("#ffffff"));
        btn.setText("CLICK HERE TO START");
        btn.setMinWidth(bucketFill.prefWidth(-1));
        btn.setMinHeight(60);
        btn.setFont(Font.font("Quantico", FontWeight.EXTRA_BOLD, 20));
        btn.setStyle("-fx-background-color: #ffffff");
        nextLine.setMinWidth(bucketFill.prefWidth(-1));
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                //System.out.println("Hello World!");
                SubjectForm sf = new SubjectForm();
                sf.start(primaryStage);
            }
        });
        
        GridPane root = new GridPane();
        root.setStyle("-fx-background-color: #2F63A3");
        root.setAlignment(Pos.CENTER);
        root.add(bucketFill,0,0);
        root.add(nextLine,0,1);
        root.add(l2,0,2);
        root.add(l3,0,3);
        root.add(l4,0,4);
        root.add(btn,0,5);
        
        Scene scene = new Scene(root,800,800,Color.AQUAMARINE);
        primaryStage.setTitle("BUCKETFILL APPLICATION!");
        primaryStage.setScene(scene);
        //primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
