package seng202.team7;


import java.util.List;

/**
 * acts as the handler between java and javascript to pass onjects into google maps javascipt API
 * @author Joshua Meneghini
 */
public class RouteHandler {

    private DatabaseRetriever databaseRetriever = new DatabaseRetriever();
    /**
     * Wrapper method for interacting with Javascript inside WebEngine
     * @return list of Station objects
     */
    public List<Station> getStationJS(){

        return databaseRetriever.getStationList();
    }





}
