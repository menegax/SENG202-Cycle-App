package seng202.team7;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainWindow extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    GridPane mainPane = new GridPane();

    @Override
    public void start(Stage primaryStage) {
        // Creates the main grid pane and add the navigation pane to it
        TreeView<String> navigationTree = createNavigationTree();
        StackPane navigationPane = new StackPane();
        navigationPane.getChildren().add(navigationTree);
        mainPane.add(navigationPane, 0, 0);

        // Creates the main scene pane
        Scene mainScene = new Scene(mainPane, 500, 500);

        // Main stage
        primaryStage.setTitle("Meraki Bikes");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }


    // Creates branches for a tree view
    private TreeItem<String> makeBranch(String title, TreeItem<String> parent) {
        TreeItem<String> item = new TreeItem<>(title);
        //item.setExpanded(true);
        parent.getChildren().add(item);
        return item;
    }

    // Creates the navigation bar
    private TreeView<String> createNavigationTree() {
        TreeItem<String> root, home, routePlanning, analytics, dataViewer, dataEntry, help;

        // Root
        root = new TreeItem();
        root.setExpanded(true);

        // Home branch
        home = makeBranch("Home", root);

        // Route planning branch
        routePlanning = makeBranch("Route Planning", root);

        // Analytics branch
        analytics = makeBranch("Analytics", root);

        // Data Viewer branch
        dataViewer = makeBranch("Raw Data Viewer", root);
        makeBranch("Retailer", dataViewer);
        makeBranch("Station", dataViewer);
        makeBranch("Trip", dataViewer);
        makeBranch("Wifi", dataViewer);

        // Data Entry branch
        dataEntry = makeBranch("Data Entry", root);
        makeBranch("Retailer", dataEntry);
        makeBranch("Station", dataEntry);
        makeBranch("Trip", dataEntry);
        makeBranch("Wifi", dataEntry);

        // Help branch
        help = makeBranch("Help", root);

        // Create tree
        TreeView<String> navigationTree = new TreeView<>(root);
        navigationTree.setShowRoot(false);

        return navigationTree;
    }
}
