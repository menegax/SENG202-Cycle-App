package seng202.team7;

public class Datagroup {


    public static String tableName = "datagroup";

    public static String tableCreation = "CREATE TABLE IF NOT EXISTS " +
            tableName +" (\n" +
            "id integer PRIMARY KEY NOT NULL, \n" +
            "name text" +
            ");";

}
