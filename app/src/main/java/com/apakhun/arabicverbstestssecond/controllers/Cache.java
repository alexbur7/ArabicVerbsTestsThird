package com.apakhun.arabicverbstestssecond.controllers;

import android.content.SharedPreferences;

import com.apakhun.arabicverbstestssecond.App;
import com.google.gson.Gson;

import java.lang.reflect.Type;

import static android.content.Context.MODE_PRIVATE;

public class Cache<T> {

    private Type type;
    private Gson gson = new Gson();
    private final String FILE_NAME;
    private SharedPreferences sPref;
    public interface ILoadCallback<T> {
        void onLoaded(T data);
    }

    public Cache(Type type) {
        this.type = type;
        FILE_NAME = type.toString();
        sPref = App.getAppContext().getSharedPreferences(FILE_NAME, MODE_PRIVATE);
    }

    public T loadData(String key) {
        String data = sPref.getString(key, "");
        if (data.equals(""))
            return null;
        return gson.fromJson(data, type);
    }

    public void loadDataAsync(String key, ILoadCallback<T> callback) {
        new Thread(() -> {
            T data = loadData(key);
            callback.onLoaded(data);
        }).start();
    }

    public void saveDataAsync(String key, T data) {
        new Thread(() -> {
            String json = gson.toJson(data, type);
            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(key, json);
            ed.apply();
        }).start();
    }
}
