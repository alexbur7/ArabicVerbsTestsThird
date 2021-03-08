package com.apakhun.arabicverbstestssecond.activities;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;

import com.apakhun.arabicverbstestssecond.R;
import com.apakhun.arabicverbstestssecond.fragments.MainFragment;
import com.apakhun.arabicverbstestssecond.fragments.ParentFragment;

public class MainActivity extends AppCompatActivity {

    Lifecycle lifecycle = new LifecycleRegistry(this);
    ParentFragment currentFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        lifecycle.addObserver(new MainActivityLifecycleObserver(this));


        currentFragment = ParentFragment.getCurrentOpenedFragment(this);
        if (currentFragment == null) {
            currentFragment  = ParentFragment.getInstance(this, MainFragment.class);
            currentFragment.open();
        }
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return lifecycle;
    }
}
