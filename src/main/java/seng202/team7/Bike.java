package seng202.team7;

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

    public Bike(int bikeID, String datagroup)
    {
        this.bikeID = bikeID;
        this.datagroup = datagroup;

    }

    public int getBikeID() {
        return bikeID;
    }
    public void setBikeID(int bikeID) {
        this.bikeID = bikeID;
    }

    public String getDatagroup() {
        return datagroup;

    }
    public void setDatagroup(String datagroup) {
        this.datagroup = datagroup;
    }
}
