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
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Trip data controller to control raw data viewing of trip data
 * @author Aidan Smith asm142
 * Last updated 13/09/17
 */

public class TripDataViewerController implements Initializable {

    // Multiple records viewer widgets
    @FXML
    private TableView<Trip> tripDataTable;

    @FXML private TableColumn<Trip, String> startColumn;
    @FXML private TableColumn<Trip, String> endColumn;
    @FXML private TableColumn<Trip, String> durationColumn;
    @FXML private TableColumn<Trip, String> genderColumn;
    @FXML private TableColumn<Trip, String> userTypeColumn;
    @FXML private ComboBox<String> startStationCB;
    @FXML private ComboBox<String> endStationCB;
    @FXML private ComboBox<String> genderCB;
    @FXML private ComboBox<String> userTypeCB;

    @FXML private Text noTripSelected;
    @FXML private AnchorPane dataViewer;
    @FXML private AnchorPane recordViewer;

    // Single record viewer widgets

    @FXML private Label startNameLabel;
    @FXML private Label endNameLabel;
    @FXML private Label startIDLabel;
    @FXML private Label endIDLabel;
    @FXML private Label durationLabel;
    @FXML private Label startTimeLabel;
    @FXML private Label endTimeLabel;
    @FXML private Label bikeIDLabel;
    @FXML private Label userTypeLabel;
    @FXML private Label birthYearLabel;
    @FXML private Label genderLabel;

    private int currentTripIndex = -1;

    private ObservableList<Trip> tripList;
    private ObservableList<Trip> filteredTripList;
    /**
     * Initialises the data within the table to the data provided by the database retriever
     * @param url Required parameter that is not used in the function
     * @param rb Required parameter that is not used in the function
     */
    public void initialize(URL url, ResourceBundle rb) {
        //used for test data, comment out block for testing
        /*DatabaseTester.deleteTables();
        DatabaseTester.createTables();
        DatabaseUpdater dbUpdater = new DatabaseUpdater();
        DatabaseTester.addData(dbUpdater);
        */

        DatabaseRetriever dbRetriever = new DatabaseRetriever();
        ArrayList<Trip> tripArrayList = dbRetriever.getTripList();
        tripList = FXCollections.observableArrayList(tripArrayList);
        filteredTripList = FXCollections.observableArrayList(tripList);

        startStationCB.getItems().addAll("5th ave","34 square","None");
        endStationCB.getItems().addAll("5th ave","34 square","None");
        startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        userTypeColumn.setCellValueFactory(new PropertyValueFactory<>("userType"));
        tripDataTable.setItems(filteredTripList); //need a method to get Arraylist of retailer objects
    }

    /**
     * Called when a scroll is started to add a action listener to the scrollbar
     * The action listener loads more data when the scrollbar reaches the bottom
     */
    public void addLoader() {
        ScrollBar scrollBar = (ScrollBar) tripDataTable.lookup(".scroll-bar:vertical");
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
        filteredTripList.clear();
        String startSelection = startStationCB.getValue();
        String endSelection = endStationCB.getValue();
        String genderSelection = genderCB.getValue();
        String userTypeSelection = userTypeCB.getValue();
        for (Trip trip : tripList) {
            if ((trip.getStart().equals(startSelection) || startSelection == null || startSelection.equals("None"))
                    && (trip.getEnd().equals(endSelection) || endSelection == null || endSelection.equals("None"))
                    && (trip.getGender().equals(genderSelection) || genderSelection == null || genderSelection.equals("None"))
                    && (trip.getUserType().equals(userTypeSelection) || userTypeSelection == null || userTypeSelection.equals("None"))
                    ) {
                filteredTripList.add(trip);
            }
        }
    }

    public void view(int tripIndex) {
        Trip trip = filteredTripList.get(tripIndex);
        startNameLabel.setText(trip.getStartStation().getAddress());
        endNameLabel.setText(trip.getEndStation().getAddress());
        startIDLabel.setText(Integer.toString(trip.getStartStationID()));
        endIDLabel.setText(Integer.toString(trip.getEndStationID()));
        durationLabel.setText(Integer.toString(trip.getDuration()));
        startTimeLabel.setText(String.valueOf(trip.getStartDate()));
        endTimeLabel.setText(String.valueOf(trip.getEndDate()));
        bikeIDLabel.setText(Integer.toString(trip.getBikeID()));
        userTypeLabel.setText(trip.getUserType());
        // need to change this birthYearLabel.setText(trip.getAge());
        genderLabel.setText(trip.getGender());
    }

    /**
     * Sets the currently viewed record to be the next one in the filtered trip list
     */
    public void next() {
        currentTripIndex += 1;
        if (currentTripIndex >= filteredTripList.size()) {
            currentTripIndex = 0;
        }
        view(currentTripIndex);
    }

    /**
     * Sets the currently viewed record to be the previous one in the filtered trip list
     */
    public void previous() {
        currentTripIndex -= 1;
        if (currentTripIndex < 0) {
            currentTripIndex = filteredTripList.size() - 1;
        }
        view(currentTripIndex);
    }

    /**
     * Changes the viewer from the multiple record viewer
     * to the single record viewer of the currently selected trip
     */
    public void viewRecord() {
        currentTripIndex = tripDataTable.getSelectionModel().getSelectedIndex();
        if (currentTripIndex == -1) {
            noTripSelected.setVisible(true);
        } else {
            noTripSelected.setVisible(false);
            dataViewer.setVisible(false);
            recordViewer.setVisible(true);
            view(currentTripIndex);
        }
    }

    /**
     * Changes the viewer from the single record viewer
     * to the multiple record viewer
     */
    public void viewTable() {
        currentTripIndex = -1;
        dataViewer.setVisible(true);
        recordViewer.setVisible(false);
    }
}
