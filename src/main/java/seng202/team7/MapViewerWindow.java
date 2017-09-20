package seng202.team7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Map viewer window to view data points on a map
 * @author Mitchell Fenwick
 */

public class MapViewerWindow extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MapViewerWindow.fxml"));

        primaryStage.setTitle("MapViewer");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}