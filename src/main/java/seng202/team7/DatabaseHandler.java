package seng202.team7;

import java.sql.*;

public class DatabaseHandler {
    public static String url = "jdbc:sqlite:src/Database/database.db";

    /**
     * Creates a database
     * Should not be used unless the database is removed
     */
    public static void createDatabase()
    {

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                //DatabaseMetaData meta = conn.getMetaData();
                System.out.println("DataBase made");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method for connecting to a database. Used as a helper method
     * @return the connection to the database
     */
    public static Connection connect()
    {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DatabaseHandler.url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    /**
     * Creates an SQL table with the name and sql script given
     * @param tableName name for the table
     * @param tableScript script for creating the table
     */
    public static void createTable(String tableName, String tableScript)
    {
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(tableScript);
            System.out.println("Table made");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Deletes the table from name given
     * @param tableName tablename to drop
     */
    public static void deleteTable(String tableName)
    {
        String sql = "DROP TABLE IF EXISTS "+tableName;
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("table: "+tableName+ " has been deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
