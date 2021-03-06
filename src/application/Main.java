package application;
	
import java.util.List;

import org.gitproject.homeassistant.SystemInfo;
import org.gitproject.homeassistant.Userdata;
import org.gitproject.homeassistant.lists.Bill;
import org.gitproject.homeassistant.lists.BillType;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Font;

public class Main extends Application{
	@Override
	public void start(Stage primaryStage) {
		Font.loadFont(getClass().getResourceAsStream("../../resources/fonts/Roboto-Medium.ttf"), 12);
		Parameters params = getParameters();
		List<String> list = params.getRaw();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	          public void handle(WindowEvent e) {
	              System.exit(0);
	          }
	    });	
		SystemInfo info = new SystemInfo();
		info.start();
		Userdata.load();
		
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
