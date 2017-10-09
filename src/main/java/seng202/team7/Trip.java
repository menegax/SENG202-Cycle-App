package seng202.team7;

import java.text.ParseException;
import java.util.Date;

/**
 * @author Morgan English
 */
public class Trip extends Location implements Data, java.io.Serializable {

    /**
     * String for the name of the table in the database
     */
    public static String tableName = "trip";

    /**
     * Strings of all the columns in the database
     */
    public static String columns[] = {"id","duration","startStationID", "startStation","endStationID","endStation","bikeID","gender","age","userType","startDate","startTime","endDate","endTime","distance","datagroup","obj" };


    /**
     * SQL string to create table
     */
    public static String tableCreation = "CREATE TABLE IF NOT EXISTS "+tableName+" (\n"
            + columns[0]+" integer PRIMARY KEY NOT NULL ,\n"
            + columns[1]+" integer,\n"
            + columns[2]+" integer,\n"
            + columns[3]+" obj,\n"
            + columns[4]+" integer,\n"
            + columns[5]+" obj,\n"
            + columns[6]+" integer,\n"
            + columns[7]+" text,\n"
            + columns[8]+" integer,\n"
            + columns[9]+" text,\n"
            + columns[10]+" date,\n"
            + columns[11]+" time,\n"
            + columns[12]+" date,\n"
            + columns[13]+" time,\n"
            + columns[14]+" real,\n"
            + columns[15]+" text,\n"
            + columns[16]+" blob\n"
            + ");";
    /**
     * Station at the start of the trip
     */
    private Station startStation;
    private int startStationID;
    /**
     * Station at the end of trip
     */
    private Station endStation;
    private int endStationID;

    /**
     * Start and end to reference the names of the stations for easy access from the raw data viewer
     */

    /**
     * Duration in seconds
     */
    private int duration;
    /**
     * Start time as java datetime object
     */
    private Date startDate;
    /**
     * End time as java datetime object
     */
    private Date endDate;

    /**
     * String for the Usertype
     */
    private String userType;
    /**
     * Bikeid of the bike used for the trip
     */
    private int bikeID;
    /**
     * Gender, could this be an Enum or int??
     */
    private String gender;
    /**
     * Attribute must be derived
     */
    private int age;
    /**
     * String for sorting within a table
     */
    private String dataGroup;

    /**
     * Distance of biketrip currently worked out using
     */
    private double distance;


    /**
     * Trip Constructor
     * @param duration duration of trip in seconds
     * @param startDate startdate of trip as string given with format "yyyy-mm-dd hh:mm:ss" 24hr time
     * @param endDate enddate of trip as string given with format "yyyy-mm-dd hh:mm:ss" 24hr time
     * @param userType usertype of cyclist
     * @param birthYear year of birth for user can then be used to calculate age
     * @param gender gander of cyclist
     * @param dataGroup datagroup string for sorting within tables
     * @param bikeID ID of bike that was used for the trip
     */
    public Trip(int startStationID, Station startStation, int endStationID, Station endStation, int duration, String startDate, String endDate, String userType, int birthYear, int gender, String dataGroup, int bikeID)
    {
        //DatabaseRetriever databaseRetriever = new DatabaseRetriever();
        //this.startStation = databaseRetriever.queryStation(StaticVariables.stationIDQuery(startStationID)).get(0);
        //this.endStation = databaseRetriever.queryStation(StaticVariables.stationIDQuery(endStationID)).get(0);

        this.startStation = startStation;
        this.startStationID = startStationID;
        this.endStation = endStation;
        this.endStationID = endStationID;
        this.duration = duration;
        try {
            this.startDate = StaticVariables.ft.parse(startDate);
            this.endDate = StaticVariables.ft.parse(endDate);
        } catch (ParseException e) {
            try {
                this.startDate = StaticVariables.ift.parse(startDate);
                this.endDate = StaticVariables.ift.parse(endDate);
            } catch (ParseException e2) {
                //System.out.println("Error parsing Dates in Trip.java Constructor");
                //e.printStackTrace();
                //System.out.println(e.getMessage());
            }
        }
        this.userType = userType;
        this.age = birthYear>1000 ? StaticVariables.currentYear - birthYear:birthYear;
        //System.out.println("age:" + this.age);
        if (gender == 1 ) {
            this.gender = "Male";
        } else  if(gender == 2){
            this.gender = "Female";
        } else {
        this.gender = "Unknown";
        }
        this.dataGroup = dataGroup;
        this.bikeID = bikeID;
        this.distance = (double)Math.round(findDistance() * 100d) / 100d;
        //System.out.println("trip created");


    }


    /**
     * Works out and returns the distance of the trip in kilometers using the algorithm in StaticVariables class
     * @return the length of the trip between the start and end station
     */
    private double findDistance()
    {
        return StaticVariables.calculateDistance(startStation.getLatitude(), startStation.getLongitude(), endStation.getLatitude(), endStation.getLongitude());
    }


    public Station getStartStation() {
        //DatabaseRetriever databaseRetriever = new DatabaseRetriever();
        //return databaseRetriever.queryStation(StaticVariables.stationIDQuery(startStationID)).get(0);
        return startStation;
    }

    /*public void setStartStation(Station startStation) {
        this.startStation = startStation;
    }*/

    public int getStartStationID() {
        return startStationID;
    }

    public void setStartStationID(int startStationID) {
        this.startStationID = startStationID;
    }

    public Station getEndStation() {
        //DatabaseRetriever databaseRetriever = new DatabaseRetriever();
        //return databaseRetriever.queryStation(StaticVariables.stationIDQuery(endStationID)).get(0);
        return endStation;
    }

    /*public void setEndStation(Station endStation) {
        this.endStation = endStation;
    }*/

    public int getEndStationID() {
        return endStationID;
    }

    public void setEndStationID(int endStationID) {
        this.endStationID = endStationID;
    }

    public int getDuration() {
        return duration;
    }


    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Sets the start date by parsing it through the expected parsing format
     * @param startDate start date string form "yyyy-MM-dd hh:mm:ss"
     */
    public void setStartDate(String startDate) {
        try {
            this.startDate = StaticVariables.ft.parse(startDate);
        } catch (ParseException e) {
            System.out.println("Error parsing start date in setter");
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Sets the end date by parsing it through the expected parsing format
     * @param endDate start date string form "yyyy-MM-dd hh:mm:ss"
     */
    public void setEndDate(String endDate) {
        try {
            this.endDate = StaticVariables.ft.parse(endDate);
        } catch (ParseException e) {
            System.out.println("Error parsing end date in setter");
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getBikeID() {
        return bikeID;
    }

    public void setBikeID(int bikeID) {
        this.bikeID = bikeID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Method to set age based on the birthyear of rider
     * @param birthYear Year of birth of bikerider
     */
    public void setAgeFromBirth(int birthYear) {
        this.age = StaticVariables.currentYear - birthYear;
    }

    public String getDataGroup() {
        return dataGroup;
    }

    public void setDataGroup(String dataGroup) {
        this.dataGroup = dataGroup;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    /**
     * gets the end of the bike trip from the bike station attached
     * @return location of ending bikestation
     */
    public String getEnd() {
        //DatabaseRetriever databaseRetriever = new DatabaseRetriever();
        //return databaseRetriever.queryStation(StaticVariables.stationIDQuery(endStationID)).get(0).getAddress();
        return endStation.getAddress();
    }

    /**
     * gets the start of the bike trip from the bike station attached
     * @return location of starting bike station
     */
    public String getStart() {
        //DatabaseRetriever databaseRetriever = new DatabaseRetriever();
        //return databaseRetriever.queryStation(StaticVariables.stationIDQuery(startStationID)).get(0).getAddress();
        return startStation.getAddress();
    }

    /**
     * Prints object for manual testing
     */
    public void testPrint()
    {
        System.out.println("Trip object:\n " +
                "Duration:" + this.getDuration() + "\n" +
                "Distance: " + this.getDistance());
    }

    @Override
    public int hashCode() {
        StaticVariables converter = new StaticVariables();
        int result = 0;
        result = (((startStationID/3) + (endStationID/2) + ((duration + 1) * 2)
                +  converter.asciiConverter(userType)
                + bikeID + converter.asciiConverter(gender) ) * (37 + age)) / 11 ;

        return result;
    }

    /**
     * Gets the starting point for the trip as a PointM object with scaling for density
     * @return startpoint
     */
    public PointM getStartPoint()
    {
        return new PointM(startStation.getLatitude()*StaticVariables.pointMultiplier, startStation.getLongitude()*StaticVariables.pointMultiplier);
    }

    /**
     * gets end point for the trip as a pointM object with scaling for density
     * @return endpoint
     */
    public PointM getEndPoint()
    {
        return new PointM(endStation.getLatitude()*StaticVariables.pointMultiplier, endStation.getLongitude()*StaticVariables.pointMultiplier);
    }


}

