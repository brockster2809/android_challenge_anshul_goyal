package com.brockster.searchflights;

import android.app.Application;
import android.os.Handler;
import android.support.v7.widget.AppCompatTextView;

import java.util.Timer;

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
