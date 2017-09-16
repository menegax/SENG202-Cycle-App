package seng202.team7;

import java.text.SimpleDateFormat;
import java.lang.Math;

public class StaticVariables {
    private static double defaultDist = 0.1;
    public static int currentYear = 2017;
    public static SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");

    public static String stationIDQuery(int stationID)
    {
        return "SELECT * FROM "+ Station.tableName + " WHERE stationid = " + stationID;
    }

    /**
     * SQL String used to find nearby retailers with default distance
     * @param lat lat of center
     * @param lon lon of center
     * @return SQL Query String
     */
    public static String retailerByLocation(double lat, double lon)
    {
        return retailerByLocation(lat, defaultDist,lon,defaultDist);
    }

    /**
     * SQL String used to find nearby retailers with specified distance
     * @param lat lat of center
     * @param latOffset difference in lat accepted
     * @param lon lon of centre
     * @param lonOffset difference in lon
     * @return SQL Query String
     */
    public static String retailerByLocation(double lat, double latOffset, double lon, double lonOffset)
    {
        return "SELECT * FROM "+Retailer.tableName + "WHERE lat BETWEEN "+(lat-latOffset)+" AND "+(lat+latOffset)+" AND lon BETWEEN "+(lon-lonOffset)+" AND "+(lon+lonOffset);

    }

    /**
     * SQL String used to find nearby wifi with default distance
     * @param lat lat of center
     * @param lon lon of center
     * @return SQL Query String
     */
    public static String wifiByLocation(double lat, double lon)
    {
        return wifiByLocation(lat, defaultDist,lon,defaultDist);
    }
    /**
     * SQL String used to find nearby wifis with specified distance
     * @param lat lat of center
     * @param latOffset difference in lat accepted
     * @param lon lon of centre
     * @param lonOffset difference in lon
     * @return SQL Query String
     */
    public static String wifiByLocation(double lat, double latOffset, double lon, double lonOffset)
    {
        return "SELECT * FROM "+Wifi.tableName + "WHERE lat BETWEEN "+(lat-latOffset)+" AND "+(lat+latOffset)+" AND lon BETWEEN "+(lon-lonOffset)+" AND "+(lon+lonOffset);

    }


    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2)
    {
        double radiusOfEarth = 6372; //radius of earth in km
        double dLat = degreeToRadian(lat2 - lat1);
        double dLon = degreeToRadian(lon2 - lon1);

        double a =
                Math.sin(dLat/2) * Math.sin(dLat/2)
                + Math.cos(degreeToRadian(lat1)) * Math.cos(degreeToRadian(lat2))
                * Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
        double d = radiusOfEarth * c;
        return d;
    }

    private static double degreeToRadian(double degree)
    {
        return degree* (Math.PI/180);
    }
}
