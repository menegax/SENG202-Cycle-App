package seng202.team7.Windows.HelpWindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

/**
 * A custom JavaFX object representing the map analytic help screen.
 * @author Connor McEwan-McDowall
 */
public class MapAnalyticHelp extends AnchorPane {

    /**
     * Attempts to create a new map analytic help screen. Throws a RuntimeException if this fails.
     */
    public MapAnalyticHelp(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/HelpWindow/MapAnalyticHelp.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}