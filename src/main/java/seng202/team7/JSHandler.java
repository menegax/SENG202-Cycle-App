package seng202.team7;

import java.util.List;

/**
 * Acts as the handler between java and javascript to pass objects into google maps javascript API
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

    public List<PointM> getPointsJS(String datagroup)
    {

        //return Analytics.checkRoutes(databaseRetriever.queryTrip(StaticVariables.singleStringQuery(Trip.tableName, Trip.columns[15],datagroup)));
        System.out.println(Analytics.checkRoutes(databaseRetriever.queryTrip("SELECT obj FROM trip LIMIT 10")));
        return Analytics.checkRoutes(databaseRetriever.queryTrip("SELECT obj FROM trip LIMIT 1000"));
    }

    /**
     * Returns a list of Wifi objects for use in the MapViewer filtered by 3 parameters
     * @param burough borough to filter by
     * @param type type to filter by
     * @param provider provider to filter by
     * @return List of Wifi
     */
    public List<Wifi> getWifiJSFiltered(String burough, String type, String provider)
    {
        boolean buroughF = !(burough.equals("")|| burough.toLowerCase().equals("none")||burough==null);
        boolean typeF = !(type.equals("")||type.toLowerCase().equals("none")||type==null);
        boolean providerF = !(provider.equals("")||provider.toLowerCase().equals("none")||provider==null);

        if(!buroughF&&!typeF&&!providerF){
            //No fields selected
            return databaseRetriever.getWifiList();
        } else if(buroughF&&!typeF&&!providerF){
            //only burough selected
            return  databaseRetriever.queryWifi(StaticVariables.singleStringQuery(Wifi.tableName, Wifi.columns[1],burough));
        } else if(buroughF&&typeF&&!providerF){
            //only burough and type
            return databaseRetriever.queryWifi(StaticVariables.doubleStringQuery(Wifi.tableName, Wifi.columns[1],burough,Wifi.columns[2],type));
        } else if(buroughF&&typeF&&providerF){
            //all
            return databaseRetriever.queryWifi(StaticVariables.mapViewWifiQuery(burough,type,provider));
        } else if(!buroughF&&typeF&&!providerF) {
            //only type
            return  databaseRetriever.queryWifi(StaticVariables.singleStringQuery(Wifi.tableName, Wifi.columns[2],type));
        } else if(!buroughF&&typeF&&providerF){
            //type and provider
            return  databaseRetriever.queryWifi(StaticVariables.doubleStringQuery(Wifi.tableName, Wifi.columns[2],type,Wifi.columns[3],provider));
        } else if(buroughF&&!typeF&&providerF){
            //provider and burough
            return  databaseRetriever.queryWifi(StaticVariables.doubleStringQuery(Wifi.tableName, Wifi.columns[1],burough,Wifi.columns[3],provider));
        } else if(!buroughF&&!typeF&&providerF){
            //only provider
            return  databaseRetriever.queryWifi(StaticVariables.singleStringQuery(Wifi.tableName, Wifi.columns[3],provider));
        }
        return databaseRetriever.getWifiList();
    }


    /**
     * Returns a list of Retailer objects for use in Mapviewer filtered by 3 fields
     * @param zip zipcode to filter by in string form
     * @param type type to filter by. Whole string needed not letter
     * @param street Street to filter by
     * @return list of Retailer objects matching the filter
     */
    public List<Retailer> getRetailerJSFiltered(String zip, String type, String street)
    {
        boolean zipF = !(zip.equals("")|| zip.toLowerCase().equals("none")||zip==null);
        boolean typeF = !(type.equals("")||type.toLowerCase().equals("none")||type==null);
        boolean streetF = !(street.equals("")||street.toLowerCase().equals("none")||street==null);

        if(!zipF&&!typeF&&!streetF){
            //No fields selected
            return databaseRetriever.getRetailerList();
        } else if(zipF&&!typeF&&!streetF){
            //only zip selected
            return databaseRetriever.queryRetailer(StaticVariables.singleIntQuery(Retailer.tableName,Retailer.columns[6], Integer.parseInt(zip)));

        } else if(zipF&&typeF&&!streetF){
            //only zip and type
            return databaseRetriever.queryRetailer(StaticVariables.stringIntQuery(Retailer.tableName,Retailer.columns[7], type, Retailer.columns[6],Integer.parseInt(zip)));
        } else if(zipF&&typeF&&streetF){
            //all
            return databaseRetriever.queryRetailer(StaticVariables.mapViewRetailerQuery(Integer.parseInt(zip),street, type));
        } else if(!zipF&&typeF&&!streetF) {
            //only type
            return databaseRetriever.queryRetailer(StaticVariables.singleStringQuery(Retailer.tableName,Retailer.columns[7], type));
        } else if(!zipF&&typeF&&streetF){
            //type and street
            return databaseRetriever.queryRetailer(StaticVariables.doubleStringQuery(Retailer.tableName,Retailer.columns[7], type, Retailer.columns[13],street));

        } else if(zipF&&!typeF&&streetF){
            //street and zip
            return databaseRetriever.queryRetailer(StaticVariables.stringIntQuery(Retailer.tableName,Retailer.columns[13], street, Retailer.columns[6],Integer.parseInt(zip)));

        } else if(!zipF&&!typeF&&streetF){
            //only street
            return databaseRetriever.queryRetailer(StaticVariables.singleStringQuery(Retailer.tableName,Retailer.columns[13], street));
        }
        return databaseRetriever.getRetailerList();

    }


    /**
     * Gets all Retailer objects for javascript
     * @return list of retailer objects
     */
    public List<Retailer> getRetailerJS() {
        return databaseRetriever.getRetailerList();
    }





}
