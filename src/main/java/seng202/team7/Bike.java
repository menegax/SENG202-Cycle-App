package seng202.team7;

/**
 * UNUSED class for bike objects
 * @author Morgan English
 */
public class Bike implements Data{
    /**
     * name for the table
     */
    public static String tableName = "bike";

    /**
     * SQL string to create the table
     */
    public static String tableCreation = "CREATE TABLE IF NOT EXISTS " + tableName + " (\n"
            + "	id integer PRIMARY KEY NOT NULL ,\n"
            + "	bikeID integer,\n"
            + "	datagroup text\n"
            + ");";
    /**
     * ID of the bike
     */
    private int bikeID;
    /**
     * datagroup for sorting in the table
     */
    private String datagroup;

    /**
     * todo
     * @param bikeID Bike number ID
     * @param datagroup
     * todo
     */
    public Bike(int bikeID, String datagroup)
    {
        this.bikeID = bikeID;
        this.datagroup = datagroup;

    }

    /**
     * @return the bikeID number
     */
    public int getBikeID() {
        return bikeID;
    }

    /**
     * creates a bike ID and sets it to a bike object
     * @param bikeID The bike ID number
     */
    public void setBikeID(int bikeID) {
        this.bikeID = bikeID;
    }

    /**
     * todo
     * @return the datagroup
     */
    public String getDatagroup() {
        return datagroup;

    }

    /**
     * todo
     * @param datagroup set of data
     */
    public void setDatagroup(String datagroup) {
        this.datagroup = datagroup;
    }
}
