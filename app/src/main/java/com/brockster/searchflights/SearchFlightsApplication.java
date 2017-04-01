package com.brockster.searchflights;

import android.app.Application;

import com.brockster.searchflights.network.HttpService;
import com.brockster.searchflights.utils.FLightUtility;

/**
 * Created by user on 01-04-2017.
 */

public class SearchFlightsApplication extends Application {

    private static final String TAG = SearchFlightsApplication.class.getSimpleName();
    private static SearchFlightsApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        HttpService.getInstance().setup(getApplicationContext());
        FLightUtility.setContext(getApplicationContext());
    }
}
