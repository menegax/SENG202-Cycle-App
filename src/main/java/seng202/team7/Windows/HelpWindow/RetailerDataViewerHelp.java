package seng202.team7.Windows.HelpWindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

/**
 * A custom JavaFX object representing the retailer data viewer help screen.
 * @author Connor McEwan-McDowall
 */
public class RetailerDataViewerHelp extends AnchorPane {

    /**
     * Attempts to create a new retailer data viewer help screen. Throws a RuntimeException if this fails.
     */
    public RetailerDataViewerHelp(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/HelpWindow/RetailerDataViewerHelp.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}