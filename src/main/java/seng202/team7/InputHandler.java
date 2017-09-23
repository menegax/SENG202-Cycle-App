package seng202.team7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Handles the parsing of csv files then creates the relevant objects
 * @author Lachlan Brewster
 */
public class InputHandler {

    private String validBorough[] = {"MN", "BK", "QU", "SI", "BX"};
    private String validType[] = {"Free", "Limited Free", "Partner Site", "SI", "BX"};
    private String validGenders[] = {"Unknown", "Male", "Female"};
    private String validUserType[] = {"customer", "subscriber", "Customer", "Subscriber"};
    private String validState[] = {"NY"};
    private int fail_counter = 0;                          //for testing how many objects were created etc


    /*
    USED FOR TESTING ACTUAL FUNCTIONALITY, put print statements on each test object created,
    e.g printThis(tripDataTest.getCity())

    public static void main(String[] args) {


        try {

            InputHandler toTest = new InputHandler();

            toTest.loadCSV("C:\\Users\\user\\Documents\\1 Uni\\1stpro\\sem2\\seng202\\retailer_data.csv", "retailer");
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
    public ArrayList<Data> loadCSV(String file, String dataType, String dataGroup) throws IOException, NumberFormatException
    {

        ArrayList<Data> data = new ArrayList<Data>();  //will add multiple objects, so need an array
        Data dataToAdd = null;   //individual data packets to add to DB, each are one object

        BufferedReader reader = new BufferedReader(new FileReader(file));   //file in format "blahblahblah.csv"
        String line = reader.readLine(); // Reading header, Ignoring/getting rid of it if there is one

        DatabaseRetriever databaseRetriever = new DatabaseRetriever();
        DatabaseUpdater uploader = new DatabaseUpdater();




        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            //split on the comma only if that comma has zero, or an even number of quotes ahead of it
            String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            try {
                switch (dataType) {

                    case "wifi":

                        String borough = fields[2];   //or 18 for full name, not code
                        String type = fields[3];
                        String provider = fields[4];
                        String location = fields[6];

                        double longitude = Double.parseDouble(fields[8]);
                        double latitude = Double.parseDouble(fields[7]);

                        String remarks = fields[12];
                        String city = fields[13];
                        String SSID = fields[14];


                        Wifi wifiDataTest = new Wifi(borough, type, provider, location, city, SSID, remarks, dataGroup, longitude, latitude); //temp test object
                        if (checkValidity(wifiDataTest).equals("Success")) {
                            dataToAdd = new Wifi(borough, type, provider, location, city, SSID, remarks, dataGroup, longitude, latitude);   //create actual 'Data' object
                            //counter++;                         //for testing how many objects were created successfully
                            //System.out.println(counter);
                        }

                        break;


                    case "retailer":
                        String name = fields[0];
                        city = fields[3];
                        String pAddress = fields[1];
                        String sAddress = fields[2];
                        String state = fields[4];
                        int zipCode = Integer.parseInt(fields[5]);

                        String typeID;

                        if (fields[8].isEmpty()) {
                            //System.out.println("No retailer type given");
                            break;
                        }
                        else {
                            typeID = fields[8].substring(0,1);
                            type = fields[8].substring(2);
                        }



                        Retailer retailerDataTest = new Retailer(name, city, pAddress, sAddress, state, zipCode, typeID, type, dataGroup);  //temp test object
                        if (checkValidity(retailerDataTest).equals("Success")) {
                            dataToAdd = new Retailer(name, city, pAddress, sAddress, state, zipCode, typeID, type, dataGroup);   //create actual 'Data' object
                            //counter++;                    //for testing how many objects were created successfully
                            //System.out.println(counter);
                        }

                        break;

                    case "trip":

                        int duration = Integer.parseInt(fields[0]);
                        String userType = fields[12];
                        int bikeID = Integer.parseInt(fields[11]);
                        int gender = Integer.parseInt(fields[14]);
                        int birthYear = Integer.parseInt(fields[13]);
                        String startDate = fields[1];
                        String endDate = fields[2];

                        Station startStation;
                        Station endStation;
                        //create stations for trip object, first check if they are in DB

                        int startStationID = Integer.parseInt(fields[3]);
                        if (databaseRetriever.queryStation(StaticVariables.stationIDQuery(startStationID)).isEmpty()) {
                            //station isn't in database, so create it

                            String startStationAddress = fields[4];
                            String startStationDataGroup = "default";
                            double startStationLat = Double.parseDouble(fields[5]);
                            double startStationLong = Double.parseDouble(fields[6]);

                            //testing station validity, else ditch this piece of data
                            startStation = new Station(startStationID, startStationAddress, startStationDataGroup, startStationLat, startStationLong);
                            if (!checkValidity(startStation)) {
                                break;
                            }
                            else {
                                //add to database as it doesn't exist there yet
                                uploader.insertStation(startStation);
                                //System.out.println("Station uploaded");
                            }
                        }
                        else {
                            startStation = databaseRetriever.queryStation(StaticVariables.stationIDQuery(startStationID)).get(0);
                            //System.out.println("Station fetched");

                        }


                        int endStationID = Integer.parseInt(fields[7]);
                        if (databaseRetriever.queryStation(StaticVariables.stationIDQuery(endStationID)).isEmpty()) {
                            //station isn't in database, so create it

                            String endStationAddress = fields[8];
                            String endStationDataGroup = "default";
                            double endStationLat = Double.parseDouble(fields[9]);
                            double endStationLong = Double.parseDouble(fields[10]);

                            //testing station validity, else ditch this piece of data
                            endStation = new Station(endStationID, endStationAddress, endStationDataGroup, endStationLat, endStationLong);
                            if (!checkValidity(endStation)) {
                                break;
                            }
                            else {
                                //add to database as it doesn't exist there yet
                                uploader.insertStation(endStation);
                                //System.out.println("Station uploaded");

                            }
                        }
                        else {
                            //System.out.println("Station fetched");
                            endStation = databaseRetriever.queryStation(StaticVariables.stationIDQuery(endStationID)).get(0);
                        }


                        Trip tripDataTest = new Trip(startStation, endStation, duration, startDate, endDate, userType, birthYear, gender, dataGroup, bikeID); //temp test object
                        if (checkValidity(tripDataTest).equals("Success")) {
                            dataToAdd = new Trip(startStation, endStation, duration, startDate, endDate, userType, birthYear, gender, dataGroup, bikeID);  //create actual 'Data' object
                            //counter++;                         //for testing how many objects were created successfully
                            //System.out.println(counter);
                        }


                        break;


                }


            } catch (NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException e ){

                //e.printStackTrace();
                fail_counter++;
                //System.out.println("Invalid data in csv while parsing or creating " + dataType + " object, could be a blank field?");
            }


            if (dataToAdd != null) {         //if object wasn't valid then don't add it
                data.add(dataToAdd);         //add object into array to be returned

            }

        }

        //System.out.println("List created");
        reader.close();    //don't need it anymore
        return data;       //return array of objects for use

    }



    public int getFail_counter() {
        return fail_counter;
    }

    /**
     * Tests an inputted Data objects data individually to see if it is valid, returns true if its valid
     * @param dataToTest
     * @return
     */
    public Boolean checkValidity(Data dataToTest)
    {
        return true;
    }

    /**
     * Tests an inputted retailer objects data individually to see if it is valid, returns true if valid
     * @param retailer
     * @return validRetailer
     */
    public String checkValidity(Retailer retailer)
    {

        String validRetailer = "Success";


        if (retailer.getCity().length() > 30 || retailer.getCity().length() < 2) {
            validRetailer = "Invalid retailer city " + retailer.getCity();
        }
        else if (retailer.getName().length() > 50) {
            validRetailer = "Invalid retailer name " + retailer.getName();

        }

        //no viable way to test valid address

        else if (!Arrays.asList(validState).contains(retailer.getState())) {
            validRetailer = "Invalid retailer state " + retailer.getState();
        }
        else if (0 >= retailer.getZipCode() || retailer.getZipCode() > 1000000) {
            validRetailer = "Invalid retailer ZIP " + retailer.getZipCode();
        }
        else if (retailer.getType().length() > 50) {
            validRetailer = "Invalid retailer type " + retailer.getType();
        }

        else if (retailer.getTypeID().length() > 31) {
            validRetailer = "Invalid retailer typeID " + retailer.getTypeID();
        }




        return validRetailer;
    }

    /**
     * Tests an inputted trip objects data individually to see if it is valid, returns true if valid
     * @param trip
     * @return validRetailer
     */
    public String checkValidity(Trip trip)
    {
        String validTrip = "Success";


        if (0 > trip.getDuration() || trip.getDuration() > 100000 ) {
            validTrip = "Invalid trip duration " + trip.getDuration() + "seconds";
        }

        else if (!Arrays.asList(validGenders).contains(trip.getGender())) {
            validTrip = "Invalid gender " + trip.getGender();
        }

        else if (0 > trip.getAge() || trip.getAge() > 120) {
            validTrip = "Invalid age " + trip.getAge();
        }

        /*else if (0 >= trip.getBikeID()) {               //not in constructor.. yet?
            validTrip = false;
        }*/

        else if (!Arrays.asList(validUserType).contains(trip.getUserType())) {
            validTrip = "Invalid user type " + trip.getUserType();
        }



        return validTrip;

    }

    /**
     * Tests an inputted wifi objects data individually to see if it is valid, returns true if valid
     * @param wifi
     * @return validRetailer
     */
    public String checkValidity(Wifi wifi)
    {

        String validWifi = "Success";


        if (!Arrays.asList(validBorough).contains(wifi.getBorough())) {
            validWifi = "Invalid wifi borough " + wifi.getBorough();
        }
        else if (!Arrays.asList(validType).contains(wifi.getType())) {
            validWifi = "Invalid wifi type " + wifi.getType();
        }
        else if (wifi.getProvider().length() > 100) {
            validWifi = "Invalid provider " + wifi.getProvider();
        }
        else if (wifi.getLocation().length() > 100) {
            validWifi = "Invalid wifi location " + wifi.getLocation();
        }

        else if (90.0 < wifi.getLatitude() || wifi.getLatitude() < -90.0 ) {         //double
            validWifi = "Invalid wifi latitude " + wifi.getLatitude();
        }
        else if (180.0 < wifi.getLongitude() || wifi.getLongitude() < -180.0) {        //double
            validWifi = "Invalid wifi longitude " + wifi.getLongitude();
        }
        else if (wifi.getRemarks().length() > 100) {
            validWifi = "Invalid remark " + wifi.getRemarks();
        }
        else if (wifi.getCity().length() > 30 || wifi.getCity().length() < 2) {
            validWifi = "Invalid wifi city " + wifi.getCity();
        }
        else if (wifi.getSSID().length() > 50) {
            validWifi = "Invalid SSID " + wifi.getSSID();
        }



        return validWifi;

    }

    /**
     * Tests an inputted station objects data individually to see if it is valid, returns true if valid
     * @param station
     * @return validRetailer
     */
    public Boolean checkValidity(Station station)
    {

        boolean validStation = true;

        if (station.getId() <= 0) {
            validStation = false;
            //System.out.println("Invalid station ID " + station.getId());
        }
        else if (station.getAddress().length() > 50 || station.getAddress().length() == 0) {
            validStation = false;
            //System.out.println("Invalid station address " + station.getAddress());
        }

        else if (90 < station.getLatitude() || station.getLatitude() < -90) {
            validStation = false;
            //System.out.println("Invalid station latitude " + station.getLatitude());
        }
        else if (180 < station.getLongitude() || station.getLongitude() < -180 ) {
            validStation = false;
            //System.out.println("Invalid station longitude " + station.getLongitude());
        }


        return validStation;
    }

}
