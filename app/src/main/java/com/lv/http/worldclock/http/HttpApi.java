package com.lv.http.worldclock.http;

import com.lv.http.worldclock.http.model.TimeList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HttpApi {

    @GET("list-time-zone")
    Call<TimeList> getTimeListZone(@Query("key") String key,@Query("format") String format);

}
