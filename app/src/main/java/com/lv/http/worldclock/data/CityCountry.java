package com.lv.http.worldclock.data;


public class CityCountry implements Comparable<CityCountry>{
    private String cityCountry;
    private String cityName;

    public CityCountry(String cityCountry, String cityName) {
        this.cityCountry = cityCountry;
        this.cityName = cityName;
    }

    public void setCityCountry(String cityCountry) {
        this.cityCountry = cityCountry;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCountry() {
        return cityCountry;
    }

    public String getCityName() {
        return cityName;
    }

    @Override
    public int compareTo(CityCountry another) {
        return cityCountry.compareTo(another.cityCountry);
    }

    @Override
    public String toString() {
        return "CityCountry{" +
                "cityCountry='" + cityCountry + '\'' +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
