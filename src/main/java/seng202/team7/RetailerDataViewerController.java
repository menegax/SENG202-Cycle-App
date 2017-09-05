package seng202.team7;


/**
 * Retailer data controller to control raw data viewing of retailer data
 * @author Aidan Smith asm142
 * Last updated 29/08/17
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RetailerDataViewerController implements Initializable {

    @FXML
    private TableView<Retailer> retailerDataTable;

    @FXML private TableColumn<Object, Object> nameColumn;
    @FXML private TableColumn<Object, Object> typeColumn;
    @FXML private TableColumn<Object, Object> addressColumn;


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
        ObservableList<Retailer> retailerList
                = FXCollections.observableArrayList(retailerArrayList
        );
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("pAddress"));
        retailerDataTable.setItems(retailerList); //need a method to get Arraylist of retailer objects
    }

}
