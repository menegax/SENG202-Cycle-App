package seng202.team7.DataTypes;

/**
 * @author Morgan English
 * location class used as parent for all objects that can be placed on a map
 */
public class Location implements Mappable {
    private double latitude;
    private double longitude;

    @Override
    public void placeOnMap(double latitude, double longitude) {
    }

    public Location(){
    }

    /**
     * Sets the location of the object
     * @param latitude lat of object
     * @param longitude lng of object
     */
    public Location(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Simple getter for the longitude of the object
     * @return longitude of object
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Simple getter for the latitude of the object
     * @return latitude of object
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Simple setter for the latitude of the object
     * @param latitude new latitude to replace object's own
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Simple setter for the longitude of the object
     * @param longitude new longitude to replace object's own
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
