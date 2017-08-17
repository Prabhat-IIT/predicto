package com.project.nikhil.predicto;

import java.io.Serializable;

/**
 * Created by nikhil on 16/8/17.
 */

public class wheather_data implements Serializable {
    String date;
    String temp;
    String rain;
    String description;

    public wheather_data(String date, String temp, String rain, String description) {
        this.date = date;
        this.temp = temp;
        this.rain = rain;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }
}
