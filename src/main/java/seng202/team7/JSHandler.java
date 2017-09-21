package seng202.team7;


import java.util.List;

/**
 * acts as the handler between java and javascript to pass onjects into google maps javascipt API
 * @author MorganEnglish
 */
public class JSHandler {

    public List<Wifi> getWifiJS(){
        DatabaseRetriever databaseRetriever = new DatabaseRetriever();
        return databaseRetriever.getWifiList();
    }





}
