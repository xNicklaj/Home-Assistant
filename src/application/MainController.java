package application;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import it.edu.majoranapa.io.*;

public class MainController {

    @FXML
    private AnchorPane rootObject;

    @FXML
    private AnchorPane AppComponent;

    @FXML
    private Text AppComponent_AppName;

    @FXML
    private ImageView AppComponent_AppIcon;

    @FXML
    private ImageView SystemComponent_BluetoothIcon;
    
    public void setBackgroundImage()
    {
    	rootObject = new AnchorPane();
    	PathFinder finder = new PathFinder();
    	rootObject.setStyle("-fx-background-image: " + finder.getResourcePath("BackgroundImage") + ";");
    }

}