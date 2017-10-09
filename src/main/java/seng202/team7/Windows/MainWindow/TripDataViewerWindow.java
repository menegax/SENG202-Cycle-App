package seng202.team7.Windows.MainWindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

/**Trip data model for raw data viewing of trip data
 * @author Aidan Smith asm142
 * Last updated 13/09/17
 */

public class TripDataViewerWindow extends AnchorPane {

    /**
     * Main constructor that loads the fxml file
     */
    public TripDataViewerWindow(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/MainWindow/TripDataViewer.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
