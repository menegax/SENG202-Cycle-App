package seng202.team7;

import com.sun.org.apache.regexp.internal.RE;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String basePath = new File("").getAbsolutePath();

        String url = "jdbc:sqlite:DataStorage.db";

        System.out.println( "Hello World! How is it going?" );
        System.out.println("Just testing a change");
        System.out.println("test commit Morgan");
        System.out.println("Aidan's test");
        System.out.println("Connor's test");
        System.out.println("Joshua's test");

//        DatabaseHandler.createDatabase();
////
//        DatabaseHandler.createDatabase();
//        DatabaseTester.createTables();

        DatabaseRetriever databaseRetriever = new DatabaseRetriever();


        for (Station s: databaseRetriever.getStationList())
        {
            s.print();
        }

//        DatabaseHandler.deleteTable(Retailer.tableName);
//        DatabaseHandler.createTable(Retailer.tableName, Retailer.tableCreation);
//
//        DatabaseHandler.deleteTable(Wifi.tableName);
//        DatabaseHandler.createTable(Wifi.tableName, Wifi.tableCreation);

//        DatabaseHandler.deleteTable(Trip.tableName);
//        DatabaseHandler.createTable(Trip.tableName, Trip.tableCreation);

//        for(Retailer r : databaseRetriever.getOnlineRetailerList())
//        {
//            r.print();
//        }
////        DatabaseUpdater databaseUpdater = new DatabaseUpdater();
//
//        ArrayList<Retailer> retailers = databaseRetriever.queryRetailer(StaticVariables.singleStringQuery(Retailer.tableName,Retailer.columns[13],"New York Plaza"));
//        for(Retailer r: retailers){
//            System.out.println(r.getStreet()+r.getZipCode()+r.getTypeID());
//
//        }
//

//
//        DatabaseHandler.deleteTable(Retailer.tableName);
//        DatabaseHandler.createTable(Retailer.tableName, Retailer.tableCreation);
//
//        for(Retailer r: retailers)
//        {
//            databaseUpdater.insertRetailer(r);
//        }
//        DatabaseTester.deleteTables();
//        DatabaseTester.createTables();
//        DatabaseTester.init();
    }
}
