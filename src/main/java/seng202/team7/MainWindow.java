package seng202.team7;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainWindow extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Meraki Bikes");

        // Create the gridpane for the overall main panel
        GridPane mainGridPane = new GridPane();
        mainGridPane.setAlignment(Pos.CENTER);

        // For debugging purposes only
        mainGridPane.setGridLinesVisible(true);

        Scene mainScene = new Scene(mainGridPane, 1000, 600);
        primaryStage.setScene(mainScene);

        primaryStage.show();
    }
}
