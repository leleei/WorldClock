package com.lv.http.worldclock.http;


import com.lv.http.worldclock.constant.UrlConstant;
import com.lv.http.worldclock.http.HttpApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitUtil {
    private static HttpApi httpApi;

    public static HttpApi createHttpApiInstance(){
        if(httpApi==null){
            if(httpApi==null){
                httpApi=new Retrofit
                        .Builder()
                        .baseUrl(UrlConstant.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(HttpApi.class);
            }
        }
        return httpApi;
    }
}
