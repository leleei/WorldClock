package com.lv.http.worldclock.gloabl;

import android.app.Application;
import android.content.Context;


public class WorldClockApp extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
