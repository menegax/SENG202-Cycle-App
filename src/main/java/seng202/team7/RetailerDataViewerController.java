package seng202.team7;


/**
 * Retailer data controller to control raw data viewing of retailer data
 * @author Aidan Smith asm142
 * Last updated 13/09/17
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RetailerDataViewerController implements Initializable {

    // Multiple records viewer widgets
    @FXML
    private TableView<Retailer> retailerDataTable;

    @FXML
    private TableColumn<Retailer, String> nameColumn;
    @FXML
    private TableColumn<Retailer, String> typeColumn;
    @FXML
    private TableColumn<Retailer, String> addressColumn;
    @FXML
    private TableColumn<Retailer, Integer> zipColumn;
    @FXML
    private ComboBox<String> typeCB;
    @FXML
    private ComboBox<String> streetCB;
    @FXML
    private ComboBox<String> zipCB;
    @FXML
    private Text noRetailerSelected;

    @FXML
    private AnchorPane dataViewer;
    @FXML private AnchorPane recordViewer;


    // Single record viewer widgets
    @FXML
    private Label nameLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label extraAddressLabel;
    @FXML
    private Label zipLabel;
    @FXML
    private Label pTypeLabel;
    @FXML
    private Label sTypeLabel;

    private int currentRetailerIndex = -1;

    private ObservableList<Retailer> retailerList;
    private ObservableList<Retailer> filteredRetailerList;

    /**
     * Initialises the data within the table to the data provide by xxx
     *
     * @param url Required parameter that is not used in the function
     * @param rb  Required parameter that is not used in the function
     */
    public void initialize(URL url, ResourceBundle rb) {
        DatabaseTester.deleteTables();
        DatabaseTester.createTables();
        DatabaseUpdater dbUpdater = new DatabaseUpdater();
        DatabaseTester.addData(dbUpdater);
        DatabaseRetriever dbRetriever = new DatabaseRetriever();
        ArrayList<Retailer> retailerArrayList = dbRetriever.getRetailerList();
        retailerList = FXCollections.observableArrayList(retailerArrayList);
        filteredRetailerList = FXCollections.observableArrayList(retailerList);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("typeID"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("pAddress"));
        zipColumn.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
        retailerDataTable.setItems(filteredRetailerList);
        ArrayList<String> streets = new ArrayList<String>();
        ArrayList<String> zips = new ArrayList<String>();
        for (Retailer retailer : retailerList) {
            if (!streets.contains(retailer.getStreet())) {
                streets.add(retailer.getStreet());
            }
            if (!zips.contains(Integer.toString(retailer.getZipCode()))) {
                zips.add(Integer.toString(retailer.getZipCode()));
            }
        }
        streetCB.getItems().addAll(streets);
        streetCB.getItems().add("None");
        zipCB.getItems().addAll(zips);
        zipCB.getItems().add("None");
    }

    /**
     * Called when a scroll is started to add a action listener to the scrollbar
     * The action listener loads more data when the scrollbar reaches the bottom
     */
    public void addLoader() {
        ScrollBar scrollBar = (ScrollBar) retailerDataTable.lookup(".scroll-bar:vertical");
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
        filteredRetailerList.clear();
        String typeSelection = typeCB.getValue();
        String streetSelection = streetCB.getValue();
        String zipSelection = zipCB.getValue();
        for (Retailer retailer : retailerList) {
            if ((retailer.getStreet().equals(streetSelection) || streetSelection == null || streetSelection.equals("None"))
                    && (retailer.getTypeID().equals(typeSelection) || typeSelection == null || typeSelection.equals("None"))
                    && (Integer.toString(retailer.getZipCode()).equals(zipSelection) || zipSelection == null || zipSelection.equals("None"))
                    ) {
                filteredRetailerList.add(retailer);
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
            noRetailerSelected.setVisible(true);
        } else {
            noRetailerSelected.setVisible(false);
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
}