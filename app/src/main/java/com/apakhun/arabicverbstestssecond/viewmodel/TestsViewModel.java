package com.apakhun.arabicverbstestssecond.viewmodel;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.apakhun.arabicverbstestssecond.controllers.Cache;
import com.apakhun.arabicverbstestssecond.model.Tests;
import com.google.gson.reflect.TypeToken;

import java.util.Objects;

public class TestsViewModel extends ViewModel {

    private final MutableLiveData<Tests> tests = new MutableLiveData<>();
    private Cache<Tests.Progress> cache = new Cache<>(new TypeToken<Tests.Progress>(){}.getType());
    private final String KEY_PROGRESS_CACHE = "PROGRESS";

    public TestsViewModel() {
        cache.loadDataAsync(KEY_PROGRESS_CACHE, new Cache.ILoadCallback<Tests.Progress>() {
            @Override
            public void onLoaded(Tests.Progress progress) {
                if (progress == null)
                    progress = new Tests.Progress();
                tests.postValue(new Tests(progress));
            }
        });
    }

    public void notifyChanges() {
        tests.postValue(tests.getValue());
    }

    public void saveProgresses() {
        cache.saveDataAsync(KEY_PROGRESS_CACHE, Objects.requireNonNull(tests.getValue()).getProgress());
    }

    public LiveData<Tests> getTests() {
        return tests;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        saveProgresses();
    }
}
