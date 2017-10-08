package seng202.team7.Controllers.MainWindowControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import seng202.team7.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Wifi data controller to control raw data viewing of wifi data
 * @author Aidan Smith asm142
 * Last updated 22/09/17
 */

public class WifiDataViewerController implements Initializable {

    // Main Containers
    @FXML private AnchorPane dataViewer;
    @FXML private AnchorPane recordViewer;
    @FXML private AnchorPane editor;

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
    @FXML private TextField searchEntry;
    @FXML private Text error;


    // Single record viewer widgets
    @FXML private Label providerLabel;
    @FXML private Label typeLabel;
    @FXML private Label locationLabel;
    @FXML private Label boroughLabel;
    @FXML private Label remarksLabel;
    @FXML private Label dataGroupLabel;

    // Editor widgets
    @FXML private TextField providerEntry;
    @FXML private ComboBox<String> typeEntry;
    @FXML private TextArea locationEntry;
    @FXML private ComboBox<String> boroughEntry;
    @FXML private TextArea remarksEntry;

    // Important attributes for functionality
    private int currentWifiIndex = -1;
    private int loadedData = 0;
    private DatabaseRetriever dbRetriever;
    private DatabaseUpdater dbUpdater;
    private boolean loadedAll = false;
    private boolean scrollAdded = false;

    private ObservableList<Wifi> wifiList;
    private ObservableList<Wifi> filteredWifiList;

    /**
     * Initialises the data within the table to the data provided by the database retriever
     * @param url Required parameter that is not used in the function
     * @param rb Required parameter that is not used in the function
     */
    public void initialize(URL url, ResourceBundle rb) {
        /**
         * Used for testing only
         * DatabaseTester.deleteTables();
         * DatabaseTester.createTables();
         */

        dbUpdater = new DatabaseUpdater();
        dbRetriever = new DatabaseRetriever();
        ArrayList<Wifi> wifiArrayList = dbRetriever.queryWifi(StaticVariables.steppedQuery(Wifi.tableName, loadedData));
        wifiList = FXCollections.observableArrayList(wifiArrayList);
        filteredWifiList = FXCollections.observableArrayList(wifiList);
        if (wifiList.size() < 50) {
            loadedAll = true;
        }

        providerColumn.setCellValueFactory(new PropertyValueFactory<>("provider"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        boroughColumn.setCellValueFactory(new PropertyValueFactory<>("borough"));
        wifiDataTable.setItems(filteredWifiList);
        ArrayList<String> providers = new ArrayList<>();
        for (Wifi wifi : wifiList) {
            if (!providers.contains(wifi.getProvider())) {
                providers.add(wifi.getProvider());
            }
        }
        providerCB.getItems().add("All");
        providerCB.getItems().addAll(providers);
    }

    /**
     * Called when the cursor enters the data table to add a action listener to the scrollbar
     * The action listener loads more data when the scrollbar nears the bottom (80%)
     */
    public void addLoader() {
        if (scrollAdded || wifiList.size() < StaticVariables.step) {
            wifiDataTable.setOnMouseEntered(null);
        } else {
            ScrollBar scrollBar = (ScrollBar) wifiDataTable.lookup(".scroll-bar:vertical");
            scrollBar.valueProperty().addListener((obs, oldValue, newValue) -> {
                if (newValue.doubleValue() >= scrollBar.getMax() - 0.2) {
                    if (!loadedAll) {
                        ArrayList<Wifi> wifiArrayList = dbRetriever.queryWifi(StaticVariables.steppedQuery(Wifi.tableName, loadedData));
                        if (wifiArrayList.size() == 0) {
                            loadedAll = true;
                        }
                        for (Wifi wifi : wifiArrayList) {
                            if (!providerCB.getItems().contains(wifi.getProvider())) {
                                providerCB.getItems().add(providerCB.getItems().size() - 2, wifi.getProvider());
                            }
                        }
                        wifiList.addAll(wifiArrayList);
                        loadedData += StaticVariables.step;
                        filter();
                    }
                }
            });
            scrollAdded = true;
        }
    }

    /**
     * Called whenever a filter combobox is changed to filter all the loaded data again
     */
    public void filter() {
        error.setVisible(false);
        filteredWifiList.clear();
        String providerSelection = providerCB.getValue();
        String typeSelection = typeCB.getValue();
        String boroughSelection = boroughCB.getValue();
        for (Wifi wifi : wifiList) {
            if ((wifi.getProvider().equals(providerSelection) || providerSelection == null || providerSelection.equals("All"))
                    && (wifi.getType().equals(typeSelection) || typeSelection == null || typeSelection.equals("All"))
                    && (wifi.getBorough().equals(boroughSelection) || boroughSelection == null || boroughSelection.equals("All"))
                    ) {
                filteredWifiList.add(wifi);
            }
        }
        while (filteredWifiList.size() < 50 && !loadedAll) {
            ArrayList<Wifi> wifiArrayList = dbRetriever.queryWifi(StaticVariables.steppedQuery(Wifi.tableName, loadedData));
            if (wifiArrayList.size() == 0) {
                loadedAll = true;
            }
            for (Wifi wifi : wifiArrayList) {
                if (!providerCB.getItems().contains(wifi.getProvider())) {
                    providerCB.getItems().add(providerCB.getItems().size() - 2, wifi.getProvider());
                }
            }
            wifiList.addAll(wifiArrayList);
            loadedData += StaticVariables.step;
            for (Wifi wifi : wifiList) {
                if ((wifi.getProvider().equals(providerSelection) || providerSelection == null || providerSelection.equals("All"))
                        && (wifi.getType().equals(typeSelection) || typeSelection == null || typeSelection.equals("All"))
                        && (wifi.getBorough().equals(boroughSelection) || boroughSelection == null || boroughSelection.equals("All"))
                        ) {
                    filteredWifiList.add(wifi);
                }
            }
        }
    }

    /**
     * Updates the attributes of the single record viewer with the attributes of the wifi
     * provided through the retailerIndex
     * @param wifiIndex The index of the wifi to be displayed
     */
    public void view(int wifiIndex) {
        Wifi wifi = filteredWifiList.get(wifiIndex);
        providerLabel.setText(wifi.getProvider());
        typeLabel.setText(wifi.getType());
        locationLabel.setText(wifi.getLocation());
        boroughLabel.setText(wifi.getBorough());
        remarksLabel.setText(wifi.getRemarks());
        dataGroupLabel.setText(wifi.getDataGroup());
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
            error.setText("Please select a retailer to view");
            error.setVisible(true);
        } else {
            error.setVisible(false);
            dataViewer.setVisible(false);
            editor.setVisible(false);
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

    /**
     * Brings up the edit page on the currently selected wifi
     */
    public void viewEdit() {
        recordViewer.setVisible(false);
        editor.setVisible(true);
        Wifi wifi = filteredWifiList.get(currentWifiIndex);
        providerEntry.setText(wifi.getProvider());
        typeEntry.getSelectionModel().select(wifi.getType());
        locationEntry.setText(wifi.getLocation());
        boroughEntry.getSelectionModel().select(wifi.getBorough());
        remarksEntry.setText(wifi.getRemarks());
    }

    /**
     * Makes the provided changes to the selected wifi
     */
    public void confirmEdit(){
        Wifi wifi = filteredWifiList.get(currentWifiIndex);
        wifi.setProvider(providerEntry.getText());
        wifi.setType(typeEntry.getValue());
        wifi.setLocation(locationEntry.getText());
        wifi.setBorough(boroughEntry.getValue());
        wifi.setRemarks(remarksEntry.getText());
        dbUpdater.updateWifi(wifi);
        viewRecord();
    }

    /**
     * Searches through the entire wifi list for matches to the search entry and then displays them
     */
    public void search() {
        if (searchEntry.getText().isEmpty()) {
            error.setText("No search entered");
            error.setVisible(true);
        } else {
            error.setVisible(false);
            String query = StaticVariables.singleStringQueryLike(Wifi.tableName, "provider", searchEntry.getText());
            ArrayList<Wifi> result = dbRetriever.queryWifi(query);
            wifiList = FXCollections.observableArrayList(result);
            loadedAll = true;
            filter();
        }
    }

    /**
     * Resets the search so that the records are no longer filtered by the search criteria
     */
    public void reset() {
        loadedAll = false;
        loadedData = 0;
        ArrayList<Wifi> wifiArrayList = dbRetriever.queryWifi(StaticVariables.steppedQuery(Wifi.tableName, loadedData));
        for (Wifi wifi : wifiArrayList) {
            if (!providerCB.getItems().contains(wifi.getProvider())) {
                providerCB.getItems().add(providerCB.getItems().size() - 2, wifi.getProvider());
            }
        }
        wifiList = FXCollections.observableArrayList(wifiArrayList);
        searchEntry.setText("");
        if (wifiList.size() < 50) {
            loadedAll = true;
        }
        filter();
    }
}
