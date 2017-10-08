package seng202.team7;

import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DatabaseTest {
    String testUrl = "jdbc:sqlite:./src/test/java/seng202/team7/testDatabase.db";

    @Test
    public void createDatabaseTest()
    {
        DatabaseHandler.createDatabase(testUrl);
        Connection conn = DatabaseHandler.connect(testUrl);
        assertFalse(conn == null);
    }

    @Test
    public void createTableTest()
    {
        DatabaseHandler.deleteTable(Wifi.tableName, testUrl);
        DatabaseHandler.createTable(Wifi.tableName, Wifi.tableCreation, testUrl);
        DatabaseUpdater dbu = new DatabaseUpdater();
        dbu.insertWifi(new Wifi("QU", "Free","Alcatel","Location String", "City String", "SSID String", "Remarks String","DatagroupString", 40.676062,-73.952250),testUrl);
        DatabaseRetriever dbr = new DatabaseRetriever();
        assertTrue(dbr.getWifiList(testUrl).size() == 1);
    }


}
