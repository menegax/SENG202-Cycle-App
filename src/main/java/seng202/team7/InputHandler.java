package seng202.team7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class InputHandler {

    String validBorough[] = {"MN", "BK", "QU", "SI", "BX"};
    String validType[] = {"Free", "Limited Free", "Partner Site", "SI", "BX"};
    String validGenders[] = {"Unknown", "Male", "Female"};
    String validUserType[] = {"Customer", "Subscriber"};
    String validCity[] = {"New York", "Brooklyn", "Bronx", "Queens", "Staten Island", "Fresh Meadows", "Laurelton", "Cambria Heights",
            "Whitestone", "Briarwood", "Rego Park", "Jackson Heights", "Rosedale", "College Point", "Far Rockaway", "Ridgewood",
            "Howard Beach", "Rockaway Beach", "East Elmhurst", "Maspeth", "Corona", "Queens Village", "South Hollis", "Arverne",
            "Glen Oaks", "Woodhaven", "Bellerose", "Hollis", "Little Neck", "Broad Channel", "South Ozone Park", "Ozone Park",
            "Rockaway Park", "Long Island City", "Middle Village", "Jamaica", "Glendale", "Woodside", "Richmond Hill", "Forest Hills",
            "Elmhurst", "Bayside", "Flushing", "Saint Albans"};
    //ON CITI BIKE IT HAS BEEN TAKING NEIGHBOURHOODS AS CITIES, ONLY 300/2500 OR SO, DO WE REALLY WANT THEM? IT'S VALID DATA I SUPPOSE
    String validState[] = {"NY"};
    int counter = 0;


    /*
    USED FOR TESTING ACTUAL FUNCTIONALITY, put print statements on each test object created,
    e.g printThis(tripDataTest.getCity())

    public static void main(String[] args) {


        try {

            InputHandler toTest = new InputHandler();

            toTest.loadCSV("retailer_data.csv", "retailer");
            //toTest.loadCSV("trip_data.csv", "trip");
            //toTest.loadCSV("wifi_data.csv", "wifi");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/



    /**
     *     Method to parse a given csv file using the default IO package and buffered reader using split line () etc
     *     Returns an array of objects of type Trip, Wifi, or Retailer
     * @param file
     * @param dataType
     * @return data
     * @throws IOException
     * @throws NumberFormatException
     */
    public ArrayList<Data> loadCSV(String file, String dataType) throws IOException, NumberFormatException
    {

        ArrayList<Data> data = new ArrayList<Data>();  //will add multiple objects, so need an array
        Data dataToAdd = null;   //individual data packets to add to DB, each are one object

        BufferedReader reader = new BufferedReader(new FileReader(file));   //file in format "blahblahblah.csv"
        String line = reader.readLine(); // Reading header, Ignoring/getting rid of it if there is one


        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            String[] fields = line.split(",");

            try {
                switch (dataType) {

                    case "wifi":

                        String burough = fields[2];   //or 18 for full name, not code
                        String type = fields[3];
                        String provider = fields[4];
                        String location = fields[6];

                        String dataGroup = "default";
                        double longitude = Double.parseDouble(fields[8]);
                        double latitude = Double.parseDouble(fields[7]);


                        //ANNOYING BUG FFS, comma in actual value confuses buffered reader,
                        //Kind of a weird way to check it, but it works so meh
                        //System.out.println(fields[12]);
                        //System.out.println(fields[13]);

                        String remarks;
                        String city;
                        String SSID;

                        try {
                            String test = fields[29];    //if it sets off a out of array error then add the fields together

                            remarks = fields[12] + "," + fields[13];
                            city = fields[14];
                            SSID = fields[15];

                        } catch (ArrayIndexOutOfBoundsException e) {
                            remarks = fields[12];
                            city = fields[13];
                            SSID = fields[14];
                        }

                        Wifi wifiDataTest = new Wifi(burough, type, provider, location, city, SSID, remarks, dataGroup, longitude, latitude); //temp test object
                        if (checkValidity(wifiDataTest)) {
                            dataToAdd = new Wifi(burough, type, provider, location, city, SSID, remarks, dataGroup, longitude, latitude);   //create actual 'Data' object
                            counter++;
                            System.out.println(counter);
                        }

                        break;


                    case "retailer":
                        String name = fields[0];
                        city = fields[3];
                        String pAddress = fields[1];
                        String sAddress = fields[2];
                        String state = fields[4];
                        int zipCode = Integer.parseInt(fields[5]);
                        type = fields[7];
                        String typeID = fields[8];                 //unsure on what this is being used for

                        dataGroup = "default";

                        Retailer retailerDataTest = new Retailer(name, city, pAddress, sAddress, state, zipCode, type, typeID, dataGroup);  //temp test object
                        if (checkValidity(retailerDataTest)) {
                            dataToAdd = new Retailer(name, city, pAddress, sAddress, state, zipCode, type, typeID, dataGroup);   //create actual 'Data' object
                            counter++;
                            System.out.println(counter);
                        }

                        break;

                    case "trip":
                        int duration = Integer.parseInt(fields[0]);
                        dataGroup = "default";
                        String userType = fields[12];
                        int bikeID = Integer.parseInt(fields[11]);
                        String gender = fields[14];
                        int birthYear = Integer.parseInt(fields[13]);
                        String startDate = fields[1];                //need to implement having this as Date type? not sure whats wanted
                        String endDate = fields[2];                //need to implement having this as Date type? not sure whats wanted

                        int startStationID = Integer.parseInt(fields[3]);
                        String startStationAddress = fields[4];
                        String startStationDataGroup = "default";
                        double startStationLat = Double.parseDouble(fields[5]);
                        double startStationLong = Double.parseDouble(fields[6]);

                        int endStationID = Integer.parseInt(fields[7]);
                        String endStationAddress = fields[8];
                        String endStationDataGroup = "default";
                        double endStationLat = Double.parseDouble(fields[9]);
                        double endStationLong = Double.parseDouble(fields[10]);

                        //create stations for creating a trip object
                        //testing station validity, else ditch this piece of data
                        Station startStation = new Station(startStationID, startStationAddress, startStationDataGroup, startStationLat, startStationLong);
                        if (!checkValidity(startStation)) {
                            break;
                        }

                        //testing station validity, else ditch this piece of data
                        Station endStation = new Station(endStationID, endStationAddress, endStationDataGroup, endStationLat, endStationLong);
                        if (!checkValidity(endStation)) {
                            break;
                        }




                        Trip tripDataTest = new Trip(startStation, endStation, duration, startDate, endDate, userType, birthYear, gender, dataGroup); //temp test object
                        if (checkValidity(tripDataTest) == true) {
                            dataToAdd = new Trip(startStation, endStation, duration, startDate, endDate, userType, birthYear, gender, dataGroup);  //create actual 'Data' object
                            counter++;
                            //System.out.println(counter);
                        }


                        break;


                }


            } catch (NumberFormatException| ArrayIndexOutOfBoundsException e ){

                //e.printStackTrace();
                //System.out.println("Wrong type of data");

            }


            if (dataToAdd != null) {         //if object wasn't valid then don't add it
                data.add(dataToAdd);         //add object into array to be returned

            }

        }

        reader.close();    //don't need it anymore
        return data;       //return appropriate array of objects for use

    }






    /**
     * Tests an inputted Data objects data individually to see if it is valid, returns true if valid
     * @param dataToTest
     * @return
     */
    private Boolean checkValidity(Data dataToTest)
    {
        return true;
    }

    /**
     * Tests an inputted retailer objects data individually to see if it is valid, returns true if valid
     * @param retailer
     * @return validRetailer
     */
    private Boolean checkValidity(Retailer retailer)
    {

        boolean validRetailer = true;

        //System.out.println(Arrays.asList(validCity).contains(retailer.getCity()));


        if (!Arrays.asList(validCity).contains(retailer.getCity())) {
            validRetailer = false;
        }
        else if (retailer.getName().length() > 100) {
            validRetailer = false;
        }

        //no viable way to test valid address

        else if (!Arrays.asList(validState).contains(retailer.getState())) {
            validRetailer = false;
        }
        else if (0 > retailer.getZipCode() || retailer.getZipCode() > 1000000) {
            validRetailer = false;
        }
        else if (retailer.getType().length() > 100) {
            validRetailer = false;
        }

        /*else if (retailer.getTypeID().length() > 100) {            //unsure how or what this is being used for
            validRetailer = false;
        }
        else if (retailer.getDataGroup()) {
            validRetailer = false;
        }*/



        return validRetailer;
    }

    /**
     * Tests an inputted trip objects data individually to see if it is valid, returns true if valid
     * @param trip
     * @return validRetailer
     */
    private Boolean checkValidity(Trip trip)
    {
        boolean validTrip = true;



        if (0 > trip.getDuration() || trip.getDuration() > 100000 ) {
            validTrip = false;
        }

        //STATIONS ARE CHECKED WHEN THEY ARE CREATED ANYWAY SO DON'T REALLY NEED THIS CHECK

        /*else if (trip.getDataGroup()) {       //WHEN IS DATA GROUP ASSIGNED?
            validRetailer = false;
        }*/


        else if (!Arrays.asList(validGenders).contains(trip.getGender())) {
            validTrip = false;
        }

        else if (0 > trip.getAge() || trip.getAge() > 120) {
            validTrip = false;
        }

        /*else if (0 >= trip.getBikeID()) {               //not in constructor
            validTrip = false;
        }*/

        else if (!Arrays.asList(validUserType).contains(trip.getUserType())) {
            validTrip = false;
        }



        return validTrip;

    }

    /**
     * Tests an inputted wifi objects data individually to see if it is valid, returns true if valid
     * @param wifi
     * @return validRetailer
     */
    private Boolean checkValidity(Wifi wifi)
    {

        boolean validWifi = true;


        if (!Arrays.asList(validBorough).contains(wifi.getBorough())) {
            validWifi = false;
        }
        else if (!Arrays.asList(validType).contains(wifi.getType())) {
            validWifi = false;
        }
        else if (wifi.getProvider().length() > 100) {
            validWifi = false;
        }
        else if (wifi.getLocation().length() > 100) {
            validWifi = false;
        }

        /*else if (wifi.getDataGroup() != ) {
            validWifi = false;
        }*/

        else if (90.0 < wifi.getLatitude() || wifi.getLatitude() < -90.0 ) {         //double
            validWifi = false;
        }
        else if (180.0 < wifi.getLongitude() || wifi.getLongitude() < -180.0) {        //double
            validWifi = false;
        }
        else if (wifi.getRemarks().length() > 100) {
            validWifi = false;
        }
        else if (!Arrays.asList(validCity).contains(wifi.getCity())) {
            validWifi = false;
            //System.out.println(wifi.getCity());
        }
        else if (wifi.getSSID().length() > 100) {
            validWifi = false;
        }



        return validWifi;

    }

    /**
     * Tests an inputted station objects data individually to see if it is valid, returns true if valid
     * @param station
     * @return validRetailer
     */
    private Boolean checkValidity(Station station)
    {

        boolean validStation = true;
        /*
        if (station.getId() != ) {                    //int
            validStation = false;
        }
        else if (station.getAddress() != ) {
            validStation = false;
        }
        else if (station.getDataGroup() != ) {
            validStation = false;
        }
        else if (station.getLatitude() != ) {             //double
            validStation = false;
        }
        else if (station.getLongitude() != ) {            //double
            validStation = false;
        }*/


        return validStation;
    }



}
