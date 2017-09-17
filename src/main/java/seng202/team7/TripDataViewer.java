package seng202.team7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**Trip data model for raw data viewing of trip data
 * @author Aidan Smith asm142
 * Last updated 13/09/17
 */

public class TripDataViewer extends AnchorPane {

    public TripDataViewer(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("TripDataViewer.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
