package seng202.team7;



import javafx.scene.control.DatePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class DataEntryWindowController {

    public Button uploadcsvButton;
    public Button add_r_button;
    public Button add_w_button;
    public Button add_t_button;



    @FXML private ComboBox dataEntryComboBox;

    // Trip
    @FXML private TextField startTimeTextfield;
    @FXML private TextField endTimeTextfield;
    @FXML private TextField bikeIDTextfield;
    @FXML private ComboBox userTypeComboBox;
    @FXML private TextField birthYearTextfield;
    @FXML private TextField genderTextfield;
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
     * Loads a file of data from a csv file name provided
     * @param event
     */
    public void uploadcsvButton(ActionEvent event) {
        InputHandler toParse = new InputHandler();
        DatabaseUpdater toUpload = new DatabaseUpdater();
        ArrayList<Data> toAdd = null;
        String dataTypeAdded = (String ) dataEntryComboBox.getValue();


        String csvFile;
        Stage stage = new Stage();
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(stage);

        try {
            csvFile = file.toString();
            toAdd =  toParse.loadCSV(csvFile, dataTypeAdded);
            toUpload.addData(toAdd);

        } catch (IOException | NullPointerException e) {
            //e.printStackTrace();
            System.out.println("Either no csv uploaded or there was an issue parsing or uploading csv");
        }


    }

    /**
     * Loads manually inputted data and adds to database
     * @param event
     */
    public void add_r_button(ActionEvent event) {

        ArrayList<Data> toAdd = new ArrayList<Data>();
        InputHandler toTest = new InputHandler();
        DatabaseUpdater dataUploader = new DatabaseUpdater();


        try {

            String nameRetailer = nameTextfield.getText();
            int ZIP = Integer.parseInt(ZIPTextfield.getText());
            String state = stateTextfield.getText();
            String cityRetailer = cityRetailerTextfield.getText();
            String pAddress = pAddressTextfield.getText();
            String sAddress = sAddressTextfield.getText();
            String typeID = typeIDTextfield.getText();
            String typeRetailer = typeRetailerTextfield.getText();

            Retailer retailer = new Retailer(nameRetailer, cityRetailer, pAddress, sAddress, state, ZIP, typeID, typeRetailer, "default");
            if (toTest.checkValidity(retailer) == true) {
                Data retailerToAdd = new Retailer(nameRetailer, cityRetailer, pAddress, sAddress, state, ZIP, typeID, typeRetailer, "default");
                toAdd.add(retailerToAdd);
            } else {
                System.out.println("Invalid retailer");
            }


        } catch (NumberFormatException | NullPointerException e) {
            //e.printStackTrace();
            System.out.println("Not enough retailer data or incorrect data inputted");
        }

        dataUploader.addData(toAdd);
    }



    public void add_w_button(ActionEvent event) {

        ArrayList<Data> toAdd = new ArrayList<Data>();
        InputHandler toTest = new InputHandler();
        DatabaseUpdater dataUploader = new DatabaseUpdater();


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


            Wifi wifi = new Wifi(borough, typeWifi, provider, location, cityWifi, SSID, remarks, "default", longitude, latitude);
            if (toTest.checkValidity(wifi) == true) {
                Data wifiToAdd = new Wifi(borough, typeWifi, provider, location, cityWifi, SSID, remarks, "default", longitude, latitude);
                toAdd.add(wifiToAdd);
            } else {
                System.out.println("Invalid wifi");
            }

        } catch (NumberFormatException | NullPointerException e) {
            //e.printStackTrace();
            System.out.println("Not enough wifi data or incorrect data inputted");
        }

        dataUploader.addData(toAdd);
    }



    public void add_t_button(ActionEvent event) {

        ArrayList<Data> toAdd = new ArrayList<Data>();
        InputHandler toTest = new InputHandler();
        DatabaseUpdater dataUploader = new DatabaseUpdater();
        Boolean data_valid = true;

        try {


            String startDate = startDatePicked.getValue().toString();
            String endDate = endDatePicked.getValue().toString();

            String startTime = startTimeTextfield.getText();
            String endTime = endTimeTextfield.getText();

            String start = startDate + " " + startTime;
            String end = endDate + " " + endTime;

            String bikeID = bikeIDTextfield.getText();
            String userType = (String ) userTypeComboBox.getValue();
            int birthYear = Integer.parseInt(birthYearTextfield.getText());
            int startStationID = Integer.parseInt(startStationIDTextfield.getText());
            int endStationID = Integer.parseInt(endStationIDTextfield.getText());
            int duration = 1;   //derive duration using start and end times etc

            String genderGiven = genderTextfield.getText();
            int gender;
            if (genderGiven == "Male" || genderGiven == "male" || genderGiven == "M") {
                gender = 1;
            }
            else if (genderGiven == "Female" || genderGiven == "female" || genderGiven == "F") {
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
                System.out.println("station isn't in database yet to derive values, default values assigned");

                startStationLat = 0;
                startStationLong = 0;
                startStationAddress = "default";

                endStationLat = 0;
                endStationLong = 0;
                endStationAddress = "default";
            }


            Station startStation = new Station(startStationID, startStationAddress, "default", startStationLat, startStationLong);
            if (!toTest.checkValidity(startStation)) {
                System.out.println("Invalid start station");
                data_valid = false;
            }

            Station endStation = new Station(endStationID, endStationAddress, "default", endStationLat, endStationLong);
            if (!toTest.checkValidity(endStation)) {
                System.out.println("Invalid end station");
                data_valid = false;
            }

            Trip trip = new Trip(startStation, endStation, duration, start, end, userType, birthYear, gender, "default");
            int newDuration = ((int ) (trip.getEndDate().getTime() - trip.getStartDate().getTime())) / 1000;
            trip.setDuration(newDuration);           //duration is derived

            if ((toTest.checkValidity(trip) == true) && data_valid) {
                Data tripToAdd = new Trip(startStation, endStation, newDuration, start, end, userType, birthYear, gender, "default");
                toAdd.add(tripToAdd);
            } else {
                System.out.println("Invalid trip");
            }


        } catch (NumberFormatException | NullPointerException e) {
            //e.printStackTrace();
            System.out.println("Not enough trip data or incorrect data inputted");
        }

        dataUploader.addData(toAdd);
    }


}
