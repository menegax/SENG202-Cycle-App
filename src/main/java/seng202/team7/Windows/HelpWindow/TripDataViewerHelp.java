package seng202.team7.Windows.HelpWindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

/**
 * A custom JavaFX object representing the trip data viewer help screen.
 * @author Connor McEwan-McDowall
 */
public class TripDataViewerHelp extends AnchorPane {

    /**
     * Attempts to create a new trip data viewer help screen. Throws a RuntimeException if this fails.
     */
    public TripDataViewerHelp(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/HelpWindow/TripDataViewerHelp.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}