package com.apakhun.arabicverbstestssecond.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.apakhun.arabicverbstestssecond.controllers.Cache;
import com.apakhun.arabicverbstestssecond.model.Test;
import com.apakhun.arabicverbstestssecond.model.Tests;
import com.apakhun.arabicverbstestssecond.model.Verb;
import com.apakhun.arabicverbstestssecond.model.TimeVerb;
import com.google.gson.reflect.TypeToken;

public class TestViewModel extends ViewModel {
    private MutableLiveData<Test> test = new MutableLiveData<>();
    private Verb verb;
    private TimeVerb.Time time;
    private Tests.Progress progress;
    private Cache<Test> cache = new Cache<>(new TypeToken<Test>(){}.getType());
    private String key;

    public void putSelectedVerb(Verb verb, TimeVerb.Time time, Tests.Progress progress) {
        this.verb = verb;
        this.time = time;
        this.progress = progress;
        key = verb.getVerbText() + time.toString();
    }

    public void requestTest() {
        cache.loadDataAsync(key, new Cache.ILoadCallback<Test>() {
            @Override
            public void onLoaded(Test data) {
                if (data == null) {
                    progress.putSucceed(verb, time, 0);
                    progress.putFailed(verb, time, 0);
                    data = Test.generateTest(verb, time);
                }
                test.postValue(data);
            }
        });
    }

    public void restartTest() {
        progress.putSucceed(verb, time, 0);
        progress.putFailed(verb, time, 0);
        test.postValue(Test.generateTest(verb, time));
    }

    public void saveTest() {
        cache.saveDataAsync(key, test.getValue());
    }

    public LiveData<Test> getTest() {
        return test;
    }
}
