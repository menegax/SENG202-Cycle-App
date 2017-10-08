package seng202.team7;

import java.util.List;

/**
 * Acts as the handler between java and javascript to pass objects into google maps javascript API
 * @author Joshua Meneghini
 */
public class RouteHandler {

    private Location locationToAdd;

    private DatabaseRetriever databaseRetriever = new DatabaseRetriever();

    /**
     * Wrapper method for interacting with Javascript inside WebEngine
     *
     * @return list of Station objects
     */
    public List<Station> getStationJS() {

        return databaseRetriever.getStationList();
    }

    public Location getLocationToAdd() {
        return locationToAdd;
    }

    public void setLocationToAdd(Location locationToAdd) {
        this.locationToAdd = locationToAdd;
    }

    public List<Wifi> getWifiJSType(String type)
    {
        if(type.equals("All") || type.equals(""))
            return databaseRetriever.getWifiList();
        return databaseRetriever.queryWifi(StaticVariables.singleStringQuery(Wifi.tableName, Wifi.columns[2],type));
    }

    public List<Retailer> getRetailerJSType(String type)
    {
        if(type.equals("All") || type.equals(""))
            return databaseRetriever.getRetailerList();
        return databaseRetriever.queryRetailer(StaticVariables.singleStringQuery(Retailer.tableName, Retailer.columns[7], type));
    }
}
