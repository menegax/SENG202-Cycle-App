package seng202.team7.Controllers.HelpWindowControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import seng202.team7.Windows.*;
import seng202.team7.Windows.HelpWindow.RoutePlannerHelpViewerWindow;

import java.net.URL;
import java.util.ResourceBundle;

public class HelpWindowController implements Initializable{
    @FXML private TreeView<String> helpNavigationTree;
    @FXML private BorderPane helpBorderPane;
    @FXML private AnchorPane helpCenterAnchorPane;

    //    private HomeHelpWindow homeHelpViewer;
    private RoutePlannerHelpViewerWindow routePlannerHelp;
    //    private MapViewerHelpWindow mapHelpViewer;
//    private RetailerDataViewerHelpWindow retailerHelpViewer;
//    private WifiDataViewerHelpWindow wifiHelpViewer;
//    private TripDataViewerHelpWindow tripHelpViewer;
//    private DataEntryHelpWindow dataEntryHelpViewer;
//    private MapAnalyticHelpWindow mapAnalyticHelpViewer;
//    private TripAnalyticHelpWindow graphHelpViewer;
    private String currentWindow;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set home window as initial window
//        homeHelpViewer = new HomeWindow();
//        helpCenterAnchorPane.getChildren().add(homeHelpViewer);
        currentWindow = "Home";

        // Initialize different custom panels (Saves loading panel again every time panel is changed)
        routePlannerHelp = new RoutePlannerHelpViewerWindow();
        helpCenterAnchorPane.getChildren().add(routePlannerHelp);
//        mapHelpViewer = new MapViewerWindow();
//        retailerHelpViewer = new RetailerDataViewerWindow();
//        wifiHelpViewer = new WifiDataViewerWindow();
//        tripHelpViewer = new TripDataViewerWindow();
//        dataEntryHelpViewer = new DataEntryWindow();
//        mapAnalyticHelpViewer = new MapAnalyticWindow();
//        graphHelpViewer = new TripAnalyticWindow();

        populateNavigationBar();
    }

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
        makeBranch("Map View", analyticsBranch);
        makeBranch("Graph View", analyticsBranch);

        // Raw Data Viewer branch
        TreeItem<String> dataViewerBranch = makeBranch("Data Viewer", root);
        makeBranch("Retailer", dataViewerBranch);
        makeBranch("Trip", dataViewerBranch);
        makeBranch("Wifi", dataViewerBranch);

        // Data Entry branch
        makeBranch("Data Entry", root);

        helpNavigationTree.setRoot(root);
        helpNavigationTree.setShowRoot(false);

        helpNavigationTree.getSelectionModel().selectedItemProperty()
                .addListener((v, oldValue, newValue) -> {
                    String currentSelection = newValue.getValue();
                    if (!(helpNavigationTree.equals(currentSelection))) {
                        changeMainScreen(currentSelection);
                    }
                });
    }

    /**
     * Creates a new TreeItem with Strings representing a branch. The new branch has the
     * text title in it and is a child of parent.
     * @param title A String representing the text title for the branch.
     * @param parent The parent TreeItem of new branch to be created.
     * @return The newly created branch (TreeItem).
     */
    private TreeItem<String> makeBranch(String title, TreeItem<String> parent) {
        TreeItem<String> item = new TreeItem<>(title);
        parent.getChildren().add(item);
        return item;
    }

    /**
     * Changes the window currently shown in the centerAnchorPane to be newScreen
     * @param newScreen The new window that will replace the current window
     */
    private void changeMainScreen(String newScreen) {
        // Removes the current window to make room for the new window
        switch (currentWindow) {
//            case "Home": removeMainScreen(homeHelpViewer); break;
            case "Route Planning": removeMainScreen(routePlannerHelp); break;
//            case "Map Viewer": removeMainScreen(mapHelpViewer); break;
//            case "Analytics": break; // todo
//            case "Map View": removeMainScreen(mapAnalyticHelpViewer); break;
//            case "Graph View": removeMainScreen(graphHelpViewer); break;
//            case "Data Viewer": break; // todo
//            case "Retailer": removeMainScreen(retailerHelpViewer); break;
//            case "Trip": removeMainScreen(tripHelpViewer); break;
//            case "Wifi": removeMainScreen(wifiHelpViewer); break;
//            case "Data Entry": removeMainScreen(dataEntryHelpViewer); break;
            default: System.out.println("ERROR: No such removal handle exists");
        }
        // Add the new window where the old one was
        switch (newScreen) {
//            case "Home": setMainScreen(homeHelpViewer); break;
            case "Route Planning": setMainScreen(routePlannerHelp); break;
//            case "Map Viewer": setMainScreen(mapHelpViewer); break;
//            case "Analytics": break; // todo
//            case "Map View": setMainScreen(mapAnalyticHelpViewer); break;
//            case "Graph View": setMainScreen(graphHelpViewer); break;
//            case "Data Viewer": break; // todo
//            case "Retailer": setMainScreen(retailerHelpViewer); break;
//            case "Trip": setMainScreen(tripHelpViewer); break;
//            case "Wifi": setMainScreen(wifiHelpViewer); break;
//            case "Data Entry": setMainScreen(dataEntryHelpViewer); break;
            default: System.out.println("ERROR: No such set handle exists");
        }
        // Update window tracker
        currentWindow = newScreen;
    }

    /**
     * Removes the current window inside centerAnchorPane
     * @param window The window to be removed
     */
    private void removeMainScreen(Node window) {
        helpCenterAnchorPane.getChildren().remove(window);
    }

    /**
     * Adds the current window inside centerAnchorPane
     * @param window The window to be added
     */
    private void setMainScreen(Node window) {
        helpCenterAnchorPane.getChildren().add(window);
    }


    public void display(String newWindow) {

    }
}
