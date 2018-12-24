package application;

import it.edu.majoranapa.io.*;
import javafx.fxml.FXML;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class MainController {
	@FXML
    private ImageView BackgroundComponent;

    @FXML
    private AnchorPane AppComponent;

    @FXML
    private ImageView AppComponent_AppIcon;

    @FXML
    private Text AppComponent_AppName;

    @FXML
    private ImageView SystemComponent_BluetoothIcon;
    
    public void setBackgroundBlur()
    {
    	BackgroundComponent = new ImageView();
    	GaussianBlur blur = new GaussianBlur(60);
    	BackgroundComponent.setEffect(blur);
    }
}
