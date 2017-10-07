package seng202.team7.Windows.MainWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/** Loading popup for when the user starts to load a CSV
 * @author Aidan Smith asm142
 * Last updated 01/10/17
 */
public class LoadingPopupWindow extends AnchorPane {

    /**
     * Main constructor that loads the fxml file
     */
    public LoadingPopupWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/LoadingPopup.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
