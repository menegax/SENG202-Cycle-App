package seng202.team7;

public class Location implements Mappable{
    double latitude;
    double longitude;

    @Override
    public void placeOnMap(double latitude, double longitude) {
    }

    public Location(){

    }

    public Location(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
