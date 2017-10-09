package seng202.team7.JSHandling;

import seng202.team7.DataTypes.*;
import seng202.team7.Database.DatabaseRetriever;

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
    /*
    the bike station objects to be added to the map
     */
    public List<Station> getStationJS() {

        return databaseRetriever.getStationList();
    }

    public Location getLocationToAdd() {
        return locationToAdd;
    }

    /*
    sets the location object to be added
     */
    public void setLocationToAdd(Location locationToAdd) {
        this.locationToAdd = locationToAdd;
    }

    /*
    the list of wifi objects to be loaded into the route planning javascript
     */
    public List<Wifi> getWifiJSType(String type)
    {
        if(type.equals("All") || type.equals(""))
            return databaseRetriever.getWifiList();
        return databaseRetriever.queryWifi(StaticVariables.singleStringQuery(Wifi.tableName, Wifi.columns[2],type));
    }

    /*
    the list of retailers objects to be loaded into the route planning javascript
     */
    public List<Retailer> getRetailerJSType(String type)
    {
        if(type.equals("All") || type.equals(""))
            return databaseRetriever.getRetailerList();
        return databaseRetriever.queryRetailer(StaticVariables.singleStringQuery(Retailer.tableName, Retailer.columns[7], type));
    }
}
