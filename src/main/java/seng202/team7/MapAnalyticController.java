package seng202.team7;

import com.sun.javafx.webkit.WebConsoleListener;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author MorganEnglish
 */
public class MapAnalyticController implements Initializable {

    @FXML private Button displayButton;
    @FXML private CheckBox wifiCB;
    @FXML private CheckBox retailerCB;
    @FXML private WebView webViewMap;
    @FXML private TextField inText;
    private WebEngine webEngine;
    private JSObject jsBridge;


    public void initialize(URL url, ResourceBundle rb)
    {
        webEngine = webViewMap.getEngine();

        webEngine.setJavaScriptEnabled(true);
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (Worker.State.SUCCEEDED == newValue) {
                JSObject jsObject = (JSObject) webEngine.executeScript("window");
                jsObject.setMember("bridge", new JSHandler());
                jsBridge = (JSObject) webEngine.executeScript("getJsConnector()");
            }
        });
        webEngine.load(getClass().getClassLoader().getResource("MapView.html").toExternalForm());


        WebConsoleListener.setDefaultListener(new WebConsoleListener() {
        @Override
        public void messageAdded(WebView webView, String message, int lineNumber, String sourceId) {
            System.out.println("Console: [" + sourceId + ":" + lineNumber + "] " + message);
        }
    });

    }



    public void displayClicked()
    {
        webEngine.executeScript("loadWifi();");
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



