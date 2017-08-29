package seng202.team7;

public class Trip extends Location implements Data {
    //Should start and end address be included as field so it can use these in database
    private Station startStation;
    private Station endStation;

    /**
     * Start and end to reference the names of the stations for easy access from the raw data viewer
     */
    private String start;
    private String end;

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

    /**
     * Currently only useful for testing trip data viewer
     * @param inputStartStation The start station of the trip
     * @param inputEndStation The end station of the trip
     * @param inputDuration The duration of the trip
     */
    public Trip(Station inputStartStation, Station inputEndStation, int inputDuration)
    {
        startStation = inputStartStation;
        endStation = inputEndStation;
        start = startStation.getName();
        end = endStation.getName();
        duration = inputDuration;
    }

    public String getEnd() {
        return end;
    }

    public String getStart() {
        return start;
    }

    public int getDuration() {
        return duration;
    }
}
