package com.brockster.searchflights;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FlightAdapter adapter;
    private SampleModel sampleModel;
    private ArrayList<Flights> originalFLights;
    private ArrayList<Flights> filteredFlights;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSortDialog();
            }
        });

        getFlights();
    }

    private void getFlights(){
                FLightUtility.showProgressBar(MainActivity.this, "Loading....");
                HttpService.getInstance().getFlights(new Callback<SampleModel>() {
                    @Override
                    public void success(SampleModel model, Response response) {
                        if (model!=null) {
                            sampleModel = model;
                            originalFLights = sampleModel.getFlights();
                            filteredFlights = new ArrayList<Flights>();
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            adapter = new FlightAdapter(MainActivity.this,sampleModel);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(MainActivity.this,"Flights could not be fetched",Toast.LENGTH_LONG).show();
                        }
                        FLightUtility.hideProgressBar();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        FLightUtility.hideProgressBar();
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showSortDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        final View dialogView = inflater.inflate(R.layout.flight_sort_dialog,null);
        ListView sortListview = (ListView)dialogView.findViewById(R.id.sort_listview);
        List<String> sortList = new ArrayList<>();
        sortList.add("Min fare");
        sortList.add("Take off time");
        sortList.add("Landing time");
        sortList.add("Clear filter");
        ArrayAdapter<String> sortAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_single_choice,android.R.id.text1,sortList);
        sortListview.setAdapter(sortAdapter);
        sortListview.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        alertDialog.getWindow().setLayout(900, LinearLayout.LayoutParams.WRAP_CONTENT);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        alertDialog.show();

        sortListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sortFlight(position);
                alertDialog.cancel();
            }
        });


    }

    private void sortFlight(int position) {
        filteredFlights.clear();
        filteredFlights.addAll(originalFLights);
        switch (position){
            case 0:
                Collections.sort(filteredFlights,new MinFareComparator());
                break;
            case 1:
                Collections.sort(filteredFlights,new TakeOffTimeComparator());
                break;
            case 2:
                Collections.sort(filteredFlights,new LandingTimeComparator());
                break;
            case 3:
                break;
            default:
                break;
        }
        adapter.setFlights(filteredFlights);
    }

    public class MinFareComparator implements Comparator<Flights>{

        @Override
        public int compare(Flights o1, Flights o2) {
            int minOfO1 = Integer.MAX_VALUE;
            for (Fares fares : o1.getFares()){
                if (fares.getFare() < minOfO1)
                    minOfO1 = fares.getFare();
            }

            int minOfO2 = Integer.MAX_VALUE;
            for (Fares fares : o2.getFares()){
                if (fares.getFare() < minOfO2)
                    minOfO2 = fares.getFare();
            }
            return minOfO1 - minOfO2;

        }
    }

    public class TakeOffTimeComparator implements Comparator<Flights>{

        @Override
        public int compare(Flights o1, Flights o2) {
            int  diff =  (int) (o1.getDepartureTime() - o2.getDepartureTime());
            return diff;
        }
    }

    public class LandingTimeComparator implements Comparator<Flights>{

        @Override
        public int compare(Flights o1, Flights o2) {
            int  diff =  (int) (o1.getArrivalTime() - o2.getArrivalTime());
            return diff;
        }
    }
}
