package seng202.team7.DataTypes;

import seng202.team7.DataTypes.Data;
import seng202.team7.DataTypes.Location;

/**
 * Station objects primarily used for Trips
 * @author Morgan English
 */
public class Station extends Location implements Data, java.io.Serializable {
    /**
     * SQL tablename
     */
    public static String tableName = "station";

    /**
     * String list of columns for database table
     */
    public static String columns[] = {"stationid","address","latitude","longitude","datagroup","obj"};
    /**
     * SQL creation table string
     */
    public static String tableCreation = "CREATE TABLE IF NOT EXISTS "+tableName+" (\n"
            + columns[0]+" integer PRIMARY KEY NOT NULL,\n"
            + columns[1]+" text,\n"
            + columns[2]+" real NOT NULL,\n"
            + columns[3]+" real NOT NULL,\n"
            + columns[4]+" text,\n"
            + columns[5]+" blob"
            + ");";


    private int id;
    private String address;
    private String dataGroup;
    private double latitude;
    private double longitude;


    /**
     * Complete constructor for Station
     * @param id bikeStation number as identifier
     * @param address address location of bikeStaion
     * @param dataGroup String datagroup for sorting data
     * @param latitude Latitude of location
     * @param longitude Latitude of location
     */
    public Station(int id, String address, String dataGroup, double latitude, double longitude)
    {
        this.id = id;
        this.address = address;
        this.dataGroup = dataGroup;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Retturns the id of the bike station
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * sets id of the bike station
     * @param id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * gets the address of the Station
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the adrress of the station
     * @param address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * gets the datagroup string the station is stored by
     * @return datagroup
     */
    public String getDataGroup() {
        return dataGroup;
    }

    /**
     * Sets the datagroup string
     * @param dataGroup to set
     */
    public void setDataGroup(String dataGroup) {
        this.dataGroup = dataGroup;
    }

    /**
     * Gets latitude of station
     * @return latitude of station
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets latitude of station
     * @param latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets longitude of station
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * sets longitude
     * @param longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Print method for checking the object is correct only prints lat and long
     */
    public void print()
    {
        System.out.println("Lat: "+latitude + " Lon: "+ longitude);
    }

}
