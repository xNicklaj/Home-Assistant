package application;

import application.controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Main extends Application implements EventHandler<ActionEvent>{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root =  FXMLLoader.load(getClass().getResource("Main.fxml"));
			MainController controller = new MainController();
			root.setId("anchor");
			Scene scene = new Scene(root, 800,480);
			scene.getStylesheets().add(getClass().getResource("css/Main.css").toExternalForm());
			primaryStage.setResizable(false);
			//primaryStage.setFullScreen(true);
			primaryStage.setTitle("Home Assistant");
			primaryStage.setScene(scene);
			it.edu.majoranapa.Main.console();
		//	controller.setBackgroundImage();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void handle(ActionEvent event)
	{
		
	}
}
