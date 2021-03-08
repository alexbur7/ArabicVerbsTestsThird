package com.apakhun.arabicverbstestssecond.model.verbs;

import android.os.Parcel;

import com.apakhun.arabicverbstestssecond.App;
import com.apakhun.arabicverbstestssecond.R;
import com.apakhun.arabicverbstestssecond.model.Verb;

public class Trust extends Verb {
    public Trust() {
    }

    public Trust(Parcel parcel) {
        super(parcel);
    }

    @Override
    protected String[] getStringArray() {
        return App.getRes().getStringArray(R.array.trust);
    }

    @Override
    public int pictureResourceId() {
        return R.mipmap.trust;
    }

    @Override
    public String getDescription() {
        return getDescription(R.string.description_trust);
    }

    @Override
    public String soundDirName() {
        return "trust";
    }

    @Override
    public int getGroup() {
        return 1;
    }
}
