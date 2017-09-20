package seng202.team7;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Map viewer window to view data points on a map controller class
 * @author Mitchell Fenwick
 */

public class MapViewerWindowController implements Initializable {

    // Widgets used in map viewer
    @FXML
    private ComboBox<String> mapComboBorough;
    @FXML
    private ComboBox<String> mapComboRetailerType;
    @FXML
    private ComboBox<String> mapComboProvider;
    @FXML
    private ComboBox<String> mapComboWifiType;
    @FXML
    private ComboBox<String> mapComboStreet;
    @FXML
    private ComboBox<String> mapComboZipCode;
    @FXML
    private WebView mapView;

    //Local variables needed
    private int currentRetailerIndex = -1;
    private int loadedData = 0;
    private DatabaseRetriever dbRetriever;
    private DatabaseUpdater dbUpdater;
    private boolean loadedAll = false;
    private boolean scrollAdded = false;
    private ObservableList<Retailer> retailerList;
    private ObservableList<Retailer> filteredRetailerList;


    /**
     * Initialises the map view and adds data to it
     *
     * @param url Required parameter that is not used in the function
     * @param rb  Required parameter that is not used in the function
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Initialise retailers and wifi
        dbUpdater = new DatabaseUpdater();
        DatabaseTester.addData(dbUpdater);
        dbRetriever = new DatabaseRetriever();
        dbUpdater = new DatabaseUpdater();
        DatabaseTester.addData(dbUpdater);
        dbRetriever = new DatabaseRetriever();
        ArrayList<Retailer> retailerArrayList = dbRetriever.queryRetailer(StaticVariables.steppedQuery(Retailer.tableName, loadedData));
        retailerList = FXCollections.observableArrayList(retailerArrayList);
        filteredRetailerList = FXCollections.observableArrayList(retailerList);

        ArrayList<String> streets = new ArrayList<>();
        ArrayList<String> zips = new ArrayList<>();
        for (Retailer retailer : retailerList) {
            if (!streets.contains(retailer.getStreet())) {
                streets.add(retailer.getStreet());
            }
            if (!zips.contains(Integer.toString(retailer.getZipCode()))) {
                zips.add(Integer.toString(retailer.getZipCode()));
            }
        }


        mapComboRetailerType.getItems().removeAll(mapComboRetailerType.getItems());
        mapComboRetailerType.getItems().addAll(
                "Food",
                "Nightlife",
                "Shopping",
                "Personal/Professional Services",
                "Visitor Services",
                "Community Resources",
                "None"
        );
        mapComboRetailerType.getSelectionModel().select("None");

        //Initialises Map View
        final WebEngine webEngine = new WebEngine(getClass().getResource("googlemaps.html").toString());

    }
}

