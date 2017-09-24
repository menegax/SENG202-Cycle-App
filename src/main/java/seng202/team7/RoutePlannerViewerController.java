package seng202.team7;
/*
 * Created by jam357 on 21/09/17.
 */

import com.sun.javafx.webkit.WebConsoleListener;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * todo
 * @author Joshua Meneghini
 */
public class RoutePlannerViewerController implements Initializable{

    @FXML
    private Button displayButton1;
    @FXML
    private WebView webViewMap1;

    private WebEngine webEngine1;
    private JSObject jsBridge1;

    public void initialize(URL url, ResourceBundle rb)
    {
        webEngine1 = webViewMap1.getEngine();

        webEngine1.setJavaScriptEnabled(true);
        webEngine1.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (Worker.State.SUCCEEDED == newValue) {
                JSObject jsObject = (JSObject) webEngine1.executeScript("window");
                jsObject.setMember("bridge", new RouteHandler());
                jsBridge1 = (JSObject) webEngine1.executeScript("getJsConnector();");
            }
        });
        webEngine1.load(getClass().getClassLoader().getResource("RoutePlanner.html").toExternalForm());

        WebConsoleListener.setDefaultListener(new WebConsoleListener() {
            @Override
            public void messageAdded(WebView webView, String message, int lineNumber, String sourceId) {
                System.out.println("Console: [" + sourceId + ":" + lineNumber + "] " + message);
            }
        });
    }

    public void displayClicked1() { /*webEngine1.executeScript("loadStation();");*/ }
}