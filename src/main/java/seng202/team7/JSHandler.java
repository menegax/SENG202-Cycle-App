package seng202.team7;


import java.util.List;

public class JSHandler {

    public List<Wifi> getWifiJS(){
        DatabaseRetriever databaseRetriever = new DatabaseRetriever();
        return databaseRetriever.getWifiList();
    }





}
