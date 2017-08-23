package seng202.team7;

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
    public Wifi()
    {

    }
}
