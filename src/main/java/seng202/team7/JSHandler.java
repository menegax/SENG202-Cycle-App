package seng202.team7;


import java.util.List;

/**
 * acts as the handler between java and javascript to pass onjects into google maps javascipt API
 * @author MorganEnglish
 */
public class JSHandler {

    private DatabaseRetriever databaseRetriever = new DatabaseRetriever();
    /**
     * Wrapper method for interacting with Javascript inside WebEngine
     * @return list of Wifi objects
     */
    public List<Wifi> getWifiJS(){

        return databaseRetriever.getWifiList();
    }

    public List<Wifi> getWifiJS(String burough, String type, String provider){

        return databaseRetriever.queryWifi(StaticVariables.mapViewWifiQuery(burough,type,provider));
    }

    public List<Retailer> getRetailerJS() {
        return databaseRetriever.getRetailerList();
    }





}
