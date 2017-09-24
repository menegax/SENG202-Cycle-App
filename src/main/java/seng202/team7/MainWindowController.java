package seng202.team7;
/**
 * Controls manual data entry and data uploaded via csv
 * @author Connor McEwan-McDowall
 */
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
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
    public MenuItem mainMenuExitButton;
    public Menu mainMenuHelpButton;

    private HomeWindow homeViewer;
    private RoutePlannerViewer routePlannerViewer;
    private MapViewerWindow mapViewer;
    private RetailerDataViewer retailerViewer;
    private WifiDataViewer wifiViewer;
    private TripDataViewer tripViewer;
    private DataEntryWindow dataEntryViewer;
    private MapAnalyticWindow mapAnalyticViewer;
    private TripAnalyticWindow graphViewer;
    private String currentScreen; // Currently visible screen

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
        currentScreen = "Home";

        // Initialize different custom panels (Saves loading panel again every time panel is changed)
        routePlannerViewer = new RoutePlannerViewer();
        mapViewer = new MapViewerWindow();
        retailerViewer = new RetailerDataViewer();
        wifiViewer = new WifiDataViewer();
        tripViewer = new TripDataViewer();
        dataEntryViewer = new DataEntryWindow();
        mapAnalyticViewer = new MapAnalyticWindow();
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

        // Map Viewer branch
        makeBranch("Map Viewer", root);

        // Analytics branch
        TreeItem<String> analyticsBranch = makeBranch("Analytics", root);
        //makeBranch("Map View", analyticsBranch);
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
                    String currentSelection = newValue.getValue();
                    if (!(currentScreen.equals(currentSelection))) {
                        changeMainScreen(currentSelection);
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

    private void changeMainScreen(String newScreen) {
        // Removes the current screen to make room for the new screen
        switch (currentScreen) {
            case "Home": removeMainScreen(homeViewer); break;
            case "Route Planning": removeMainScreen(routePlannerViewer); break;
            case "Map Viewer": removeMainScreen(mapViewer); break;
            case "Analytics": break;
            case "Map View": removeMainScreen(mapAnalyticViewer); break;
            case "Graph View": removeMainScreen(graphViewer); break;
            case "Data Viewer": break;
            case "Retailer": removeMainScreen(retailerViewer); break;
            case "Trip": removeMainScreen(tripViewer); break;
            case "Wifi": removeMainScreen(wifiViewer); break;
            case "Data Entry": removeMainScreen(dataEntryViewer); break;
            default: System.out.println("ERROR: No such removal handle exists");
        }
        // Add the new screen where the old one was
        switch (newScreen) {
            case "Home": setMainScreen(homeViewer); break;
            case "Route Planning": setMainScreen(routePlannerViewer); break;
            case "Map Viewer": setMainScreen(mapViewer); break;
            case "Analytics": break;
            case "Map View": setMainScreen(mapAnalyticViewer); break;
            case "Graph View": setMainScreen(graphViewer); break;
            case "Data Viewer": break;
            case "Retailer": setMainScreen(retailerViewer = new RetailerDataViewer()); break;
            case "Trip": setMainScreen(tripViewer = new TripDataViewer()); break;
            case "Wifi": setMainScreen(wifiViewer = new WifiDataViewer()); break;
            case "Data Entry": setMainScreen(dataEntryViewer); break;
            default: System.out.println("ERROR: No such set handle exists");
        }
        // Update screen tracker
        currentScreen = newScreen;
    }

    /**
     * todo
     * @param screen
     */
    private void removeMainScreen(Node screen) {
        centerAnchorPane.getChildren().remove(screen);
    }

    /**
     * todo
     * @param screen
     */
    private void setMainScreen(Node screen) {
        centerAnchorPane.getChildren().add(screen);
    }

}