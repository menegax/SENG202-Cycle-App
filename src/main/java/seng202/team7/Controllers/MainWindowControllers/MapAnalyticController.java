package seng202.team7.Controllers.MainWindowControllers;

import com.sun.javafx.webkit.WebConsoleListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import seng202.team7.JSHandler;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static seng202.team7.Datagroup.getDatagroups;

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
    @FXML private Button displayButton;
    @FXML private Button clearButton;
    @FXML private ToggleButton wifiButton;
    @FXML private ToggleButton retailerButton;

    @FXML private TextField poiDatagroup;
    @FXML private TextField tripDatagroup;

    @FXML private ComboBox dataGroupCombo;
    @FXML private ComboBox poiDatagroupCombo;

    @FXML public void setDataGroupComboItems() {
        ArrayList<String> list = getDatagroups();
        list.add("All");
        ObservableList<String> items = FXCollections.observableArrayList(list);
        dataGroupCombo.setItems(items);
    }

    @FXML public void setPOIDataGroupComboItems() {
        ArrayList<String> list = getDatagroups();
        list.add("All");
        ObservableList<String> items = FXCollections.observableArrayList(list);
        poiDatagroupCombo.setItems(items);
    }

    private boolean wifiToggled = false;
    private boolean retailerToggled = false;
    private WebEngine webEngine;
    private JSObject jsObject;
    private JSHandler jsHandler;

    /**
     * todo
     * @param url For testing
     * @param rb For testing
     */
    public void initialize(URL url, ResourceBundle rb)
    {
       webEngine = webViewMap.getEngine();
       genderCombo.getItems().addAll("All","Male","Female", "Unknown");
       userCombo.getItems().addAll("All", "Customer", "Subscriber");
       ageCombo.getItems().addAll("All","0-15","15-25","25-35","35-45","45-55","55-55+");
       densityCombo.getItems().addAll("Low","Medium","High");


        webEngine.setJavaScriptEnabled(true);
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (Worker.State.SUCCEEDED == newValue) {
                jsObject = (JSObject) webEngine.executeScript("window");
                jsHandler = new JSHandler();
                jsObject.setMember("Abridge", jsHandler);
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
     * On click oisplay button will take the parameters entered and pass them into js to create a heat map overlay
     */
    public void displayClicked()
    {
        String userType;
        String gender;
        String age;
        String density;
        String tripGroup = "";
        try {
            if (!((String) dataGroupCombo.getValue()).equals(""))
                tripGroup = (String) dataGroupCombo.getValue();
        } catch (NullPointerException e) { }
        if (tripGroup.equals("All")) {
            tripGroup = "";
        }

        if(genderCombo.getSelectionModel().getSelectedItem() == null){
            gender = "All";
        } else {
            gender = (String) genderCombo.getSelectionModel().getSelectedItem();
        }

        if(userCombo.getSelectionModel().getSelectedItem() == null)
        {
            userType = "All";
        } else {
            userType = (String) userCombo.getSelectionModel().getSelectedItem();
        }

        if(ageCombo.getSelectionModel().getSelectedItem() == null) {
            age = "All";
        } else {
            age = (String) ageCombo.getSelectionModel().getSelectedItem();
        }


        if(densityCombo.getSelectionModel().getSelectedItem() == null)
        {
            density = "medium";
        } else {
            density = (String) densityCombo.getSelectionModel().getSelectedItem();
        }

        System.out.println("display");
        jsObject.call("loadHeat",tripGroup, gender, age, userType, density);
    }


    /**
     * removes all heatmaps
     */
    public void clearClicked()
    {
        jsObject.call("clearHeat");
    }


    /**
     * Turns on the retailer icon
     */
    public void retailerClicked()
    {
        System.out.println("retailer clicked");
        if(!retailerToggled) {
            System.out.println("retailer on");
            retailerToggled = true;
            String poiGroup = "";
            try {
                if (!((String) poiDatagroupCombo.getValue()).equals(""))
                    poiGroup = (String) poiDatagroupCombo.getValue();
            } catch (NullPointerException e) { }
            if (poiGroup.equals("All")) {
                poiGroup = "";
            }

            System.out.println(poiGroup);
            jsObject.call("loadRetailerDatagroup", poiGroup);
        } else {
            System.out.println("retailer off");

            jsObject.call("deleteRetailerMarkers");
            retailerToggled = false;
        }
    }


    /**
     * turns on the Wifi icons
     */
    public void wifiClicked()
    {
        System.out.println("wifi clicked");
        if(!wifiToggled) {
            System.out.println("wifi on");
            wifiToggled = true;
            String poiGroup = "";
            try {
                if (!((String) poiDatagroupCombo.getValue()).equals(""))
                    poiGroup = (String) poiDatagroupCombo.getValue();
            } catch (NullPointerException e) { }
            if (poiGroup.equals("All")) {
                poiGroup = "";
            }

            System.out.println(poiGroup);
            jsObject.call("loadWifiDatagroup", poiGroup);
        } else {
            System.out.println("wifi off");

            jsObject.call("deleteWifiMarkers");
            wifiToggled = false;
        }
    }
}



