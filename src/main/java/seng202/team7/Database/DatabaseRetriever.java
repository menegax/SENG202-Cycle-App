package seng202.team7.Database;

import seng202.team7.DataTypes.Retailer;
import seng202.team7.DataTypes.Station;
import seng202.team7.DataTypes.Trip;
import seng202.team7.DataTypes.Wifi;

import java.io.*;
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
     * @return ArrayList of station objects
     */
    public ArrayList<Station> getStationList(){
        ArrayList<Station> stationList = new ArrayList<>();
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
     * @return ArrayList of wifi objects
     */
    public ArrayList<Wifi> getWifiList(){
        ArrayList<Wifi> wifiList = new ArrayList<>();
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
     * Gets all wifis in database
     * @param url Url of the database to be changed
     * @return ArrayList of wifi objects
     */
    public ArrayList<Wifi> getWifiList(String url){
        ArrayList<Wifi> wifiList = new ArrayList<>();
        String sql = "SELECT obj FROM " + Wifi.tableName;
        try (Connection conn = DatabaseHandler.connect(url);
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
     * @return ArrayList of all retailers
     */
    public ArrayList<Retailer> getRetailerList() {
        ArrayList<Retailer> retailerList = new ArrayList<>();
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
     * Used to get all retailers from a specific database
     * @param url database url
     * @return Array list of retailers
     */
    public ArrayList<Retailer> getRetailerList(String url) {
        ArrayList<Retailer> retailerList = new ArrayList<Retailer>();
        String sql = "SELECT obj FROM " + Retailer.tableName;
        try (Connection conn = DatabaseHandler.connect(url);
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
    public ArrayList<Trip> getTripList() {
        ArrayList<Trip> tripList = new ArrayList<>();
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
     * Gets the trip of a specific database
     * @param url url of the database to search
     * @return ArrayList of trips
     */
    public ArrayList<Trip> getTripList(String url) {
        ArrayList<Trip> tripList = new ArrayList<>();
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
     * @return ArrayList of retailers
     */
    public ArrayList<Retailer> queryRetailer(String query) {
        ArrayList<Retailer> retailerList = new ArrayList<>();
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
     * @return ArrayList of Wifi objects that match the query
     */
    public ArrayList<Wifi> queryWifi(String query){
        ArrayList<Wifi> wifiList = new ArrayList<>();

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
        ArrayList<Station> stationList = new ArrayList<>();

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
     * Gets a single station object from a wuery
     * @param query sql query to be ran on the station database
     * @return First station object found that matches the query
     */
    public ByteArrayInputStream getStationObj(String query) {
        ByteArrayInputStream bais = null;
        try (Connection conn = DatabaseHandler.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(query)) {

            // loop through the result set
            while (rs.next()) {
                Station station = null;
                try {
                    bais = new ByteArrayInputStream(rs.getBytes("obj"));

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e){
            System.out.println("Get Station error");
            System.out.println(e.getMessage());
        }
        return bais;
    }

    /**
     * Completes sql query on trip table must have obj in query
     * only returns trip objects
     * @param query full sql query to be run
     * @return ArrayList of trips matching query
     */
    public ArrayList<Trip> queryTrip(String query) {
        ArrayList<Trip> tripList = new ArrayList<>();

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
     * Testing the query-ability of time objects within SQL. Currently not working as expected
     */
    public void testQueryTrip() {
        ArrayList<Trip> tripList = new ArrayList<>();

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
        ArrayList<String> stringList = new ArrayList<>();
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
        ArrayList<String> stringList = new ArrayList<>();
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
        ArrayList<Integer> stringList = new ArrayList<>();
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
