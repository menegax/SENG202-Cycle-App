package seng202.team7;

import seng202.team7.Database.DatabaseHandler;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author Morgan English
 * Deals with the datagroup table in the database
 */
public class Datagroup {

    /**
     * Table name for datagroup table
     */
    public static String tableName = "datagroup";

    /**
     * Columns in the datagroup table
     */
    public static String[] columns = {"id", "name"};

    /**
     * Table creation string for the datagroup table
     */
    public static String tableCreation = "CREATE TABLE IF NOT EXISTS " +
            tableName +" (\n" +
            columns[0] + " integer PRIMARY KEY NOT NULL, \n" +
            columns[1] + " text UNIQUE" +
            ");";

    /**
     * Gets all the datagroups in the databases as Strings
     * @return ArrayList of strings of datagroups used
     */
    public static ArrayList<String> getDatagroups() {
        ArrayList<String> groups = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName;
        try (Connection conn = DatabaseHandler.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {
            while(rs.next()){
                groups.add(rs.getString(columns[1]));
            }
        } catch (SQLException e){
            System.out.println("Get Station error");
            System.out.println(e.getMessage());
        }
        return groups;
    }

    /**
     * Adds a datagroup to the datagroup table
     * @param datagroup datagroup to add
     */
    public static void addDatagroup(String datagroup) {
        String sql = "INSERT INTO "+tableName +" ("+ columns[1]+") VALUES (?)";
        try (Connection conn = DatabaseHandler.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, datagroup );
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
