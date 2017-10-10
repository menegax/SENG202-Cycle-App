package seng202.team7.DataTypes;

import seng202.team7.DataTypes.Data;
import seng202.team7.DataTypes.Location;
import seng202.team7.DataTypes.StaticVariables;

/**
 * Basic Wifi class to store information about a specific wifi location
 * @author Aidan Smith , Morgan English
 * Last Edited 29/08/17
 */
public class Wifi extends Location implements Data, java.io.Serializable{
    /**
     * SQL table name
     */
    public static String tableName = "wifi";
    /**
     * Strings for SQL table columns
     */
    public static String[] columns = {"id","burough","type","provider","location","city","SSID","remarks","latitude","longitude","datagroup","obj"};

    /**
     * SQL script to create wifi table
     */
    public static String tableCreation = "CREATE TABLE IF NOT EXISTS "+tableName+" (\n"
            + columns[0] +" integer PRIMARY KEY NOT NULL ,\n"
            + columns[1] +" text,\n"
            + columns[2] +"	text,\n"
            + columns[3] +"	text,\n"
            + columns[4] +"	text,\n"
            + columns[5] +"	text,\n"
            + columns[6] +"	text,\n"
            + columns[7] +"	text,\n"
            + columns[8] +" real NOT NULL,\n"
            + columns[9] +" real NOT NULL,\n"
            + columns[10] +" text,\n"
            + columns[11] +" blob\n"
            + ");";

    /**
     * Burough location
     *
     */
    private String borough;
    /**
     * Type of wifi: Limited free, free, partner site
     */
    private String type;
    /**
     * Provider of wifi
     */
    private String provider;
    /**
     * physical address or loose direction
     *
     */
    private String location;
    /**
     * City the Wi-fi hotspot is in
     */
    private String city;
    /**
     * Name of wifi network
     */
    private String SSID;
    /**
     * Brief location remarks
     */
    private String remarks;
    /**
     * Group for sorting within table
     */
    private String dataGroup;

    /**
     * Latitude of Wifi hotspot
     */
    private double latitude;
    /**
     * Longitude of Wifi hotspot
     */
    private double longitude;


    /**
     * Constructor For Wifi objects
     * @param borough burough located in e.g ("QU")
     * @param type Type of wifi as string
     * @param provider provider as string
     * @param location location as string
     * @param city city as String
     * @param SSID wifi SSID as String
     * @param remarks remarks as String
     * @param dataGroup datagroup to sort with database
     * @param longitude longitude Wifi is located at
     * @param latitude latitude wifi is located at
     */
    public Wifi(String borough, String type, String provider, String location, String city, String SSID, String remarks, String dataGroup, double longitude, double latitude)
    {
        this.borough = borough;
        this.type = type;
        this.provider = provider;
        this.location = location;
        this.city = city;
        this.SSID = SSID;
        this.remarks = remarks;
        this.dataGroup = dataGroup;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     *
     * @return borough of retailer object
     */
    public String getBorough() {
        return borough;
    }

    /**
     * set burough
     * @param borough to set
     */
    public void setBorough(String borough) {
        this.borough = borough;
    }

    /**
     * gets type
     * @return type of wifi
     */
    public String getType() {
        return type;
    }

    /**
     * sets type of wifi
     * @param type of wifi to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * gets provider of wifi
     * @return provider of wifi
     */
    public String getProvider() {
        return provider;
    }

    /**
     * sets provider of wifi
     * @param provider to set
     */
    public void setProvider(String provider) {
        this.provider = provider;
    }

    /**
     * gets location string of wifi
     * @return location string
     */
    public String getLocation() {
        return location;
    }

    /**
     * sets location string
     * @param location string to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets city the wifi is in
     * @return city wifi is in
     */
    public String getCity() {
        return city;
    }

    /**
     * sets city the wifi is in
     * @param city wifi is located in
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets SSID of Wifi
     * @return SSID of wifi
     */
    public String getSSID() {
        return SSID;
    }

    /**
     * sets the ssid
     * @param SSID to set
     */
    public void setSSID(String SSID) {
        this.SSID = SSID;
    }

    /**
     * gets the remarks for the wifi(String), vague comments about wifi
     * @return remarks for the wifi
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * sets the remarks for wifi, vague comments about wifi
     * @param remarks remarks of wifi
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * gets datagroup the wifi belongs to
     * @return datagroup string
     */
    public String getDataGroup() {
        return dataGroup;
    }

    /**
     * Sets Datagroup String
     * @param dataGroup string to sort by
     */
    public void setDataGroup(String dataGroup) {
        this.dataGroup = dataGroup;
    }

    /**
     * gets Longitude of wifi
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets longitude of wifi
     * @param longitude to set
     */
    public void setLongitude(double longitude) {
        //Check value on change??
        this.longitude = longitude;
    }

    /**
     * gets latitude of wifi
     * @return latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * sets latitude of wifi
     * @param latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    /**
     * Change the tostring method to show usefull information about the wifi object
     */
    public String toString()
    {
        return "Wifi object @ Lat: "+(double)latitude + " Lon:" + longitude + " Type: " + type;
    }

    /**
     * Prints basic information about the wifi object
     */
    public void print()
    {
        System.out.println(this.toString());
    }

    @Override
    /**
     * Ovverides the hashcode to get a primary key from hashcode for the database
     */
    public int hashCode() {
        StaticVariables converter = new StaticVariables();
        int result = 0;
        result = (int) ((longitude*100000 + latitude*100000 + converter.asciiConverter(provider)
                + converter.asciiConverter(SSID)
                + converter.asciiConverter(city) + converter.asciiConverter(provider) ) * 37) / 11 ;

        return result;
    }
}
