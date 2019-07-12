package application.controller;

import de.jensd.fx.glyphs.octicons.OctIconView;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class MapsController extends AppController{

    @FXML
    private WebView mapsViewport;

    @FXML
    private OctIconView back_button;
    
    private WebEngine engine;
    
    public void initialize() {
    	this.setEngine("https://www.google.com/maps");
    }
    
    public void setEngine(String website)
    {
    	engine = mapsViewport.getEngine();
    	engine.setJavaScriptEnabled(true);
    	engine.load(website);
    }
}
