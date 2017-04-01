
package com.brockster.searchflights;



import java.util.ArrayList;
import java.util.Map;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Anshul
 */
public interface SearchFlightsService {

    @GET("/v0/b/gcm-test-6ab64.appspot.com/o/ixigoandroidchallenge%2Fsample_flight_api_response.json")
    void getFlights(@Query("alt") String alt, @Query("token") String token, Callback<SampleModel> callback);
}

