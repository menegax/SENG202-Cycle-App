package seng202.team7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class DataEntryWindow extends AnchorPane {
    /*<AnchorPane prefHeight="400.0" prefWidth="1100.0" xmlns="http://javafx.com/javafx/8" xmlns:fx="http://javafx.com/fxml/1" fx:controller="seng202.team7.DataEntryWindowController">
    <fx:root type="javafx.scene.layout.AnchorPane" id="AnchorPane" maxHeight="-Infinity" maxWidth="-Infinity" minHeight="-Infinity" minWidth="-Infinity" prefHeight="400.0" prefWidth="1100.0" xmlns="http://javafx.com/javafx/8" xmlns:fx="http://javafx.com/fxml/1" fx:controller="seng202.team7.DataEntryWindowController">

    extends Application
    AnchorPane



    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("DataEntryWindow.fxml"));
        primaryStage.setTitle("Data Entry Window");
        primaryStage.setScene(new Scene(root, 1100, 400));
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }

    */




    public DataEntryWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("DataEntryWindow.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
