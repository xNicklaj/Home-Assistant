package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import de.jensd.fx.glyphs.octicons.OctIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class MapsController extends AppController implements Initializable, MapComponentInitializedListener{

    @FXML
    private GoogleMapView mapView;

    @FXML
    private JFXButton backButton_BG;

    @FXML
    private OctIconView backButton_Icon;
    
    private GoogleMap map;

	@Override
	public void mapInitialized() {
		mapView.addMapInializedListener(this);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		File database = new File("../../../MySql Database/GeoLite2-City.mmdb");
		CityResponse response = null;
		try {
			DatabaseReader dbReader = new DatabaseReader.Builder(database).build();
			response = dbReader.city(InetAddress.getLocalHost());
		} catch (IOException | GeoIp2Exception e) {
			System.out.println(e.getClass().getName());
			e.printStackTrace();
		}
		LatLong currentLocation = new LatLong(response.getLocation().getLongitude(), response.getLocation().getLatitude());
		
		MapOptions mapOptions = new MapOptions();
		mapOptions.center(currentLocation)
        .overviewMapControl(false)
        .panControl(false)
        .rotateControl(false)
        .scaleControl(false)
        .streetViewControl(false)
        .zoomControl(false)
        .zoom(12);
		
		map = mapView.createMap(mapOptions);
		
	}

}
