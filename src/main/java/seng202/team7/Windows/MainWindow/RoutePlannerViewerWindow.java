package seng202.team7.Windows.MainWindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

/**
 * Route planner window where users can manually enter their desired start and end locations for the route they would
 * like to bike
 * @author Joshua Meneghini
 */
public class RoutePlannerViewerWindow extends AnchorPane {

    /**
     * Attempts to create a new route planner screen. Throws a RuntimeException if this fails.
     */
    public RoutePlannerViewerWindow(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/MainWindow/RoutePlannerViewer.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}