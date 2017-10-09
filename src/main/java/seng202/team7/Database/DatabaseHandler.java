package seng202.team7.Database;

import org.apache.commons.io.FileUtils;
import seng202.team7.Windows.MainWindow.MainWindow;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.*;

/**
 * Class to handle common database operations like creation and deletion of tables
 * Holds the connect() method used by all SQL connections
 * @author MorganEnglish
 */
public class DatabaseHandler {
    public static String url = "jdbc:sqlite:./src/Database/database.db";
    public static String testUrl = "jdbc:sqlite:./src/test/java/seng202/team7/testDatabase.db";
    public static String onlineUrl = "jdbc:sqlite:./src/Database/databaseOnline.db";
    public static String tempUrl = "jdbc:sqlite:./src/Database/database.db";
    private static String databaseLocal;
    public static String onlineDatabaseUrl = "http://seng202team7.000webhostapp.com/database.txt";
    public static URL dbUrl = null;

    /**
     * Runs on start-up to make sure there is a database in the directory of the jar
     * Also sets the default url to that of the new database
     */
    public static void initializeDatabase() {
        String path =
                MainWindow.class.getProtectionDomain().
                        getCodeSource().getLocation().getPath();
        try {

            path =
                    URLDecoder.decode(
                            path,
                            "UTF-8");

        }catch (IOException e)
        {
            e.printStackTrace();
        }
        File jarDir = new File(path);
        System.out.println(jarDir.getParentFile());
        url = "jdbc:sqlite:"+jarDir.getParentFile()+"/database.db";
        databaseLocal = jarDir.getParentFile()+"/database.db";
        File f = new File(databaseLocal);
        if(f.exists())
            return;
        else {
            getDatabase();
        }
    }

    /**
     * Creates a local copy of the database from a file within the executable. As a db inside the executable would be unmodifyable
     */
    private static void getDatabase() {
        DatabaseRetriever temp  = new DatabaseRetriever();
        File dbFile = new File(databaseLocal);
        //System.out.println();
        try {
            FileUtils.copyURLToFile(temp.getClass().getClassLoader().getResource("TextFiles/database.db"),dbFile);
        } catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("finished");
    }

    /**
     * Creates a database
     * Should not be used unless the database is removed
     * @param url Location for the database
     */
    public static void createDatabase(String url) {

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
     * Creates a database in the default location
     * Should not be used unless the database is removed
     */
    public static void createDatabase()
    {
        createDatabase(url);
    }

    /**
     * Method for connecting to a database. Used as a helper method
     * @param url the url of databse to connect to
     * @return the connection to the database
     */
    public static Connection connect(String url) {
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
     * Creates an SQL table with the name and sql script given
     * @param tableName name for the table
     * @param tableScript script for creating the table
     * @param url Url for database to add table to
     */
    public static void createTable(String tableName, String tableScript, String url) {
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
    public static void deleteTable(String tableName, String url) {
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
