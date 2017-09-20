package seng202.team7;

import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable{

    public BorderPane mainBorderPane;
    public TreeView<String> navigationTree;
    public AnchorPane centerAnchorPane;
    private HomeWindow homeViewer;
    private RetailerDataViewer retailerViewer;
    private WifiDataViewer wifiViewer;
    private TripDataViewer tripViewer;
    private DataEntryWindow dataEntryViewer;
    private MapAnalyticWindow mapViewer;
    private TripAnalyticWindow graphViewer;

    /**
     * Runs as the program initially starts. Initialises each of the custom
     * JavaFX panels (loads them into memory) and calls for the navigation bar
     * to be populated.
     *
     * @param location URL of the location
     * @param resources ResourceBundle of the resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set home panel to default screen
        homeViewer = new HomeWindow();
        centerAnchorPane.getChildren().add(homeViewer);

        // Initialize different custom panels (Saves loading panel again every time panel is changed)
        retailerViewer = new RetailerDataViewer();
        wifiViewer = new WifiDataViewer();
        tripViewer = new TripDataViewer();
        dataEntryViewer = new DataEntryWindow();
        mapViewer = new MapAnalyticWindow();
        graphViewer = new TripAnalyticWindow();

        populateNavigationBar();
    }

    /**
     * Populates the navigation bar with all necessary tree items. Also creates
     * an event listener for whenever a tree item in the navigation bar is clicked.
     * This listener call and appropriate handler.
     */
    private void populateNavigationBar() {
        // Tree root
        TreeItem<String> root = new TreeItem<>();
        root.setExpanded(true);

        // Home branch
        makeBranch("Home", root);

        // Route Planner branch
        makeBranch("Route Planning", root);

        // Analytics branch
        TreeItem<String> analyticsBranch = makeBranch("Analytics", root);
        makeBranch("Map View", analyticsBranch);
        makeBranch("Graph View", analyticsBranch);

        // Raw Data Viewer branch
        TreeItem<String> dataViewerBranch = makeBranch("Data Viewer", root);
        makeBranch("Retailer", dataViewerBranch);
        makeBranch("Trip", dataViewerBranch);
        makeBranch("Wifi", dataViewerBranch);

        // Data Entry branch
        makeBranch("Data Entry", root);

        navigationTree.setRoot(root);
        navigationTree.setShowRoot(false);

        navigationTree.getSelectionModel().selectedItemProperty()
                .addListener((v, oldValue, newValue) -> {
                    if ((newValue != null) && (oldValue != null)) {
                        String previousSelection = oldValue.getValue();
                        switch (previousSelection) {
                            case "Home":removeHomeViewer(); break;
                            case "Route Planning": removeRoutePlanningViewer(); break;
                            case "Analytics": removeAnalyticsViewer(); break;
                            case "Map View": removeMapViewer(); break;
                            case "Graph View": removeGraphView(); break;
                            case "Data Viewer": removeDataViewerViewer(); break;
                            case "Retailer": removeRetailerViewer(); break;
                            case "Trip": removeTripViewer(); break;
                            case "Wifi": removeWifiViewer(); break;
                            case "Data Entry": removeDataEntry(); break;
                            default: System.out.println("ERROR: No such removal handle exists");
                        }

                        String currentSelection = newValue.getValue();
                        switch (currentSelection) {
                            case "Home": setHomeViewer(); break;
                            case "Route Planning": setRoutePlanningViewer(); break;
                            case "Analytics": setAnalyticsViewer(); break;
                            case "Map View": setMapViewer(); break;
                            case "Graph View": setGraphView(); break;
                            case "Data Viewer": setDataViewerViewer(); break;
                            case "Retailer": setRetailerViewer(); break;
                            case "Trip": setTripViewer(); break;
                            case "Wifi": setWifiViewer(); break;
                            case "Data Entry": setDataEntry(); break;
                            default: System.out.println("ERROR: No such set handle exists");
                        }
                    }
                });
    }

    /**
     * Creates a new TreeItem<String> representing a branch. The new branch has the
     * text title in it and is a child of parent.
     * @param title A String representing the text title for the branch.
     * @param parent The parent TreeItem<String> of new branch to be created.
     * @return The newly created branch (TreeItem<String>).
     */
    private TreeItem<String> makeBranch(String title, TreeItem<String> parent) {
        TreeItem<String> item = new TreeItem<>(title);
        parent.getChildren().add(item);
        return item;
    }

    private void removeHomeViewer() {
        centerAnchorPane.getChildren().remove(homeViewer);
    }

    private void removeRoutePlanningViewer() {
        // To implement
    }

    private void removeAnalyticsViewer() {
        // To implement
    }

    private void removeMapViewer() {
        centerAnchorPane.getChildren().remove(mapViewer);
    }

    private void removeGraphView() {
        centerAnchorPane.getChildren().remove(graphViewer);
    }

    private void removeDataViewerViewer() {
        // To implement
    }

    private void removeRetailerViewer() {
        centerAnchorPane.getChildren().remove(retailerViewer);
        retailerViewer = new RetailerDataViewer();
    }

    private void removeTripViewer() {
        centerAnchorPane.getChildren().remove(tripViewer);
        tripViewer = new TripDataViewer();
    }

    private void removeWifiViewer() {
        centerAnchorPane.getChildren().remove(wifiViewer);
        wifiViewer = new WifiDataViewer();
    }

    private void removeDataEntry() {
        centerAnchorPane.getChildren().remove(dataEntryViewer);
    }

    /**
     * Set home viewer
     * todo
     */
    private void setHomeViewer() {
        centerAnchorPane.getChildren().add(homeViewer);
    }

    /**
     * todo
     */
    private void setRoutePlanningViewer() {
        // To implement
    }

    /**
     * todo
     */
    private void setAnalyticsViewer() {
        // To implement
    }

    private void setMapViewer() {
        centerAnchorPane.getChildren().add(mapViewer);
    }

    private void setGraphView() {
        centerAnchorPane.getChildren().add(graphViewer);
    }

    /**
     * todo
     */
    private void setDataViewerViewer() {
        // To implement
    }

    /**
     * Sets the center of the mainBorderPane to display the retailerViewer
     * custom JavaFX object.
     */
    private void setRetailerViewer() {
        centerAnchorPane.getChildren().add(retailerViewer);
    }

    /**
     * Sets the center of the mainBorderPane to display the tripViewer
     * custom JavaFX object.
     */
    private void setTripViewer() {
        centerAnchorPane.getChildren().add(tripViewer);
    }

    /**
     * Sets the center of the mainBorderPane to display the wifiViewer
     * custom JavaFX object.
     */
    private void setWifiViewer() {
        centerAnchorPane.getChildren().add(wifiViewer);
    }

    /**
     * Sets the center of the mainBorderPane to display the dataEntryViewer
     * custom JavaFX object.
     */
    private void setDataEntry() {
        centerAnchorPane.getChildren().add(dataEntryViewer);
    }
}
