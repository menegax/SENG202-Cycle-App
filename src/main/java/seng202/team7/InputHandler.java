package seng202.team7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//import org.apache.commons.csv.CSVFormat;
//import org.apache.commons.csv.CSVParser;
//import org.apache.commons.csv.CSVRecord;

public class InputHandler {


    /*

    USED FOR TESTING ACTUAL FUNCTIONALITY,
    HAVE TO MAKE INPUT HANDLER STATIC THOUGH LOL

    public static void main(String[] args) {


        try {
            //loadCSV("wifi_data.csv", "wifi");
            loadCSV("retailer_data.csv", "retailer");
            //loadCSV("trip_data.csv", "trip");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
*/


    /*
    Method to parse a given csv file using the apache package ORRR using default IO package
    buffered reader and split line () etc
     */
    public ArrayList<Data> loadCSV(String file, String dataType) throws IOException, NumberFormatException
    {

        ArrayList<Data> data = new ArrayList<Data>();  //will add multiple objects, so need an array
        Data dataToAdd = null;   //individual data packets to add to DB, each are one object

        /*
        Using apache parser, might just use built in stuff as have more control seems like

        CSVParser parser = new CSVParser(new FileReader("blahblahblah.csv"), CSVFormat.DEFAULT.withHeader());

        for (CSVRecord record : parser) {
            System.out.printf("%s\t%s\t%s\n", record.get("NAME"),
                    record.get("CAPITAL"), record.get("CURRENCY"));
        }
        parser.close();
        */


        //file in format "blahblahblah.csv"
        BufferedReader reader = new BufferedReader(new FileReader(file));
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

                        String dataGroup = "0";
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
                            String test = fields[29];

                            remarks = fields[12] + "," + fields[13];
                            city = fields[14];
                            SSID = fields[15];

                        } catch (ArrayIndexOutOfBoundsException e) {
                            remarks = fields[12];
                            city = fields[13];
                            SSID = fields[14];
                        }


                        dataToAdd = new Wifi(burough, type, provider, location, city, SSID, remarks, dataGroup, longitude, latitude);   //create actual object
                        break;


                    case "retailer":
                        String name = fields[0];
                        city = fields[3];
                        String pAddress = fields[1];
                        String sAddress = "0";     //eh?
                        String state = fields[4];
                        int zipCode = Integer.parseInt(fields[5]);
                        type = fields[7];

                        String typeID = "0";         //doesn't have it yo
                        dataGroup = "0";

                        dataToAdd = new Retailer(name, city, pAddress, sAddress, state, zipCode, type, typeID, dataGroup);   //create actual object
                        break;

                    case "trip":

                        int startStationID = Integer.parseInt(fields[3]);
                        String startStationAddress = fields[4];
                        String startStationDataGroup = "0";
                        double startStationLat = Double.parseDouble(fields[5]);
                        double startStationLong = Double.parseDouble(fields[6]);

                        int endStationID = Integer.parseInt(fields[7]);
                        String endStationAddress = fields[8];
                        String endStationDataGroup = "0";
                        double endStationLat = Double.parseDouble(fields[9]);
                        double endStationLong = Double.parseDouble(fields[10]);

                        //create stations for creating a trip object
                        Station startStation = new Station(startStationID, startStationAddress, startStationDataGroup, startStationLat, startStationLong);   //Station
                        Station endStation = new Station(endStationID, endStationAddress, endStationDataGroup, endStationLat, endStationLong);   //Station

                        int duration = Integer.parseInt(fields[0]);


                        //WHERE ARE THESE RECORDED?
                        //CAN EASY ADD IT IN HERE, JUST DON'T KNOW IF IM MEANT TO
                        /*
                        String userType = fields[12];
                        String bikeID = fields[11];     //int
                        String gender = fields[14];    //int
                        String birthYear = fields[13]; //int
                        dataGroup = "0";
                        */

                        dataToAdd = new Trip(startStation, endStation, duration);  //create actual object
                        break;


                    }

                } catch (NumberFormatException| ArrayIndexOutOfBoundsException e ){
                //e.printStackTrace();
                //System.out.println("Wrong type of data");

            }

            data.add(dataToAdd); //add object into array to be returned

        }

        reader.close();  //don't need it anymore
        return data;  //return appropriate array for use

    }






//don't need quite yet as if the data is invalid it'll just set off an error as i try parse it

    private Boolean checkValidity(Retailer retailer)
    {
        return true;
    }

    private Boolean checkValidity(Trip trip)
    {
        return true;
    }

    private Boolean checkValidity(Wifi wifi)
    {
        return true;
    }

    private Boolean checkValidity(Station station)
    {
        return true;
    }



}
