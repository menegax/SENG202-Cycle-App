package seng202.team7;

/**
 * @author Morgan English
 * location class used as parent for all objects that can be placed on a map
 */
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

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
