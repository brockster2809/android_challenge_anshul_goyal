package com.brockster.searchflights;

import java.util.ArrayList;

/**
 * Created by user on 01-04-2017.
 */

public class SampleModel {
    private ArrayList<Flights> flights;
    private Appendix appendix;

    public ArrayList<Flights> getFlights() {
        return flights;
    }

    public void setFlights(ArrayList<Flights> flights) {
        this.flights = flights;
    }

    public Appendix getAppendix() {
        return appendix;
    }

    public void setAppendix(Appendix appendix) {
        this.appendix = appendix;
    }
}
