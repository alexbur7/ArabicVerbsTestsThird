package com.apakhun.arabicverbstestssecond.model.verbs;

import android.os.Parcel;

import com.apakhun.arabicverbstestssecond.App;
import com.apakhun.arabicverbstestssecond.R;
import com.apakhun.arabicverbstestssecond.model.Verb;

public class Dread extends Verb {
    public Dread() {
    }

    public Dread(Parcel parcel) {
        super(parcel);
    }

    @Override
    protected String[] getStringArray() {
        return App.getRes().getStringArray(R.array.dread);
    }

    @Override
    public int pictureResourceId() {
        return R.mipmap.dread;
    }

    @Override
    public String getDescription() {
        return getDescription(R.string.description_dread);
    }

    @Override
    public String soundDirName() {
        return "dread";
    }

    @Override
    public int getGroup() {
        return 2;
    }
}
