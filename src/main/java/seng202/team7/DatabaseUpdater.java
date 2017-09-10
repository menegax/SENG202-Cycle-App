package seng202.team7;

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
    public void insertWifi(Wifi wifi)
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        String sql = "INSERT INTO "+Wifi.tableName+" (burough, type, provider, location, city, SSID,remarks,latitude, longitude, datagroup, obj) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setString(1, wifi.getBorough());
            pstmt.setString(2, wifi.getType());
            pstmt.setString(3, wifi.getProvider());
            pstmt.setString(4, wifi.getLocation());
            pstmt.setString(5, wifi.getCity());
            pstmt.setString(6, wifi.getSSID());
            pstmt.setString(7, wifi.getRemarks());
            pstmt.setDouble(8, wifi.getLatitude());
            pstmt.setDouble(9, wifi.getLatitude());
            pstmt.setString(10, wifi.getDataGroup());
            pstmt.setObject(11, bos.toByteArray());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("wifi added");
    }


    /**
     * Adds a single station to the station to the database
     * @param station station object to be added
     */
    public void insertStation(Station station)
    {
        String sql = "INSERT INTO "+ Station.tableName+" (bikeid, address,latitude, longitude, datagroup, obj) VALUES(?,?,?,?,?,?)";
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
            pstmt.setDouble(4, station.getLatitude());
            pstmt.setString(5, station.getDataGroup());
            pstmt.setObject(6, bos.toByteArray());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("station added");
    }

    /**
     * adds a retailer to the database
     * @param retailer retailer object to be added
     */
    public void insertRetailer(Retailer retailer)
    {
        String sql = "INSERT INTO "+ retailer.tableName+" (name, city, pAddress, sAddress, state, zipCode, typeID, type,latitude, longitude, datagroup, obj) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
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
            pstmt.setString(1,retailer.getName());
            pstmt.setString(2,retailer.getCity());
            pstmt.setString(3,retailer.getPAddress());
            pstmt.setString(4,retailer.getSAddress());
            pstmt.setString(5,retailer.getState());
            pstmt.setInt(6,retailer.getZipCode());
            pstmt.setString(7, retailer.getTypeID());
            pstmt.setString(8, retailer.getType());
            pstmt.setDouble(9, retailer.getLatitude());
            pstmt.setDouble(10, retailer.getLatitude());
            pstmt.setString(11, retailer.getDataGroup());
            pstmt.setObject(12, bos.toByteArray());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("retailer added");
    }

    /**
     * Inserts a trip into the database
     * @param trip trip to be stored in the database
     */
    public void insertTrip(Trip trip)
    {
        String sql = "INSERT INTO "+ trip.tableName+" (duration, startStationID, startStation, endStationID, endStation, bikeID, gender, age, userType, startDate, startTime, endDate, endTime, distance, datagroup, obj) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        //ByteArrayOutputStream initialization
        ByteArrayOutputStream bosTrip, bosStartStation, bosEndStation, bosStartDate, bosEndDate;
        bosTrip = new ByteArrayOutputStream();
        bosStartStation = new ByteArrayOutputStream();
        bosEndStation = new ByteArrayOutputStream();
        bosStartDate = new ByteArrayOutputStream();
        bosEndDate = new ByteArrayOutputStream();

        try {
            //ObjectOutputStream initialization
            ObjectOutputStream oosTrip, oosStartStation, oosEndStation, oosStartDate, oosEndDate;
            oosTrip = new ObjectOutputStream(bosTrip);
            oosStartStation = new ObjectOutputStream(bosStartStation);
            oosEndStation = new ObjectOutputStream(bosEndStation);
            oosStartDate = new ObjectOutputStream(bosStartDate);
            oosEndDate = new ObjectOutputStream(bosEndDate);

            //Writing Objects
            oosTrip.writeObject(trip);
            oosStartStation.writeObject(trip.getStartStation());
            oosEndStation.writeObject(trip.getEndStation());
            oosStartDate.writeObject(trip.getStartDate());
            oosEndDate.writeObject(trip.getEndDate());

            //Tiding up -- Flushing oos's
            oosTrip.flush();
            oosStartStation.flush();
            oosEndStation.flush();
            oosStartDate.flush();
            oosEndDate.flush();

            //Tiding up -- Closing oos's
            oosTrip.close();
            oosStartStation.close();
            oosEndStation.close();
            oosStartDate.close();
            oosEndDate.close();


            //Tiding up -- Closing bos's
            bosTrip.close();
            bosStartStation.close();
            bosEndStation.close();
            bosStartDate.close();
            bosEndDate.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        try (Connection conn = DatabaseHandler.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,trip.getDuration());
            pstmt.setInt(2,trip.getStartStationID());
            pstmt.setObject(3, bosStartStation.toByteArray());
            pstmt.setInt(4, trip.getEndStationID());
            pstmt.setObject(5, bosEndStation);
            pstmt.setInt(6,trip.getBikeID());
            pstmt.setString(7,trip.getGender());
            pstmt.setInt(8,trip.getAge());
            pstmt.setString(9,trip.getUserType());
            pstmt.setDate(10,new java.sql.Date(trip.getStartDate().getDate()));
            pstmt.setTime(11,new java.sql.Time(trip.getStartDate().getTime()));
            pstmt.setDate(12,new java.sql.Date(trip.getEndDate().getDate()));
            pstmt.setTime(13, new java.sql.Time(trip.getEndDate().getTime()));
            pstmt.setDouble(14, trip.getDistance());
            pstmt.setString(15, trip.getDataGroup());
            pstmt.setObject(16, bosTrip.toByteArray());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("trip added");
    }

}
