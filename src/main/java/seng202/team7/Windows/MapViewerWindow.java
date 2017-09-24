package seng202.team7.Windows;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

/**
 * Map viewer window to view data points on a map
 * @author Mitchell Fenwick
 */

public class MapViewerWindow extends AnchorPane {

    public MapViewerWindow(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/MapViewerWindow.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}