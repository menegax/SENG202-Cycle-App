package seng202.team7;

import com.sun.javafx.webkit.WebConsoleListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

public class MapAnalyticController {

    @FXML private Button displayButton;
    @FXML private CheckBox wifiCB;
    @FXML private CheckBox retailerCB;
    @FXML private WebView webViewMap;
    @FXML private TextField inText;



    public void displayClicked()
    {
        WebEngine webEngine = webViewMap.getEngine();
        webEngine.load(getClass().getClassLoader().getResource("MapView.html").toExternalForm());
        webEngine.setJavaScriptEnabled(true);
        JSObject jsObject = (JSObject) webEngine.executeScript("window");
        jsObject.setMember("bridge", new JSHandler());

        WebConsoleListener.setDefaultListener(new WebConsoleListener() {
            @Override
            public void messageAdded(WebView webView, String message, int lineNumber, String sourceId) {
                System.out.println("Console: [" + sourceId + ":" + lineNumber + "] " + message);
            }
        });
    }

    @FXML
    private void wifiChecked()
    {

    }

    @FXML
    private void retailerChecked()
    {

    }
}



