package seng202.team7.Windows.HelpWindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

/**
 * A custom JavaFX object representing the wifi data viewer help screen.
 * @author Connor McEwan-McDowall
 */
public class WifiDataViewerHelp extends AnchorPane {

    /**
     * Attempts to create a new wifi data viewer help screen. Throws a RuntimeException if this fails.
     */
    public WifiDataViewerHelp(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/HelpWindow/WifiDataViewerHelp.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}