package com.example.fitboi;

import android.app.Application;
import android.content.Context;
import com.example.fitboi.api.MyVolley;

public class Fitboi extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        Fitboi.context = getApplicationContext();
        init();
    }

    public static Context getAppContext() {
        return Fitboi.context;
    }

    private void init() {
        MyVolley.init(this);
    }
}
