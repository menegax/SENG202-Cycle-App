package seng202.team7;

public class Station extends Location implements Data {
    private String address;
    private int id;
    private String dataGroup;
    private String name;

    public Station(String inputName)
    {
        name = inputName;
    }

    public String getName() {
        return name;
    }
}
