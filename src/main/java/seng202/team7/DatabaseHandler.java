package seng202.team7;

import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.sql.*;

/**
 * Class to handle common database operations like creation and deletion of tables
 * Holds the connect() method used by all SQL connections
 * @author MorganEnglish
 */
public class DatabaseHandler {
    public static String url;
    public static String onlineUrl = "jdbc:sqlite:./src/Database/databaseOnline.db";
    public static String databaseLocal;
    public static String onlineDatabaseUrl = "http://seng202team7.000webhostapp.com/database.db";
    public static URL dbUrl = null;


    public static void initializeDatabase()
    {
        System.out.println("initdatabase");
        File jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
        System.out.println(jarDir.getAbsolutePath());
        url = "jdbc:sqlite:"+jarDir.getAbsolutePath()+"/database.db";
        databaseLocal = jarDir.getAbsolutePath()+"/database.db";
        File f = new File(databaseLocal);
        if(f.exists())
            return;
        else {
            getOnlineDatabase();
            JOptionPane.showMessageDialog(null,
                    "Initialixing Database\n Please Wait...",
                    "Meraki Bike",
                    JOptionPane.WARNING_MESSAGE);


        }
    }

    //public static String onlineURL = "jdbc:sqlite:https//seng202team7.000webhostapp.com/database.db";

    public static void getOnlineDatabase()
    {
        System.out.println("getting databse");
        try {
            File dbFile = new File(databaseLocal);
            URL website = new URL(onlineDatabaseUrl);
            FileUtils.copyURLToFile(website, dbFile);
            System.out.println("finished");
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Creates a database
     * Should not be used unless the database is removed
     */
    public static void createDatabase(String url)
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
     * Creates a database
     * Should not be used unless the database is removed
     */
    public static void createDatabase()
    {
        createDatabase(url);
    }


    /**
     * Method for connecting to a database. Used as a helper method
     * @return the connection to the database
     */
    public static Connection connect(String url)
    {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Method for connecting to a database. Used as a helper method
     * @return the connection to the database
     */
    public static Connection connect()
    {
        return connect(DatabaseHandler.url);
    }


    /**
     * Connects to online database maybe :o
     * @return
     */
    public static Connection connectOnline()
    {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DatabaseHandler.onlineUrl);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }




    /**
     * Creates an SQL table with the name and sql script given
     * @param tableName name for the table
     * @param tableScript script for creating the table
     * @param url Url for database to add table to
     */
    public static void createTable(String tableName, String tableScript, String url)
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
     * Creates an SQL table with the name and sql script given
     * @param tableName name for the table
     * @param tableScript script for creating the table
     */
    public static void createTable(String tableName, String tableScript)
    {
        createTable(tableName,tableScript, url);
    }

    /**
     * Deletes the table from name given
     * @param tableName tablename to drop
     * @param url database to delete table from
     */
    public static void deleteTable(String tableName, String url)
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

    /**
     * Deletes the table from name given
     * @param tableName tablename to drop
     */
    public static void deleteTable(String tableName)
    {
        deleteTable(tableName, url);
    }

}
