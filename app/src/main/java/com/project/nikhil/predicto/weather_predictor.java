package com.project.nikhil.predicto;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static com.project.nikhil.predicto.weather_utils.extractBankAccount;
import static com.project.nikhil.predicto.weather_utils.makeHttpRequest;
import static com.project.nikhil.predicto.weather_utils.response_data;

public class weather_predictor extends AppCompatActivity {
    ArrayList<wheather_data> data;
    cordinates cor;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_predictor);
        Intent i=getIntent();
        cor=(cordinates)i.getSerializableExtra("latlong");

        AccountAsync task=new AccountAsync();
        task.execute();

    }

    public class AccountAsync extends AsyncTask<URL,Void,ArrayList<wheather_data>> {
        String jsonResponse="";
        String req_url="http://api.openweathermap.org/data/2.5/forecast/daily?lat="+cor.getLat().toString()+"&lon="+cor.getLon().toString()+"&cnt=15&APPID=b1b1e96f4226e2f4034538ec306a8231";

        @Override
        protected ArrayList<wheather_data> doInBackground(URL... params) {

            data=response_data(req_url);

            return data;
        }

        @Override
        protected void onPostExecute(ArrayList<wheather_data> account) {

            if(data==null){

                return;            }

            set(data);
        }

    }

    private void set(ArrayList<wheather_data> data) {

        final list_adapter adapter=new list_adapter(weather_predictor.this,data);
        listView=(ListView)findViewById(R.id.wheather_list);
        listView.setAdapter(adapter);

    }

}
