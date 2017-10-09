package seng202.team7.Windows.HelpWindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

/**
 * A custom JavaFX object representing the trip analytic help screen.
 * @author Connor McEwan-McDowall
 */
public class TripAnalyticHelp extends AnchorPane {

    /**
     * Attempts to create a new trip analytic help screen. Throws a RuntimeException if this fails.
     */
    public TripAnalyticHelp(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/HelpWindow/TripAnalyticHelp.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}