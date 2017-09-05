package seng202.team7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**Retailer data model for raw data viewing of retailer data
 * @author Aidan Smith asm142
 * Last updated 05/09/17
 */

public class RetailerDataViewer extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("RetailerDataViewer.fxml"));
        primaryStage.setTitle("Retailer Data Viewer");
        primaryStage.setScene(new Scene(root, 1155, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}