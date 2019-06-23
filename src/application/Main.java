package application;
	
import java.util.List;

import it.edu.majoranapa.SystemInfo;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.event.EventHandler;

public class Main extends Application{
	@Override
	public void start(Stage primaryStage) {
		
		Parameters params = getParameters();
		List<String> list = params.getRaw();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	          public void handle(WindowEvent e) {
	              System.exit(0);
	          }
	    });	
		SystemInfo info = new SystemInfo();
		info.start();
		try {
			SceneSwitcher switcher = new SceneSwitcher(primaryStage);
			switcher.init(list);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
