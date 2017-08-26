package seng202.team7;/**
 * Created by jam357 on 24/08/17.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomeWindow extends Application {

    public Stage window;
    public Scene help, settings, home;
    public Button button, button1, button2, button3;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //This is the main cotainer which will be connors implementation
        window = primaryStage;

        //Creating the individual buttons and home has to be created twice as the same button cannot exist in the same
        //scene
        button = new Button("Help");
        button1 = new Button("Settings");
        button2 = new Button("Home");
        button3 = new Button("Home");

        //Layout - creating the home scene
        HBox layout = new HBox(380);
        layout.getChildren().addAll(button, button1);
        layout.setAlignment(Pos.BOTTOM_RIGHT);
        home = new Scene(layout, 500,400);

        //Layout1 - creating the help scene
        VBox layout1 = new VBox();
        layout1.getChildren().add(button3);
        layout1.setAlignment(Pos.BOTTOM_RIGHT);
        help = new Scene(layout1, 500,400);

        // layout2 - creating the settings scene
        VBox layout2 = new VBox();
        layout2.getChildren().add(button2);
        layout2.setAlignment(Pos.BOTTOM_RIGHT);
        settings = new Scene(layout2, 500,400);

        //getting to the help scene
        button.setOnAction(new EventHandler<ActionEvent>() {
            //*@Override*//*
            public void handle(ActionEvent e) {
                window.setScene(help);
            }
        });
        //getting to the settings scene
        button1.setOnAction(new EventHandler<ActionEvent>() {
            //*@Override*//*
            public void handle(ActionEvent e) {
                window.setScene(settings);
            }
        });

        //getting to the home scene
        button2.setOnAction(new EventHandler<ActionEvent>() {
            //*@Override*//*
            public void handle(ActionEvent e) {
                window.setScene(home);
            }
        });

        //getting to the home scene
        button3.setOnAction(new EventHandler<ActionEvent>() {
            //*@Override*//*
            public void handle(ActionEvent e) {
                window.setScene(home);
            }
        });

        window.setScene(home);
        window.setTitle("Window");
        window.show();
    }

}
