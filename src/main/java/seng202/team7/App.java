package seng202.team7;

import java.io.File;


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

        DatabaseRetriever databaseRetriever = new DatabaseRetriever();

//        DatabaseHandler.getOnlineDatabase();
//        Retailer retailer = new Retailer("bob's burgers", "New York", "3 New York Plaza", "" ,"NY", 10004, "F-Coffeehouse", "Casual Eating & Takeout", "retailer");
//        String new_address = retailer.getPAddress() +  ", " + retailer.getCity() + ", " + retailer.getState();
//        new_address = new_address.replaceAll(" ", "+");
//        System.out.println(new_address);
//        System.out.println(retailer.getLatitude());
//        System.out.println(retailer.getLongitude());
//
//        Retailer retailers = new Retailer("bob's burgers", "New York", "57 Murray Street", "" ,"NY", 10007, "Shopping", "Casual Eating & Takeout", "retailer");
//        System.out.println(retailers.getType());
//        System.out.println(retailers.getTypeID());


//
//        String[] ageList = {"0-15","15-25","25-35","35-45","45-55","55-55+"};
//        for(String age: ageList) {
//            String low = age.split("-")[0];
//            String high = age.split("-")[1];
//            high = high.replace("+","");
//            int lowAge = Integer.parseInt(low);
//            int highAge = Integer.parseInt(high);
//
//
//            System.out.println(""+ lowAge + highAge);
//        }

//        ArrayList<Trip> tempT= new ArrayList<Trip>();
//        for(Trip t: databaseRetriever.getTripList())
//        {
//            System.out.println(t.getDataGroup());
//        }
//        ArrayList<PointM> points = new ArrayList<PointM>();
//        for(Trip t: tempT)
//        {
//            points.addAll(Analytics.checkRoute(t.getStartPoint(), t.getEndPoint()));
//        }
//        for(PointM p: points)
//        {
//            p.print();
//        }



//        DatabaseHandler.createDatabase();
//
//        DatabaseHandler.createDatabase();
//        DatabaseTester.createTables();
//        DatabaseHandler.deleteTable(Datagroup.tableName);
//        DatabaseHandler.createTable(Datagroup.tableName, Datagroup.tableCreation);

//        DatabaseRetriever databaseRetriever = new DatabaseRetriever();
//
//
//        for (Station s: databaseRetriever.getStationList())
//        {
//            s.print();
//        }
//
//        DatabaseHandler.deleteTable(Retailer.tableName);
//        DatabaseHandler.createTable(Retailer.tableName, Retailer.tableCreation);
//
//        DatabaseHandler.deleteTable(Wifi.tableName);
//        DatabaseHandler.createTable(Wifi.tableName, Wifi.tableCreation);
//        Datagroup.addDatagroup("test1");
//        Datagroup.addDatagroup("test2");
//        Datagroup.addDatagroup("test3");
//        Datagroup.addDatagroup("test3");
//
//        for (String s : Datagroup.getDatagroups()){
//            System.out.println(s);
//        }
//        //DatabaseRetriever databaseRetriever = new DatabaseRetriever();
//
//
//        for (Station s: databaseRetriever.getStationList())
//        {
//            s.print();
//        }

         //System.out.println(databaseRetriever.getTripList().size());

//        DatabaseHandler.deleteTable(Retailer.tableName);
//        DatabaseHandler.createTable(Retailer.tableName, Retailer.tableCreation);
//
//        DatabaseHandler.deleteTable(Wifi.tableName);
//        DatabaseHandler.createTable(Wifi.tableName, Wifi.tableCreation);

//        DatabaseHandler.initializeDatabase();
//        DatabaseHandler.deleteTable(Trip.tableName);
//        DatabaseHandler.createTable(Trip.tableName, Trip.tableCreation);
//
//        for(Retailer r : databaseRetriever.queryRetailer(StaticVariables.singleStringQuery(Retailer.tableName, Retailer.columns[11], "test")))
//        {
//            r.print();
//        }
//
//        for(Wifi w : databaseRetriever.queryWifi(StaticVariables.singleStringQuery(Wifi.tableName, Wifi.columns[10], "test")))
//        {
//             System.out.println(w.getDataGroup());
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
