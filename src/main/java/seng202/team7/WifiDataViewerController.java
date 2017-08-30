package seng202.team7;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Wifi data controller to control raw data viewing of wifi data
 * @author Aidan Smith asm142
 * Last updated 29/08/17
 */

public class WifiDataViewerController implements Initializable {

    @FXML
    private TableView<Wifi> wifiDataTable;

    @FXML private TableColumn<Wifi, String> providerColumn;
    @FXML private TableColumn<Wifi, String> typeColumn;
    @FXML private TableColumn<Wifi, String> locationColumn;
    @FXML private TableColumn<Wifi, String> buroughColumn;


    private ObservableList<Wifi> wifiList
            = FXCollections.observableArrayList(
                new Wifi("BO", "Limited free","Alcatel","5th Ave","NY","Alcatel Hotspot","","",234.324,324.554),
                new Wifi("BO", "Free","Alcatel","5th Ave","NY","Alcatel Hotspot","","",234.324,324.554),
                new Wifi("BO", "Subscription","Alcatel","5th Ave","NY","Alcatel Hotspot","","",234.324,324.554)
    );

    /**
     * Initialises the data within the table to the data provide by xxx
     * @param url Not sure what this is
     * @param rb Not sure what this is either
     */
    public void initialize(URL url, ResourceBundle rb) {
        providerColumn.setCellValueFactory(new PropertyValueFactory<>("provider"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        buroughColumn.setCellValueFactory(new PropertyValueFactory<>("burough"));
        wifiDataTable.setItems(wifiList); //need a method to get Arraylist of retailer objects
    }
}
