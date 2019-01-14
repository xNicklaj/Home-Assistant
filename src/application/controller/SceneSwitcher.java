package application.controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SceneSwitcher {
	private Parent root;
	private Stage primaryStage;
	private Scene mainScene;
	
	public SceneSwitcher(Stage primaryStage)
	{
		this.primaryStage = primaryStage;
		//primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setResizable(false);
		//primaryStage.setFullScreen(true);
		primaryStage.setTitle("Home Assistant");
	}
	
	public int switchToMain()
	{
		Parent root = null;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../Main.fxml"));
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		root.setId("anchor");
		mainScene = new Scene(root, 800,480);
		mainScene.getStylesheets().add(getClass().getResource("../css/MainDarkTheme.css").toExternalForm());
		
		primaryStage.setScene(mainScene);
		primaryStage.show();
		return 0;
	}
}
