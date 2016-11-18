/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.view;

import com.bfa.model.Subject;
import java.util.ArrayList;
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
    
    int i;
    //TableView<ViewTestClass> table2 = new TableView<>();
    ComboBox[][] teachBox;
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

        Label teacherName = new Label("ENTER THE NUMBER OF SECTIONS:");
        grid.add(teacherName, 0, 3);

        TextField noOfSections = new TextField();
        noOfSections.setPrefWidth(80);
        grid.add(noOfSections, 1, 3);
        Button setButton = new Button();
        setButton.setText("CONFIRM");
        grid.add(setButton,2,3);
        Label subjectNames = new Label("SUBJECTS");
        setButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //btn1.setVisible(false);
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
                 ArrayList<Subject> arrList = new ArrayList<>();
                 arrList =  Subject.getSubjectByYear(2);
                //arrList.add("JAVA");
                //arrList.add("DATA MINING");
                //arrList.add("DBMS");
                //arrList.add("WEB PROGRAMMING");
                //arrList.add("DCN");
                teachBox = new ComboBox[arrList.size()][Integer.parseInt((String)noOfSections.getText())];
                 char b='A',e='B';
                for(int j=0;j<arrList.size();j++){
                    Label getSubject = new Label(arrList.get(j).getName());
                    
                    grid.add(getSubject, 0, 7+j);
                    for(int k=1;k<i;k++){
                        teachBox[j][k-1] = new ComboBox();
                        teachBox[j][k-1].getItems().addAll(String.valueOf(b),String.valueOf(e));
                        teachBox[j][k-1].setValue("");
                        grid.add(teachBox[j][k-1], k, 7+j);
                    }
                    b++;
                    e++;
                }
        }});
        
       
            

        Button btn = new Button("SUBMIT");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.CENTER);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 5, 15);
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
       
               
                 if(teachBox[0][0].getValue().toString().equalsIgnoreCase("0"))
                 {
                     System.out.println("Not entered properly");
                 }
                
               
            }
             
        });
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 16);
        Scene scene = new Scene(grid, 300, 275);
        //scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
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
