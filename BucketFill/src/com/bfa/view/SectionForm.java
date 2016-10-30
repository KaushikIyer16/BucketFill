/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;

/**
 *
 * @author mahesh
 */
public class SectionForm extends Application {
    
    TableView<ViewTestClass> tableOne = new TableView<>();
    final ObservableList<ViewTestClass> data =
        FXCollections.observableArrayList(
            new ViewTestClass("A", "Z", "a@example.com","A"),
            new ViewTestClass("B", "X", "b@example.com","B"),
            new ViewTestClass("C", "W", "c@example.com","C"),
            new ViewTestClass("D", "Y", "d@example.com","D"),
            new ViewTestClass("E", "V", "e@example.com","E")
        );
    
    ObservableList<String> options = FXCollections.observableArrayList("A", "B");

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("SECTION FORM");
        primaryStage.setMaximized(true);
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(15);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("ENTER DETAILS FOR SECTIONS");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label yearOfStudy = new Label("YEAR:");
        grid.add(yearOfStudy, 0, 1);

        final ComboBox desBox = new ComboBox();
        desBox.getItems().addAll(
                "2",
                "3",
                "4"
        );
        desBox.setValue("");
        grid.add(desBox, 1, 1);

        Label numberOfSections = new Label("NUMBER OF SECTIONS:");
        grid.add(numberOfSections, 3, 1);

        TextField sectionField = new TextField();
        grid.add(sectionField, 4, 1);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 16);
        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();

        tableOne.setEditable(true);
                

        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<ViewTestClass,String>("firstName"));
        
        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<ViewTestClass,String>("lastName"));
        
        TableColumn emailCol = new TableColumn("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<ViewTestClass,String>("email"));
        
        TableColumn optionsCol = new TableColumn("Option Selection");
        optionsCol.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), options));
        
        //firstNameCol.setCellFactory(ComboBoxTableCell.forTableColumn("A", "B", "C"));

        tableOne.setItems(data);
        tableOne.getColumns().addAll(firstNameCol, lastNameCol, emailCol, optionsCol);
        
        grid.add(tableOne, 1, 10);

        Button confirmButton = new Button();
        confirmButton.setText("Set");
        grid.add(confirmButton, 1, 15);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
