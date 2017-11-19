package sample;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;

import java.net.URL;
import java.util.*;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import netscape.javascript.JSObject;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;


public class FXMLController implements Initializable, MapComponentInitializedListener {

    private static final Logger LOG = Logger.getLogger(FXMLController.class.getName());

    @FXML
    private Button button;

    @FXML
    private GoogleMapView googleMapView;

    private GoogleMap map;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        googleMapView.addMapInializedListener(this);
    }

    @Override
    public void mapInitialized() {

        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(12.58833377,37.4415246))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(10);

        map = googleMapView.createMap(mapOptions);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        DriverLocationClient client = retrofit.create(DriverLocationClient.class);
        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {

            while(true) {

            Call<List<DriverLocation>> call = client.getDriverLocations();
            call.enqueue(new Callback<List<DriverLocation>>() {
                @Override
                public void onResponse(Call<List<DriverLocation>> call, Response<List<DriverLocation>> response) {


                    Platform.runLater(
                            () -> {

                                map.clearMarkers();
                                int currentZoom = map.getZoom();
                                map.setZoom( currentZoom - 1 );
                                map.setZoom( currentZoom );

                    List<DriverLocation> driverLocationList = response.body();
                    List<Map<Marker,DriverLocation>> markerLocations = new ArrayList<>();
                                //LatLongBounds latLongBounds = new LatLongBounds();

                    for (DriverLocation driverLocation : driverLocationList) {
                                    LatLong latLong = new LatLong(Double.valueOf(driverLocation.getLatitude()), Double.valueOf(driverLocation.getLongitude()));
                                    MarkerOptions markerOptions = new MarkerOptions();
                                    markerOptions.position(latLong);
                                    Marker marker = new Marker(markerOptions);
                                   // latLongBounds.extend(latLong);
                                  //  map.fitBounds(latLongBounds);
                                    map.addMarker(marker);
                        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                        infoWindowOptions.content("Driver: "+driverLocation.getDriverName()+"<br> Car Plate Number: "+driverLocation.getCarPlateNumber()+"<br> Phone: "+driverLocation.getDriverPhoneNumber());
                        InfoWindow window = new InfoWindow(infoWindowOptions);
                        window.open(map, marker);

                                }

                    }
);
                }

                @Override
                public void onFailure(Call<List<DriverLocation>> call, Throwable throwable) {
                    LOG.severe("Something went wrong");
                }
            });
            Thread.sleep(5000);
        }

            }

    };
    Thread th = new Thread(task);
    th.setDaemon(true);
    th.start();
/*
        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("Driver: "+driverLocation.getDriverName()+"<br> Car Plate Number: "+driverLocation.getCarPlateNumber()+"<br> Phone: "+driverLocation.getDriverPhoneNumber());
        InfoWindow infoWindow = new InfoWindow(infoWindowOptions);
        map.addUIEventHandler(marker, UIEventType.click, (JSObject jsObject) -> {
            infoWindow.open(infoWindowOptions);
        }); */
  /*
        //Add markers to the map
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(joeSmithLocation);

        MarkerOptions markerOptions2 = new MarkerOptions();
        markerOptions2.position(joshAndersonLocation);

        MarkerOptions markerOptions3 = new MarkerOptions();
        markerOptions3.position(bobUnderwoodLocation);

        MarkerOptions markerOptions4 = new MarkerOptions();
        markerOptions4.position(tomChoiceLocation);

        MarkerOptions markerOptions5 = new MarkerOptions();
        markerOptions5.position(fredWilkieLocation);

        Marker joeSmithMarker = new Marker(markerOptions1);
        Marker joshAndersonMarker = new Marker(markerOptions2);
        Marker bobUnderwoodMarker = new Marker(markerOptions3);
        Marker tomChoiceMarker= new Marker(markerOptions4);
        Marker fredWilkieMarker = new Marker(markerOptions5);

        map.addMarker( joeSmithMarker );
        map.addMarker( joshAndersonMarker );
        map.addMarker( bobUnderwoodMarker );
        map.addMarker( tomChoiceMarker );
        map.addMarker( fredWilkieMarker );

        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("<h2>Fred Wilkie</h2>"
                + "Current Location: Safeway<br>"
                + "ETA: 45 minutes" );

        InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
        fredWilkeInfoWindow.open(map, fredWilkieMarker); */
    }
}
