package seng202.team7.Windows.MainWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import seng202.team7.Database.DatabaseHandler;

/**
 * Main window model for running the main program
 * @author Connor McEwan-McDowall
 */

public class MainWindow extends Application {

    /**
     * Launches the application
     * @param args Unused parameter
     */
    public static void main(String[] args) {
        DatabaseHandler.initializeDatabase();
        launch(args);
    }

    /**
     * Starts the application. Sets the stage and title. This is the main entry point for the application.
     * @param primaryStage The main window stage containing all core screens
     * @throws Exception The exception that can be thrown.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/MainWindow/MainWindow.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("Stylesheets/MainStyle.css").toExternalForm());
        primaryStage.setTitle("Meraki Bikes");
        primaryStage.setMinWidth(1100);
        primaryStage.setMinHeight(500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
