package seng202.team7.Controllers.MainWindowControllers;


import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import seng202.team7.*;
import seng202.team7.Windows.MainWindow.LoadingPopupWindow;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static seng202.team7.Datagroup.addDatagroup;
import static seng202.team7.Datagroup.getDatagroups;

/**
 * Controls manual data entry and data uploaded via csv
 * @author Lachlan Brewster
 */
public class DataEntryWindowController implements Initializable{

    public Button uploadcsvButton;
    public Button add_r_button;
    public Button add_w_button;
    public Button add_t_button;
    public Button clearTrip;
    public Button clearWifi;
    public Button clearRetailer;
    public Button clearAllFields;


    @FXML private ComboBox dataGroupCombo;
    @FXML private Text status_text;
    @FXML private ComboBox dataEntryComboBox;
    @FXML private ProgressBar loadingBar;
    @FXML private Text loadingText;

    // Trip
    @FXML private TextField startTimeTextfield;
    @FXML private TextField endTimeTextfield;
    @FXML private TextField bikeIDTextfield;
    @FXML private ComboBox userTypeComboBox;
    @FXML private TextField birthYearTextfield;
    @FXML private ComboBox genderComboBox;
    @FXML private TextField startStationIDTextfield;
    @FXML private TextField endStationIDTextfield;
    @FXML private DatePicker startDatePicked;
    @FXML private DatePicker endDatePicked;

    // Wifi
    @FXML private TextField providerTextfield;
    @FXML private ComboBox typeWifiComboBox;
    @FXML private TextField locationWifiTextfield;
    @FXML private TextField cityWifiTextfield;
    @FXML private ComboBox boroughComboBox;
    @FXML private TextField SSIDTextfield;
    @FXML private TextField remarksTextfield;
    @FXML private TextField longitudeTextfield;
    @FXML private TextField latitudeTextfield;

    // Retailer
    @FXML private TextField nameTextfield;
    @FXML private TextField ZIPTextfield;
    @FXML private ComboBox stateComboBox;
    @FXML private TextField cityRetailerTextfield;
    @FXML private TextField pAddressTextfield;
    @FXML private TextField sAddressTextfield;
    @FXML private ComboBox typeRetailerComboBox;

    /**
     * sets the drop down combo box for datagroup selection
     */
    @FXML public void setDataGroupComboItems() {

        ObservableList<String> items = FXCollections.observableArrayList(getDatagroups());
        dataGroupCombo.setItems(items);
    }

    /**
     * Initializes the formatting listeners for the appropriate text fields
     * @param url Just a testing argument
     * @param rb Just a testing argument
     */
    public void initialize(URL url, ResourceBundle rb) {

        startStationIDTextfield.textProperty().addListener(
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
        endStationIDTextfield.textProperty().addListener(
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
        bikeIDTextfield.textProperty().addListener(
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
        birthYearTextfield.textProperty().addListener(
            (observable, oldValue, newValue) -> {
                String formatted = "";
                for (int i = 0; i < newValue.length() && i < 4; i++) {
                    if (Character.isDigit(newValue.charAt(i))) {
                        formatted += newValue.charAt(i);
                    }
                }
                ((StringProperty)observable).setValue(formatted);
            }
        );
        startTimeTextfield.textProperty().addListener(
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
        endTimeTextfield.textProperty().addListener(
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
        ZIPTextfield.textProperty().addListener(
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
    }

    /**
     * Loads a file of data from a csv file name provided
     * Contains many layers of checks for; if fields are missing, wrong data type or file type is detected, error handling
     * @param event Event when button is clicked
     */
    public void uploadcsvButton(ActionEvent event) {
        InputHandler toParse = new InputHandler();
        DatabaseUpdater toUpload = new DatabaseUpdater();
        String dataTypeAdded = (String) dataEntryComboBox.getValue();
        String dataGroup = (String) dataGroupCombo.getValue();

        if (dataTypeAdded == null && dataGroup == null) {
            status_text.setText("No data group or data type entered!");
        }
        else if (dataGroup  == null && !(dataTypeAdded == null)) {
            status_text.setText("No " + dataTypeAdded + " data group entered!");
        }
        else if (dataTypeAdded == null && !(dataGroup == null)) {
            status_text.setText("No data type entered!");
        }

        else {

            if (dataGroup.length() > 20) {
                status_text.setText("Datagroup too long! Enter another");
                return;
            }

            if (!getDatagroups().contains(dataGroup)) {
                addDatagroup(dataGroup);
            }

            Stage stage = new Stage();
            FileChooser chooser = new FileChooser();
            File file = chooser.showOpenDialog(stage);


            if (file != null) {
                Parent layout = new LoadingPopupWindow();
                Scene scene = new Scene(layout);
                Stage popupStage = new Stage();
                popupStage.setTitle("Caution");
                popupStage.initModality(Modality.WINDOW_MODAL);
                popupStage.setScene(scene);
                popupStage.show();

            Task<Void> task = new Task<Void>() {
                @Override
                public Void call() {
                    try {

                        String csvFile = file.toString();
                        if (!Objects.equals(csvFile.substring(csvFile.length() - 4, csvFile.length()), ".csv")) {
                            status_text.setText("Invalid type of file! Has to be of type .csv");
                            return null;
                        }

                        ArrayList<Data> toAdd;
                        status_text.setText("Parsing " + dataTypeAdded + " csv");
                        toAdd =  toParse.loadCSV(csvFile, dataTypeAdded, dataGroup);

                        if (toAdd.size() == 0 && toParse.getDuplicate_counter() == 0 && toParse.getFail_counter() == 0) {
                            status_text.setText("Detected wrong data type selected! try again!");
                            return null;
                        }

                        status_text.setText("Uploading " + dataTypeAdded + " objects");
                        toUpload.addData(toAdd);
                        if (toParse.getFail_counter() == 0) {
                               if (toParse.getDuplicate_counter() == 0) {
                                status_text.setText("Csv file parsed and uploaded, " + toParse.getSuccess_counter() + " "
                                        + dataTypeAdded + " objects added.");
                                } else {
                                   status_text.setText("Csv file parsed and uploaded, " + toParse.getSuccess_counter() + " "
                                           + dataTypeAdded + " objects added, " + toParse.getDuplicate_counter() + " duplicates (not added)");
                                }

                        } else {
                                if (toParse.getDuplicate_counter() == 0) {
                                    status_text.setText("Csv file parsed and uploaded, " + toParse.getSuccess_counter() + " "
                                            + dataTypeAdded + " objects added. " + toParse.getFail_counter()
                                            + " issues, likely empty fields or incorrect formats");
                                } else {
                                    status_text.setText("Csv file parsed and uploaded, " + toParse.getSuccess_counter() + " "
                                            + dataTypeAdded + " objects added. " + toParse.getFail_counter()
                                            + " issues, likely empty fields or incorrect formats "
                                            + toParse.getDuplicate_counter() + " duplicates (not added)");
                                }

                        }
                            toParse.resetSuccessCounter();
                            toParse.resetFailCounter();
                            toParse.resetDuplicateCounter();

                        } catch (IOException | NullPointerException e) {
                            //e.printStackTrace();
                            status_text.setText("Either no csv uploaded or there was an issue parsing or uploading csv ");
                        }
                        return null;
                    }
                };
                task.setOnSucceeded(e -> {
                    uploadcsvButton.setVisible(true);
                    loadingBar.setVisible(false);
                    loadingText.setVisible(false);
                    popupStage.close();
                });
                Thread thread = new Thread(task);
                thread.start();
                uploadcsvButton.setVisible(false);
                loadingBar.setVisible(true);
                loadingText.setVisible(true);
            }
        }
    }


    /**
     * Loads manually inputted retailer data and adds to database
     * Contains several layers of checks for empty or invalid data, with error messages, as well as detects duplicate data
     * @param event Event when add retailer button is clicked
     */
    public void add_r_button(ActionEvent event) {

        ArrayList<Data> toAdd = new ArrayList<>();
        InputHandler toTest = new InputHandler();
        DatabaseUpdater dataUploader = new DatabaseUpdater();
        DatabaseRetriever retriever = new DatabaseRetriever();

        String dataGroup = (String) dataGroupCombo.getValue();
        if (dataGroup != null) {

            if (dataGroup.length() > 20) {
                status_text.setText("Datagroup too long! Enter another");
                return;
            }

            if (!getDatagroups().contains(dataGroup)) {
                addDatagroup(dataGroup);
            }

            try {
                String nameRetailer = nameTextfield.getText();
                String state = (String ) stateComboBox.getValue();
                String cityRetailer = cityRetailerTextfield.getText();
                String pAddress = pAddressTextfield.getText();
                String sAddress = sAddressTextfield.getText();
                String typeRetailer = (String) typeRetailerComboBox.getValue();
                String typeID = typeRetailer.substring(0, 1);


                int ZIP;
                try {
                    ZIP = Integer.parseInt(ZIPTextfield.getText());
                } catch (NumberFormatException e) {
                    status_text.setText("Invalid retailer ZIP!");
                    return;
                }

                Retailer retailerToAdd = new Retailer(nameRetailer, cityRetailer, pAddress, sAddress, state, ZIP, typeID, typeRetailer, dataGroup);
                if (toTest.checkValidity(retailerToAdd).equals("Success")) {
                    toAdd.add(retailerToAdd);

                    //check if its in the database already, if not then upload it
                    int hashID = retailerToAdd.hashCode();
                    if ((retriever.getStringListFromInt("retailer", hashID, Retailer.columns[0], Retailer.columns[0])).isEmpty()) {
                        dataUploader.addData(toAdd);
                        status_text.setText("Retailer added");
                    } else {
                        status_text.setText("Retailer already in database");
                    }

                } else {
                    status_text.setText(toTest.checkValidity(retailerToAdd));
                }


            } catch (NullPointerException e) {
                //e.printStackTrace();
                status_text.setText("Not enough retailer data or incorrect data inputted");

            }

        }
        else {
            status_text.setText("No retailer data group entered!");
        }

    }


    /**
     * Loads manually inputted wifi data and adds to database
     * Contains several layers of checks for empty or invalid data, with error messages, as well as detects duplicate data
     * @param event Event when add wifi button is clicked
     */
    public void add_w_button(ActionEvent event) {

        ArrayList<Data> toAdd = new ArrayList<>();
        InputHandler toTest = new InputHandler();
        DatabaseUpdater dataUploader = new DatabaseUpdater();
        DatabaseRetriever retriever = new DatabaseRetriever();

        String dataGroup = (String) dataGroupCombo.getValue();
        if (dataGroup != null) {

            if (dataGroup.length() > 20) {
                status_text.setText("Datagroup too long! Enter another");
                return;
            }

            if (!getDatagroups().contains(dataGroup)) {
                addDatagroup(dataGroup);
            }

            try {

                String provider = providerTextfield.getText();
                String typeWifi = (String ) typeWifiComboBox.getValue();
                String location = locationWifiTextfield.getText();
                String cityWifi = cityWifiTextfield.getText();
                String borough = (String ) boroughComboBox.getValue();
                String SSID = SSIDTextfield.getText();
                String remarks = remarksTextfield.getText();
                double longitude;
                double latitude;

                try {
                    longitude = Double.parseDouble(longitudeTextfield.getText());
                } catch (NumberFormatException e) {
                    status_text.setText("Invalid Wifi longitude!");
                    return;
                }

                try {
                    latitude = Double.parseDouble(latitudeTextfield.getText());
                } catch (NumberFormatException e) {
                    status_text.setText("Invalid Wifi latitude!");
                    return;
                }


                Wifi wifiToAdd = new Wifi(borough, typeWifi, provider, location, cityWifi, SSID, remarks, dataGroup, longitude, latitude);
                if (toTest.checkValidity(wifiToAdd).equals("Success")) {
                    toAdd.add(wifiToAdd);

                    //check if its in the database already, if not then upload it
                    int hashID = wifiToAdd.hashCode();
                    if (retriever.getStringListFromInt("wifi", hashID, Wifi.columns[0], Wifi.columns[0]).isEmpty()) {
                        dataUploader.addData(toAdd);
                        status_text.setText("Wifi added");
                    } else {
                        status_text.setText("Wifi already in database");
                    }

                } else {
                    status_text.setText(toTest.checkValidity(wifiToAdd));
                }



            } catch (NumberFormatException | NullPointerException e) {
                //e.printStackTrace();
                status_text.setText("Not enough wifi data or incorrect data inputted");
            }

        }
        else {
            status_text.setText("No wifi data group entered!");
        }


    }


    /**
     * Loads manually inputted trip data and adds to database
     * Contains several layers of checks for empty or invalid data, with error messages, as well as detects duplicate data
     * Also adds station objects to the database, alot of derived values via queries
     * @param event Event when add trip data is clicked
     */
    public void add_t_button(ActionEvent event) {

        ArrayList<Data> toAdd = new ArrayList<>();
        InputHandler toTest = new InputHandler();
        DatabaseUpdater dataUploader = new DatabaseUpdater();
        DatabaseRetriever retriever = new DatabaseRetriever();

        String dataGroup = (String) dataGroupCombo.getValue();
        if (dataGroup != null) {

            if (dataGroup.length() > 20) {
                status_text.setText("Datagroup too long! Enter another");
                return;
            }

            if (!getDatagroups().contains(dataGroup)) {
                addDatagroup(dataGroup);
            }

            try {

                int duration = 1;   //derive duration using start and end times later

                String startDate = startDatePicked.getValue().toString();
                String endDate = endDatePicked.getValue().toString();

                String startTime = startTimeTextfield.getText();
                String endTime = endTimeTextfield.getText();

                String start = startDate + " " + startTime;
                String end = endDate + " " + endTime;
                String userType = (String ) userTypeComboBox.getValue();

                int bikeID;
                int endStationID;
                int startStationID;
                int birthYear;

                try {
                    bikeID = Integer.parseInt(bikeIDTextfield.getText());
                } catch (NumberFormatException e) {
                    status_text.setText("Invalid trip bike ID !");
                    return;
                }

                try {
                    endStationID = Integer.parseInt(endStationIDTextfield.getText());
                } catch (NumberFormatException e) {
                    status_text.setText("Invalid trip end station ID!");
                    return;
                }

                try {
                    startStationID = Integer.parseInt(startStationIDTextfield.getText());
                } catch (NumberFormatException e) {
                    status_text.setText("Invalid trip start station ID!");
                    return;

                }

                try {
                    birthYear = Integer.parseInt(birthYearTextfield.getText());
                } catch (NumberFormatException e) {
                    status_text.setText("Invalid trip birth year!");
                    return;
                }


                String genderGiven = (String ) genderComboBox.getValue();
                int gender;
                if (genderGiven.equals( "Male")) {
                    gender = 1;
                }
                else if (genderGiven.equals("Female")) {
                    gender = 2;
                }
                else {
                    gender = 0;
                }


                try {
                    DatabaseRetriever databaseRetriever = new DatabaseRetriever();
                    Station startQuery  = databaseRetriever.queryStation(StaticVariables.stationIDQuery(startStationID)).get(0);
                } catch (IndexOutOfBoundsException e) {
                    status_text.setText("Station don't exist in our data base! Double check your station ID: " + startStationID);
                    return;
                }

                try {
                    DatabaseRetriever databaseRetriever = new DatabaseRetriever();
                    Station endQuery  = databaseRetriever.queryStation(StaticVariables.stationIDQuery(endStationID)).get(0);
                } catch (IndexOutOfBoundsException e) {
                    status_text.setText("Station don't exist in our data base! Double check your station ID: " + endStationID);
                    return;
                }

                //start and end station address, lat and long derived here
                double startStationLat;
                double startStationLong;
                String startStationAddress;
                double endStationLat;
                double endStationLong;
                String endStationAddress;


                try {
                    DatabaseRetriever databaseRetriever = new DatabaseRetriever();
                    Station startQuery  = databaseRetriever.queryStation(StaticVariables.stationIDQuery(startStationID)).get(0);
                    Station endQuery  = databaseRetriever.queryStation(StaticVariables.stationIDQuery(endStationID)).get(0);

                    startStationLat = startQuery.getLatitude();
                    startStationLong = startQuery.getLongitude();
                    startStationAddress = startQuery.getAddress();

                    endStationLat = endQuery.getLatitude();
                    endStationLong = endQuery.getLongitude();
                    endStationAddress = endQuery.getAddress();

                } catch (IndexOutOfBoundsException e) {
                    status_text.setText("Station isn't in database yet to derive values, default values assigned");

                    startStationLat = 0;
                    startStationLong = 0;
                    startStationAddress = "default";

                    endStationLat = 0;
                    endStationLong = 0;
                    endStationAddress = "default";
                }


                Station startStation = new Station(startStationID, startStationAddress, "default", startStationLat, startStationLong);
                if (!toTest.checkValidity(startStation)) {
                    status_text.setText("Invalid start station");
                    return;
                }

                Station endStation = new Station(endStationID, endStationAddress, "default", endStationLat, endStationLong);
                if (!toTest.checkValidity(endStation)) {
                    status_text.setText("Invalid end station");
                    return;
                }


                Trip tripToAdd = new Trip(startStationID, startStation, endStationID, endStation, duration, start, end, userType, birthYear, gender, dataGroup, bikeID);
                int newDuration = ((int ) (tripToAdd.getEndDate().getTime() - tripToAdd.getStartDate().getTime())) / 1000;
                tripToAdd.setDuration(newDuration);           //duration is derived

                if (toTest.checkValidity(tripToAdd).equals("Success")) {
                    toAdd.add(tripToAdd);

                    //check if its in the database already, if not then upload it
                    int hashID = toAdd.hashCode();
                    if ((retriever.getStringListFromInt("trip", hashID, Trip.columns[0], Trip.columns[0])).isEmpty()) {
                        dataUploader.addData(toAdd);
                        //System.out.println(hashID);
                        status_text.setText("Trip added");
                    } else {
                        status_text.setText("Trip already in database");
                    }

                } else {
                    status_text.setText(toTest.checkValidity(tripToAdd));
                }


            } catch (NullPointerException e) {
                //e.printStackTrace();
                status_text.setText("Not enough trip data or incorrect data inputted");
            }

        } else {
            status_text.setText("No trip data group entered!");
        }

    }

    /**
     * Clears trip data entry fields completely, as well as combobox selection and date picked
     */
    public void clearTrip() {

        startTimeTextfield.clear();
        endTimeTextfield.clear();
        bikeIDTextfield.clear();
        birthYearTextfield.clear();
        startStationIDTextfield.clear();
        endStationIDTextfield.clear();

        startDatePicked.setValue(null);
        endDatePicked.setValue(null);

        if (userTypeComboBox.getValue() != null) {
            userTypeComboBox.getSelectionModel().clearSelection();
        }

        if (genderComboBox.getValue() != null) {
            genderComboBox.getSelectionModel().clearSelection();
        }

    }


    /**
     * Clears wifi data entry fields completely, as well as combobox selection
     */
    public void clearWifi() {

        providerTextfield.clear();
        locationWifiTextfield.clear();
        cityWifiTextfield.clear();
        SSIDTextfield.clear();
        remarksTextfield.clear();
        longitudeTextfield.clear();
        latitudeTextfield.clear();

        if (boroughComboBox.getValue() != null) {
            boroughComboBox.getSelectionModel().clearSelection();
        }
        if (typeWifiComboBox.getValue() != null) {
            typeWifiComboBox.getSelectionModel().clearSelection();
        }

    }

    /**
     * Clears retailer data entry fields completely, as well as combobox selection
     */
    public void clearRetailer() {

        nameTextfield.clear();
        ZIPTextfield.clear();
        cityRetailerTextfield.clear();
        pAddressTextfield.clear();
        sAddressTextfield.clear();

        if (typeRetailerComboBox.getValue() != null) {
            typeRetailerComboBox.getSelectionModel().clearSelection();
        }
        if (stateComboBox.getValue() != null) {
            stateComboBox.getSelectionModel().clearSelection();
        }

    }

    /**
     * Clears all fields on data entry screen, including non textfields
     */
    public void clearAllFields() {

        nameTextfield.clear();
        ZIPTextfield.clear();
        cityRetailerTextfield.clear();
        pAddressTextfield.clear();
        sAddressTextfield.clear();
        if (typeRetailerComboBox.getValue() != null) {
            typeRetailerComboBox.getSelectionModel().clearSelection();
        }
        if (stateComboBox.getValue() != null) {
            stateComboBox.getSelectionModel().clearSelection();
        }

        startTimeTextfield.clear();
        endTimeTextfield.clear();
        bikeIDTextfield.clear();
        birthYearTextfield.clear();
        startStationIDTextfield.clear();
        endStationIDTextfield.clear();
        startDatePicked.setValue(null);
        endDatePicked.setValue(null);
        if (userTypeComboBox.getValue() != null) {
            userTypeComboBox.getSelectionModel().clearSelection();
        }

        if (genderComboBox.getValue() != null) {
            genderComboBox.getSelectionModel().clearSelection();
        }

        providerTextfield.clear();
        locationWifiTextfield.clear();
        cityWifiTextfield.clear();
        SSIDTextfield.clear();
        remarksTextfield.clear();
        longitudeTextfield.clear();
        latitudeTextfield.clear();
        if (boroughComboBox.getValue() != null) {
            boroughComboBox.getSelectionModel().clearSelection();
        }
        if (typeWifiComboBox.getValue() != null) {
            typeWifiComboBox.getSelectionModel().clearSelection();
        }

    }

}
