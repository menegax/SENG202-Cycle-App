package seng202.team7;

import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import sun.reflect.generics.tree.Tree;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable{

    public BorderPane mainBorderPane;
    public TreeView<String> navigationTree;

    public TreeView<String> getNavigationTree() {
        return navigationTree;
    }

    /** Run right before program starts. Can be used to load things in if needed
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateNavigationBar();
    }

    private void populateNavigationBar() {
        // Tree root
        TreeItem<String> root = new TreeItem<>();
        root.setExpanded(true);

        // Home branch
        TreeItem<String> homeBranch = makeBranch("Home", root);

        // Route Planner branch
        TreeItem<String> routePlanningBranch = makeBranch("Route Planning", root);

        // Analytics branch
        TreeItem<String> analyticsBranch = makeBranch("Analytics", root);

        // Raw Data Viewer branch
        TreeItem<String> DataViewerBranch = makeBranch("Data Viewer", root);
        makeBranch("Retailer", DataViewerBranch);
        makeBranch("Station", DataViewerBranch);
        makeBranch("Trip", DataViewerBranch);
        makeBranch("Wifi", DataViewerBranch);

        // Data Entry branch
        TreeItem<String> dataEntryBranch = makeBranch("Data Entry", root);
        makeBranch("Retailer", dataEntryBranch);
        makeBranch("Station", dataEntryBranch);
        makeBranch("Trip", dataEntryBranch);
        makeBranch("Wifi", dataEntryBranch);

        // Help branch
        TreeItem<String> helpBranch = makeBranch("Help", root);


        navigationTree.setRoot(root);
        navigationTree.setShowRoot(false);
    }

    /* Creates branches
     */
    private TreeItem<String> makeBranch(String title, TreeItem<String> parent) {
        TreeItem<String> item = new TreeItem<>(title);
        parent.getChildren().add(item);
        return item;
    }
}
