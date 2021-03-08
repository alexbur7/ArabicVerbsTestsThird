package com.apakhun.arabicverbstestssecond.model.verbs;

import android.os.Parcel;

import com.apakhun.arabicverbstestssecond.R;
import com.apakhun.arabicverbstestssecond.model.TimeVerb;

public class Protect extends TimeVerb {
    public Protect() {
    }

    public Protect(Parcel parcel) {
        super(parcel);
    }

    @Override
    public int pictureResourceId() {
        return R.mipmap.protect;
    }

    @Override
    public String getDescription() {
        return getDescription(R.string.description_protect);
    }

    @Override
    protected String soundUnderFolderDir() {
        return "protect";
    }

    @Override
    protected int getPastArrayId() {
        return R.array.past_protect;
    }

    @Override
    protected int getPresentArrayId() {
        return R.array.present_protect;
    }

    @Override
    protected int getFutureArrayId() {
        return R.array.future_protect;
    }

    @Override
    public int getGroup(){
        return 3;
    }
}
