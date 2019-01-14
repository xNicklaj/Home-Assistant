package application.controller;

import it.edu.majoranapa.io.DateAndTime;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class MainController {

    @FXML
    private AnchorPane rootObject;

    @FXML
    private AnchorPane BlurringPanel;

    @FXML
    private AnchorPane DimmingPanel;

    @FXML
    private AnchorPane AppComponent_ListsApp;

    @FXML
    private ImageView AppComponent_ListsAppIcon;

    @FXML
    private Text AppComponent_ListsAppText;

    @FXML
    private AnchorPane AppComponent_ClockApp;

    @FXML
    private ImageView AppComponent_ClockAppIcon;

    @FXML
    private Text AppComponent_ClockAppText;

    @FXML
    private AnchorPane UtilityBar;

    @FXML
    private Text SystemText_FormattedDateComponent;

    @FXML
    private ImageView SystemIcon_BluetoothIcon;

    private DateAndTime updater = new DateAndTime();
    
    
    public void setDateFormatted()
    {
    	SystemText_FormattedDateComponent.setText(updater.DateFormatter());
    }
}
