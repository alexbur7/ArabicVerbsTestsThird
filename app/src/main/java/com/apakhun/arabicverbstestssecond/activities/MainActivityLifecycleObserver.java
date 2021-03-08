package com.apakhun.arabicverbstestssecond.activities;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class MainActivityLifecycleObserver implements LifecycleObserver {

    private MainActivity mainActivity;

    MainActivityLifecycleObserver(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {

    }
}
