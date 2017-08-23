package seng202.team7;

public class Retailer extends Location implements Data{

    private String name;
    private String City;
    /**
     * Primary Address
     */
    private String pAddress;
    /**
     * Secondary Address
     */
    private String sAddress;
    //IS THIS NECESSARY, If we want to go out side US this is inhibiting
    private String state;
    private int zipCode;
    /**
     * Letter representation of type. Could be made an enum
     */
    private String typeID;
    /**
     * Better description of type
     */
    private String type;



    public Retailer()
    {

    }

    /**
     * Converts an address to a latitude and longitude and stores them within the object.
     * Should be ran on instantiation
     */
    private void addressToLATLONG(String address)
    {

    }
}
