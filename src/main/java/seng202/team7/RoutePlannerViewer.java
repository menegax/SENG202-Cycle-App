package seng202.team7;
/**
 * Created by jam357 on 19/09/17.
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

/**
 * Route planner window where users can manually enter their desired start and end locations for the route they would
 * like to bike
 * @author Joshua Meneghini
 */
public class RoutePlannerViewer extends AnchorPane {

    public RoutePlannerViewer(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("RoutePlannerViewer.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}