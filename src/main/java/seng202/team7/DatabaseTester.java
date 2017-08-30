package seng202.team7;

import java.util.ArrayList;

public class DatabaseTester {
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


    }
    private static void deleteTables()
    {
        DatabaseHandler.deleteTable(Wifi.tableName);
        DatabaseHandler.deleteTable(Station.tableName);
        DatabaseHandler.deleteTable(Retailer.tableName);
    }
    private static void createTables()
    {
        DatabaseHandler.createTable(Wifi.tableName, Wifi.tableCreation);
        DatabaseHandler.createTable(Station.tableName, Station.tableCreation);
        DatabaseHandler.createTable(Retailer.tableName, Retailer.tableCreation);
    }

    private static void addData(DatabaseUpdater databaseUpdater)
    {
        ArrayList<Data> data = new ArrayList<Data>();

        Wifi w1 = new Wifi("BO", "Limited free","Alcatel","5th Ave","NY","Alcatel Hotspot","","",234.324,324.554);
        Wifi w2 = new Wifi("BO", "Free","Alcatel","5th Ave","NY","Alcatel Hotspot","","",234.324,324.554);
        Wifi w3 = new Wifi("BO", "Subscription","Alcatel","5th Ave","NY","Alcatel Hotspot","","",234.324,324.554);

        Station s1 = new Station(231,"5th ave", "CitiBike", 2387.987, 384.98);
        Station s2 = new Station(3241,"34 square", "Bike Shah", 2387.987, 384.98);

        Retailer r1 = new Retailer("McD's Lower MN", "New York", "5th ave", "23", "NY", 2344, "F", "Phast Phood", "McD's Chain" );
        Retailer r2 = new Retailer("McD's Upper BO", "New York", "5th ave", "23", "NY", 2344, "F", "Phast Phood", "McD's Chain" );


        data.add(w1);
        data.add(w2);
        data.add(w3);

        data.add(s1);
        data.add(s2);

        data.add(r1);
        data.add(r2);

        databaseUpdater.addData(data);

    }

    private static void printWifi(DatabaseRetriever databaseRetriever)
    {
        for(Wifi w :databaseRetriever.getWifiList()){
            w.print();
        }
    }

    private static void printRetailer(DatabaseRetriever databaseRetriever)
    {
        for(Retailer r : databaseRetriever.getRetailerList()){
            r.print();
        }
    }


}
