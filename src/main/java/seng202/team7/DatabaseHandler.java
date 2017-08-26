package seng202.team7;

import java.sql.*;

public class DatabaseHandler {

    public static void createDatabase(String fileName) {

        //try{Class.forName("Maven.org.sqlite.JDBC");}catch(Exception e){e.printStackTrace();}
        String url = "jdbc:sqlite:C://Maven/sqlite/db/tests.db";

        try (Connection conn = DriverManager.getConnection(fileName)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void createTable(databaseType type) {
        // SQLite connection string
        String url = "jdbc:sqlite:C://sqlite/db/tests.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS "+type+" (\n"//need a name field
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	capacity real\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public DatabaseHandler(){
        System.out.println(this.getClass().getClassLoader().getResource("").getPath());
    }
}
