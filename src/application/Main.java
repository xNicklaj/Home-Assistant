package application;

import com.gluonhq.charm.glisten.application.MobileApplication;

import application.controller.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Main extends Application implements EventHandler<ActionEvent>{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			SceneSwitcher switcher = new SceneSwitcher(primaryStage);
			switcher.switchToMain();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void handle(ActionEvent event)
	{
		
	}
}
