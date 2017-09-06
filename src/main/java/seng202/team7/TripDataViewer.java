package seng202.team7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**Tri[ data model for raw data viewing of trip data
 * @author Aidan Smith asm142
 * Last updated 28/08/17
 */

public class TripDataViewer extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("TripDataViewer.fxml"));
        primaryStage.setTitle("Trip Data Viewer");
        primaryStage.setScene(new Scene(root, 1100, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
