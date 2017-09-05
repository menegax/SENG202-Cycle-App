package seng202.team7;


/**
 * Retailer data controller to control raw data viewing of retailer data
 * @author Aidan Smith asm142
 * Last updated 05/09/17
 */

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

public class RetailerDataViewerController implements Initializable {

    @FXML
    private TableView<Retailer> retailerDataTable;

    @FXML private TableColumn<Retailer, String> nameColumn;
    @FXML private TableColumn<Retailer, String> typeColumn;
    @FXML private TableColumn<Retailer, String> addressColumn;
    @FXML private TableColumn<Retailer, Integer> zipColumn;
    @FXML private ComboBox<String> typeCB;
    @FXML private ComboBox<String> streetCB;
    @FXML private ComboBox<String> zipCB;

    private ObservableList<Retailer> retailerList;
    private ObservableList<Retailer> filteredRetailerList;

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
        ArrayList<Retailer> retailerArrayList = dbRetriever.getRetailerList();
        retailerList = FXCollections.observableArrayList(retailerArrayList);
        filteredRetailerList = FXCollections.observableArrayList(retailerList);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("typeID"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("pAddress"));
        zipColumn.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
        retailerDataTable.setItems(filteredRetailerList); //need a method to get Arraylist of retailer objects
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

}
