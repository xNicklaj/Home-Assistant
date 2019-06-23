package application;

import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class MainController {

    @FXML
    private AnchorPane notificationBar;
    
    @FXML
    private Label systemTime;

    @FXML
    private SubScene app_viewport;

    public SubScene getApp_viewport() {
		return app_viewport;
	}

	public void setApp_viewport(SubScene app_viewport) {
		this.app_viewport = app_viewport;
	}
	
	public void setSystemTime(String time)
	{
		systemTime.setText(time);
	}
}
