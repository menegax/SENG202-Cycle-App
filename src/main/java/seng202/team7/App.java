package seng202.team7;

import java.io.File;
import java.sql.Connection;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String basePath = new File("").getAbsolutePath();

        String url = "jdbc:sqlite:DataStorage.db";

        System.out.println( "Hello World! How is it going?" );
        System.out.println("Just testing a change");
        System.out.println("test commit Morgan");
        System.out.println("Aidan's test");
        System.out.println("Connor's test");
        System.out.println("Joshua's test");


        DatabaseTester.deleteTables();
        DatabaseTester.createTables();
        //DatabaseTester.init();
    }
}
