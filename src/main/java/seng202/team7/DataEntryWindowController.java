package seng202.team7;


import javafx.beans.property.StringProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.lang.System.identityHashCode;

/**
 * Controls manual data entry and data uploaded via csv
 * @author Lachlan Brewster
 */
public class DataEntryWindowController implements Initializable{

    public Button uploadcsvButton;
    public Button add_r_button;
    public Button add_w_button;
    public Button add_t_button;

    @FXML private TextField dataGroupTextfield;
    @FXML private Text status_text;
    @FXML private ComboBox dataEntryComboBox;

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
    @FXML private TextField typeWifiTextfield;
    @FXML private TextField locationWifiTextfield;
    @FXML private TextField cityWifiTextfield;
    @FXML private TextField boroughTextfield;
    @FXML private TextField SSIDTextfield;
    @FXML private TextField remarksTextfield;
    @FXML private TextField longitudeTextfield;
    @FXML private TextField latitudeTextfield;

    // Retailer
    @FXML private TextField nameTextfield;
    @FXML private TextField ZIPTextfield;
    @FXML private TextField stateTextfield;
    @FXML private TextField cityRetailerTextfield;
    @FXML private TextField pAddressTextfield;
    @FXML private TextField sAddressTextfield;
    @FXML private TextField typeIDTextfield;
    @FXML private TextField typeRetailerTextfield;

    /**
     * Initializes the formatting listeners for the appropriate text fields
     * @param url
     * @param rb
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
     * @param event
     */
    public void uploadcsvButton(ActionEvent event) {
        InputHandler toParse = new InputHandler();
        DatabaseUpdater toUpload = new DatabaseUpdater();
        ArrayList<Data> toAdd = null;
        String dataTypeAdded = (String ) dataEntryComboBox.getValue();

        String dataGroup;
        try {
            dataGroup = dataGroupTextfield.getText();
            String tester = dataGroup.substring(1);

            String csvFile;
            Stage stage = new Stage();
            FileChooser chooser = new FileChooser();
            File file = chooser.showOpenDialog(stage);

            try {
                csvFile = file.toString();
                toAdd =  toParse.loadCSV(csvFile, dataTypeAdded, dataGroup);
                toUpload.addData(toAdd);
                status_text.setText("Csv " + dataTypeAdded + " file parsed and uploaded, " +
                        toParse.getFail_counter() + " objects failed to load, likely empty fields");

            } catch (IOException | NullPointerException e) {
                //e.printStackTrace();
                status_text.setText("Either no csv uploaded or there was an issue parsing or uploading csv");
            }

        } catch (StringIndexOutOfBoundsException e) {
            status_text.setText("No data group entered!");
        }



    }

    /**
     * Loads manually inputted retailer data and adds to database
     * @param event
     */
    public void add_r_button(ActionEvent event) {

        ArrayList<Data> toAdd = new ArrayList<Data>();
        InputHandler toTest = new InputHandler();
        DatabaseUpdater dataUploader = new DatabaseUpdater();
        DatabaseRetriever retriever = new DatabaseRetriever();

        String dataGroup;
        try {
            dataGroup = dataGroupTextfield.getText();
            String tester = dataGroup.substring(1);

            try {
                String nameRetailer = nameTextfield.getText();
                int ZIP = Integer.parseInt(ZIPTextfield.getText());
                String state = stateTextfield.getText();
                String cityRetailer = cityRetailerTextfield.getText();
                String pAddress = pAddressTextfield.getText();
                String sAddress = sAddressTextfield.getText();
                String typeID = typeIDTextfield.getText();
                String typeRetailer = typeRetailerTextfield.getText();

                Retailer retailer = new Retailer(nameRetailer, cityRetailer, pAddress, sAddress, state, ZIP, typeID, typeRetailer, dataGroup);
                if (toTest.checkValidity(retailer).equals("Success")) {
                    Data retailerToAdd = new Retailer(nameRetailer, cityRetailer, pAddress, sAddress, state, ZIP, typeID, typeRetailer, dataGroup);
                    toAdd.add(retailerToAdd);

                } else {
                    status_text.setText(toTest.checkValidity(retailer));
                }


            } catch (NumberFormatException | NullPointerException e) {
                //e.printStackTrace();
                status_text.setText("Not enough retailer data or incorrect data inputted");
            }

            //check if its in the database already, if not then upload it
            int hashID = toAdd.hashCode();
            if ((retriever.getStringListFromInt("retailer", hashID, Retailer.columns[0], Retailer.columns[0])).isEmpty()) {
                dataUploader.addData(toAdd);
                status_text.setText("Retailer added");
            }

        } catch (StringIndexOutOfBoundsException e) {
            status_text.setText("No data group entered!");
        }

    }


    /**
     * Loads manually inputted wifi data and adds to database
     * @param event
     */
    public void add_w_button(ActionEvent event) {

        ArrayList<Data> toAdd = new ArrayList<Data>();
        InputHandler toTest = new InputHandler();
        DatabaseUpdater dataUploader = new DatabaseUpdater();
        DatabaseRetriever retriever = new DatabaseRetriever();

        String dataGroup;
        try {
            dataGroup = dataGroupTextfield.getText();
            String tester = dataGroup.substring(1);

            try {

                String provider = providerTextfield.getText();
                String typeWifi = typeWifiTextfield.getText();
                String location = locationWifiTextfield.getText();
                String cityWifi = cityWifiTextfield.getText();
                String borough = boroughTextfield.getText();
                String SSID = SSIDTextfield.getText();
                String remarks = remarksTextfield.getText();
                double longitude = Double.parseDouble(longitudeTextfield.getText());
                double latitude = Double.parseDouble(latitudeTextfield.getText());


                Wifi wifi = new Wifi(borough, typeWifi, provider, location, cityWifi, SSID, remarks, dataGroup, longitude, latitude);
                if (toTest.checkValidity(wifi).equals("Success")) {
                    Data wifiToAdd = new Wifi(borough, typeWifi, provider, location, cityWifi, SSID, remarks, dataGroup, longitude, latitude);
                    toAdd.add(wifiToAdd);

                } else {
                    status_text.setText(toTest.checkValidity(wifi));
                }

            } catch (NumberFormatException | NullPointerException e) {
                //e.printStackTrace();
                status_text.setText("Not enough wifi data or incorrect data inputted");
            }

            //check if its in the database already, if not then upload it
            int hashID = toAdd.hashCode();
            if ((retriever.getStringListFromInt("wifi", hashID, Wifi.columns[0], Wifi.columns[0])).isEmpty()) {
                dataUploader.addData(toAdd);
                status_text.setText("Wifi added");
            }

        } catch (StringIndexOutOfBoundsException e) {
            status_text.setText("No data group entered!");
        }


    }


    /**
     * Loads manually inputted trip data and adds to database
     * @param event
     */
    public void add_t_button(ActionEvent event) {

        ArrayList<Data> toAdd = new ArrayList<Data>();
        InputHandler toTest = new InputHandler();
        DatabaseUpdater dataUploader = new DatabaseUpdater();
        DatabaseRetriever retriever = new DatabaseRetriever();
        Boolean data_valid = true;

        String dataGroup;
        try {
            dataGroup = dataGroupTextfield.getText();
            String tester = dataGroup.substring(1);

            try {

                String startDate = startDatePicked.getValue().toString();
                String endDate = endDatePicked.getValue().toString();

                String startTime = startTimeTextfield.getText();
                String endTime = endTimeTextfield.getText();

                String start = startDate + " " + startTime;
                String end = endDate + " " + endTime;

                int bikeID = Integer.parseInt(bikeIDTextfield.getText());
                String userType = (String ) userTypeComboBox.getValue();
                int birthYear = Integer.parseInt(birthYearTextfield.getText());
                int startStationID = Integer.parseInt(startStationIDTextfield.getText());
                int endStationID = Integer.parseInt(endStationIDTextfield.getText());
                int duration = 1;   //derive duration using start and end times etc

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
                    status_text.setText("station isn't in database yet to derive values, default values assigned");

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
                    data_valid = false;
                }

                Station endStation = new Station(endStationID, endStationAddress, "default", endStationLat, endStationLong);
                if (!toTest.checkValidity(endStation)) {
                    status_text.setText("Invalid end station");
                    data_valid = false;
                }

                Trip trip = new Trip(startStation, endStation, duration, start, end, userType, birthYear, gender, dataGroup, bikeID);
                int newDuration = ((int ) (trip.getEndDate().getTime() - trip.getStartDate().getTime())) / 1000;
                trip.setDuration(newDuration);           //duration is derived

                if ((toTest.checkValidity(trip).equals("Success")) && data_valid) {
                    Data tripToAdd = new Trip(startStation, endStation, newDuration, start, end, userType, birthYear, gender, dataGroup, bikeID);
                    toAdd.add(tripToAdd);

                } else {
                    status_text.setText(toTest.checkValidity(trip));
                }


            } catch (NumberFormatException | NullPointerException e) {
                //e.printStackTrace();
                status_text.setText("Not enough trip data or incorrect data inputted");
            }

            //check if its in the database already, if not then upload it
            int hashID = identityHashCode(toAdd);
            if ((retriever.getStringListFromInt("trip", hashID, Trip.columns[0], Trip.columns[0])).isEmpty()) {
                dataUploader.addData(toAdd);
                System.out.println(hashID);
                status_text.setText("Trip added");
            }

        } catch (StringIndexOutOfBoundsException e) {
            status_text.setText("No data group entered!");
        }

    }

}
