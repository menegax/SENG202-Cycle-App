package seng202.team7.Windows.MainWindow;
/**
 * @author Morgan English
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * The map analytic window where users can view the analytic data
 */
public class MapAnalyticWindow extends AnchorPane {

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
