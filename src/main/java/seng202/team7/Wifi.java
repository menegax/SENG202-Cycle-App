package seng202.team7;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

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
     * @param type
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
     * todo
     * @return
     */
    public String getBorough() {
        return borough;
    }
    public void setBorough(String borough) {
        this.borough = borough;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getProvider() {
        return provider;
    }
    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getSSID() {
        return SSID;
    }
    public void setSSID(String SSID) {
        this.SSID = SSID;
    }

    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDataGroup() {
        return dataGroup;
    }
    public void setDataGroup(String dataGroup) {
        this.dataGroup = dataGroup;
    }

    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        //Check value on change??
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString()
    {
        return "Wifi object @ Lat: "+(double)latitude + " Lon:" + longitude + " Type: " + type;
    }

    public void print()
    {
        System.out.println(this.toString());
    }

}
