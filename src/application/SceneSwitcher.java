package application;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import application.controller.ControllerList;
import application.controller.TimerController;

public class SceneSwitcher {
	private Parent root;
	private Stage primaryStage;
	private Scene mainScene;

	public SceneSwitcher(Stage primaryStage)
	{
		this.primaryStage = primaryStage;
		primaryStage.initStyle(StageStyle.UNDECORATED);
		//primaryStage.setFullScreen(true);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Home-Assistant");
	}

	public SceneSwitcher() {
		super();
	}

	public int init(List<String> args)
	{
		root = null;
		ControllerList.switcher = this;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));

		try {
			root = loader.load();
			ControllerList.mainController = (MainController) loader.getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		root.setId("anchor");


		mainScene = new Scene(root, 720, 480);
		mainScene.getStylesheets().add(getClass().getResource("css/Main.css").toExternalForm());
		primaryStage.sizeToScene();
		primaryStage.setScene(mainScene);


		int ret = 0;
		if(args.size() > 0)
			switch(args.get(0))
			{
			case "timer":
				ret = this.switchToTimerScreen();
				break;
			}
		else
			ret = this.switchToHomeScreen();
		primaryStage.show();
		return ret;
	}

	public int switchToHomeScreen(){
		if(Thread.currentThread().getStackTrace()[2].getMethodName() == "appClose")
			ControllerList.mainController.getApp_viewport().getScene().getStylesheets().remove(ControllerList.mainController.getApp_viewport().getScene().getStylesheets().size() -1);
		
		Parent test = null;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
		try {
			test = (Parent) loader.load();
		} catch (IOException e) {
			System.out.println(e.getClass().getName());
			e.printStackTrace();
		}
		ControllerList.mainController.getApp_viewport().setRoot(test);
		ControllerList.mainController.getApp_viewport().getScene().getStylesheets().add(getClass().getResource("css/Home.css").toExternalForm());
		return 0;
	}
	
	public int switchToTimerScreen()
	{
		Parent test = null;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Timer.fxml"));
		try {
			test = (Parent) loader.load();
		} catch (IOException e) {
			System.out.println(e.getClass().getName());
			e.printStackTrace();
		}
		
		ControllerList.timerController = (TimerController) loader.getController();
		ControllerList.mainController.getApp_viewport().setRoot(test);
		ControllerList.mainController.getApp_viewport().getScene().getStylesheets().add(getClass().getResource("css/Timer.css").toExternalForm());
		return 0;
	}

}
