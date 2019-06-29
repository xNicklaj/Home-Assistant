package application.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class HomeController{

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
    
    @FXML
    void appOpen_Timer(MouseEvent event) {
    	ControllerList.switcher.switchToTimerScreen();
    }
    
    @FXML
    void appOpen_Map(MouseEvent event) {
    	ControllerList.switcher.switchToMapsScreen();
    }
    
    @FXML
    void appOpen_List(MouseEvent event) {
    	
    }

}