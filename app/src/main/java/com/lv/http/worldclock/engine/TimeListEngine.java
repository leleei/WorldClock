package com.lv.http.worldclock.engine;

import com.lv.http.worldclock.data.CityCountry;
import com.lv.http.worldclock.http.model.TimeList;

import java.util.ArrayList;
import java.util.List;


public class TimeListEngine {
    public static List<CityCountry> initCityList (TimeList timeList){
        List<TimeList.ZonesBean> zones = timeList.getZones();
        List<CityCountry> cityList = new ArrayList<>();
        for (TimeList.ZonesBean zonesBean : zones) {
            String[] zoonNames = zonesBean.getZoneName().split("/");
            String cityName = zoonNames[zoonNames.length - 1];
            String cityCountry = cityName + "," + zonesBean.getCountryName();
            cityList.add(new CityCountry(cityCountry,cityName));
        }
        return cityList;
    }
}
