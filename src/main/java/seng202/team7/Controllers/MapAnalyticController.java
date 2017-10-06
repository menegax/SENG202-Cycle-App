package seng202.team7.Controllers;

import com.sun.javafx.webkit.WebConsoleListener;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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


    @FXML private ComboBox genderCombo;
    @FXML private ComboBox userCombo;
    @FXML private ComboBox ageCombo;
    @FXML private ComboBox densityCombo;
    @FXML private WebView webViewMap;
    @FXML private TextField tripDatagroup;
    @FXML private Button displayButton;
    @FXML private Button clearButton;
    @FXML private ToggleButton wifiButton;
    @FXML private ToggleButton retailerButton;
    @FXML private TextField poiDatagroup;

    private boolean wifiToggled = false;
    private boolean retailerToggled = false;
    private WebEngine webEngine;
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
        String tripGroup = "";
        if(!tripDatagroup.getText().equals(""))
            tripGroup = tripDatagroup.getText();

        System.out.println("display");
        jsObject.setMember("Abridge", new JSHandler());
        jsObject.call("loadHeat","test");
    }


    public void clearClicked()
    {
        jsObject.setMember("Abridge", new JSHandler());
        jsObject.call("clearHeat");
    }


    public void retailerClicked()
    {
        jsObject.setMember("Abridge", new JSHandler());
        System.out.println("retailer clicked");
        if(!retailerToggled) {
            System.out.println("retailer on");
            retailerToggled = true;
            String poiGroup = "";
            if (!poiDatagroup.getText().equals(""))
                poiGroup = poiDatagroup.getText();
            System.out.println(poiGroup);
            jsObject.call("loadRetailerDatagroup", poiGroup);
        } else {
            System.out.println("retailer off");

            jsObject.call("deleteRetailerMarkers");
            retailerToggled = false;
        }
    }

    public void wifiClicked()
    {
        jsObject.setMember("Abridge", new JSHandler());
        System.out.println("wifi clicked");
        if(!wifiToggled) {
            System.out.println("wifi on");
            wifiToggled = true;
            String poiGroup = "";
            if (!poiDatagroup.getText().equals(""))
                poiGroup = poiDatagroup.getText();
            System.out.println(poiGroup);
            jsObject.call("loadWifiDatagroup", poiGroup);
        } else {
            System.out.println("wifi off");

            jsObject.call("deleteWifiMarkers");
            wifiToggled = false;
        }
    }
}



