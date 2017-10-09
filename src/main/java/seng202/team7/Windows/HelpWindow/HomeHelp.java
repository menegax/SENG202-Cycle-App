package seng202.team7.Windows.HelpWindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

/**
 * A custom JavaFX object representing the home help screen.
 * @author Connor McEwan-McDowall
 */
public class HomeHelp extends AnchorPane {

    /**
     * Attempts to create a new data home help screen. Throws a RuntimeException if this fails.
     */
    public HomeHelp(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/HelpWindow/HomeHelp.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}