package seng202.team7;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Retrieves data from the Database
 * @author Morgan English
 */
public class DatabaseRetriever {

    /**
     * gets all stations in database
     * @return Arraylist of station objects
     */
    public ArrayList<Station> getStationList(){
        ArrayList<Station> stationList = new ArrayList<Station>();
        String sql = "SELECT obj FROM " + Station.tableName;
        try (Connection conn = DatabaseHandler.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                Station station = null;
                try {
                    ByteArrayInputStream bais = new ByteArrayInputStream(rs.getBytes("obj"));
                    ObjectInputStream ois = new ObjectInputStream(bais);
                    station = (Station) ois.readObject();
                }catch (IOException io){
                    io.printStackTrace();
                }catch (ClassNotFoundException cnf){
                    cnf.printStackTrace();
                }
                if(station != null)
                {
                    //station.print();
                    stationList.add(station);
                } else {
                    System.out.println("No station");
                }
            }
        } catch (SQLException e){
            System.out.println("Get Station error");
            System.out.println(e.getMessage());
        }
        return stationList;
    }

    /**
     * Gets all wifis in database
     * @return Arraylist of wifi objects
     */
    public ArrayList<Wifi> getWifiList(){
        ArrayList<Wifi> wifiList = new ArrayList<Wifi>();
        String sql = "SELECT obj FROM " + Wifi.tableName;
        try (Connection conn = DatabaseHandler.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                Wifi wifi = null;
                try {
                    ByteArrayInputStream bais = new ByteArrayInputStream(rs.getBytes("obj"));
                    ObjectInputStream ois = new ObjectInputStream(bais);
                    wifi = (Wifi) ois.readObject();
                }catch (IOException io){
                    io.printStackTrace();
                }catch (ClassNotFoundException cnf){
                    cnf.printStackTrace();
                }
                if(wifi != null)
                {
                    wifiList.add(wifi);
                    //wifi.print();
                } else {
                    System.out.println("No wifi");
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return wifiList;
    }

    /**
     * Gets the ArrayList of all retailers in database
     * @return Arraylist of all retailers
     */
    public ArrayList<Retailer> getRetailerList()
    {
        ArrayList<Retailer> retailerList = new ArrayList<Retailer>();
        String sql = "SELECT obj FROM " + Retailer.tableName;
        try (Connection conn = DatabaseHandler.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                Retailer retailer = null;
                try {
                    ByteArrayInputStream bais = new ByteArrayInputStream(rs.getBytes("obj"));
                    ObjectInputStream ois = new ObjectInputStream(bais);
                    retailer = (Retailer) ois.readObject();
                }catch (IOException io){
                    io.printStackTrace();
                }catch (ClassNotFoundException cnf){
                    cnf.printStackTrace();
                }
                if(retailer != null)
                {
                    retailerList.add(retailer);
                    //wifi.print();
                } else {
                    System.out.println("No retailer");
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return retailerList;
    }

    /**
     * Gets an ArrayList of all trips
     * @return ArrayList of all trip objects
     */
    public ArrayList<Trip> getTripList()
    {
        ArrayList<Trip> tripList = new ArrayList<Trip>();
        String sql = "SELECT obj FROM " + Trip.tableName;
        try (Connection conn = DatabaseHandler.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){


            // loop through the result set
            while (rs.next()) {
                Trip trip = null;
                try {
                    ByteArrayInputStream bais = new ByteArrayInputStream(rs.getBytes("obj"));
                    ObjectInputStream ois = new ObjectInputStream(bais);
                    trip = (Trip) ois.readObject();
                }catch (IOException io){
                    io.printStackTrace();
                }catch (ClassNotFoundException cnf){
                    cnf.printStackTrace();
                }
                if(trip != null)
                {
                    tripList.add(trip);
                    //wifi.print();
                } else {
                    System.out.println("No trip");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tripList;
    }


    /**
     * Completes an sql query on Retailers from the given query string
     * Only returns retailer object
     * @param query sql query to run
     * @return Arraylist of retailers
     */
    public ArrayList<Retailer> queryRetailer(String query)
    {
        ArrayList<Retailer> retailerList = new ArrayList<Retailer>();
        try (Connection conn = DatabaseHandler.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(query)){

            while (rs.next()) {
                Retailer retailer = null;
                try {
                    ByteArrayInputStream bais = new ByteArrayInputStream(rs.getBytes("obj"));
                    ObjectInputStream ois = new ObjectInputStream(bais);
                    retailer = (Retailer) ois.readObject();
                } catch (IOException io) {
                    io.printStackTrace();
                } catch (ClassNotFoundException cnf) {
                    cnf.printStackTrace();
                }
                if (retailer != null) {
                    retailerList.add(retailer);

                } else {
                    System.out.println("No retailer");
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return retailerList;

    }


    /**
     * Completes an sql query on the wifi table
     * only returns Wifi objects
     * @param query full sql query to be run
     * @return arraylist of Wifi objects that match the query
     */
    public ArrayList<Wifi> queryWifi(String query){
        ArrayList<Wifi> wifiList = new ArrayList<Wifi>();

        try (Connection conn = DatabaseHandler.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(query)){

            // loop through the result set
            while (rs.next()) {
                Wifi wifi = null;
                try {
                    ByteArrayInputStream bais = new ByteArrayInputStream(rs.getBytes("obj"));
                    ObjectInputStream ois = new ObjectInputStream(bais);
                    wifi = (Wifi) ois.readObject();

                }catch (IOException io){
                    io.printStackTrace();
                }catch (ClassNotFoundException cnf){
                    cnf.printStackTrace();
                }
                if(wifi != null)
                {
                    wifiList.add(wifi);
                    //wifi.print();
                } else {
                    System.out.println("No wifi");
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return wifiList;
    }


    /**
     * Completes a query on the station database given the sql query
     * Only returns Station object
     * @param query complete sql query to use
     * @return List of Stations from query
     */
    public ArrayList<Station> queryStation(String query){
        ArrayList<Station> stationList = new ArrayList<Station>();

        try (Connection conn = DatabaseHandler.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(query)) {

            // loop through the result set
            while (rs.next()) {
                Station station = null;
                try {
                    ByteArrayInputStream bais = new ByteArrayInputStream(rs.getBytes("obj"));
                    ObjectInputStream ois = new ObjectInputStream(bais);
                    station = (Station) ois.readObject();
                }catch (IOException io){
                    io.printStackTrace();
                }catch (ClassNotFoundException cnf){
                    cnf.printStackTrace();
                }
                if(station != null)
                {
                    //station.print();
                    stationList.add(station);
                } else {
                    System.out.println("No station");
                }
            }
        } catch (SQLException e){
            System.out.println("Get Station error");
            System.out.println(e.getMessage());
        }
        return stationList;
    }


    /**
     * Completes sql query on trip table must have obj in query
     * only returns trip objects
     * @param query full sql query to be run
     * @return ArrayList of trips matching query
     */
    public ArrayList<Trip> queryTrip(String query)
    {
        ArrayList<Trip> tripList = new ArrayList<Trip>();

        try (Connection conn = DatabaseHandler.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(query)){

            // loop through the result set
            while (rs.next()) {
                Trip trip = null;
                try {
                    ByteArrayInputStream bais = new ByteArrayInputStream(rs.getBytes("obj"));
                    ObjectInputStream ois = new ObjectInputStream(bais);
                    trip = (Trip) ois.readObject();
                }catch (IOException io){
                    io.printStackTrace();
                }catch (ClassNotFoundException cnf){
                    cnf.printStackTrace();
                }
                if(trip != null)
                {
                    tripList.add(trip);
                    //wifi.print();
                } else {
                    System.out.println("No trip");
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tripList;
    }


    /**
     * Testing the queryability of time objects within SQL. Currently not working as expected
     */
    public void testQueryTrip()
    {
        ArrayList<Trip> tripList = new ArrayList<Trip>();

        try (Connection conn = DatabaseHandler.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery("SELECT distance, startTime, STRFTIME('%H',datetime(startTime/1000,'unixepoch')) AS startHour, endTime FROM trip;")){
             //ResultSet rs    = stmt.executeQuery("SELECT distance, startTime, STRFTIME(\"%H\",startTime) AS \"startHour\", endTime FROM trip")){

            // loop through the result set
            while (rs.next()) {


                System.out.println("StartTime: " + rs.getString("startTime") + "StartHour: " + rs.getString("startHour"));
                System.out.println("EndTime: " + rs.getTime("endTime"));
                System.out.println("Distance: " + rs.getDouble("distance"));



            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Returns a list of strings from a specific column where another column matches the integer passed in
     * @param tableName table to search
     * @param value value to match
     * @param columnSearch column to search
     * @param columnReturn column to return
     * @return String Array
     */
    public ArrayList<String> getStringListFromInt(String tableName, int value, String columnSearch, String columnReturn){
        ArrayList<String> stringList = new ArrayList<String>();
        String sql = "SELECT "+columnReturn+" FROM " + tableName +" WHERE " + columnSearch + " = " + value;
        try (Connection conn = DatabaseHandler.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {


                String returnStr =  rs.getString(columnReturn);

                if(returnStr != null && !returnStr.equals(""))
                {
                    //station.print();
                    stringList.add(returnStr);
                } else {
                    System.out.println("No String");
                }
            }
        } catch (SQLException e){
            System.out.println("Got String Error");
            System.out.println(e.getMessage());
        }
        return stringList;
    }

    /**
     * Returns a list of strings from a specific column where another column matches the string passed in with LIKE '%___%' SQL syntax
     * @param tableName table to search
     * @param value value to match
     * @param columnSearch column to search
     * @param columnReturn column to return
     * @return String Array
     */
    public ArrayList<String> getStringListFromLikeString(String tableName, String value, String columnSearch, String columnReturn){
        ArrayList<String> stringList = new ArrayList<String>();
        String sql = "SELECT "+columnReturn+" FROM " + tableName +" WHERE " + columnSearch + " LIKE \"%" + value +"%\"";
        try (Connection conn = DatabaseHandler.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {


                String returnStr =  rs.getString(columnReturn);

                if(returnStr != null && !returnStr.equals(""))
                {
                    //station.print();
                    stringList.add(returnStr);
                } else {
                    System.out.println("No String");
                }
            }
        } catch (SQLException e){
            System.out.println("Got String Error");
            System.out.println(e.getMessage());
        }
        return stringList;
    }

    /**
     * Returns a list of ints from a specific column where another column matches the string passed in with LIKE '%___%' SQL syntax
     * @param tableName table to search
     * @param value string to LIKE match
     * @param columnSearch column to search
     * @param columnReturn column to return
     * @return String Array
     */
    public ArrayList<Integer> getIntListFromLikeString(String tableName, String value, String columnSearch, String columnReturn){
        ArrayList<Integer> stringList = new ArrayList<Integer>();
        String sql = "SELECT "+columnReturn+" FROM " + tableName +" WHERE " + columnSearch + " LIKE \"%" + value +"%\"";
        try (Connection conn = DatabaseHandler.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {


                int returnInt =  rs.getInt(columnReturn);
                stringList.add(returnInt);

            }
        } catch (SQLException e){
            System.out.println("Got Int Error");
            System.out.println(e.getMessage());
        }
        return stringList;
    }
}
