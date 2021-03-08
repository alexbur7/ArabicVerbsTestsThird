package com.apakhun.arabicverbstestssecond.model.verbs;

import android.os.Parcel;

import com.apakhun.arabicverbstestssecond.App;
import com.apakhun.arabicverbstestssecond.R;
import com.apakhun.arabicverbstestssecond.model.Verb;

public class Meet extends Verb {
    public Meet() {
    }

    public Meet(Parcel parcel) {
        super(parcel);
    }

    @Override
    protected String[] getStringArray() {
        return App.getRes().getStringArray(R.array.meet);
    }

    @Override
    public int pictureResourceId() {
        return R.mipmap.meet;
    }

    @Override
    public String getDescription() {
        return getDescription(R.string.description_meet);
    }

    @Override
    public String soundDirName() {
        return "meet";
    }

    @Override
    public int getGroup() {
        return 3;
    }
}
