package seng202.team7;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class used to complete analytics through sql queries where applicable
 */
public class SQLAnalytics {

    /**
     * Total trip duration of a datagroup of trips
     * @param datagroup datagroup string to match. if "" will look at all trips
     * @return sum of durations of all trips matched
     */
    public static long totalGroupTripDuration(String datagroup)
    {
        long totalTime = 0;
        String sql;
        if(datagroup != ""){
            sql = "SELECT SUM(duration) AS sum FROM " + Trip.tableName
                    + " WHERE datagroup = \""+datagroup+"\";";
        } else {
            sql = "SELECT SUM(duration) AS sum FROM " + Trip.tableName + ";";
        }

        try (Connection conn = DatabaseHandler.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            while (rs.next()){
              totalTime = rs.getInt("sum");
            }

        } catch (SQLException e){
            System.out.println("Get Station error");
            System.out.println(e.getMessage());
        }

        return totalTime;
    }


    /**
     * Finds the total duration of a datagroup of trips
     * @param datagroup datagroup string to match. if "" will look at all trips
     * @return total duration of matched trips
     */
    public static long totalGroupTripDistance(String datagroup)
    {
        long totalTime = 0;
        String sql;
        if(datagroup != "") {
            sql = "SELECT SUM(distance) AS sum FROM " + Trip.tableName
                    + " WHERE datagroup = \"" + datagroup + "\";";
        } else {
            sql = "SELECT SUM(distance) AS sum FROM " + Trip.tableName
                    + ";";
        }

        try (Connection conn = DatabaseHandler.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            while (rs.next()){
                totalTime = rs.getInt("sum");
            }

        } catch (SQLException e){
            System.out.println("SQLAnalytic Error");
            System.out.println(e.getMessage());
        }

        return totalTime;
    }

    /**
     * @param gender
     * @param datagroup
     * @return
     */
    public static int totalGenderTrips(String gender, String datagroup)
    {
        if (gender.toLowerCase() == "m") {
            gender = "male";
        } else if (gender.toLowerCase() == "f" || gender.toLowerCase() == "w") {
            gender = "female";
        }
        int trips = 0;
        String sql;

        if(datagroup != ""){
            sql = "SELECT COUNT(gender) AS sum FROM " + Trip.tableName
                    + " WHERE datagroup = \""+datagroup+"\" \n" +
                    " AND gender = \"" +gender+"\";";
        } else {
            sql = "SELECT COUNT(gender) AS sum FROM " + Trip.tableName
                    + " WHERE Lower(gender) = \"" +gender.toLowerCase()+"\";";
        }

        try (Connection conn = DatabaseHandler.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            while (rs.next()){
                trips = rs.getInt("sum");
            }

        } catch (SQLException e){
            System.out.println("SQLAnalytic Error");
            System.out.println(e.getMessage());
        }

        return trips;
    }
}
