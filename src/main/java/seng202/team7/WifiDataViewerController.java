package seng202.team7;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Wifi data controller to control raw data viewing of wifi data
 * @author Aidan Smith asm142
 * Last updated 13/09/17
 */

public class WifiDataViewerController implements Initializable {

    // Multiple records viewer widgets
    @FXML
    private TableView<Wifi> wifiDataTable;

    @FXML private TableColumn<Wifi, String> providerColumn;
    @FXML private TableColumn<Wifi, String> typeColumn;
    @FXML private TableColumn<Wifi, String> locationColumn;
    @FXML private TableColumn<Wifi, String> boroughColumn;
    @FXML private ComboBox<String> providerCB;
    @FXML private ComboBox<String> typeCB;
    @FXML private ComboBox<String> boroughCB;

    @FXML private Text noWifiSelected;
    @FXML private AnchorPane dataViewer;
    @FXML private AnchorPane recordViewer;

    // Single record viewer widgets
    @FXML private Label providerLabel;
    @FXML private Label typeLabel;
    @FXML private Label locationLabel;
    @FXML private Label boroughLabel;
    @FXML private Label nameLabel;
    @FXML private Label remarksLabel;

    private int currentWifiIndex = -1;

    private ObservableList<Wifi> wifiList;
    private ObservableList<Wifi> filteredWifiList;

    /**
     * Initialises the data within the table to the data provided by the database retriever
     * @param url Required parameter that is not used in the function
     * @param rb Required parameter that is not used in the function
     */
    public void initialize(URL url, ResourceBundle rb) {
        //used for test data, comment out block for testing to keep data
        /*DatabaseTester.deleteTables();
        DatabaseTester.createTables();
        DatabaseUpdater dbUpdater = new DatabaseUpdater();
        DatabaseTester.addData(dbUpdater);
        */

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
     * Called when a scroll is started to add a action listener to the scrollbar
     * The action listener loads more data when the scrollbar reaches the bottom
     */
    public void addLoader() {
        ScrollBar scrollBar = (ScrollBar) wifiDataTable.lookup(".scroll-bar:vertical");
        scrollBar.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue.doubleValue() >= scrollBar.getMax()) {
                System.out.println("Load more data");
            }
        });
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

    public void view(int wifiIndex) {
        Wifi wifi = filteredWifiList.get(wifiIndex);
        providerLabel.setText(wifi.getProvider());
        typeLabel.setText(wifi.getType());
        locationLabel.setText(wifi.getLocation());
        boroughLabel.setText(wifi.getBorough());
        //are we implementing this?? nameLabel.setText(wifi.getName());
        remarksLabel.setText(wifi.getRemarks());
    }

    /**
     * Sets the currently viewed record to be the next one in the filtered wifi list
     */
    public void next() {
        currentWifiIndex += 1;
        if (currentWifiIndex >= filteredWifiList.size()) {
            currentWifiIndex = 0;
        }
        view(currentWifiIndex);
    }

    /**
     * Sets the currently viewed record to be the previous one in the filtered wifi list
     */
    public void previous() {
        currentWifiIndex -= 1;
        if (currentWifiIndex < 0) {
            currentWifiIndex = filteredWifiList.size() - 1;
        }
        view(currentWifiIndex);
    }

    /**
     * Changes the viewer from the multiple record viewer
     * to the single record viewer of the currently selected wifi
     */
    public void viewRecord() {
        currentWifiIndex = wifiDataTable.getSelectionModel().getSelectedIndex();
        if (currentWifiIndex == -1) {
            noWifiSelected.setVisible(true);
        } else {
            noWifiSelected.setVisible(false);
            dataViewer.setVisible(false);
            recordViewer.setVisible(true);
            view(currentWifiIndex);
        }
    }

    /**
     * Changes the viewer from the single record viewer
     * to the multiple record viewer
     */
    public void viewTable() {
        currentWifiIndex = -1;
        dataViewer.setVisible(true);
        recordViewer.setVisible(false);
    }
}
