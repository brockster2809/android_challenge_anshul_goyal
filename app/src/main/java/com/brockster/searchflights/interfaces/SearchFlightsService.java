
package com.brockster.searchflights.interfaces;


import com.brockster.searchflights.model.SampleModel;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Anshul
 */
public interface SearchFlightsService {

    @GET("/v0/b/gcm-test-6ab64.appspot.com/o/ixigoandroidchallenge%2Fsample_flight_api_response.json")
    void getFlights(@Query("alt") String alt, @Query("token") String token, Callback<SampleModel> callback);
}

