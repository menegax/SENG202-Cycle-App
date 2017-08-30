package seng202.team7;

/**
 * Base class for a Retailer object to hold parameters and basic getters and setters
 * @author Aidan Smith asm142, (Morgan English???)
 * Last edited 29/08/17
 */
public class Retailer extends Location implements Data, java.io.Serializable{


    /**
     * SQL tablename
     */
    public static String tableName = "retailer";
    /**
     * SQL table creation script
     */
    public static String tableCreation = "CREATE TABLE IF NOT EXISTS "+tableName+" (\n"
            + "	id integer PRIMARY KEY NOT NULL ,\n"
            + "	name text,\n"
            + "	city text,\n"
            + "	pAddress text,\n"
            + " sAddress text,\n"
            + "	state text,\n"
            + "	zipCode integer,\n"
            + "	typeID text,\n"
            + "	type text,\n"
            + " latitude real NOT NULL,\n"
            + " longitude real NOT NULL,\n"
            + "	datagroup text,\n"
            + "	obj blob\n"
            + ");";
    /**
     * Retailer name
     */
    private String name;
    /**
     * City
     */
    private String city;
    /**
     * Primary Address
     */
    private String pAddress;
    /**
     * Secondary Address
     */
    private String sAddress;
    /**
     * State
     */
    private String state;
    /**
     * zipCode
     */
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
     * Datagroup string for database storing
     */
    private String dataGroup;

    private double latitude;
    private double longitude;



    public Retailer(String name, String city, String pAddress, String sAddress, String state, int zipCode, String typeID, String type, String dataGroup)
    {
        this.name = name;
        this.city = city;
        this.pAddress = pAddress;
        this.sAddress = sAddress;
        this.state = state;
        this.zipCode = zipCode;
        this.typeID = typeID;
        this.type = type;
        this.dataGroup = dataGroup;
        addressToLATLONG();
    }


    /**
     * Converts an address to a latitude and longitude and stores them within the object.
     * Should be ran on instantiation
     */
    private void addressToLATLONG()
    {

        //find lat and long
        this.latitude = 0;
        this.longitude = 0;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getPAddress() {
        return pAddress;
    }
    public void setpAddress(String pAddress) {
        this.pAddress = pAddress;
    }

    public String getSAddress() {
        return sAddress;
    }
    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getTypeID() {
        return typeID;
    }
    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getDataGroup() {
        return dataGroup;
    }
    public void setDataGroup(String dataGroup) {
        this.dataGroup = dataGroup;
    }

    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void print(){
        System.out.println("Name: " + name +" Lat: "+latitude + " Lon: "+ longitude);
    }
}
