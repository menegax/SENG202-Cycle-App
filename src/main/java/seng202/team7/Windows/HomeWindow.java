package seng202.team7.Windows;/**
 * Created by jam357 on 24/08/17.
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

/** Data entry window where users can manually enter their own data
 * @author Connor McEwan-McDowall
 */
public class HomeWindow extends AnchorPane {

    public HomeWindow(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/Home.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}