package seng202.team7;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

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



