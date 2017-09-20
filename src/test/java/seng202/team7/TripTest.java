package seng202.team7;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Date;

public class TripTest extends TestCase {


    @Test
    public void test_age() {

        Station s1 = new Station(231,"5th ave", "CitiBike", 2367.987, 394.98);
        Station s2 = new Station(3241,"34 square", "Bike Shah", 2387.987, 384.98);
        Trip test = new Trip(s1,s2,4345,"2015-10-01 00:22:42","2015-10-01 00:38:42", "Subscriber", 1990, 2, "test");

        assertEquals(27, test.getAge());
    }

    @Test
    public void test_date() {

        Station s1 = new Station(231,"5th ave", "CitiBike", 2367.987, 394.98);
        Station s2 = new Station(3241,"34 square", "Bike Shah", 2387.987, 384.98);
        Trip test = new Trip(s1,s2,4345,"2015-10-01 00:22:42","2015-10-01 00:38:42", "Subscriber", 1990, 2, "test");

        assertTrue(test.getStartDate() instanceof Date);

    }


}
