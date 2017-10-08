package seng202.team7.Controllers.MainWindowControllers;

import com.sun.javafx.webkit.WebConsoleListener;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import seng202.team7.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * todo
 * @author Joshua Meneghini
 */
public class RoutePlannerViewerController implements Initializable{

    @FXML
    private WebView webViewMap1;
    @FXML private ComboBox<String> wifiTypeCB;
    @FXML private ComboBox<String> retailerTypeCB;
    @FXML private Label errorLabel;


    private WebEngine webEngine1;
    private JSObject jsBridge1;
    private RouteHandler routeHandler;

    /**
     * todo
     * @param url
     * @param rb
     */
    public void initialize(URL url, ResourceBundle rb)
    {
        String[] wifiTypes = {"All", "Free", "Limited Free", "Partner Site"};
        wifiTypeCB.getItems().addAll(wifiTypes);
        wifiTypeCB.getSelectionModel().select(0);

        String[] retailerTypes = {"All", "Food", "Nightlife", "Shopping", "Personal/Professional Services", "Visitor Services", "Community Resources"};
        retailerTypeCB.getItems().addAll(retailerTypes);
        retailerTypeCB.getSelectionModel().select(0);

        webEngine1 = webViewMap1.getEngine();

        webEngine1.setJavaScriptEnabled(true);
        webEngine1.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (Worker.State.SUCCEEDED == newValue) {
                jsBridge1 = (JSObject) webEngine1.executeScript("window");
                routeHandler = new RouteHandler();
                jsBridge1.setMember("bridge", routeHandler);
                //jsBridge1 = (JSObject) webEngine1.executeScript("getJsConnector();");
            }
        });
        webEngine1.load(getClass().getClassLoader().getResource("HTMLFiles/RoutePlanner.html").toExternalForm());

        WebConsoleListener.setDefaultListener(new WebConsoleListener() {
            @Override
            public void messageAdded(WebView webView, String message, int lineNumber, String sourceId) {
                System.out.println("Console: [" + sourceId + ":" + lineNumber + "] " + message);
            }
        });
    }


    public void clearWifi() {
        errorLabel.setVisible(false);
        jsBridge1.call("loadWifiType", "Clear");
    }

    public void clearRetailer() {
        errorLabel.setVisible(false);
        jsBridge1.call("loadRetailerType", "Clear");
    }

    public void displayWifis() {
        errorLabel.setVisible(false);
        jsBridge1.call("loadWifiType", wifiTypeCB.getSelectionModel().getSelectedItem());
    }

    public void displayRetailers() {
        errorLabel.setVisible(false);
        jsBridge1.call("loadRetailerType", retailerTypeCB.getSelectionModel().getSelectedItem());
    }

    public void addWifi() {
        errorLabel.setVisible(false);
        Integer points = (Integer) jsBridge1.call("getPoints");
        if (points == 0) {
            errorLabel.setText("Please select a location on the map first.");
            errorLabel.setVisible(true);
        } else if (points == 1) {
            String start = (String) jsBridge1.call("getStart");
            Location startLocation = new Location(Double.valueOf(start.split(",")[0]), Double.valueOf(start.split(",")[1]));
            try {
                Wifi nearestWifi = findNearestWifi(startLocation);
                routeHandler.setLocationToAdd(nearestWifi);
                jsBridge1.call("addWifiToRoute");
            } catch (DatabaseEmptyException e) {
                errorLabel.setText("Please load some Wi-Fi locations to the database.");
                errorLabel.setVisible(true);
            }
        } else {
            String start = (String) jsBridge1.call("getStart");
            String end = (String) jsBridge1.call("getEnd");
            Location startLocation = new Location(Double.valueOf(start.split(",")[0]), Double.valueOf(start.split(",")[1]));
            Location endLocation = new Location(Double.valueOf(end.split(",")[0]), Double.valueOf(end.split(",")[1]));
            try {
                Wifi nearestWifi = findNearestWifi(startLocation, endLocation);
                routeHandler.setLocationToAdd(nearestWifi);
                jsBridge1.call("addWifiToRoute");
            } catch (DatabaseEmptyException e) {
                errorLabel.setText("Please load some Wi-Fi locations to the database.");
                errorLabel.setVisible(true);
            }
        }
    }

    public void addRetailer() {
        errorLabel.setVisible(false);
        Integer points = (Integer) jsBridge1.call("getPoints");
        if (points == 0) {
            errorLabel.setText("Please select a location on the map first.");
            errorLabel.setVisible(true);
        } else if (points == 1) {
            String start = (String) jsBridge1.call("getStart");
            Location startLocation = new Location(Double.valueOf(start.split(",")[0]), Double.valueOf(start.split(",")[1]));
            try {
                Retailer nearestRetailer = findNearestRetailer(startLocation);
                routeHandler.setLocationToAdd(nearestRetailer);
                jsBridge1.call("addRetailerToRoute");
            } catch (DatabaseEmptyException e) {
                errorLabel.setText("Please load some Retailer locations to the database.");
                errorLabel.setVisible(true);
            }
        } else {
            String start = (String) jsBridge1.call("getStart");
            String end = (String) jsBridge1.call("getEnd");
            Location startLocation = new Location(Double.valueOf(start.split(",")[0]), Double.valueOf(start.split(",")[1]));
            Location endLocation = new Location(Double.valueOf(end.split(",")[0]), Double.valueOf(end.split(",")[1]));
            try {
                Retailer nearestRetailer = findNearestRetailer(startLocation, endLocation);
                routeHandler.setLocationToAdd(nearestRetailer);
                jsBridge1.call("addRetailerToRoute");
            } catch (DatabaseEmptyException e) {
                errorLabel.setText("Please load some Retailer locations to the database.");
                errorLabel.setVisible(true);
            }
        }
    }


    /**
     * Finds the nearest wifi location to a retailer object
     * @param point The point to search around
     * @return The nearest wifi object
     * @throws DatabaseEmptyException thrown when there are no wifi objects in the database
     */
    public Wifi findNearestWifi(Location point) throws DatabaseEmptyException{
        if (wifiInDatabase()) {
            DatabaseRetriever dbRetriever = new DatabaseRetriever();
            ArrayList<Wifi> wifiList = dbRetriever.queryWifi(StaticVariables.wifiByLocation(point.getLatitude(), point.getLongitude()));
            double defaultDist = StaticVariables.defaultDist;
            while (wifiList.isEmpty()) {
                defaultDist *= 2;
                wifiList = dbRetriever.queryWifi(StaticVariables.wifiByLocation(point.getLatitude(), defaultDist, point.getLongitude(), defaultDist));
            }
            double minDistance = -1;
            double currentDistance;
            int minIndex = 0;
            for (Wifi wifi : wifiList) {
                currentDistance = StaticVariables.calculateDistance(point.getLatitude(), point.getLongitude(), wifi.getLatitude(), wifi.getLongitude());
                if (minDistance == -1) {
                    minDistance = currentDistance;
                } else if (minDistance > currentDistance) {
                    minDistance = currentDistance;
                    minIndex = wifiList.indexOf(wifi);
                }
            }
            return wifiList.get(minIndex);
        } else {
            throw new DatabaseEmptyException();
        }
    }

    /**
     * Finds the nearest wifi object to a straight line route
     * @param start The start location of the route
     * @param end The end location of the route
     * @return The nearest wifi object to the route
     * @throws DatabaseEmptyException thrown when there are no wifi objects in the database
     */
    public Wifi findNearestWifi(Location start, Location end) throws DatabaseEmptyException{
        if (wifiInDatabase()) {
            Location centre = new Location(((start.getLatitude() + end.getLatitude())/2), ((start.getLongitude() + end.getLongitude())/2));
            double latOffset = Math.abs(centre.getLatitude() - start.getLatitude()) + StaticVariables.defaultDist;
            double longOffset = Math.abs(centre.getLongitude() - start.getLongitude()) + StaticVariables.defaultDist;
            DatabaseRetriever dbRetriever = new DatabaseRetriever();
            ArrayList<Wifi> wifiList = dbRetriever.queryWifi(StaticVariables.wifiByLocation(centre.getLatitude(), latOffset, centre.getLongitude(), longOffset));
            double defaultDist = StaticVariables.defaultDist;
            while (wifiList.isEmpty()) {
                wifiList = dbRetriever.queryWifi(StaticVariables.wifiByLocation(centre.getLatitude(), latOffset + defaultDist, centre.getLongitude(), longOffset + defaultDist));
                defaultDist *= 2;
            }
            double minDistance = -1;
            double currentDistance;
            int minIndex = 0;
            for (Wifi wifi : wifiList) {
                currentDistance = StaticVariables.calculateDistance(start, end, wifi.getLatitude(), wifi.getLongitude());
                if (minDistance == -1) {
                    minDistance = currentDistance;
                } else if (minDistance > currentDistance) {
                    minDistance = currentDistance;
                    minIndex = wifiList.indexOf(wifi);
                }
            }
            return wifiList.get(minIndex);
        } else {
            throw new DatabaseEmptyException();
        }
    }

    /**
     * Finds the nearest retailer object to a straight line route
     * @param start The start location of the route
     * @param end The end location of the route
     * @return The nearest retailer object to the route
     * @throws DatabaseEmptyException thrown when there are no retailer objects in the database
     */
    public Retailer findNearestRetailer(Location start, Location end) throws DatabaseEmptyException{
        if (retailerInDatabase()) {
            Location centre = new Location(((start.getLatitude() + end.getLatitude())/2), ((start.getLongitude() + end.getLongitude())/2));
            double latOffset = Math.abs(centre.getLatitude() - start.getLatitude()) + StaticVariables.defaultDist;
            double longOffset = Math.abs(centre.getLongitude() - start.getLongitude()) + StaticVariables.defaultDist;
            DatabaseRetriever dbRetriever = new DatabaseRetriever();
            ArrayList<Retailer> retailerList = dbRetriever.queryRetailer(StaticVariables.retailerByLocation(centre.getLatitude(), latOffset, centre.getLongitude(), longOffset));
            double defaultDist = StaticVariables.defaultDist;
            while (retailerList.isEmpty()) {
                retailerList = dbRetriever.queryRetailer(StaticVariables.retailerByLocation(centre.getLatitude(), latOffset + defaultDist, centre.getLongitude(), longOffset + defaultDist));
                defaultDist *= 2;
            }
            double minDistance = -1;
            double currentDistance;
            int minIndex = 0;
            for (Retailer retailer : retailerList) {
                currentDistance = StaticVariables.calculateDistance(start, end, retailer.getLatitude(), retailer.getLongitude());
                if (minDistance == -1) {
                    minDistance = currentDistance;
                } else if (minDistance > currentDistance) {
                    minDistance = currentDistance;
                    minIndex = retailerList.indexOf(retailer);
                }
            }
            return retailerList.get(minIndex);
        } else {
            throw new DatabaseEmptyException();
        }
    }

    /**
     * Finds the nearest retailer location to a retailer object
     * @param point The point to search around
     * @return The nearest retailer object
     * @throws DatabaseEmptyException thrown when there are no retailer objects in the database
     */
    public Retailer findNearestRetailer(Location point) throws DatabaseEmptyException{
        if (retailerInDatabase()) {
            DatabaseRetriever dbRetriever = new DatabaseRetriever();
            ArrayList<Retailer> retailerList = dbRetriever.queryRetailer(StaticVariables.retailerByLocation(point.getLatitude(), point.getLongitude()));
            double defaultDist = StaticVariables.defaultDist;
            while (retailerList.isEmpty()) {
                defaultDist *= 2;
                retailerList = dbRetriever.queryRetailer(StaticVariables.retailerByLocation(point.getLatitude(), defaultDist, point.getLongitude(), defaultDist));
            }
            double minDistance = -1;
            double currentDistance;
            int minIndex = 0;
            for (Retailer retailer : retailerList) {
                currentDistance = StaticVariables.calculateDistance(point.getLatitude(), point.getLongitude(), retailer.getLatitude(), retailer.getLongitude());
                if (minDistance == -1) {
                    minDistance = currentDistance;
                } else if (minDistance > currentDistance) {
                    minDistance = currentDistance;
                    minIndex = retailerList.indexOf(retailer);
                }
            }
            return retailerList.get(minIndex);
        } else {
            throw new DatabaseEmptyException();
        }
    }

    /**
     * Checks whether there are any wifi records in the database
     * @return true if there are wifi records in the database
     */
    public boolean wifiInDatabase() {
        DatabaseRetriever dbRetriever = new DatabaseRetriever();
        ArrayList<Wifi> wifiList = dbRetriever.queryWifi(StaticVariables.steppedQuery(Wifi.tableName, 0));
        return !wifiList.isEmpty();
    }

    /**
     * Checks whether there are any retailer records in the database
     * @return true if there are retailer records in the database
     */
    public boolean retailerInDatabase() {
        DatabaseRetriever dbRetriever = new DatabaseRetriever();
        ArrayList<Retailer> retailerList = dbRetriever.queryRetailer(StaticVariables.steppedQuery(Retailer.tableName, 0));
        return !retailerList.isEmpty();
    }
}