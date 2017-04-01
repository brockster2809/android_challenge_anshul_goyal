package com.brockster.searchflights;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Created by user on 01-04-2017.
 */

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.FlightViewHolder> {

    Context context;
    Appendix appendix;
    ArrayList<Flights> flights;

    public FlightAdapter(Context context, SampleModel sampleModel) {
        this.context = context;
        this.appendix = sampleModel.getAppendix();
        this.flights = sampleModel.getFlights();
    }

    public void setFlights(ArrayList<Flights> flights) {
        this.flights = flights;
        notifyDataSetChanged();
    }

    @Override
    public FlightViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item, null);
        return new FlightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FlightViewHolder holder, int position) {
        Flights flight = flights.get(position);
        if (appendix.getAirlines().containsKey(flight.getAirlineCode())) {
            holder.rowTvAirline.setText(appendix.getAirlines().get(flight.getAirlineCode()));
        }
        if (appendix.getAirports().containsKey(flight.getOriginCode())) {
            holder.rowTvOrigin.setText(appendix.getAirports().get(flight.getOriginCode()));
        }
        if (appendix.getAirports().containsKey(flight.getDestinationCode())) {
            holder.rowTvDestination.setText(appendix.getAirports().get(flight.getDestinationCode()));
        }
        SimpleDateFormat df2 = new SimpleDateFormat("MMM dd, yyyy HH:mm");
        if (flight.getDepartureTime() != 0) {
            holder.rowTvDeparture.setText(df2.format(new Date(flight.getDepartureTime())));
        }
        if (flight.getArrivalTime() != 0) {
            holder.rowTvArrival.setText(df2.format(new Date(flight.getArrivalTime())));
        }
        LayoutInflater inflater = LayoutInflater.from(context);
        holder.rowLinlayFares.removeAllViews();
        for (Fares fares : flight.getFares()) {
            View fareView = (LinearLayout) inflater.inflate(R.layout.fare_row_item, null);
            holder.rowLinlayFares.addView(fareView);

            TextView rowTvProvider = (TextView) fareView.findViewById(R.id.row_tv_provider_name);
            TextView rowTvFare = (TextView) fareView.findViewById(R.id.row_tv_fare);
            if (appendix.getProviders().containsKey(fares.getProviderId())) {
                rowTvProvider.setText(appendix.getProviders().get(fares.getProviderId()));
            }
            if (fares.getFare() != 0) {
                rowTvFare.setText(String.valueOf(fares.getFare()));
            }
        }

    }

    @Override
    public int getItemCount() {
        return flights.size();
    }

    public static class FlightViewHolder extends RecyclerView.ViewHolder {

        TextView rowTvAirline, rowTvOrigin, rowTvDestination,
                rowTvDeparture, rowTvArrival;
        LinearLayout rowLinlayFares;

        public FlightViewHolder(View itemView) {
            super(itemView);
            rowTvAirline = (TextView) itemView.findViewById(R.id.row_tv_airline);
            rowTvOrigin = (TextView) itemView.findViewById(R.id.row_tv_origin);
            rowTvDestination = (TextView) itemView.findViewById(R.id.row_tv_destination);
            rowTvDeparture = (TextView) itemView.findViewById(R.id.row_tv_departure_time);
            rowTvArrival = (TextView) itemView.findViewById(R.id.row_tv_arrival_time);
            rowLinlayFares = (LinearLayout) itemView.findViewById(R.id.row_linlay_fares);
        }
    }

}
