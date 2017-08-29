package seng202.team7;

/**
 * Basic Wifi class to store information about a specific wifi location
 * @author Aidan Smith (Morgan English??)
 * Last Edited 29/08/17
 */

public class Wifi extends Location implements Data{
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


    //HOW WILL THIS BE INSTANTIATED

    /**
     * Currently only useful for testing wifi data viewer
     * @param inputProvider The provider of the Wifi
     * @param inputType The type of the Wifi provided
     * @param inputLocation The location of the Wifi
     * @param inputBurough The burough where the Wifi is located
     */
    public Wifi(String inputProvider, String inputType, String inputLocation, String inputBurough)
    {
        provider = inputProvider;
        type = inputType;
        location = inputLocation;
        burough = inputBurough;
    }

    public String getProvider() {
        return provider;
    }

    public String getType() {
        return type;
    }

    public String getLocation() {
        return location;
    }

    public String getBurough() {
        return burough;
    }
}
