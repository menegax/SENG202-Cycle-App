package seng202.team7;

public class Trip extends Location implements Data {
    //Should start and end address be included as field so it can use these in database
    private Station startStation;
    private Station endStation;
    /*What type should we make this
     * thinking ENUM so more types can be added
     */
    //private String userType;
    /**
     * Duration in seconds
     */
    private int duration;
    private int bikeID;
    /*
     * What type should times use. Search for a possible library to use
     */
    //private ___ startTime;
    //private ___ endTime;
    /**
     * Gender, could this be an Enum or int??
      */
    private String gender;
    private int birthYear;
    /**
     * Attribute must be derived
     */
    private int age;
    private String dataGroup;

    public Trip()
    {

    }
}
