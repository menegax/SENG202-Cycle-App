package seng202.team7;


import org.junit.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by lbr63 on 22/09/17.
 */
public class StaticVariablesTest
{



    @Test
    public void calculateDistanceTest() throws Exception {

        //calculateDistance(double lat1, double lon1, double lat2, double lon2)

        double lat1 = 15.0;
        double lon1 = 15.0;
        double lat2 = 20.0;
        double lon2 = 20.0;

        StaticVariables tester = new StaticVariables();

        assertEquals(tester.calculateDistance(lat1, lon1, lat2, lon2), 768.2461727646099);


    }



    @Test
    public void retailerByLocationTest() {


        double lat = 15.0;
        double latOffset = 16.0;
        double lon = 17.0;
        double lonOffset = 18.0;

        StaticVariables tester = new StaticVariables();
        String expected = "SELECT obj FROM "+ Retailer.tableName + " WHERE (latitude BETWEEN ("+(lat-latOffset)+") AND ("+(lat+latOffset)+")) AND (longitude BETWEEN ("+(lon-lonOffset)+") AND ("+(lon+lonOffset)+"));";

        assertEquals(tester.retailerByLocation(lat, latOffset, lon, lonOffset), expected);


    }

}