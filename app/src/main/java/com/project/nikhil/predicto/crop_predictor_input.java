package com.project.nikhil.predicto;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import static com.project.nikhil.predicto.weather_utils.response_data;

public class crop_predictor_input extends AppCompatActivity {
    static ArrayList<wheather_data> data;
    cordinates cor;
    String ans="";
    static EditText p_r;
    static EditText ph_e;
    ArrayAdapter<CharSequence> adapter_soil,adapter_state,adapter_season;
    static Spinner spinner_soil;
    static Spinner spinner_state;
    static Spinner spinner_season;
    static String pr="";
    String ph="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_predictor_input);

        Intent i=getIntent();
        cor=(cordinates)i.getSerializableExtra("latlong");

        wheather task=new wheather();
        task.execute();


         p_r=(EditText)findViewById(R.id.p_r);
         ph_e=(EditText)findViewById(R.id.ph);
        Button submit=(Button) findViewById(R.id.submit);
        spinner_state=(Spinner)findViewById(R.id.state_spinner);
        spinner_season=(Spinner)findViewById(R.id.season_spinner);
        spinner_soil=(Spinner)findViewById(R.id.soil_spinner);

        adapter_soil = ArrayAdapter.createFromResource(this,R.array.soil_names,R.layout.support_simple_spinner_dropdown_item);
        adapter_state = ArrayAdapter.createFromResource(this,R.array.states_name,R.layout.support_simple_spinner_dropdown_item);
        adapter_season = ArrayAdapter.createFromResource(this,R.array.season_name,R.layout.support_simple_spinner_dropdown_item);

        spinner_soil.setAdapter(adapter_soil);
        spinner_state.setAdapter(adapter_state);
        spinner_season.setAdapter(adapter_season);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pr=p_r.getText().toString();
                ph=ph_e.getText().toString();


            }
        });

    }
    public class wheather extends AsyncTask<URL,Void,ArrayList<wheather_data>> {
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
    this.data=data;
    }

    public class prediction extends AsyncTask<URL,Void,String> {
        String jsonResponse="";
        String req_url="0.0.0.1/predict";


        @Override
        protected String doInBackground(URL... params) {

            URL url=create(req_url);
            String jsonresponse="";
            jsonresponse = makeHttpRequest(url);
            ans =extractBankAccount(jsonresponse);
            return ans;
        }

        @Override
        protected void onPostExecute(String s) {
        }
    }

    public static URL create(String str){
        URL url=null;
        try {
            url=new URL(str);
        } catch (MalformedURLException e) {
            return null;
        }
        return url;
    }
    public static String makeHttpRequest(URL url){
        String jsonResponse="";
        HttpURLConnection httpURLConnection=null;
        InputStream inputStream=null;
        JSONObject jsonObject=new JSONObject();

        try {
            jsonObject.put("rain",data.get(0).getRain().toString());
            jsonObject.put("temperature",data.get(0).getTemp().toString());
            jsonObject.put("season",spinner_season.getSelectedItem().toString());
            jsonObject.put("state",spinner_state.getSelectedItem().toString());
            jsonObject.put("soil",spinner_soil.getSelectedItem().toString());
            jsonObject.put("P_r",p_r.getText().toString());
            jsonObject.put("ph",ph_e.getText().toString());

            httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type","application/json;chatset=utf-8");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setReadTimeout(1000000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.connect();

            OutputStream wr=new BufferedOutputStream(httpURLConnection.getOutputStream());
            wr.write(jsonObject.toString().getBytes());
            wr.flush();


            inputStream=httpURLConnection.getInputStream();
            jsonResponse=readfromstream(inputStream);

        }catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return jsonResponse;

    }

    private static String readfromstream(InputStream inputStream) throws IOException {
        StringBuilder output=new StringBuilder();
        if(inputStream!=null){
            InputStreamReader inputstreamReader=new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader=new BufferedReader(inputstreamReader);
            String line=reader.readLine();
            while (line!=null){
                output.append(line);
                line=reader.readLine();
            }
        }

        return output.toString();
    }

    public static String extractBankAccount(String json) {
        ArrayList<wheather_data> arrayList= new ArrayList<wheather_data>();
        String crop="";
        try {
            JSONObject jsonObject = new JSONObject(json);
            crop=jsonObject.getString("crop");
            } catch (JSONException e) {
            e.printStackTrace();
        }
        return crop;
    }


}
