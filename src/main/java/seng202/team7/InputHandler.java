package seng202.team7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Handles the parsing of csv files then creates the relevant objects
 * @author Lachlan Brewster
 */
public class InputHandler {

    private String validBorough[] = {"MN", "BK", "QU", "SI", "BX"};
    private String validType[] = {"Free", "Limited free", "Limited Free", "Partner Site", "SI", "BX"};
    private String validGenders[] = {"Unknown", "Male", "Female"};
    private String validUserType[] = {"customer", "subscriber", "Customer", "Subscriber", "\"customer\"", "\"subscriber\"", "\"Customer\"", "\"Subscriber\"",};
    private String validState[] = {"NY"};
    private int fail_counter = 0;                          //for testing how many objects were created etc
    private int success_counter = 0;
    private int duplicate_counter;


    /**
     *     Method to parse a given csv file using the default IO package and buffered reader using split line () etc
     *     Returns an array of objects of type Trip, Wifi, or Retailer
     * @param file csv file to be read
     * @param dataType Type of data to be processed from Retailer, Wifi or Trips
     * @param dataGroup name of the group that the data belongs to
     * @return data returns an arraylist of the processed data from the input file
     * @throws IOException Exception thrown when there is an Input/Output error
     * @throws NumberFormatException Exception thrown when there is a number format error
     */
    public ArrayList<Data> loadCSV(String file, String dataType, String dataGroup) throws IOException, NumberFormatException
    {

        ArrayList<Data> data = new ArrayList<Data>();  //will add multiple objects, so need an array
        //Data dataToAdd = null;   //individual data packets to add to DB, each are one object

        Trip tripDataToAdd = null;
        Wifi wifiDataToAdd = null;
        Retailer retailerDataToAdd = null;

        DatabaseRetriever databaseRetriever = new DatabaseRetriever();
        DatabaseUpdater uploader = new DatabaseUpdater();

        BufferedReader reader = new BufferedReader(new FileReader(file));   //file in format "blahblahblah.csv"
        String line = reader.readLine(); // Reading header, Ignoring/getting rid of it if there is one


        //to detect if data given is in the correct type for what has been specified
        if (line.substring(0,36).equals("OBJECTID,the_geom,BORO,TYPE,PROVIDER")) {

            if (!dataType.equals("wifi")) {
                return data;
            }

        } else if ((line.substring(0,36).equals("CnBio_Org_Name,CnAdrPrf_Addrline1,Cn"))) {

                if (!dataType.equals("retailer")) {
                    return data;
                }

        } else {
            //must be trip data then

            if (!dataType.equals("trip")) {
                return data;
            }

        }


        //loop for parsing csv
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            //split on the comma only if that comma has zero, or an even number of quotes ahead of it
            String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);         //",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"
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

                        //check if its in the database already, if not then upload it, also checks 'validity'
                        wifiDataToAdd = new Wifi(borough, type, provider, location, city, SSID, remarks, dataGroup, longitude, latitude); //temp test object
                        int hashID = wifiDataToAdd.hashCode();
                        if (checkValidity(wifiDataToAdd).equals("Success") && (databaseRetriever.getStringListFromInt(dataType, hashID, Wifi.columns[0], Wifi.columns[0])).isEmpty()) {
                            System.out.println("Wifi added to upload list");
                        }
                        else if (!(databaseRetriever.getStringListFromInt(dataType, hashID, Wifi.columns[0], Wifi.columns[0])).isEmpty()) {
                            duplicate_counter++;
                            wifiDataToAdd = null;
                        }
                        else {
                            System.out.println(checkValidity(wifiDataToAdd));
                            wifiDataToAdd = null;
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

                        //check if its in the database already, if not then upload it, also checks 'validity'
                        retailerDataToAdd = new Retailer(name, city, pAddress, sAddress, state, zipCode, typeID, type, dataGroup);  //temp test object
                        hashID = retailerDataToAdd.hashCode();
                        if (checkValidity(retailerDataToAdd).equals("Success") && (databaseRetriever.getStringListFromInt(dataType, hashID, Retailer.columns[0], Retailer.columns[0])).isEmpty()) {
                            retailerDataToAdd = new Retailer(name, city, pAddress, sAddress, state, zipCode, typeID, type, dataGroup);   //create actual 'Data' object
                            System.out.println("Retailer added to upload list");
                        }
                        else if (!(databaseRetriever.getStringListFromInt(dataType, hashID, Retailer.columns[0], Retailer.columns[0])).isEmpty()) {
                            duplicate_counter++;
                            retailerDataToAdd = null;

                        }
                        else {
                            System.out.println(checkValidity(retailerDataToAdd));
                            retailerDataToAdd = null;
                        }

                        break;

                    case "trip":

                        //some trip files have strangely formatted int values, this fixes most
                        int birthYear;
                        int gender;
                        int startStationID;
                        int endStationID;
                        int bikeID;
                        int duration;

                        if (fields[0].isEmpty() || fields[3].isEmpty() || fields[7].isEmpty() || fields[11].isEmpty()
                                || fields[13].isEmpty() || fields[14].isEmpty()) {
                            System.out.println("At least one important number field is empty, would cause parser to freeze up");
                            break;
                        }

                        try {
                            duration = Integer.parseInt(fields[0]);
                            startStationID = Integer.parseInt(fields[3]);
                            endStationID = Integer.parseInt(fields[7]);
                            bikeID = Integer.parseInt(fields[11]);
                            birthYear = Integer.parseInt(fields[13]);
                            gender = Integer.parseInt(fields[14]);
                        } catch (NumberFormatException e) {
                            duration = removeQuotesInt(fields[0]);
                            startStationID = removeQuotesInt(fields[3]);
                            endStationID = removeQuotesInt(fields[7]);
                            bikeID = removeQuotesInt(fields[11]);
                            birthYear = removeQuotesInt(fields[13]);
                            gender = removeQuotesInt(fields[14]);
                        }

                        String userType = fields[12];
                        String startDate = fields[1];
                        String endDate = fields[2];

                        //some trip files have strangely formatted dates, this fixes most
                        if (startDate.charAt(0) == '"') {
                            startDate = startDate.substring(1, startDate.length() - 1);
                            endDate = endDate.substring(1, endDate.length() - 1);
                        }

                        //System.out.println(startDate);

                        Station startStation;
                        Station endStation;

                        //create stations for trip object, first check if they are in DB
                        if (databaseRetriever.queryStation(StaticVariables.stationIDQuery(startStationID)).isEmpty()) {
                            //station isn't in database, so create it

                            double startStationLong = 0;
                            try {
                                startStationLong = Double.parseDouble(fields[6]);
                            } catch (NumberFormatException e) {
                                startStationLong = removeQuotesDouble(fields[6]);
                            }

                            double startStationLat = 0;
                            try {
                                startStationLat = Double.parseDouble(fields[5]);
                            } catch (NumberFormatException e) {
                                startStationLat = removeQuotesDouble(fields[5]);
                            }

                            String startStationAddress = fields[4];
                            String startStationDataGroup = "default";


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
                        } else {
                            //fetch start station to use
                            startStation = databaseRetriever.queryStation(StaticVariables.stationIDQuery(startStationID)).get(0);
                        }


                        if (databaseRetriever.queryStation(StaticVariables.stationIDQuery(endStationID)).isEmpty()) {
                            //station isn't in database, so create it

                            double endStationLong = 0;
                            try {
                                endStationLong = Double.parseDouble(fields[10]);
                            } catch (NumberFormatException e) {
                                endStationLong = removeQuotesDouble(fields[10]);
                            }

                            double endStationLat = 0;
                            try {
                                endStationLat = Double.parseDouble(fields[9]);
                            } catch (NumberFormatException e) {
                                endStationLat = removeQuotesDouble(fields[9]);
                            }


                            String endStationAddress = fields[8];
                            String endStationDataGroup = "default";


                            //testing station validity, else ditch this piece of data
                            endStation = new Station(endStationID, endStationAddress, endStationDataGroup, endStationLat, endStationLong);
                            if (!checkValidity(endStation)) {
                                break;
                            }
                            else {
                                //add to database as it doesn't exist there yet
                                uploader.insertStation(endStation);
                                System.out.println("New station uploaded");

                            }
                        } else {
                            //fetch end station to use
                            endStation = databaseRetriever.queryStation(StaticVariables.stationIDQuery(endStationID)).get(0);
                        }


                        //check if its in the database already, if not then upload it, also checks 'validity'
                        tripDataToAdd = new Trip(startStationID, startStation, endStationID, endStation, duration, startDate, endDate, userType, birthYear, gender, dataGroup, bikeID); //temp test object


                        hashID = tripDataToAdd.hashCode();
                        if (checkValidity(tripDataToAdd).equals("Success") && (databaseRetriever.getStringListFromInt(dataType, hashID, Trip.columns[0], Trip.columns[0])).isEmpty()) {
                            System.out.println("Trip added to upload list");
                        }
                        else if (!databaseRetriever.getStringListFromInt(dataType, hashID, Trip.columns[0], Trip.columns[0]).isEmpty()) {
                            duplicate_counter++;
                            System.out.println("duplicate");
                            tripDataToAdd = null;
                        }
                        else {
                            System.out.println(checkValidity(tripDataToAdd));
                            tripDataToAdd = null;
                        }

                        break;

                }


            } catch (NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException e ){

                e.printStackTrace();
                fail_counter++;
                //System.out.println("Invalid data in csv while parsing or creating " + dataType + " object, could be a blank field?");
            }

            //if object wasn't valid then don't add it
            if (dataType.equals("trip")) {
                if (tripDataToAdd != null) {
                    data.add(tripDataToAdd);
                    success_counter++;
                }

            } else if (dataType.equals("retailer")) {
                    if (retailerDataToAdd != null) {
                        data.add(retailerDataToAdd);
                        success_counter++;
                    }

            } else if (dataType.equals("wifi")) {
                if (wifiDataToAdd != null) {
                    data.add(wifiDataToAdd);
                    success_counter++;
                }

            }

        }

        System.out.println("List created to be added into DB");
        reader.close();    //don't need it anymore
        return data;       //return array of objects for use

    }

    /**
     * Reset the fail counter to zero
     */
    public void resetFailCounter() {
        fail_counter = 0;
    }

    /**
     * returns how many issues the parser had
     * @return fail_counter
     */
    public int getFail_counter() {
        return fail_counter;
    }

    /**
     * Reset the success counter to zero
     */
    public void resetSuccessCounter() {
        success_counter = 0;
    }

    /**
     * returns how many successful object creation the parser had
     * @return success_counter
     */
    public int getSuccess_counter() {
        return success_counter;
    }

    public void resetDuplicateCounter() {
        duplicate_counter = 0;
    }

    /**
     * returns how many successful object creation the parser had
     * @return duplicate_counter
     */
    public int getDuplicate_counter() {
        return duplicate_counter;
    }





    /**
     * Tests an inputted Data objects data individually to see if it is valid, returns true if its valid
     * @param dataToTest The data object that is to be tested for validity
     * @return True if dataToTest is valid
     */
    public boolean checkValidity(Data dataToTest) { return true; }

    /**
     * Tests an inputted retailer objects data individually to see if it is valid, returns success if valid
     * or an error message if not
     * @param retailer retailer object to be tested for validity
     * @return validRetailer String success if valid
     */
    public String checkValidity(Retailer retailer)
    {

        String validRetailer = "Success";


        if (retailer.getCity().length() > 30 || retailer.getCity().length() < 2) {
            validRetailer = "Invalid retailer city " + retailer.getCity();
        }
        else if (retailer.getName().length() > 50 || retailer.getName().length() < 1) {
            validRetailer = "Invalid retailer name " + retailer.getName();

        }

        //no viable way to test valid address

        else if (!Arrays.asList(validState).contains(retailer.getState())) {
            validRetailer = "Invalid retailer state " + retailer.getState();
        }
        else if (0 >= retailer.getZipCode() || retailer.getZipCode() > 1000000) {
            validRetailer = "Invalid retailer ZIP " + retailer.getZipCode();
        }
        else if (retailer.getType().length() > 50 || retailer.getType().length() < 1) {
            validRetailer = "Invalid retailer type " + retailer.getType();
        }

        else if (retailer.getTypeID().length() > 31 || retailer.getTypeID().length() < 1) {
            validRetailer = "Invalid retailer typeID " + retailer.getTypeID();
        }



        return validRetailer;
    }

    /**
     * Tests an inputted trip objects data individually to see if it is valid, returns success if valid
     * or an error message if not
     * @param trip Trip object to be tested for validity
     * @return validTrip String success if valid
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

        else if (0 >= trip.getBikeID()) {
            validTrip = "Invalid bike ID " + trip.getBikeID();
        }

        else if (!Arrays.asList(validUserType).contains(trip.getUserType())) {
            validTrip = "Invalid user type " + trip.getUserType();
        }
        /*else if (trip.getStartDate() == null) {
            validTrip = "Start date not set, maybe didn't parse properly";
        }
        else if (trip.getEndDate() == null) {
            validTrip = "End date not set, maybe didn't parse properly";
        }*/


        return validTrip;

    }

    /**
     * Tests an inputted wifi objects data individually to see if it is valid, returns success if valid
     * or an error message if not
     * @param wifi
     * @return validWifi String success if valid
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
        else if (wifi.getProvider().length() > 100 || wifi.getProvider().length() < 2) {
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
     * @param station Station to be tested for validity
     * @return validStation True if station is valid
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

    public int removeQuotesInt(String value) {

        if (value.equals("\"\"")) {
            return 0;
        }
        int result = Integer.parseInt(value.substring(1, value.length() - 1));
        return result;
    }

    public double removeQuotesDouble(String value) {
        if (value.equals("\"\"")) {
            return 0;
        }
        double result = Double.parseDouble(value.substring(1, value.length() - 1));
        return result;
    }

    public String removeQuotesStr(String value) {
        String result = value.substring(1, value.length() - 1);
        return result;
    }

}
