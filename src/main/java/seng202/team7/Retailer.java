package seng202.team7;


/**
 * Base class for a Retailer object to hold parameters and basic getters and setters
 * @author Aidan Smith asm142, (Morgan English???)
 * Last edited 29/08/17
 */
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


    /**
     * Constructor of a retailer, currently only able to test retailer data viewer functionality
     * @param inputName The name of the retailer
     * @param inputPAddress The primary address of the retailer
     * @param inputType The more detailed description of the type of the retailer
     */
    public Retailer(String inputName, String inputPAddress, String inputType)
    {
        name = inputName;
        pAddress = inputPAddress;
        type = inputType;
    }

    /**
     * Converts an address to a latitude and longitude and stores them within the object.
     * Should be ran on instantiation
     */
    private void addressToLATLONG(String address)
    {

    }

    public String getName() {
        return name;
    }

    public String getPAddress() {
        return pAddress;
    }

    public String getType() {
        return type;
    }
}
