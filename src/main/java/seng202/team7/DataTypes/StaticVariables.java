package seng202.team7.DataTypes;

import java.text.SimpleDateFormat;
import java.lang.Math;
import java.util.ArrayList;

/**
 * Holds many static functions
 * Many functions here to create SQL query Stings for use in DatabaseRetriever
 * @author Morgan English
 */
public class StaticVariables {

    /**
     * point multiplier for point density on heat map
     */
    public static int pointMultiplier = 3000;
    /**
     * Step size used for lazyloading
     */
    public static int step = 50;
    /**
     * default distance for searching for nearby locations (1.12 km)
     */
    public static double defaultDist = 0.01;
    /**
     * current year, for finding age of users
     */
    public static int currentYear = 2017;
    /**
     * String for making a date object from the string supplied in the csv
     */
    public static SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    public static SimpleDateFormat ift = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");

    /**
     * Creates an SQL query String for search by an int.
     * @param tableName table to search
     * @param col       column to search
     * @param toMatch   int to match
     * @return sql query to run on databaseRetriver object
     */
    public static String singleIntQuery(String tableName, String col, int toMatch) {
        return "SELECT obj FROM " + tableName + " WHERE " + col + " = " + toMatch;
    }

    /**
     * Creates an SQL query string for searching by containing the string using LIKE(%string%) syntax
     * @param tableName table to search
     * @param col       column to search
     * @param toMatch   String to match
     * @return SQL query string
     */
    public static String singleStringQueryLike(String tableName, String col, String toMatch) {
        return "SELECT obj FROM " + tableName + " WHERE LOWER(" + col + ") LIKE \"%" + toMatch.toLowerCase() + "%\"";
    }

    /**
     * Creates an SQL query to matching strings. Case in-sensitive
     * @param tableName table to search
     * @param col       column to search
     * @param toMatch   string to match(case in-sensitiive)
     * @return SQL Query string
     */
    public static String singleStringQuery(String tableName, String col, String toMatch) {
        return "SELECT obj FROM " + tableName + " WHERE LOWER(" + col + ") = \"" + toMatch.toLowerCase() + "\"";
    }

    /**
     * Creates an SQL query to match two strings. Case in-sensitive
     * @param tableName table to search
     * @param col1      col to find first string
     * @param match1    first string to match
     * @param col2      col fo find second string
     * @param match2    second string to match
     * @return SQL Query String
     */
    public static String doubleStringQuery(String tableName, String col1, String match1, String col2, String match2) {
        return "SELECT obj FROM " + tableName + " WHERE LOWER(" + col1 + ") = \"" + match1.toLowerCase() + "\" AND LOWER(" + col2 + ") = \"" + match2.toLowerCase() + "\"";
    }

    /**
     * Creates and SQL query to match a String and an Int
     * @param tableName table to search
     * @param col1      string column to search
     * @param match1    string to match(case in-sensitive)
     * @param col2      int column to search
     * @param match2    int to match
     * @return SQL Query String
     */
    public static String stringIntQuery(String tableName, String col1, String match1, String col2, int match2) {
        return "SELECT obj FROM " + tableName + " WHERE LOWER(" + col1 + ") = \"" + match1.toLowerCase() + "\" AND " + col2 + " = " + match2;
    }

    public static String doubleStringIntQuery(String tableName, String col1, String match1, String col2, String match2, String col3, int match3) {
        return "SELECT obj FROM " + tableName + " WHERE LOWER(" + col1 + ") = \"" + match1.toLowerCase() + "\" AND LOWER(" + col2 + ") = \"" + match2.toLowerCase() + "\" AND " + col3 + " = " + match3;

    }

    /**
     * SQL query String for filtering wifi objects when all fields are filled in the mapViewer window
     *
     * @param burough  burough to search
     * @param type     type to search
     * @param provider provider to search
     * @return SQL Query string
     */
    public static String mapViewWifiQuery(String burough, String type, String provider) {
        return "SELECT obj FROM " + Wifi.tableName + " WHERE LOWER(" + Wifi.columns[1] + ") = '" + burough.toLowerCase()
                + "' AND LOWER(" + Wifi.columns[2] + ") = '" + type.toLowerCase() + "' AND LOWER(" + Wifi.columns[3] + ") = '" + provider.toLowerCase() + "';";
    }

    public static String mapViewRetailerQuery(int zip, String street, String type) {
        return "SELECT obj FROM " + Retailer.tableName + " WHERE LOWER(" + Retailer.columns[13] + ") = '" + street.toLowerCase()
                + "' AND LOWER(" + Retailer.columns[7] + ") = '" + type.toLowerCase() + "' AND " + Retailer.columns[3] + " = " + zip;
    }

    /**
     * SQL query used to implement lazy loading. relies on StaticVariable.step
     *
     * @param tableName table to load from
     * @param start     the starting point for the current loading segment
     * @return SQL Query string for lazy loading
     */
    public static String steppedQuery(String tableName, int start) {
        return "SELECT obj FROM " + tableName + " LIMIT " + start + "," + (start + step);

    }

    /**
     * SQL query for station by ID
     *
     * @param stationID ID to search for
     * @return SQL Query finding station by id
     */
    public static String stationIDQuery(int stationID) {
        return "SELECT * FROM " + Station.tableName + " WHERE stationid = " + stationID;
    }

    /**
     * SQl query for wifi by ID
     *
     * @param wifiID ID to search for
     * @return sql queary finding wifi by id
     */
    public static String wifiIDQuery(int wifiID) {
        return "SELECT * FROM " + Wifi.tableName + " WHERE id = " + wifiID;
    }

    /**
     * SQL String used to find nearby retailers with default distance
     *
     * @param lat lat of center
     * @param lon lon of center
     * @return SQL Query String
     */

    public static String retailerByLocation(double lat, double lon) {
        return retailerByLocation(lat, defaultDist, lon, defaultDist);
    }

    /**
     * SQL String used to find nearby retailers with specified distance
     *
     * @param lat       lat of center
     * @param latOffset difference in lat accepted
     * @param lon       lon of centre
     * @param lonOffset difference in lon
     * @return SQL Query String
     */
    public static String retailerByLocation(double lat, double latOffset, double lon, double lonOffset) {
        return "SELECT obj FROM " + Retailer.tableName + " WHERE (latitude BETWEEN (" + (lat - latOffset) + ") AND (" + (lat + latOffset) + ")) AND (longitude BETWEEN (" + (lon - lonOffset) + ") AND (" + (lon + lonOffset) + "));";

    }

    /**
     * SQL String used to find nearby wifi with default distance
     *
     * @param lat lat of center
     * @param lon lon of center
     * @return SQL Query String
     */
    public static String wifiByLocation(double lat, double lon) {
        return wifiByLocation(lat, defaultDist, lon, defaultDist);
    }

    /**
     * SQL String used to find nearby wifis with specified distance
     *
     * @param lat       lat of center
     * @param latOffset difference in lat accepted
     * @param lon       lon of centre
     * @param lonOffset difference in lon
     * @return SQL Query String
     */
    public static String wifiByLocation(double lat, double latOffset, double lon, double lonOffset) {
        //System.out.println("latlow: " + (lat-latOffset) + " lathigh" + (lat+latOffset));
        return "SELECT obj FROM " + Wifi.tableName + " WHERE (latitude BETWEEN (" + (lat - latOffset) + ") AND (" + (lat + latOffset) + ")) AND (longitude BETWEEN (" + (lon - lonOffset) + ") AND (" + (lon + lonOffset) + "));";

    }

    /**
     * Calculates distance in meters between two lat lon points
     *
     * @param lat1 lat point1
     * @param lon1 lon point1
     * @param lat2 lat point2
     * @param lon2 lon point2
     * @return distance in meters as double
     */
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double radiusOfEarth = 6372; //radius of earth in km
        double dLat = degreeToRadian(lat2 - lat1);
        double dLon = degreeToRadian(lon2 - lon1);

        double a =
                Math.sin(dLat / 2) * Math.sin(dLat / 2)
                        + Math.cos(degreeToRadian(lat1)) * Math.cos(degreeToRadian(lat2))
                        * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = radiusOfEarth * c;
        return d;
    }

    /**
     * Calculates the distance in metres between a point and a line defined by two locations
     *
     * @param start the start location of the line
     * @param end   the end location of the line
     * @param lat   the latitude of the point
     * @param lon   the longitude of the point
     * @return the distance between the point and the line
     */
    public static double calculateDistance(Location start, Location end, double lat, double lon) {
        double x0 = longitudeToKMs(lon);
        double y0 = latitudeToKMs(lat);
        double x1 = longitudeToKMs(start.getLongitude());
        double y1 = latitudeToKMs(start.getLatitude());
        double x2 = longitudeToKMs(end.getLongitude());
        double y2 = latitudeToKMs(end.getLatitude());
        double distance =
                (Math.abs((y2 - y1) * x0 - (x2 - x1) * y0 + x2 * y1 - y2 * x1)) /
                        (Math.sqrt(Math.pow((y2 - y1), 2) + Math.pow((x2 - x1), 2)));
        return distance * 1000;
    }

    /**
     * returns the ascii value of a given string, or 1 if the result is 0
     *
     * @param toConvert
     * @return convertedValue
     */
    public static int asciiConverter(String toConvert) {

        int convertedValue = 0;
        for (int i = 0; i < toConvert.length(); i++) {
            convertedValue += ((int) (toConvert.charAt(i)));
        }

        if (convertedValue == 0) {
            return 1;
        } else {
            return convertedValue;
        }
    }

    /**
     * Converts degrees to radians. Helper for calculateDistance
     *
     * @param degree degrees to convert
     * @return radian out as double
     */
    private static double degreeToRadian(double degree) {
        return degree * (Math.PI / 180);
    }

    /**
     * Converts from latitude to kilometres
     *
     * @param latitude latitude to convert
     * @return kilometres as a double
     */
    public static double latitudeToKMs(double latitude) {
        return latitude * 111;
    }

    /**
     * Converts from longitude to kilometres at 40 degrees latitude (NYC)
     *
     * @param longitude longitude to convert
     * @return kilometres as a double
     */
    public static double longitudeToKMs(double longitude) {
        return longitude * 85;
    }

    /**
     * Checks if a string is in an Array of strings.
     * @param toFind string to find
     * @param array Array of strings to look in
     * @return boolean. true = found, false = not found
     */
    public static boolean stringInArray(String toFind, ArrayList<String> array)
    {
    for(String s: array){
        if(s.equals(toFind))
            return true;
    }
    return false;
    }
}
