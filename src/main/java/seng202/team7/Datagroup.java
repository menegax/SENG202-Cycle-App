package seng202.team7;

import java.sql.*;
import java.util.ArrayList;

public class Datagroup {


    public static String tableName = "datagroup";

    public static String[] columns = {"id", "name"};
    public static String tableCreation = "CREATE TABLE IF NOT EXISTS " +
            tableName +" (\n" +
            columns[0] + " integer PRIMARY KEY NOT NULL, \n" +
            columns[1] + " text" +
            ");";

    public static ArrayList<String> getDatagroups()
    {
        ArrayList<String> groups = new ArrayList<String>();
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

    public static void addDatagroup(String datagroup)
    {
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
