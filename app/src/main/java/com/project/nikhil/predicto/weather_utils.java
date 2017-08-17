package com.project.nikhil.predicto;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

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

/**
 * Created by nikhil on 16/8/17.
 */

public class weather_utils {

    public static ArrayList<wheather_data> response_data(String x){
        Log.e("problem","Internet");

        URL url=create(x);
        String jsonresponse="";
        jsonresponse = makeHttpRequest(url);

        return extractBankAccount(jsonresponse);

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

        try {
            httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(1000000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.connect();


            inputStream=httpURLConnection.getInputStream();
            jsonResponse=readfromstream(inputStream);

        }catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
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

    public static ArrayList<wheather_data> extractBankAccount(String json) {
        ArrayList<wheather_data> arrayList= new ArrayList<wheather_data>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object=jsonArray.getJSONObject(i);

               String rain="0";
                try {
                    rain=object.getString("rain");
                }catch (JSONException e1){
                    e1.printStackTrace();
                }
                String  date=object.getString("dt");
                Log.e("working","yup");

                JSONArray weather=object.getJSONArray("weather");
                JSONObject object1=weather.getJSONObject(0);
                String description=object1.getString("description");

                JSONObject object2=object.getJSONObject("temp");
               String temp =object2.getString("day");

               arrayList.add(new wheather_data(date,temp,rain,description));
                }} catch (JSONException e1) {

            e1.printStackTrace();
        }
        return arrayList;
    }

}


