package seng202.team7;

import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
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
    public static String onlineDatabaseUrl = "http://seng202team7.000webhostapp.com/database.txt";
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
            //getOnlineDatabase();
            getDatabase();
            JOptionPane.showMessageDialog(null,
                    "Initialixing Database\n Please Wait...",
                    "Meraki Bike",
                    JOptionPane.WARNING_MESSAGE);


        }
    }


    public static void downloadFile(String fileURL, String saveDir)
            throws IOException {
        URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();

        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            int contentLength = httpConn.getContentLength();

            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }
            } else {
                // extracts file name from URL
                fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,
                        fileURL.length());
            }

            System.out.println("Content-Type = " + contentType);
            System.out.println("Content-Disposition = " + disposition);
            System.out.println("Content-Length = " + contentLength);
            System.out.println("fileName = " + fileName);

            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            String saveFilePath = saveDir + File.separator + fileName;

            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);

            int bytesRead = -1;
            byte[] buffer = new byte[4096];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.close();
            inputStream.close();

            System.out.println("File downloaded");
        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
    }


    //public static String onlineURL = "jdbc:sqlite:https//seng202team7.000webhostapp.com/database.db";

    public static void getOnlineDatabase()
    {
//        try {
//            downloadFile(onlineDatabaseUrl, databaseLocal);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("finished");

        try {
            File dbFile = new File(databaseLocal);
            URL website = new URL(onlineDatabaseUrl);
            FileUtils.copyURLToFile(website, dbFile);
            System.out.println("finished");
        }catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void getDatabase()
    {
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
