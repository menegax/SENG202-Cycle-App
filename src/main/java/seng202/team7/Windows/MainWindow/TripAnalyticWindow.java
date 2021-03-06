package seng202.team7.Windows.MainWindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

/**
 * The analytics window for the trips to view data analysis of previous trips in the database
 * @author Morgan English
 */
public class TripAnalyticWindow extends AnchorPane {

    /**
     * Attempts to create a new trip analytic screen. Throws a RuntimeException if this fails.
     */
    public TripAnalyticWindow(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/MainWindow/TripAnalyticWindow.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}