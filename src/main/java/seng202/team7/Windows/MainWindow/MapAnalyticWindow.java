package seng202.team7.Windows.MainWindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

/**
 * The map analytic window where users can view the analytic data
 * @author Morgan English
 */
public class MapAnalyticWindow extends AnchorPane {

    /**
     * Attempts to create a new map analytic screen. Throws a RuntimeException if this fails.
     */
    public MapAnalyticWindow(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/MainWindow/MapAnalyticWindow.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
