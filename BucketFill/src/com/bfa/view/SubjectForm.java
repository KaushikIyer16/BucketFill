/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.view;

import com.bfa.model.Subject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    int l1[], t1[], p1[], ss[];
    String electiveSubject[];
    String cc[], sn[];
                
    //ArrayList<String> courseCode = new ArrayList<>();
    //ArrayList<String> subjName = new ArrayList<>();
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("SECTION FORM");
        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color: white");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(15);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("YEAR 2");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 50));
        grid.add(scenetitle, 0, 0, 4, 1);

        Label subjectLabel = new Label("ENTER THE NUMBER OF SUBJECTS:");
        grid.add(subjectLabel, 0, 3);

        TextField noOfSubjects = new TextField();
        noOfSubjects.setPrefWidth(80);
        grid.add(noOfSubjects, 1, 3);
        Button setButton = new Button();
        setButton.setText("CONFIRM");
        grid.add(setButton,2,3);
        Label subjectNames = new Label("SUBJECTS:");
        setButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //btn1.setVisible(false);
                String []args = null;
                System.out.println(noOfSubjects.getText());
                if(noOfSubjects.getText().length()==0||!Character.isDigit(noOfSubjects.getText().charAt(0))){
                    noOfSubjects.setText("");
                    return;
                }
                    
                grid.add(subjectNames,0,5);
                Label cCode = new Label("COURSE CODE");
                cCode.setPrefWidth(140);
                grid.add(cCode, 1, 6);
                Label subName = new Label("SUBJECT");
                subName.setPrefWidth(120);
                grid.add(subName, 2, 6);
                Label lecture = new Label("L");
                lecture.setPrefWidth(80);
                grid.add(lecture,3,6);
                Label theory = new Label("T");
                theory.setPrefWidth(80);
                grid.add(theory,4,6);
                Label practical = new Label("P");
                practical.setPrefWidth(80);
                grid.add(practical,5,6);
                Label self = new Label("S");
                self.setPrefWidth(80);
                grid.add(self,6,6);
                Label elective = new Label("ELECTIVE");
                grid.add(elective, 7, 6);
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
                    grid.add(courseCode[i],1,i+7);
                    grid.add(subjName[i],2,i+7);
                    grid.add(l[i],3,i+7);
                    grid.add(t[i],4,i+7);
                    grid.add(p[i],5,i+7);
                    grid.add(s[i],6,i+7);
                    grid.add(elect[i], 7, i+7);
                }
                Button submitButton = new Button();
                submitButton.setText("SUBMIT");
                grid.add(submitButton, 7, i+10);
                 submitButton.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                    for(int i = 0; i<number;i++){
                        if(courseCode[i].getText().equalsIgnoreCase("")){
                            System.out.println("Booyah!");
                            return;
                        }
                        
                        System.out.println(courseCode[i].getText());
                     l1[i]=Integer.parseInt(l[i].getText());
                     t1[i]=Integer.parseInt(t[i].getText());
                     p1[i]=Integer.parseInt(p[i].getText());
                     ss[i]=Integer.parseInt(s[i].getText());
                     electiveSubject[i]=elect[i].getValue().toString();
                     cc[i]=courseCode[i].getText().toUpperCase();
                     sn[i]=subjName[i].getText();
                    }    
                    
               Subject.insertDetails(cc, sn, l1, t1, p1, ss, electiveSubject);
               
                }}); 
        
       
           

       }}); 
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 16);
        Scene scene = new Scene(grid);
        //scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
        

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
