package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import it.edu.majoranapa.io.*;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			PathFinder finder = new PathFinder();
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			//BorderPane root = new BorderPane();
			Scene scene = new Scene(root,1600,900);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.getIcons().add(new Image(finder.getResourcePath("Icons/Bluetooth.png")));
			primaryStage.setTitle("Home Assistant");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
