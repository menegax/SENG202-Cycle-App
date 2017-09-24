package seng202.team7;

import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeWindowController implements Initializable{

    public ImageView backgroundImage;
    public AnchorPane mainHomeAnchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Makes it so the image re-sizes
        backgroundImage.fitWidthProperty().bind(mainHomeAnchorPane.widthProperty());
        //backgroundImage.fitHeightProperty().bind(mainHomeAnchorPane.heightProperty());
    }

    
}
