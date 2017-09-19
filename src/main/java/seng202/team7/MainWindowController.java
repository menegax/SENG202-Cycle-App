package seng202.team7;

import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable{

    public BorderPane mainBorderPane;
    public TreeView<String> navigationTree;
    private TripAnalyticWindow analyticsViewer;
    private RetailerDataViewer retailerViewer;
    private WifiDataViewer wifiViewer;
    private TripDataViewer tripViewer;
    private DataEntryWindow dataEntryViewer;

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
        //Temporary home panel
        AnchorPane homeViewer = new AnchorPane();
        TextField homeText = new TextField();
        homeViewer.getChildren().add(homeText);
        mainBorderPane.setCenter(homeViewer);

        // Initialize different custom panels (Saves loading panel again every time panel is changed)
        analyticsViewer = new TripAnalyticWindow();
        retailerViewer = new RetailerDataViewer();
        wifiViewer = new WifiDataViewer();
        tripViewer = new TripDataViewer();
        dataEntryViewer = new DataEntryWindow();

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
        makeBranch("Analytics", root);

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
                    if (newValue != null) {
                        String selectedString = newValue.getValue();
                        switch (selectedString) {
                            case "Home": setHomeViewer(); break;
                            case "Route Planning": setRoutePlanningViewer(); break;
                            case "Analytics": setAnalyticsViewer(); break;
                            case "Data Viewer": setDataViewerViewer(); break;
                            case "Retailer": setRetailerViewer(); break;
                            case "Trip": setTripViewer(); break;
                            case "Wifi": setWifiViewer(); break;
                            case "Data Entry": setDataEntry(); break;
                            default: System.out.println("ERROR: No such selection handle exists");
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

    /**
     * Set home viewer
     * todo
     */
    private void setHomeViewer() {
        // To implement...
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
        mainBorderPane.setCenter(analyticsViewer);
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
        mainBorderPane.setCenter(retailerViewer);
    }

    /**
     * Sets the center of the mainBorderPane to display the tripViewer
     * custom JavaFX object.
     */
    private void setTripViewer() {
        mainBorderPane.setCenter(tripViewer);
    }

    /**
     * Sets the center of the mainBorderPane to display the wifiViewer
     * custom JavaFX object.
     */
    private void setWifiViewer() {
        mainBorderPane.setCenter(wifiViewer);
    }

    /**
     * Sets the center of the mainBorderPane to display the dataEntryViewer
     * custom JavaFX object.
     */
    private void setDataEntry() {
        mainBorderPane.setCenter(dataEntryViewer);
    }
}
