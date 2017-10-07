package seng202.team7;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;
import java.sql.*;

/**
 * Class to handle common database operations like creation and deletion of tables
 * Holds the connect() method used by all SQL connections
 * @author MorganEnglish
 */
public class DatabaseHandler {
    public static String url = "jdbc:sqlite:./src/Database/database.db";
    public static String onlineUrl = "jdbc:sqlite:./src/Database/databaseOnline.db";
    public static String onlineDatabaseLocal = "./src/Database/databaseOnline.db";
    public static String onlineDatabaseUrl = "http://seng202team7.000webhostapp.com/database.db";
    public static URL dbUrl = null;

    public static void createURL(){

    }

    //public static String onlineURL = "jdbc:sqlite:https//seng202team7.000webhostapp.com/database.db";

    public static void getOnlineDatabase()
    {
        try {
            File dbFile = new File(onlineDatabaseLocal);
            URL website = new URL(onlineDatabaseUrl);
            FileUtils.copyURLToFile(website, dbFile);
        }catch (Exception e) {
            e.printStackTrace();
        }
//
//        try {
//            URL website = new URL(onlineDatabaseUrl);
//            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
//            FileOutputStream fos = new FileOutputStream(onlineDatabaseLocal);
//            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//        }

//
//        try
//        {
//            dbUrl = new URL("https://seng202team7.000webhostapp.com/database.db");
//            try (InputStream in = dbUrl.openStream()) {
//                Files.copy(in,new Path("./src/Database/database.db") ,StandardCopyOption.REPLACE_EXISTING);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
    }

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
     * Caopies all the contents from an online table into the local database
     * @param tableName
     */
    public static void copyOnline(String tableName)
    {

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
