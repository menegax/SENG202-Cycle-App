package seng202.team7;

public class Station extends Location implements Data, java.io.Serializable {
    public static String tableName = "station";
    public static String tableCreation = "CREATE TABLE IF NOT EXISTS "+tableName+" (\n"//need a name field
            + "	bikeid integer PRIMARY KEY NOT NULL,\n"
            + "	address text,\n"
            + " latitude real NOT NULL,\n"
            + " longitude real NOT NULL,\n"
            + "	datagroup text,\n"
            + " obj blob"
            + ");";
    private int id;
    private String address;
    private String dataGroup;
    private double latitude;
    private double longitude;


    public Station(int id, String address, String dataGroup, double latitude, double longitude)
    {
        this.id = id;
        this.address = address;
        this.dataGroup = dataGroup;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
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
        //this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        //this.longitude = longitude;
    }

    public void print()
    {
        System.out.println("Lat: "+latitude + " Lon: "+ longitude);
    }

}
