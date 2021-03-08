package com.apakhun.arabicverbstestssecond.model.verbs;

import android.os.Parcel;

import com.apakhun.arabicverbstestssecond.R;
import com.apakhun.arabicverbstestssecond.model.TimeVerb;

public class Discover extends TimeVerb {

    public Discover() {
    }

    public Discover(Parcel parcel) {
        super(parcel);
    }

    @Override
    public int pictureResourceId() {
        return R.mipmap.discover;
    }

    @Override
    public String getDescription() {
        return getDescription(R.string.description_discover);
    }

    @Override
    protected String soundUnderFolderDir() {
        return "discover";
    }

    @Override
    protected int getPastArrayId() {
        return R.array.past_discover;
    }

    @Override
    protected int getPresentArrayId() {
        return R.array.present_discover;
    }

    @Override
    protected int getFutureArrayId() {
        return R.array.future_discover;
    }

    @Override
    public int getGroup(){
        return 1;
    }
}

