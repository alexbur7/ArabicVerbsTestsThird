package com.apakhun.arabicverbstestssecond.model.verbs;

import android.os.Parcel;

import com.apakhun.arabicverbstestssecond.App;
import com.apakhun.arabicverbstestssecond.R;
import com.apakhun.arabicverbstestssecond.model.Verb;

public class Visit extends Verb {

    public Visit() {
    }

    public Visit(Parcel parcel) {
        super(parcel);
    }

    @Override
    protected String[] getStringArray() {
        return App.getRes().getStringArray(R.array.visit);
    }

    @Override
    public int pictureResourceId() {
        return R.mipmap.visit;
    }

    @Override
    public String getDescription() {
        return getDescription(R.string.description_visit);
    }

    @Override
    public String soundDirName() {
        return "visit";
    }

    @Override
    public int getGroup() {
        return 2;
    }
}
