package seng202.team7;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Basic Wifi class to store information about a specific wifi location
 * @author Aidan Smith (Morgan English??)
 * Last Edited 29/08/17
 */
public class Wifi extends Location implements Data, java.io.Serializable{
    public static String tableName = "wifi";

    /**
     * SQL script to create wifi table
     */
    public static String tableCreation = "CREATE TABLE IF NOT EXISTS "+tableName+" (\n"
            + "	id integer PRIMARY KEY NOT NULL ,\n"
            + "	burough text,\n"
            + "	type text,\n"
            + "	provider text,\n"
            + "	location text,\n"
            + "	city text,\n"
            + "	SSID text,\n"
            + "	remarks text,\n"
            + " latitude real NOT NULL,\n"
            + " longitude real NOT NULL,\n"
            + "	datagroup text,\n"
            + "	obj blob\n"
            + ");";

    /**
     * Burough location
     *
     */
    private String burough;
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





    //HOW WILL THIS BE INSTANTIATED
    public Wifi(String burough, String type, String provider, String location, String city, String SSID, String remarks, String dataGroup, double longitude, double latitude)
    {
        this.burough = burough;
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


    public String getBurough() {
        return burough;
    }
    public void setBurough(String burough) {
        this.burough = burough;
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
