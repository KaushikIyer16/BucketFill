/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.view;

import com.bfa.model.Section;
import com.bfa.model.Subject;
import com.bfa.model.TeacherSubject;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;

/**
 *
 * @author mahesh
 */
public class SectionForm extends Application {
    
    int p = 3;
    int i;
    //TableView<ViewTestClass> table2 = new TableView<>();
    ComboBox[][] teachBox;
    String[][] matrix;
    int subjectSize;
    ArrayList<Subject> arrList = new ArrayList<>();
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("SECTION FORM");
        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color: white");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(15);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("YEAR "+p);
        scenetitle.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 50));
        grid.add(scenetitle, 0, 0, 4, 1);

        Label teacherName = new Label("ENTER THE NUMBER OF SECTIONS:");
        grid.add(teacherName, 0, 3);

        TextField noOfSections = new TextField();
        noOfSections.setPrefWidth(80);
        grid.add(noOfSections, 1, 3);
        Button setButton = new Button();
        setButton.setText("CONFIRM");
        grid.add(setButton,2,3);
        Label subjectNames = new Label("SUBJECTS");
        subjectNames.setFont(Font.font("Tahoma",FontWeight.BOLD,25));
        setButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(isValid(noOfSections.getText())){
                     Label label = new Label("enter valid values. (Digits only)");
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
                    
                setButton.setVisible(false);
                grid.add(subjectNames,0,5);
                
                char c = 'A';
                for (i = 1; i <= Integer.parseInt((String)noOfSections.getText()); i++) {
                    /*Label subno = new Label("SUBJECT " + i + ":");
                    grid.add(subno, 0, i + 5);
                    TextField subName1 = new TextField();
                    grid.add(subName1, 1, i + 5);
                    TextField subName2 = new TextField();
                    grid.add(subName2, 2, i + 5);*/
                    Label sectionNames = new Label("SECTION "+String.valueOf(c));
                    sectionNames.setPrefWidth(80);
                    grid.add(sectionNames,i,5);
                    c++;
                }
                System.out.println(i);
                 
                 arrList =  Subject.getSubjectByYear(p);
                 for(Subject itr: arrList){
                     System.out.println(itr.getName());
                 }
                //arrList.add("JAVA");
                //arrList.add("DATA MINING");
                //arrList.add("DBMS");
                //arrList.add("WEB PROGRAMMING");
                //arrList.add("DCN");
               teachBox = new ComboBox[arrList.size()][Integer.parseInt((String)noOfSections.getText())];
               matrix = new String[arrList.size()][Integer.parseInt((String)noOfSections.getText())];
                 subjectSize = arrList.size();
                for(int j=0;j<arrList.size();j++){
                    Label getSubject = new Label(arrList.get(j).getName());
                    ArrayList <String> teacherSubject = TeacherSubject.getTeacherBySubject(arrList.get(j).getName());
                    getSubject.setMinWidth(150);
                    grid.add(getSubject, 0, 7+j);
                    
                    for(int k=1;k<i;k++){
                        teachBox[j][k-1] = new ComboBox();
                        for(int ct = 0;ct<teacherSubject.size();ct++)
                            teachBox[j][k-1].getItems().add(teacherSubject.get(ct));
                        teachBox[j][k-1].setValue("  ");
                        teachBox[j][k-1].setPrefWidth(100);
                        grid.add(teachBox[j][k-1], k, 7+j);
                    }
                    
                }
                 Button btn = new Button("SUBMIT");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.CENTER);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 5, 15);
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                boolean flag=false;
                 for(int m=0;m<subjectSize;m++){
                     
                 
                 for(int h = 0;h< Integer.parseInt((String)noOfSections.getText());h++){
                     //System.out.println(Integer.parseInt((String)noOfSections.getText()));
                    
                 if(teachBox[m][h].getValue().toString().equalsIgnoreCase("  "))
                     flag=true;
                     break;
                     }
               
            }
                 if(flag){
                     Label label = new Label("SELECT TEACHERS FOR THE SUBJECTS");
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
                     for(int m=0;m<subjectSize;m++){
                     
                 
                 for(int h = 0;h< Integer.parseInt((String)noOfSections.getText());h++){
                     //System.out.println(Integer.parseInt((String)noOfSections.getText()));
                     matrix[m][h] = teachBox[m][h].getValue().toString();
                     System.out.print(matrix[m][h]);
                    }
                         System.out.println();
                 }
               //Section.insertDetails(arrList,matrix,p); 
               Label label = new Label("FORM SUBMISSION DONE.");
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
               p++;
               if(p<4)
                start(primaryStage);
               else{
                   CCPForm ccp = new CCPForm();
                         try {
                             ccp.start(primaryStage);
                         } catch (Exception ex) {
                             Logger.getLogger(SectionForm.class.getName()).log(Level.SEVERE, null, ex);
                         }
               }
                   
               
            }
            }
             
        });
       }
    }});
        
       
            

       
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 16);
        Scene scene = new Scene(grid);
        //scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
    public boolean isValid(String  str){
        boolean b = false;
        if(str.length()==0||!Character.isDigit(str.charAt(0))||!Character.isDigit(str.charAt(0))||Integer.parseInt(str)>20)
            b=true; 
        return b;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
