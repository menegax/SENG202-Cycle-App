package seng202.team7.Controllers.MainWindowControllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Controller for the loading popup window
 * @author Aidan Smith asm142
 * Last updated 1/10/17
 */
public class LoadingPopupController {

    @FXML private AnchorPane mainContainer;

    /**
     * Called by the close button to close the loading window
     */
    public void close() {
        Stage stage = (Stage) mainContainer.getScene().getWindow();
        stage.close();
    }
}
