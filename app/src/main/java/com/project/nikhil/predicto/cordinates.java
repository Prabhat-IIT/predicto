package com.project.nikhil.predicto;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by nikhil on 16/8/17.
 */

public class cordinates implements Serializable {
    Double lat,lon;

    public cordinates(Double lat, Double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}
