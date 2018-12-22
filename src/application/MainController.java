package application;

import javafx.fxml.FXML;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class MainController {
	@FXML
    private ImageView BackgroundComponent = new ImageView();

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
    	BackgroundComponent.setEffect(new GaussianBlur(40));
    }
}
