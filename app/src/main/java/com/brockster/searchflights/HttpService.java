package com.brockster.searchflights;

import android.content.Context;
import android.util.LruCache;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.client.Response;

/**
 * Created by user on 21-03-2016.
 */
public class HttpService {
    private static HttpService instance = new HttpService();
    private Context context;
    private LruCache<String, Object> cache = new LruCache<>(4 * 1024 * 1024);
    private SearchFlightsService searchFlightsService;
    private RestAdapter.Builder builder;
    private RestAdapter adapter;


    private HttpService() {
    }

    public static HttpService getInstance() {
        return instance;
    }

    public void setup(Context context) {

        if(context == null)  return;
        
        this.context = context;

        OkHttpClient okHttpClient = new OkHttpClient();
        OkClient okClient = new OkClient(okHttpClient);

        Cache cache = new Cache(context.getCacheDir(), 1024);
        okHttpClient.setCache(cache);
        adapter  = new RestAdapter.Builder()
                .setLogLevel((AppConstants.IS_DEVELOPMENT) ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                .setEndpoint(AppConstants.HOST)
                .setClient(okClient)
                .build();
        searchFlightsService =  adapter.create(SearchFlightsService.class);
    }

    public void getFlights(Callback<SampleModel> callback){
        searchFlightsService.getFlights("media", "d8005801-7878-4f57-a769-ac24133326d6", callback);
    }
}
