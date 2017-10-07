package seng202.team7.Windows.HelpWindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class MapAnalyticHelp extends AnchorPane {

    public MapAnalyticHelp(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/HelpWindow/MapAnalyticHelp.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}