package com.example.weatherappv2;

public class Datalist {
    String temperature_mean, precipitation, pressure, month;

    public Datalist(){}

    public Datalist(String month, String temperature_mean, String precipitation, String pressure) {
        this.temperature_mean = temperature_mean;
        this.precipitation = precipitation;
        this.pressure = pressure;
        this.month = month;
    }

    public String getTemperature_mean() {
        return temperature_mean;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setTemperature_mean(String temperature_mean) {
        this.temperature_mean = temperature_mean;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }
}
