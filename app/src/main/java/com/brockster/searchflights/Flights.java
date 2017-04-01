package com.brockster.searchflights;

import android.support.v7.widget.RecyclerView;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by user on 01-04-2017.
 */

class Flights implements Serializable{
    private ArrayList<Fares> fares;
    private String destinationCode;
    private String airlineCode;
    private long arrivalTime;
    private long departureTime;
    private String originCode;
    @SerializedName("class")
    private String airlineClass;

    public ArrayList<Fares> getFares() {
        return fares;
    }

    public void setFares(ArrayList<Fares> fares) {
        this.fares = fares;
    }

    public String getDestinationCode() {
        return destinationCode;
    }

    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public long getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(long departureTime) {
        this.departureTime = departureTime;
    }

    public String getOriginCode() {
        return originCode;
    }

    public void setOriginCode(String originCode) {
        this.originCode = originCode;
    }

    public String getAirlineClass() {
        return airlineClass;
    }

    public void setAirlineClass(String airlineClass) {
        this.airlineClass = airlineClass;
    }
}
