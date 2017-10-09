package seng202.team7.Windows.HelpWindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

/**
 * A custom JavaFX object representing the route planner help screen.
 * @author Connor McEwan-McDowall
 */
public class RoutePlannerHelp extends AnchorPane {

    /**
     * Attempts to create a new route planner help screen. Throws a RuntimeException if this fails.
     */
    public RoutePlannerHelp(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/HelpWindow/RoutePlannerHelp.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}