package com.brockster.searchflights.model;

import java.util.HashMap;

/**
 * Created by user on 01-04-2017.
 */

public class Appendix {
    private HashMap<String,String> providers;

    private HashMap<String,String> airports;

    private HashMap<String,String> airlines;

    public HashMap<String, String> getProviders() {
        return providers;
    }

    public void setProviders(HashMap<String, String> providers) {
        this.providers = providers;
    }

    public HashMap<String, String> getAirports() {
        return airports;
    }

    public void setAirports(HashMap<String, String> airports) {
        this.airports = airports;
    }

    public HashMap<String, String> getAirlines() {
        return airlines;
    }

    public void setAirlines(HashMap<String, String> airlines) {
        this.airlines = airlines;
    }
}

