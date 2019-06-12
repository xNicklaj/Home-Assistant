package application.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class AppController {
	@FXML
	private void appClose(MouseEvent event)
	{
		ControllerList.switcher.switchToHomeScreen();
	}
}
