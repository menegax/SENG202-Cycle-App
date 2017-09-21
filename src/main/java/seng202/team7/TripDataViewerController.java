package seng202.team7;


import javafx.beans.property.StringProperty;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Trip data controller to control raw data viewing of trip data
 * @author Aidan Smith asm142
 * Last updated 17/09/17
 */

public class TripDataViewerController implements Initializable {

    // Main Containers
    @FXML private AnchorPane dataViewer;
    @FXML private AnchorPane recordViewer;
    @FXML private AnchorPane editor;

    // Multiple records viewer widgets
    @FXML private TableView<Trip> tripDataTable;
    @FXML private TableColumn<Trip, String> startColumn;
    @FXML private TableColumn<Trip, String> endColumn;
    @FXML private TableColumn<Trip, String> durationColumn;
    @FXML private TableColumn<Trip, String> genderColumn;
    @FXML private TableColumn<Trip, String> userTypeColumn;
    @FXML private ComboBox<String> startStationCB;
    @FXML private ComboBox<String> endStationCB;
    @FXML private ComboBox<String> genderCB;
    @FXML private ComboBox<String> userTypeCB;
    @FXML private TextField searchEntry;
    @FXML private Text error;


    // Single record viewer widgets

    @FXML private Label startNameLabel;
    @FXML private Label endNameLabel;
    @FXML private Label startIDLabel;
    @FXML private Label endIDLabel;
    @FXML private Label durationLabel;
    @FXML private Label startDateLabel;
    @FXML private Label endDateLabel;
    @FXML private Label startTimeLabel;
    @FXML private Label endTimeLabel;
    @FXML private Label bikeIDLabel;
    @FXML private Label userTypeLabel;
    @FXML private Label ageLabel;
    @FXML private Label genderLabel;

    // Editor widgets
    @FXML private TextField startNameEntry;
    @FXML private TextField endNameEntry;
    @FXML private TextField startIDEntry;
    @FXML private TextField endIDEntry;
    @FXML private TextField startTimeEntry;
    @FXML private TextField endTimeEntry;
    @FXML private DatePicker startDateEntry;
    @FXML private DatePicker endDateEntry;
    @FXML private TextField bikeIDEntry;
    @FXML private ComboBox<String> userTypeEntry;
    @FXML private TextField ageEntry;
    @FXML private ComboBox<String> genderEntry;
    @FXML private Text incorrectFormat;
    @FXML private Text formatTimes;

    // Important attributes for functionality
    private int currentTripIndex = -1;
    private int loadedData = 0;
    private DatabaseRetriever dbRetriever;
    private DatabaseUpdater dbUpdater;
    private boolean loadedAll = false;
    private boolean scrollAdded = false;

    private ObservableList<Trip> tripList;
    private ObservableList<Trip> filteredTripList;

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
        ArrayList<Trip> tripArrayList = dbRetriever.queryTrip(StaticVariables.steppedQuery(Trip.tableName, loadedData));
        tripList = FXCollections.observableArrayList(tripArrayList);
        filteredTripList = FXCollections.observableArrayList(tripList);
        if (tripList.size() < 50) {
            loadedAll = true;
        }
        ArrayList<Station> stationArrayList = dbRetriever.getStationList();
        ArrayList<String> stationAddresses = new ArrayList<>();
        for (Station station : stationArrayList) {
            stationAddresses.add(station.getAddress());
        }
        startStationCB.getItems().addAll(stationAddresses);
        endStationCB.getItems().addAll(stationAddresses);
        startStationCB.getItems().add("None");
        endStationCB.getItems().add("None");
        startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        userTypeColumn.setCellValueFactory(new PropertyValueFactory<>("userType"));
        tripDataTable.setItems(filteredTripList); //need a method to get Arraylist of retailer objects
    }

    /**
     * Called when the cursor enters the data table to add a action listener to the scrollbar
     * The action listener loads more data when the scrollbar nears the bottom (80%)
     */
    public void addLoader() {
        if (scrollAdded || tripList.size() < StaticVariables.step) {
            tripDataTable.setOnMouseEntered(null);
        } else {
            ScrollBar scrollBar = (ScrollBar) tripDataTable.lookup(".scroll-bar:vertical");
            scrollBar.valueProperty().addListener((obs, oldValue, newValue) -> {
                if (newValue.doubleValue() >= scrollBar.getMax() - 0.2) {
                    if (!loadedAll) {
                        ArrayList<Trip> tripArrayList = dbRetriever.queryTrip(StaticVariables.steppedQuery(Trip.tableName, loadedData));
                        if (tripArrayList.size() == 0) {
                            loadedAll = true;
                        }
                        tripList.addAll(tripArrayList);
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
        while (filteredTripList.size() < 50 && !loadedAll) {
            ArrayList<Trip> tripArrayList = dbRetriever.queryTrip(StaticVariables.steppedQuery(Trip.tableName, loadedData));
            if (tripArrayList.size() == 0) {
                loadedAll = true;
            }
            tripList.addAll(tripArrayList);
            loadedData += StaticVariables.step;
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
    }

    public void view(int tripIndex) {
        Trip trip = filteredTripList.get(tripIndex);
        startNameLabel.setText(trip.getStartStation().getAddress());
        endNameLabel.setText(trip.getEndStation().getAddress());
        startIDLabel.setText(Integer.toString(trip.getStartStationID()));
        endIDLabel.setText(Integer.toString(trip.getEndStationID()));
        durationLabel.setText(Integer.toString(trip.getDuration()));
        String[] startDate = String.valueOf(trip.getStartDate()).split(" ");
        String[] endDate = String.valueOf(trip.getEndDate()).split(" ");
        startDateLabel.setText(startDate[0] + " " + startDate[1] + " " + startDate[2]);
        endDateLabel.setText(endDate[0] + " " + endDate[1] + " " + endDate[2]);
        startTimeLabel.setText(startDate[3]);
        endTimeLabel.setText(endDate[3]);
        bikeIDLabel.setText(Integer.toString(trip.getBikeID()));
        userTypeLabel.setText(trip.getUserType());
        ageLabel.setText(Integer.toString(trip.getAge()));
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
            error.setText("Please select a trip to view");
            error.setVisible(true);
        } else {
            error.setVisible(false);
            incorrectFormat.setVisible(false);
            formatTimes.setVisible(false);
            dataViewer.setVisible(false);
            editor.setVisible(false);
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

    public void viewEdit() {
        recordViewer.setVisible(false);
        editor.setVisible(true);
        Trip trip = filteredTripList.get(currentTripIndex);
        startNameEntry.setText(trip.getStart());
        endNameEntry.setText(trip.getEnd());
        startIDEntry.setText(Integer.toString(trip.getStartStation().getId()));
        endIDEntry.setText(Integer.toString(trip.getEndStation().getId()));
        String[] startDate = String.valueOf(trip.getStartDate()).split(" ");
        String[] endDate = String.valueOf(trip.getEndDate()).split(" ");
        startDateEntry.setValue(trip.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        endDateEntry.setValue(trip.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        startTimeEntry.setText(startDate[3]);
        endTimeEntry.setText(endDate[3]);
        bikeIDEntry.setText(Integer.toString(trip.getBikeID()));
        userTypeEntry.getSelectionModel().select(trip.getUserType());
        ageEntry.setText(Integer.toString(trip.getAge()));
        genderEntry.getSelectionModel().select(trip.getGender());
        // Formatting zip entry to only accept integers
        startIDEntry.textProperty().addListener(
            (observable, oldValue, newValue) -> {
                String formatted = "";
                for (int i = 0; i < newValue.length(); i++) {
                    if (Character.isDigit(newValue.charAt(i))) {
                        formatted += newValue.charAt(i);
                    }
                }
                ((StringProperty)observable).setValue(formatted);
            }
        );
        endIDEntry.textProperty().addListener(
            (observable, oldValue, newValue) -> {
                String formatted = "";
                for (int i = 0; i < newValue.length(); i++) {
                    if (Character.isDigit(newValue.charAt(i))) {
                        formatted += newValue.charAt(i);
                    }
                }
                ((StringProperty)observable).setValue(formatted);
            }
        );
        bikeIDEntry.textProperty().addListener(
            (observable, oldValue, newValue) -> {
                String formatted = "";
                for (int i = 0; i < newValue.length(); i++) {
                    if (Character.isDigit(newValue.charAt(i))) {
                        formatted += newValue.charAt(i);
                    }
                }
                ((StringProperty)observable).setValue(formatted);
            }
        );
        ageEntry.textProperty().addListener(
            (observable, oldValue, newValue) -> {
                String formatted = "";
                for (int i = 0; i < newValue.length() && i < 3; i++) {
                    if (Character.isDigit(newValue.charAt(i))) {
                        formatted += newValue.charAt(i);
                    }
                }
                ((StringProperty)observable).setValue(formatted);
            }
        );
        startTimeEntry.textProperty().addListener(
            (observable, oldValue, newValue) -> {
                String formatted = "";
                for (int i = 0; i < newValue.length() && i < 8; i++) {
                    if (i == 2 || i == 5) {
                        if (newValue.charAt(i) == ':') {
                            formatted += newValue.charAt(i);
                        }
                    } else if (Character.isDigit(newValue.charAt(i))) {
                        formatted += newValue.charAt(i);
                    }
                }
                ((StringProperty)observable).setValue(formatted);
            }
        );
        endTimeEntry.textProperty().addListener(
            (observable, oldValue, newValue) -> {
                String formatted = "";
                for (int i = 0; i < newValue.length() && i < 8; i++) {
                    if (i == 2 || i == 5) {
                        if (newValue.charAt(i) == ':') {
                            formatted += newValue.charAt(i);
                        }
                    } else if (Character.isDigit(newValue.charAt(i))) {
                        formatted += newValue.charAt(i);
                    }
                }
                ((StringProperty)observable).setValue(formatted);
            }
        );
    }

    public void confirmEdit() {
        if (startTimeEntry.getText().length() != 8 || endTimeEntry.getText().length() != 8) {
            incorrectFormat.setVisible(true);
            formatTimes.setVisible(true);
        } else {
            Boolean correctFormat = true;
            for (int i = 0; i < 8; i++) {
                if (i == 2 || i == 5) {
                    if (startTimeEntry.getText().charAt(i) != ':' || endTimeEntry.getText().charAt(i) != ':'){
                        correctFormat = false;
                    }
                } else if (!Character.isDigit(startTimeEntry.getText().charAt(i)) || !Character.isDigit(endTimeEntry.getText().charAt(i))) {
                    correctFormat = false;
                }
            }
            if (!correctFormat) {
                incorrectFormat.setVisible(true);
                formatTimes.setVisible(true);
            } else {
                int startHour = Integer.parseInt(startTimeEntry.getText().substring(0, 2));
                int startMinute = Integer.parseInt(startTimeEntry.getText().substring(3, 5));
                int startSecond = Integer.parseInt(startTimeEntry.getText().substring(6, 8));
                int endHour = Integer.parseInt(endTimeEntry.getText().substring(0, 2));
                int endMinute = Integer.parseInt(endTimeEntry.getText().substring(3, 5));
                int endSecond = Integer.parseInt(endTimeEntry.getText().substring(6, 8));
                if (startHour > 24 || startMinute > 59 || startSecond > 59 || endHour > 24 || endMinute > 59 || endSecond > 59) {
                    incorrectFormat.setVisible(true);
                    formatTimes.setVisible(true);
                } else {
                    Trip trip = filteredTripList.get(currentTripIndex);
                    trip.getStartStation().setAddress(startNameEntry.getText());
                    trip.getEndStation().setAddress(endNameEntry.getText());
                    trip.getStartStation().setId(Integer.valueOf(startIDEntry.getText()));
                    trip.getEndStation().setId(Integer.valueOf(endIDEntry.getText()));
                    String startTime = startDateEntry.getValue() + " " + startTimeEntry.getText();
                    String endTime = endDateEntry.getValue() + " " + endTimeEntry.getText();
                    trip.setStartDate(startTime);
                    trip.setEndDate(endTime);
                    trip.setBikeID(Integer.valueOf(bikeIDEntry.getText()));
                    trip.setUserType(userTypeEntry.getSelectionModel().getSelectedItem());
                    trip.setAge(Integer.valueOf(ageEntry.getText()));
                    trip.setGender(genderEntry.getSelectionModel().getSelectedItem());
                    dbUpdater.updateTrip(trip);
                    viewRecord();
                }
            }
        }
    }

    public void search() {
        if (searchEntry.getText().isEmpty()) {
            error.setText("No search entered");
            error.setVisible(true);
        } else {
            error.setVisible(false);
            String query = StaticVariables.singleStringQueryLike(Station.tableName, "address", searchEntry.getText());
            ArrayList<Station> stations = dbRetriever.queryStation(query);
            ArrayList<Trip> result = new ArrayList<>();
            ArrayList<Trip> endsWith = new ArrayList<>();
            for (Station station : stations) {
                query = StaticVariables.singleStringQuery(Trip.tableName, "startStationID", Integer.toString(station.getId()));
                result.addAll(dbRetriever.queryTrip(query));
            }
            for (Station station : stations) {
                query = StaticVariables.singleStringQuery(Trip.tableName, "endStationID", Integer.toString(station.getId()));
                endsWith.addAll(dbRetriever.queryTrip(query));
            }
            for (Trip trip : endsWith) {
                if (!result.contains(trip)) {
                    result.add(trip);
                }
            }
            tripList = FXCollections.observableArrayList(result);
            loadedAll = true;
            filter();
        }
    }

    public void reset() {
        loadedAll = false;
        loadedData = 0;
        ArrayList<Trip> tripArrayList = dbRetriever.queryTrip(StaticVariables.steppedQuery(Trip.tableName, loadedData));
        tripList = FXCollections.observableArrayList(tripArrayList);
        searchEntry.setText("");
        if (tripList.size() < 50) {
            loadedAll = true;
        }
        filter();
    }
}
