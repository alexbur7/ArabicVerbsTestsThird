package com.apakhun.arabicverbstestssecond.model.verbs;

import android.os.Parcel;

import com.apakhun.arabicverbstestssecond.App;
import com.apakhun.arabicverbstestssecond.R;
import com.apakhun.arabicverbstestssecond.model.Verb;

public class Leave extends Verb {
    public Leave() {
    }

    public Leave(Parcel parcel) {
        super(parcel);
    }

    @Override
    protected String[] getStringArray() {
        return App.getRes().getStringArray(R.array.leave);
    }

    @Override
    public int pictureResourceId() {
        return R.mipmap.leave;
    }

    @Override
    public String getDescription() {
        return getDescription(R.string.description_leave);
    }

    @Override
    public String soundDirName() {
        return "leave";
    }

    @Override
    public int getGroup() {
        return 1;
    }
}

