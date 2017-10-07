package seng202.team7.Windows.MainWindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**Wifi data model for raw data viewing of wifi data
 * @author Aidan Smith asm142
 * Last updated 13/09/17
 */

public class WifiDataViewerWindow extends AnchorPane {

    /**
     * Main constructor that loads the fxml file
     */
    public WifiDataViewerWindow(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/MainWindow/WifiDataViewer.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
