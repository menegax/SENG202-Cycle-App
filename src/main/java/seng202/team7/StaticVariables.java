package seng202.team7;

import java.text.SimpleDateFormat;
import java.lang.Math;

public class StaticVariables {
    public static int currentYear = 2017;
    public static SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");

    public static String stationIDQuery(int stationID)
    {
        return "SELECT * FROM "+ Station.tableName + " WHERE stationid = " + stationID;
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
