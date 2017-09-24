package seng202.team7.Windows;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

/**
 * A custom JavaFX object representing a home window.
 * @author Connor McEwan-McDowall
 */
public class HomeWindow extends AnchorPane {

    /**
     * Attempts to create a new home window. Throws a RuntimeException if this fails.
     */
    public HomeWindow(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/Home.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}