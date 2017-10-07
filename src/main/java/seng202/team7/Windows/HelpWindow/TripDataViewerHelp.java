package seng202.team7.Windows.HelpWindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class TripDataViewerHelp extends AnchorPane {

    public TripDataViewerHelp(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/HelpWindow/TripDataViewerHelp.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}