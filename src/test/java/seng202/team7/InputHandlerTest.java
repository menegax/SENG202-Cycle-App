package seng202.team7;

import org.junit.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;



import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class InputHandlerTest
{

    InputHandler tester = new InputHandler();

    /*
    String file_retailer = "Team7_Seng202\\src\\test\\test_resources\\retailer_data_test.csv";
    String file_wifi = "Team7_Seng202\\src\\test\\test_resources\\wifi_data_test.csv";
    String file_trip = "Team7_Seng202\\src\\test\\test_resources\\trip_data_test.csv";
    not sure how to do this, below works fine for now
    */

    String file_retailer = "retailer_data_test.csv";
    String file_wifi = "wifi_data_test.csv";
    String file_trip = "trip_data_test.csv";



    @Test
    public void testRetailer() throws IOException {

        assertEquals(771, tester.loadCSV(file_retailer, "retailer", "default").size());

    }
    @Test
    public void testWifi() throws IOException {

        assertEquals(2566, tester.loadCSV(file_wifi, "wifi", "default").size());

    }

    /*@Test
    public void testTrip() throws IOException {

        assertEquals(21832, tester.loadCSV(file_trip, "trip").size());

    }*/



}
