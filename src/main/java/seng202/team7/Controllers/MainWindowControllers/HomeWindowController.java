package seng202.team7.Controllers.MainWindowControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for all home window interactions.
 * @author Connor McEwan-McDowall
 */
public class HomeWindowController implements Initializable{

    @FXML public ImageView backgroundImage;
    @FXML public AnchorPane mainHomeAnchorPane;
    @FXML public Text headingText;

    /**
     * Runs as the program initially starts. It binds the background image to the pane so it
     * can be resized and adds a listener to allow for a dynamic title.
     * @param location Unused parameter (There to fit signature)
     * @param resources Unused parameter (There to fit signature)
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Makes it so the image re-sizes
        //backgroundImage.fitWidthProperty().bind(mainHomeAnchorPane.widthProperty());
        mainHomeAnchorPane.widthProperty().addListener((v, oldValue, newValue) -> resize());
        mainHomeAnchorPane.heightProperty().addListener((v, oldValue, newValue) -> resize());
    }

    /**
     * This function makes sure that the image always resize's so that the full mainHomeAnchorPane
     * is covered.
     */
    private void resize() {
        if (mainHomeAnchorPane.getWidth() < mainHomeAnchorPane.getHeight()) {
            backgroundImage.setFitHeight(mainHomeAnchorPane.getHeight());
        } else {
            backgroundImage.setFitHeight((mainHomeAnchorPane.getWidth()));
        }
        setFontSize();
    }

    /**
     * Sets the title of the home window's font to an appropriate size scaling with the mainHomeAnchorPane
     */
    private void setFontSize(){
        double size = mainHomeAnchorPane.getWidth() / 22;
        if (size < 20) {
            size = 20;
        }
        headingText.setFont(Font.font("Tahoma", size));
    }
}
