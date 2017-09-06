package seng202.team7;

import java.util.ArrayList;

public class DatabaseTester {
    /**
     * Static method to test the database
     */
    public static void init()
    {
        //Create database
        //DatabaseHandler.createDatabase();

        deleteTables();
        createTables();
        DatabaseUpdater databaseUpdater = new DatabaseUpdater();
        addData(databaseUpdater);



        DatabaseRetriever databaseRetriever = new DatabaseRetriever();
        printWifi(databaseRetriever);
        printRetailer(databaseRetriever);
        printTrip(databaseRetriever);


    }

    /**
     * Deletes all the tables
     */
    public static void deleteTables()
    {
        DatabaseHandler.deleteTable(Wifi.tableName);
        DatabaseHandler.deleteTable(Station.tableName);
        DatabaseHandler.deleteTable(Retailer.tableName);
        DatabaseHandler.deleteTable(Trip.tableName);
    }

    /**
     * Creates all the tables
     */
    public static void createTables()
    {
        DatabaseHandler.createTable(Wifi.tableName, Wifi.tableCreation);
        DatabaseHandler.createTable(Station.tableName, Station.tableCreation);
        DatabaseHandler.createTable(Retailer.tableName, Retailer.tableCreation);
        DatabaseHandler.createTable(Trip.tableName, Trip.tableCreation);
    }

    /**
     * Adds an arraylist of datatypes
     * @param databaseUpdater databaseupdater object with connection to the current database
     */
    public static void addData(DatabaseUpdater databaseUpdater)
    {
        ArrayList<Data> data = new ArrayList<Data>();

        Wifi w1 = new Wifi("BX", "Limited free","Alcatel","5th Ave","NY","Alcatel Hotspot","","",234.324,324.554);
        Wifi w2 = new Wifi("SI", "Free","Alcatel","5th Ave","NY","Alcatel Hotspot","","",234.324,324.554);
        Wifi w3 = new Wifi("MN", "Subscription","Alcatel","5th Ave","NY","Alcatel Hotspot","","",234.324,324.554);

        Station s1 = new Station(231,"5th ave", "CitiBike", 2387.987, 384.98);
        Station s2 = new Station(3241,"34 square", "Bike Shah", 2387.987, 384.98);

        Retailer r1 = new Retailer("McD's Lower MN", "New York", "5th ave", "23", "NY", 2344, "F", "Phast Phood", "test" );
        Retailer r2 = new Retailer("McD's Upper BO", "New York", "5th ave", "23", "NY", 2344, "F", "Phast Phood", "test" );

        Trip t1 = new Trip(s1,s2,4345,"2015-10-01 00:22:42","2015-10-01 00:38:42", "casual", 1990, "M", "test");
        Trip t2 = new Trip(s2,s1,4345,"2015-10-01 00:20:42","2015-10-01 00:29:42", "casual", 1934, "F", "test");


        data.add(w1);
        data.add(w2);
        data.add(w3);

        data.add(s1);
        data.add(s2);

        data.add(r1);
        data.add(r2);

        data.add(t1);
        data.add(t2);

        databaseUpdater.addData(data);

    }

    /**
     * prints wifis
     * @param databaseRetriever
     */
    private static void printWifi(DatabaseRetriever databaseRetriever)
    {
        for(Wifi w :databaseRetriever.getWifiList()){
            w.print();
        }
    }

    /**
     * prints retailer
     * @param databaseRetriever
     */
    private static void printRetailer(DatabaseRetriever databaseRetriever)
    {
        for(Retailer r : databaseRetriever.getRetailerList()){
            r.print();
        }
    }

    private static void printTrip(DatabaseRetriever databaseRetriever)
    {
        for(Trip t : databaseRetriever.getTripList()){
            t.print();
        }
    }


}
