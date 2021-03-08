package com.apakhun.arabicverbstestssecond;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

public class App extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return App.context;
    }
    public static Resources getRes() {
        return App.context.getResources();
    }
}
