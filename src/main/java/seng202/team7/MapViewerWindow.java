package seng202.team7;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;

public class MapViewerWindow extends AnchorPane {

    public MapViewerWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("MapViewerWindow.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


    public void start (Stage stage) {
        // create web engine and view
        final WebEngine webEngine = new WebEngine(getClass().getClassLoader().getResource("googlemaps.html").toString());
        final WebView webView = new WebView();
    }

}