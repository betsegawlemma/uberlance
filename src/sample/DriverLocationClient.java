package sample;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface DriverLocationClient {

    @GET("/locations")
    Call<List<DriverLocation>> getDriverLocations();
}
