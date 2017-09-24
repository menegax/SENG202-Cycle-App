package seng202.team7.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeWindowController implements Initializable{

    public ImageView backgroundImage;
    public AnchorPane mainHomeAnchorPane;
    public Text headingText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Makes it so the image re-sizes
        backgroundImage.fitWidthProperty().bind(mainHomeAnchorPane.widthProperty());
        mainHomeAnchorPane.widthProperty().addListener((v, oldValue, newValue) -> setFontSize());
    }

    private void setFontSize(){
        double size = mainHomeAnchorPane.getWidth() / 25;
        headingText.setFont(Font.font("Tahoma", size));
    }
}
