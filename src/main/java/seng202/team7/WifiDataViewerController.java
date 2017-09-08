package seng202.team7;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Wifi data controller to control raw data viewing of wifi data
 * @author Aidan Smith asm142
 * Last updated 05/09/17
 */

public class WifiDataViewerController implements Initializable {

    @FXML
    private TableView<Wifi> wifiDataTable;

    @FXML private TableColumn<Wifi, String> providerColumn;
    @FXML private TableColumn<Wifi, String> typeColumn;
    @FXML private TableColumn<Wifi, String> locationColumn;
    @FXML private TableColumn<Wifi, String> boroughColumn;
    @FXML private ComboBox<String> providerCB;
    @FXML private ComboBox<String> typeCB;
    @FXML private ComboBox<String> boroughCB;

    private ObservableList<Wifi> wifiList;
    private ObservableList<Wifi> filteredWifiList;


    /**
     * Initialises the data within the table to the data provide by xxx
     * @param url Not sure what this is
     * @param rb Not sure what this is either
     */
    public void initialize(URL url, ResourceBundle rb) {
        DatabaseTester.deleteTables();
        DatabaseTester.createTables();
        DatabaseUpdater dbUpdater = new DatabaseUpdater();
        DatabaseTester.addData(dbUpdater);
        DatabaseRetriever dbRetriever = new DatabaseRetriever();
        ArrayList<Wifi> wifiArrayList = dbRetriever.getWifiList();
        wifiList = FXCollections.observableArrayList(wifiArrayList);
        filteredWifiList = FXCollections.observableArrayList(wifiList);

        providerColumn.setCellValueFactory(new PropertyValueFactory<>("provider"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        boroughColumn.setCellValueFactory(new PropertyValueFactory<>("borough"));
        wifiDataTable.setItems(filteredWifiList); //need a method to get Arraylist of retailer objects
        ArrayList<String> providers = new ArrayList<String>();
        for (Wifi wifi : wifiList) {
            if (!providers.contains(wifi.getProvider())) {
                providers.add(wifi.getProvider());
            }
        }
        providerCB.getItems().addAll(providers);
        providerCB.getItems().add("None");
    }

    /**
     * Called whenever a filter combobox is changed to filter all the loaded data again
     */
    public void filter() {
        filteredWifiList.clear();
        String providerSelection = providerCB.getValue();
        String typeSelection = typeCB.getValue();
        String boroughSelection = boroughCB.getValue();
        for (Wifi wifi : wifiList) {
            if ((wifi.getProvider().equals(providerSelection) || providerSelection == null || providerSelection.equals("None"))
                    && (wifi.getType().equals(typeSelection) || typeSelection == null || typeSelection.equals("None"))
                    && (wifi.getBorough().equals(boroughSelection) || boroughSelection == null || boroughSelection.equals("None"))
                    ) {
                filteredWifiList.add(wifi);
            }
        }
    }
}
