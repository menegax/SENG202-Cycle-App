package seng202.team7.Controllers;

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
import seng202.team7.JSHandler;
import seng202.team7.StaticVariables;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * todo
 * @author MorganEnglish
 */
public class MapAnalyticController implements Initializable {

    @FXML private Button displayButton;
    @FXML private CheckBox wifiCB;
    @FXML private CheckBox retailerCB;
    @FXML private WebView webViewMap;
    @FXML private TextField inText;
    @FXML private TextField limitText;
    private WebEngine webEngine;
    private JSObject jsBridge;
    private JSObject jsObject;

    /**
     * todo
     * @param url For testing
     * @param rb For testing
     */
    public void initialize(URL url, ResourceBundle rb)
    {
       webEngine = webViewMap.getEngine();

        webEngine.setJavaScriptEnabled(true);
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (Worker.State.SUCCEEDED == newValue) {
                jsObject = (JSObject) webEngine.executeScript("window");
                jsObject.setMember("Abridge", new JSHandler());
                System.out.println("set bridge");
                //jsBridge = (JSObject) webEngine.executeScript("getJsConnector()");
            }
        });
        webEngine.load(getClass().getClassLoader().getResource("HTMLFiles/MapView.html").toExternalForm());


        WebConsoleListener.setDefaultListener(new WebConsoleListener() {
        @Override
        public void messageAdded(WebView webView, String message, int lineNumber, String sourceId) {
            System.out.println("Console: [" + sourceId + ":" + lineNumber + "] " + message);
        }
    });

    }


    /**
     * todo
     */
    public void displayClicked()
    {
        if(!limitText.getText().equals(""))
            StaticVariables.limit = Integer.parseInt(limitText.getText());
        if(!inText.getText().equals(""))
            StaticVariables.pointMultiplier = Integer.parseInt(inText.getText());
        System.out.println("display");
        jsObject.setMember("Abridge", new JSHandler());
        jsObject.call("loadHeat","test");
    }


    public void clearClicked()
    {
        jsObject.setMember("Abridge", new JSHandler());
        jsObject.call("clearHeat");
    }

    /**
     * todo
     */
    @FXML
    private void wifiChecked()
    {

    }

    /**
     * todo
     */
    @FXML
    private void retailerChecked()
    {

    }
}



