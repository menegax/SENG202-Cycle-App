package seng202.team7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**Wifi data model for raw data viewing of wifi data
 * @author Aidan Smith asm142
 * Last updated 13/09/17
 */

public class WifiDataViewer extends AnchorPane {

    public WifiDataViewer(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("WifiDataViewer.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void reload() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("WifiDataViewer.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
