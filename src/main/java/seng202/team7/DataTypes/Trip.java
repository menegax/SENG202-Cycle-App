package seng202.team7.DataTypes;

import seng202.team7.Analysis.PointM;
import seng202.team7.DataTypes.Data;
import seng202.team7.DataTypes.Location;
import seng202.team7.DataTypes.StaticVariables;
import seng202.team7.DataTypes.Station;

import java.text.ParseException;
import java.util.Date;

/**
 * Model class for the basic trip object
 * which represents a trip taken by a cyclist between two stations
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
    public Trip(int startStationID, Station startStation, int endStationID, Station endStation, int duration, String startDate, String endDate, String userType, int birthYear, int gender, String dataGroup, int bikeID) {
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
    private double findDistance() {
        return StaticVariables.calculateDistance(startStation.getLatitude(), startStation.getLongitude(), endStation.getLatitude(), endStation.getLongitude());
    }

    /**
     * Gets the start station of the trip
     * @return The station object that is the start of the trip
     */
    public Station getStartStation() {
        return startStation;
    }

    /**
     * Gets the ID of the start station
     * @return The ID of the start station as an integer
     */
    public int getStartStationID() {
        return startStationID;
    }

    /**
     * Sets the start station ID to the ID provided
     * @param startStationID The ID of the new start station
     */
    public void setStartStationID(int startStationID) {
        this.startStationID = startStationID;
    }

    /**
     * Gets the end station of the trip
     * @return The station object that is the end of the trip
     */
    public Station getEndStation() {
        //DatabaseRetriever databaseRetriever = new DatabaseRetriever();
        //return databaseRetriever.queryStation(StaticVariables.stationIDQuery(endStationID)).get(0);
        return endStation;
    }

    /**
     * Gets the ID of the end station
     * @return The ID of the end station as an integer
     */
    public int getEndStationID() {
        return endStationID;
    }

    /**
     * Sets the end station ID to the ID provided
     * @param endStationID The ID of the new end station
     */
    public void setEndStationID(int endStationID) {
        this.endStationID = endStationID;
    }

    /**
     * Gets the duration of the trip
     * @return The duration of the trip in seconds
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the trip to the provided duration
     * @param duration The new duration of the trip
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Gets the start date of the trip as a date object
     * @return The start date of the trip
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the trip to the provided start date
     * @param startDate The new start date of the trip
     */
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

    /**
     * Gets the end date of the trip as a date object
     * @return The end date of the trip
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the trip to the provided end date
     * @param endDate The new end date of the trip
     */
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

    /**
     * Gets the type of user pass used for the trip
     * @return The type of user pass of the trip
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Sets the user pass type to the type provided
     * @param userType The new user pass type of the trip
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * Gets the ID of the bike that was used in the trip
     * @return The bike ID of the trip as an integer
     */
    public int getBikeID() {
        return bikeID;
    }

    /**
     * Sets the ID of the bike used in the trip to the provided ID
     * @param bikeID The new ID of the bike used in the trip
     */
    public void setBikeID(int bikeID) {
        this.bikeID = bikeID;
    }

    /**
     * Gets the gender of the user who performed the trip
     * @return The gender of the trip taker as a string
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender of the trip taker to be the new gender provided
     * @param gender The new gender of the trip taker
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets the age of the user who performed the trip
     * @return The age of the trip taker as an integer
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the trip taker to be the new age provided
     * @param age The new age of the trip taker
     */
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

    /**
     * Gets the datagroup that the trip belongs to
     * @return The datagroup that the trip belongs to as a string
     */
    public String getDataGroup() {
        return dataGroup;
    }

    /**
     * Sets the datagroup that the trip belongs to as the datagroup provided
     * @param dataGroup The new datagroup of the trip
     */
    public void setDataGroup(String dataGroup) {
        this.dataGroup = dataGroup;
    }

    /**
     * Sets the distance of the trip to a new distance provided
     * @param distance The new distance of the trip as a double
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Gets the distance of the trip as a double
     * @return The distance of the trip
     */
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
    public void testPrint() {
        System.out.println("Trip object:\n " +
                "Duration:" + this.getDuration() + "\n" +
                "Distance: " + this.getDistance());
    }

    /**
     * Overwrites the hashcode of the trip object to get a primary key for the database
     * @return The new hascode of the trip object
     */
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
    public PointM getStartPoint() {
        return new PointM(startStation.getLatitude()*StaticVariables.pointMultiplier, startStation.getLongitude()*StaticVariables.pointMultiplier);
    }

    /**
     * gets end point for the trip as a pointM object with scaling for density
     * @return endpoint
     */
    public PointM getEndPoint() {
        return new PointM(endStation.getLatitude()*StaticVariables.pointMultiplier, endStation.getLongitude()*StaticVariables.pointMultiplier);
    }
}

