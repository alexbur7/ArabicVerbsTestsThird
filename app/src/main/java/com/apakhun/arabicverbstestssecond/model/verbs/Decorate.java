package com.apakhun.arabicverbstestssecond.model.verbs;

import android.os.Parcel;

import com.apakhun.arabicverbstestssecond.App;
import com.apakhun.arabicverbstestssecond.R;
import com.apakhun.arabicverbstestssecond.model.Verb;

public class Decorate extends Verb {
    public Decorate() {
    }

    public Decorate(Parcel parcel) {
        super(parcel);
    }

    @Override
    protected String[] getStringArray() {
        return App.getRes().getStringArray(R.array.decorate);
    }

    @Override
    public int pictureResourceId() {
        return R.mipmap.decorate;
    }

    @Override
    public String getDescription() {
        return getDescription(R.string.description_decorate);
    }

    @Override
    public String soundDirName() {
        return "decorate";
    }

    @Override
    public int getGroup() {
        return 2;
    }
}
