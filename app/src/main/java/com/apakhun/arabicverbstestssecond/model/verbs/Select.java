package com.apakhun.arabicverbstestssecond.model.verbs;

import android.os.Parcel;

import com.apakhun.arabicverbstestssecond.App;
import com.apakhun.arabicverbstestssecond.R;
import com.apakhun.arabicverbstestssecond.model.Verb;

public class Select extends Verb {
    public Select() {
    }

    public Select(Parcel parcel) {
        super(parcel);
    }

    @Override
    protected String[] getStringArray() {
        return App.getRes().getStringArray(R.array.select);
    }

    @Override
    public int pictureResourceId() {
        return R.mipmap.select;
    }

    @Override
    public String getDescription() {
        return getDescription(R.string.description_select);
    }

    @Override
    public String soundDirName() {
        return "select";
    }

    @Override
    public int getGroup() {
        return 2;
    }
}
