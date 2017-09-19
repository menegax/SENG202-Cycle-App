package seng202.team7;

import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import sun.reflect.generics.tree.Tree;

import javax.xml.soap.Text;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable{

    private BorderPane mainBorderPane;
    private TreeView<String> navigationTree;
    private RetailerDataViewer retailerViewer;
    private WifiDataViewer wifiViewer;
    private TripDataViewer tripViewer;
    private DataEntryWindow dataEntryViewer;

    /** Run right before program starts. Can be used to load things in if needed
     * todo
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Temporary home panel
        AnchorPane homeViewer = new AnchorPane();
        TextField homeText = new TextField();
        homeViewer.getChildren().add(homeText);
        mainBorderPane.setCenter(homeViewer);

        // Initialize different custom panels (Saves loading panel again every time panel is changed)
        retailerViewer = new RetailerDataViewer();
        wifiViewer = new WifiDataViewer();
        tripViewer = new TripDataViewer();
        dataEntryViewer = new DataEntryWindow();

        populateNavigationBar();
    }

    /**
     * todo
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
     * todo
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
        // To implement
    }

    /**
     * todo
     */
    private void setDataViewerViewer() {
        // To implement
    }

    /**
     * Set retailer viewer
     * todo
     */
    private void setRetailerViewer() {
        mainBorderPane.setCenter(retailerViewer);
    }

    /**
     * todo
     */
    private void setTripViewer() {
        mainBorderPane.setCenter(tripViewer);
    }

    /**
     * todo
     */
    private void setWifiViewer() {
        mainBorderPane.setCenter(wifiViewer);
    }

    /**
     * todo
     */
    private void setDataEntry() {
        mainBorderPane.setCenter(dataEntryViewer);
    }
}
