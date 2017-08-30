package seng202.team7;

import com.sun.org.apache.bcel.internal.generic.RET;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseRetriever {

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
                    System.out.println("no station");
                }
            }
        } catch (SQLException e){
            System.out.println("Get Station error");
            System.out.println(e.getMessage());
        }
        return stationList;
    }

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
                    System.out.println("no wifi");
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return wifiList;
    }

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
                    System.out.println("no retailer");
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return retailerList;
    }
}
