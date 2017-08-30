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
            pstmt.setString(1, wifi.getBurough());
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
    }

    /**
     * adds a inlge retailer to the database
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
    }

}
