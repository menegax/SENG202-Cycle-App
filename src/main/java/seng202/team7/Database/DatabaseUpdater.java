package seng202.team7.Database;

import seng202.team7.*;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author Morgan English
 * 27/8/17
 * Class to update database
 */
public class DatabaseUpdater {
    //public static final String databaseURL = "jdbc:sqlite:Database.db";

    /**
     * public class that allows for the addition of an arraylist of Data
     * @param data data in an arraylist
     */
    public void addData(ArrayList<Data> data)
    {
        DatabaseUpdater insertData = new DatabaseUpdater();

        for(Data d:data)
        {
            if (d.getClass() == Wifi.class){
                insertWifi((Wifi)d);
            } else if (d.getClass() == Station.class){
                insertStation((Station) d);
            } else if (d.getClass() == Retailer.class){
                insertRetailer((Retailer) d);
            } else if (d.getClass() == Trip.class) {
                insertTrip((Trip) d);
            }
        }
    }

    /**
     * Method adds a single Wifi object to the wifi table
     * @param wifi Wifi to be added to the database
     */
    public void insertWifi(Wifi wifi, String url)
    {
        String sql = "INSERT INTO "+Wifi.tableName+" ("+Wifi.columns[0]+", "+Wifi.columns[1]+", "+Wifi.columns[2]+", "+Wifi.columns[3]+", "+Wifi.columns[4]+", "+Wifi.columns[5]+", "+Wifi.columns[6]+", "+Wifi.columns[7]+", "+Wifi.columns[8]+", "+Wifi.columns[9]+", "+Wifi.columns[10]+", "+Wifi.columns[11]+") VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            //ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(wifi);
            oos.flush();
            oos.close();
            bos.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        try (Connection conn = DatabaseHandler.connect(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,wifi.hashCode());
            pstmt.setString(2, wifi.getBorough());
            pstmt.setString(3, wifi.getType());
            pstmt.setString(4, wifi.getProvider());
            pstmt.setString(5, wifi.getLocation());
            pstmt.setString(6, wifi.getCity());
            pstmt.setString(7, wifi.getSSID());
            pstmt.setString(8, wifi.getRemarks());
            pstmt.setDouble(9, wifi.getLatitude());
            pstmt.setDouble(10, wifi.getLongitude());
            pstmt.setString(11, wifi.getDataGroup());
            pstmt.setObject(12, bos.toByteArray());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Wifi added");
    }

    /**
     * Method adds a single Wifi object to the wifi table
     * @param wifi Wifi to be added to the database
     */
    public void insertWifi(Wifi wifi)
    {
        String sql = "INSERT INTO "+Wifi.tableName+" ("+Wifi.columns[0]+", "+Wifi.columns[1]+", "+Wifi.columns[2]+", "+Wifi.columns[3]+", "+Wifi.columns[4]+", "+Wifi.columns[5]+", "+Wifi.columns[6]+", "+Wifi.columns[7]+", "+Wifi.columns[8]+", "+Wifi.columns[9]+", "+Wifi.columns[10]+", "+Wifi.columns[11]+") VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            //ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(wifi);
            oos.flush();
            oos.close();
            bos.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        try (Connection conn = DatabaseHandler.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,wifi.hashCode());
            pstmt.setString(2, wifi.getBorough());
            pstmt.setString(3, wifi.getType());
            pstmt.setString(4, wifi.getProvider());
            pstmt.setString(5, wifi.getLocation());
            pstmt.setString(6, wifi.getCity());
            pstmt.setString(7, wifi.getSSID());
            pstmt.setString(8, wifi.getRemarks());
            pstmt.setDouble(9, wifi.getLatitude());
            pstmt.setDouble(10, wifi.getLongitude());
            pstmt.setString(11, wifi.getDataGroup());
            pstmt.setObject(12, bos.toByteArray());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Wifi added");
    }

    /**
     * Adds a single station to the station to the database
     * @param station station object to be added
     */
    public void insertStation(Station station)
    {
        String sql = "INSERT INTO "+ Station.tableName+" (stationID, address,latitude, longitude, datagroup, obj) VALUES(?,?,?,?,?,?)";
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            //ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(station);
            oos.flush();
            oos.close();
            bos.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        try (Connection conn = DatabaseHandler.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,station.getId());
            pstmt.setString(2, station.getAddress());
            pstmt.setDouble(3, station.getLatitude());
            pstmt.setDouble(4, station.getLongitude());
            pstmt.setString(5, station.getDataGroup());
            pstmt.setObject(6, bos.toByteArray());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Station added");
    }

    /**
     * Adds a retailer to the database
     * @param retailer retailer object to be added
     */
    public void insertRetailer(Retailer retailer)
    {
        String sql = "INSERT INTO "+ retailer.tableName+" (id, name, city, pAddress, sAddress, state, zipCode, typeID, type,latitude, longitude, datagroup, obj, street) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            //ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(retailer);
            oos.flush();
            oos.close();
            bos.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        try (Connection conn = DatabaseHandler.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,retailer.hashCode());
            pstmt.setString(2,retailer.getName());
            pstmt.setString(3,retailer.getCity());
            pstmt.setString(4,retailer.getPAddress());
            pstmt.setString(5,retailer.getSAddress());
            pstmt.setString(6,retailer.getState());
            pstmt.setInt(7,retailer.getZipCode());
            pstmt.setString(8, retailer.getTypeID());
            pstmt.setString(9, retailer.getType());
            pstmt.setDouble(10, retailer.getLatitude());
            pstmt.setDouble(11, retailer.getLongitude());
            pstmt.setString(12, retailer.getDataGroup());
            pstmt.setObject(13, bos.toByteArray());
            pstmt.setString(14,retailer.getStreet());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Retailer added");
    }

    /**
     * Inserts a trip into the database
     * @param trip trip to be stored in the database
     */
    public void insertTrip(Trip trip)
    {
        String sql = "INSERT INTO "+ trip.tableName+" (id, duration, startStationID, endStationID, bikeID, gender, age, userType, startDate, startTime, endDate, endTime, distance, datagroup, obj) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        //ByteArrayOutputStream initialization
        ByteArrayOutputStream bosTrip, bosStartStation, bosEndStation, bosStartDate, bosEndDate;
        bosTrip = new ByteArrayOutputStream();
        bosStartStation = new ByteArrayOutputStream();
        bosEndStation = new ByteArrayOutputStream();
        //bosStartDate = new ByteArrayOutputStream();
        //bosEndDate = new ByteArrayOutputStream();

        try {
            //ObjectOutputStream initialization
            ObjectOutputStream oosTrip, oosStartStation, oosEndStation, oosStartDate, oosEndDate;
            oosTrip = new ObjectOutputStream(bosTrip);

            //Writing Objects
            oosTrip.writeObject(trip);
            DatabaseRetriever databaseRetriever = new DatabaseRetriever();

            //Tiding up -- Flushing oos's
            oosTrip.flush();

            //Tiding up -- Closing oos's
            oosTrip.close();

            //Tiding up -- Closing bos's
            bosTrip.close();

        } catch(Exception e){
            e.printStackTrace();
        }

        try (Connection conn = DatabaseHandler.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,trip.hashCode());
            pstmt.setInt(2,trip.getDuration());
            pstmt.setInt(3,trip.getStartStationID());
            pstmt.setInt(4, trip.getEndStationID());
            pstmt.setInt(5,trip.getBikeID());
            pstmt.setString(6,trip.getGender());
            pstmt.setInt(7,trip.getAge());
            pstmt.setString(8,trip.getUserType());
            pstmt.setDate(9,new java.sql.Date(trip.getStartDate().getDate()));
            pstmt.setTime(10,new java.sql.Time(trip.getStartDate().getTime()));
            pstmt.setDate(11,new java.sql.Date(trip.getEndDate().getDate()));
            pstmt.setTime(12, new java.sql.Time(trip.getEndDate().getTime()));
            pstmt.setDouble(13, trip.getDistance());
            pstmt.setString(14, trip.getDataGroup());
            pstmt.setObject(15, bosTrip.toByteArray());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Trip added");
    }




    /**
     * Deletes Wifi object based off its hashcode as id
     * @param id Wifi id to be deleted from database
     */
    public void deleteWifi(int id)
    {
        String sql = "DELETE FROM "+ Wifi.tableName + " WHERE id = ?";
        try(Connection conn = DatabaseHandler.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Updates a wifi object by deleting it then reading it
     * @param wifi wifi object to 'update'
     */
    public void updateWifi(Wifi wifi, int oldId)
    {
        deleteWifi(oldId);
        insertWifi(wifi);
    }

    /**
     * Deletes Retailer object based off its hashcode as id
     * @param oldId Retailer id to be deleted from database
     */
    public void deleteRetailer(int oldId)
    {
        String sql = "DELETE FROM "+ Retailer.tableName + " WHERE id = ?";
        try(Connection conn = DatabaseHandler.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, oldId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Updates a Retailer object by deleting it then readding it
     * @param retailer Retailer object to 'update'
     */
    public void updateRetailer(Retailer retailer, int oldId)
    {
        deleteRetailer(oldId);
        insertRetailer(retailer);
    }

    /**
     * Deletes Station object based off its id
     * @param oldId Station id to be deleted from database
     */
    public void deleteStation(int oldId)
    {
        String sql = "DELETE FROM "+ Station.tableName + " WHERE id = ?";
        try(Connection conn = DatabaseHandler.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, oldId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Updates a Station object by deleting it then readding it
     * @param station Station object to 'update'
     */
    public void updateStation(Station station, int oldId)
    {
        deleteStation(oldId);
        insertStation(station);
    }

    /**
     * Deletes Trip object based off its hashcode as id
     * @param oldId Trip id to be deleted from database
     */
    public void deleteTrip(int oldId)
    {
        String sql = "DELETE FROM "+ Trip.tableName + " WHERE id = ?";
        try(Connection conn = DatabaseHandler.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, oldId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Updates a Trip object by deleting it then readding it
     * @param trip Trip object to 'update'
     */
    public void updateTrip(Trip trip, int oldId)
    {
        deleteTrip(oldId);
        insertTrip(trip);
    }


}
