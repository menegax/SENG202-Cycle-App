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
 * Trip data controller to control raw data viewing of trip data
 * @author Aidan Smith asm142
 * Last updated 29/08/17
 */

public class TripDataViewerController implements Initializable {

    @FXML
    private TableView<Trip> tripDataTable;

    @FXML private TableColumn<Trip, String> startColumn;
    @FXML private TableColumn<Trip, String> endColumn;
    @FXML private TableColumn<Trip, String> durationColumn;


    private ObservableList<Trip> tripList
            = FXCollections.observableArrayList(
            new Trip(new Station(231,"5th ave", "CitiBike", 2387.987, 384.98), new Station(231,"5th ave", "CitiBike", 2387.987, 384.98), 10),
            new Trip(new Station(231,"5th ave", "CitiBike", 2387.987, 384.98), new Station(231,"5th ave", "CitiBike", 2387.987, 384.98), 12),
            new Trip(new Station(231,"5th ave", "CitiBike", 2387.987, 384.98), new Station(231,"5th ave", "CitiBike", 2387.987, 384.98), 13),
            new Trip(new Station(231,"5th ave", "CitiBike", 2387.987, 384.98), new Station(231,"5th ave", "CitiBike", 2387.987, 384.98), 17),
            new Trip(new Station(231,"5th ave", "CitiBike", 2387.987, 384.98), new Station(231,"5th ave", "CitiBike", 2387.987, 384.98), 2)
    );

    /**
     * Initialises the data within the table to the data provide by xxx
     * @param url Not sure what this is
     * @param rb Not sure what this is either
     */
    public void initialize(URL url, ResourceBundle rb) {
        startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        tripDataTable.setItems(tripList); //need a method to get Arraylist of retailer objects
    }
}
