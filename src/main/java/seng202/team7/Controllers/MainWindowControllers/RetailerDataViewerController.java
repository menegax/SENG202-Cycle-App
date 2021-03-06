package seng202.team7.Controllers.MainWindowControllers;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import seng202.team7.DataTypes.Datagroup;
import seng202.team7.DataTypes.Retailer;
import seng202.team7.DataTypes.StaticVariables;
import seng202.team7.Database.DatabaseRetriever;
import seng202.team7.Database.DatabaseUpdater;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Retailer data controller to control raw data viewing of retailer data
 * @author Aidan Smith asm142
 * Last updated 09/10/17
 */
public class RetailerDataViewerController implements Initializable {

    // Main containers
    @FXML private AnchorPane dataViewer;
    @FXML private AnchorPane recordViewer;
    @FXML private AnchorPane editor;

    // Multiple records viewer widgets
    @FXML private TableView<Retailer> retailerDataTable;
    @FXML private TableColumn<Retailer, String> nameColumn;
    @FXML private TableColumn<Retailer, String> typeColumn;
    @FXML private TableColumn<Retailer, String> addressColumn;
    @FXML private TableColumn<Retailer, Integer> zipColumn;
    @FXML private TableColumn<Retailer, String> dataGroupColumn;
    @FXML private ComboBox<String> typeCB;
    @FXML private ComboBox<String> streetCB;
    @FXML private ComboBox<String> zipCB;
    @FXML private ComboBox<String> dataGroupCB;
    @FXML private TextField searchEntry;
    @FXML private Text error;

    // Single record viewer widgets
    @FXML private Label nameLabel;
    @FXML private Label addressLabel;
    @FXML private Label extraAddressLabel;
    @FXML private Label zipLabel;
    @FXML private Label pTypeLabel;
    @FXML private Label sTypeLabel;
    @FXML private Label dataGroupLabel;

    // Editor widgets
    @FXML TextField nameEntry;
    @FXML TextField addressEntry;
    @FXML TextArea extraAddressEntry;
    @FXML TextField zipEntry;
    @FXML TextField pTypeEntry;
    @FXML ComboBox<String> sTypeEntry;

    // Important attributes for functionality
    private int currentRetailerIndex = -1;
    private int loadedData = 0;
    private DatabaseRetriever dbRetriever;
    private DatabaseUpdater dbUpdater;
    private boolean loadedAll = false;
    private boolean scrollAdded = false;
    private ObservableList<Retailer> retailerList;
    private ObservableList<Retailer> filteredRetailerList;

    /**
     * Initialises the data within the table to the data provided by the database retriever
     * @param url Required parameter that is not used in the function
     * @param rb  Required parameter that is not used in the function
     */
    public void initialize(URL url, ResourceBundle rb) {
        dbUpdater = new DatabaseUpdater();
        dbRetriever = new DatabaseRetriever();
        ArrayList<Retailer> retailerArrayList = dbRetriever.queryRetailer(StaticVariables.steppedQuery(Retailer.tableName, loadedData));
        retailerList = FXCollections.observableArrayList(retailerArrayList);
        filteredRetailerList = FXCollections.observableArrayList(retailerList);
        if (retailerList.size() < 50) {
            loadedAll = true;
        }

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("typeID"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("pAddress"));
        zipColumn.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
        dataGroupColumn.setCellValueFactory(new PropertyValueFactory<>("dataGroup"));
        retailerDataTable.setItems(filteredRetailerList);
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
        ArrayList<String> datagroups = Datagroup.getDatagroups();
        streetCB.getItems().add("All");
        streetCB.getItems().addAll(streets);
        zipCB.getItems().add("All");
        zipCB.getItems().addAll(zips);
        dataGroupCB.getItems().add("All");
        dataGroupCB.getItems().addAll(datagroups);
    }

    /**
     * Called when the cursor enters the data table to add a action listener to the scrollbar
     * The action listener loads more data when the scrollbar nears the bottom (80%)
     */
    public void addLoader() {
        if (scrollAdded || retailerList.size() < StaticVariables.step) {
            retailerDataTable.setOnMouseEntered(null);
        } else {
            ScrollBar scrollBar = (ScrollBar) retailerDataTable.lookup(".scroll-bar:vertical");
            scrollBar.valueProperty().addListener((obs, oldValue, newValue) -> {
                if (newValue.doubleValue() >= scrollBar.getMax() - 0.2) {
                    if (!loadedAll) {
                        ArrayList<Retailer> retailerArrayList = dbRetriever.queryRetailer(StaticVariables.steppedQuery(Retailer.tableName, loadedData));
                        if (retailerArrayList.size() == 0) {
                            loadedAll = true;
                        }
                        for (Retailer retailer : retailerArrayList) {
                            if (!streetCB.getItems().contains(retailer.getStreet())) {
                                streetCB.getItems().add(streetCB.getItems().size() - 2, retailer.getStreet());
                            }
                            if (!zipCB.getItems().contains(Integer.toString(retailer.getZipCode()))) {
                                zipCB.getItems().add(zipCB.getItems().size() - 2, Integer.toString(retailer.getZipCode()));
                            }
                        }
                        retailerList.addAll(retailerArrayList);
                        loadedData += StaticVariables.step;
                        filter();
                    }
                }
            });
            scrollAdded = true;
        }
    }

    /**
     * Called whenever a filter combo box is changed to filter all the loaded data again
     */
    public void filter() {
        error.setVisible(false);
        filteredRetailerList.clear();
        String typeSelection = typeCB.getValue();
        String streetSelection = streetCB.getValue();
        String zipSelection = zipCB.getValue();
        String dataGroupSelection = dataGroupCB.getValue();
        for (Retailer retailer : retailerList) {
            String typeID = retailer.getTypeID();
            if (typeID == null) {
                typeID = "Null";
            }
            else if ((streetSelection == null || retailer.getStreet().equals(streetSelection) || streetSelection.equals("All"))
                    && (typeSelection == null || typeID.equals(typeSelection) || typeSelection.equals("All"))
                    && (zipSelection == null || Integer.toString(retailer.getZipCode()).equals(zipSelection) || zipSelection.equals("All"))
                    && (dataGroupSelection == null || retailer.getDataGroup().equals(dataGroupSelection) || dataGroupSelection.equals("All"))
                    ) {
                filteredRetailerList.add(retailer);
            }
        }
        while (filteredRetailerList.size() < 50 && !loadedAll) {
            ArrayList<Retailer> retailerArrayList = dbRetriever.queryRetailer(StaticVariables.steppedQuery(Retailer.tableName, loadedData));
            if (retailerArrayList.size() == 0) {
                loadedAll = true;
            }
            for (Retailer retailer : retailerArrayList) {
                if (!streetCB.getItems().contains(retailer.getStreet())) {
                    streetCB.getItems().add(streetCB.getItems().size() - 2, retailer.getStreet());
                }
                if (!zipCB.getItems().contains(Integer.toString(retailer.getZipCode()))) {
                    zipCB.getItems().add(zipCB.getItems().size() - 2, Integer.toString(retailer.getZipCode()));
                }
            }
            retailerList.addAll(retailerArrayList);
            loadedData += StaticVariables.step;
            for (Retailer retailer : retailerList) {
                String typeID = retailer.getTypeID();
                if (typeID == null) {
                    typeID = "Null";
                }
                if ((retailer.getStreet().equals(streetSelection) || streetSelection == null || streetSelection.equals("All"))
                        && (typeID.equals(typeSelection) || typeSelection == null || typeSelection.equals("All"))
                        && (Integer.toString(retailer.getZipCode()).equals(zipSelection) || zipSelection == null || zipSelection.equals("All"))
                        && (dataGroupSelection == null || retailer.getDataGroup().equals(dataGroupSelection) || dataGroupSelection.equals("All"))
                        ) {
                    filteredRetailerList.add(retailer);
                }
            }
        }
    }

    /**
     * Updates the attributes of the single record viewer with the attributes of the retailer
     * provided through the retailerIndex
     * @param retailerIndex The index of the retailer to be displayed
     */
    public void view(int retailerIndex) {
        Retailer retailer = filteredRetailerList.get(retailerIndex);
        nameLabel.setText(retailer.getName());
        addressLabel.setText(retailer.getPAddress());
        extraAddressLabel.setText(retailer.getSAddress());
        zipLabel.setText(Integer.toString(retailer.getZipCode()));
        pTypeLabel.setText(retailer.getType());
        sTypeLabel.setText(retailer.getTypeID());
        dataGroupLabel.setText(retailer.getDataGroup());
    }

    /**
     * Sets the currently viewed record to be the next one in the filtered retailer list
     */
    public void next() {
        currentRetailerIndex += 1;
        if (currentRetailerIndex >= filteredRetailerList.size()) {
            currentRetailerIndex = 0;
        }
        view(currentRetailerIndex);
    }

    /**
     * Sets the currently viewed record to be the previous one in the filtered retailer list
     */
    public void previous() {
        currentRetailerIndex -= 1;
        if (currentRetailerIndex < 0) {
            currentRetailerIndex = filteredRetailerList.size() - 1;
        }
        view(currentRetailerIndex);
    }

    /**
     * Changes the viewer from the multiple record viewer
     * to the single record viewer of the currently selected retailer
     */
    public void viewRecord() {
        currentRetailerIndex = retailerDataTable.getSelectionModel().getSelectedIndex();
        if (currentRetailerIndex == -1) {
            error.setText("Please select a retailer to view");
            error.setVisible(true);
        } else {
            error.setVisible(false);
            editor.setVisible(false);
            dataViewer.setVisible(false);
            recordViewer.setVisible(true);
            view(currentRetailerIndex);
        }
    }

    /**
     * Changes the viewer from the single record viewer
     * to the multiple record viewer
     */
    public void viewTable() {
        currentRetailerIndex = -1;
        dataViewer.setVisible(true);
        recordViewer.setVisible(false);
    }

    /**
     * Brings up the edit page on the currently selected retailer
     */
    public void viewEdit() {
        recordViewer.setVisible(false);
        editor.setVisible(true);
        Retailer retailer = filteredRetailerList.get(currentRetailerIndex);
        nameEntry.setText(retailer.getName());
        addressEntry.setText(retailer.getPAddress());
        extraAddressEntry.setText(retailer.getSAddress());
        zipEntry.setText(Integer.toString(retailer.getZipCode()));
        // Formatting zip entry to only accept integers
        zipEntry.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    String formatted = "";
                    for (int i = 0; i < newValue.length() && i < 5; i++) {
                        if (Character.isDigit(newValue.charAt(i))) {
                            formatted += newValue.charAt(i);
                        }
                    }
                    ((StringProperty)observable).setValue(formatted);
                }
        );
        pTypeEntry.setText(retailer.getType());
        sTypeEntry.getSelectionModel().select(retailer.getTypeID());
    }

    /**
     * Makes the provided changes to the selected retailer
     */
    public void confirmEdit(){
        Retailer retailer = filteredRetailerList.get(currentRetailerIndex);
        int id = retailer.hashCode();
        retailer.setName(nameEntry.getText());
        retailer.setPAddress(addressEntry.getText());
        retailer.setSAddress(extraAddressEntry.getText());
        retailer.setZipCode(Integer.parseInt(zipEntry.getText()));
        retailer.setType(pTypeEntry.getText());
        retailer.setTypeID(sTypeEntry.getValue());
        dbUpdater.updateRetailer(retailer, id);
        viewRecord();
    }

    /**
     * Searches through the entire retailer list for matches to the search entry and then displays them
     */
    public void search() {
        if (searchEntry.getText().isEmpty()) {
            error.setText("No search entered");
            error.setVisible(true);
        } else {
            error.setVisible(false);
            String query = StaticVariables.singleStringQueryLike(Retailer.tableName, "name", searchEntry.getText());
            ArrayList<Retailer> result = dbRetriever.queryRetailer(query);
            retailerList = FXCollections.observableArrayList(result);
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
        ArrayList<Retailer> retailerArrayList = dbRetriever.queryRetailer(StaticVariables.steppedQuery(Retailer.tableName, loadedData));
        for (Retailer retailer : retailerArrayList) {
            if (!streetCB.getItems().contains(retailer.getStreet())) {
                streetCB.getItems().add(streetCB.getItems().size() - 2, retailer.getStreet());
            }
            if (!zipCB.getItems().contains(Integer.toString(retailer.getZipCode()))) {
                zipCB.getItems().add(zipCB.getItems().size() - 2, Integer.toString(retailer.getZipCode()));
            }
        }
        retailerList = FXCollections.observableArrayList(retailerArrayList);
        if (retailerList.size() < 50) {
            loadedAll = true;
        }
        searchEntry.setText("");
        filter();
    }
}