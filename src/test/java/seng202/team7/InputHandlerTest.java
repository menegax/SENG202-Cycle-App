package seng202.team7;

import org.junit.Test;
import seng202.team7.DataTypes.Retailer;
import seng202.team7.DataTypes.Station;
import seng202.team7.DataTypes.Trip;
import seng202.team7.DataTypes.Wifi;
import seng202.team7.Input.InputHandler;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static seng202.team7.Database.DatabaseHandler.*;
import static seng202.team7.Database.DatabaseTester.*;

public class InputHandlerTest
{

    InputHandler tester = new InputHandler();

    String file_retailer = "retailer_data_test.csv";
    String file_wifi = "wifi_data_test.csv";
    String file_trip = "trip_data_test.csv";

    //because of file chooser, they always have a valid path
    @Test(expected = IOException.class)
    public void testInvalidName() throws IOException {

        InputHandler testInvalid = new InputHandler();
        testInvalid.loadCSV("somecsv.csv", "wifi", "testInvalid");

    }


    @Test
    public void testRetailer() throws IOException {
        url = testUrl;
        deleteTables();
        createTables();
        InputHandler testerRetailer = new InputHandler();
        assertEquals(282, testerRetailer.loadCSV(file_retailer, "retailer", "default").size());
        //amount added always changes because of duplicate check in database, not so much in test DB created
        //just check that the parser works basically
        //assertTrue(testerRetailer.loadCSV(file_retailer, "retailer", "default").size() >= 0);
        url = null;
    }

    @Test
    public void testWifi() throws IOException {
        url = testUrl;
        deleteTables();
        createTables();
        InputHandler testerWifi = new InputHandler();
        assertEquals(2566, testerWifi.loadCSV(file_wifi, "wifi", "default").size());
        //amount added always changes because of duplicate check in database, not so much in test DB created
        //just check that the parser works basically
        //assertTrue(testerWifi.loadCSV(file_wifi, "wifi", "default").size() >= 0);
        url = null;
    }

    @Test
    public void testTrip() throws IOException {
        url = testUrl;
        deleteTables();
        createTables();
        InputHandler testerTrip = new InputHandler();
        assertEquals(11837, testerTrip.loadCSV(file_trip, "trip", "default").size());
        //amount added always changes because of duplicate check in database, not so much in test DB created
        //just check that the parser works basically
        //assertTrue(testerTrip.loadCSV(file_trip, "trip", "default").size() >= 0);
        url = null;
    }

    @Test
    public void testTripValidityMethod() {
        Station s1 = new Station(231,"5th ave", "CitiBike", 2367.987, 394.98);
        Station s2 = new Station(3241,"34 square", "Bike Shah", 2387.987, 384.98);

        Trip t1 = new Trip(231, s1, 3241, s2, 4345,"2015-10-01 05:22:42","2015-10-01 00:38:42", "customer", 1990, 0, "kl", 1);
        Trip t2 = new Trip(231, s1, 3241, s2, 4345,"2015-10-01 00:20:42","2015-10-01 00:29:42", "CUSTOMER", 34, 1, "test", 2);
        Trip t3 = new Trip(231, s1, 3241, s2,4345,"2015-10-01 00:22:42","2015-10-01 00:38:42", "Subscriber", 1990, 2, "test", 3);

        assertEquals("Success", tester.checkValidity(t1));
        assertEquals("Invalid user type CUSTOMER", tester.checkValidity(t2));
        assertEquals("Success", tester.checkValidity(t3));

    }

    @Test
    public void testWifiValidityMethod() {
        Wifi w1 = new Wifi("BX", "Limited free","Alcatel","5th Ave","NY","Alcatel Hotspot","","",179,91);
        Wifi w2 = new Wifi("SI", "Free","AlcatEl","5th Ave","NY","Alcatel Hotspot","","",181,89);
        Wifi w3 = new Wifi("MN", "Free","Alcatekl","5th Ave","NY","Alcatel Hotspot","","",20,25);

        assertEquals("Invalid wifi latitude 91.0", tester.checkValidity(w1));
        assertEquals("Invalid wifi longitude 181.0", tester.checkValidity(w2));
        assertEquals("Success", tester.checkValidity(w3));
    }


    @Test
    public void testRetailerValidityMethod() {
        Retailer r1 = new Retailer("McD's Lower MN", "New York", "5th ave", "23", "test", 2344, "F", "Phast Phood", "test" );
        Retailer r2 = new Retailer("McD's Upper BO", "New York", "6th ave", "23", "NY", 0, "P", "Phast Phood", "test" );
        Retailer r3 = new Retailer("McD's Upper BO", "loooooooooooooooonnggg citttttyyyyyy", "7th ave", "23", "NY", 2333, "P", "Phast Phood", "test" );
        Retailer r4 = new Retailer("McD's Upper BO", "New York", "8th ave", "23", "NY", 2312, "S", "Phast Phood", "test1" );

        assertEquals("Invalid retailer state test", tester.checkValidity(r1));
        assertEquals("Invalid retailer ZIP 0", tester.checkValidity(r2));
        assertEquals("Invalid retailer city loooooooooooooooonnggg citttttyyyyyy", tester.checkValidity(r3));
        assertEquals("Success", tester.checkValidity(r4));
    }


    @Test
    public void testStationValidityMethod() {
        Station s1 = new Station(231,"", "CitiBike", 15, 25);
        Station s2 = new Station(3241,"34 square", "Bike Shah", 25, 15);

        assertTrue( !tester.checkValidity(s1));
        assertTrue( tester.checkValidity(s2));

    }


}
