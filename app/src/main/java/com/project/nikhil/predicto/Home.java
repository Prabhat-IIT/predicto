package com.project.nikhil.predicto;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class Home extends AppCompatActivity{
    LocationManager locationManager;
    cordinates cordinates;
    String lat;
    String provider;
    protected String latitude,longitude;
    private RecyclerView grids;
    private static int currentPage = 0;
    private static final Integer[] rajasthan=
            {
            R.drawable.multicrop,
            R.drawable.index,
            R.drawable.palak,
            R.drawable.digital_india_logo,
            };
    private ArrayList<Integer> rajansthanArray = new ArrayList<Integer>();
    ArrayList<grid_object> test=new ArrayList<grid_object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();

        test.add(new grid_object(R.drawable.rajasthan_gov_logo,"Crop Predictor","फसल की भविष्यवाणी",MapsActivity.class));
        test.add(new grid_object(R.drawable.rajasthan_gov_logo,"Weather Predictor","मौसम की भविष्यवाणी",MapsActivity_weather.class));
        test.add(new grid_object(R.drawable.rajasthan_gov_logo,"Talk to Bheem ","भीम से बात करो",speech_recognizer.class,cordinates));
        test.add(new grid_object(R.drawable.rajasthan_gov_logo,"Market Price","मंडी दर",Crop_predictor.class));


        set_grid_item(test);


    }

    private void set_grid_item(ArrayList<grid_object> test) {
        grids=(RecyclerView)findViewById(R.id.home_grid);
        final GridLayoutManager gridLayoutManager;
        gridLayoutManager = new GridLayoutManager(Home.this,2);
        grids.setHasFixedSize(true);
        grids.setLayoutManager(gridLayoutManager);
        adapter_grid items=new adapter_grid(test,this);
        grids.setAdapter(items);


    }

    private void init() {
    for(int i=0;i<rajasthan.length;i++) {
        rajansthanArray.add(rajasthan[i]);
    }


        final ViewPager viewPager=(ViewPager)findViewById(R.id.view_pager);
    viewPager.setAdapter(new adapter_slider(Home.this,rajansthanArray));

       CircleIndicator indicator=(CircleIndicator)findViewById(R.id.circle_indicator);
        indicator.setViewPager(viewPager);
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == rajasthan.length) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2000, 2000);


    }


}
