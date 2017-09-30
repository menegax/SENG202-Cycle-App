package seng202.team7;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Date;

public class TripTest extends TestCase {


    @Test
    public void test_age() {

        Station s1 = new Station(231,"5th ave", "CitiBike", 2367.987, 394.98);
        Station s2 = new Station(3241,"34 square", "Bike Shah", 2387.987, 384.98);
        Trip testBirthYear = new Trip(231,3241,4345,"2015-10-01 00:22:42","2015-10-01 00:38:42", "Subscriber", 1990, 2, "test", 1);
        Trip testAge = new Trip(231,3241,4345,"2015-10-01 00:22:42","2015-10-01 00:38:42", "Subscriber", 15, 2, "test", 2);

        assertEquals(27, testBirthYear.getAge());
        assertEquals(15, testAge.getAge());
    }

    @Test
    public void test_date() {

        Station s1 = new Station(231,"5th ave", "CitiBike", 2367.987, 394.98);
        Station s2 = new Station(3241,"34 square", "Bike Shah", 2387.987, 384.98);
        Trip test = new Trip(231,3241,4345,"2015-10-01 00:22:42","2015-10-01 00:38:42", "Subscriber", 1990, 2, "test", 3);

        assertTrue(test.getStartDate() instanceof Date);

    }

    @Test
    public void test_gender()
    {
        Station s1 = new Station(231,"5th ave", "CitiBike", 2367.987, 394.98);
        Station s2 = new Station(3241,"34 square", "Bike Shah", 2387.987, 384.98);
        Trip testMale = new Trip(231,3241,4345,"2015-10-01 00:22:42","2015-10-01 00:38:42", "Subscriber", 1990, 1, "test", 1);
        Trip testFemale = new Trip(231,3241,4345,"2015-10-01 00:22:42","2015-10-01 00:38:42", "Subscriber", 15, 2, "test", 2);
        Trip testOther = new Trip(231,3241,4345,"2015-10-01 00:22:42","2015-10-01 00:38:42", "Subscriber", 15, 0, "test", 3);

        assertEquals("Male", testMale.getGender());
        assertEquals("Female", testFemale.getGender());
        assertEquals("Unknown", testOther.getGender());
    }

    /*@Test
    public void test_distance()
    {
        Station s1 = new Station(231,"5th ave", "CitiBike", 36.0, 74.0);
        Station s2 = new Station(231,"5th ave", "CitiBike", 39.0, 79.0);
        //DatabaseUpdater databaseUpdater = new DatabaseUpdater();
        //databaseUpdater.insertStation(s1);
        //databaseUpdater.insertStation(s2);
        Trip testDistance = new Trip(231,3241,4345,"2015-10-01 00:22:42","2015-10-01 00:38:42", "Subscriber", 1990, 1, "test", 1);
        Trip testDistanceLarge = new Trip(231,3241,4345,"2015-10-01 00:22:42","2015-10-01 00:38:42", "Subscriber", 1990, 1, "test", 2);

        assertEquals(0.0, testDistance.getDistance());
        assertTrue(testDistanceLarge.getDistance()<560 && testDistanceLarge.getDistance() > 550);
    }*/


}
