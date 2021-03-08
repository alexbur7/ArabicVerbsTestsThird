package com.apakhun.arabicverbstestssecond.model.verbs;

import android.os.Parcel;

import com.apakhun.arabicverbstestssecond.App;
import com.apakhun.arabicverbstestssecond.R;
import com.apakhun.arabicverbstestssecond.model.Verb;

public class Heal extends Verb {
    public Heal() {
    }

    public Heal(Parcel parcel) {
        super(parcel);
    }

    @Override
    protected String[] getStringArray() {
        return App.getRes().getStringArray(R.array.heal);
    }

    @Override
    public int pictureResourceId() {
        return R.mipmap.heal;
    }

    @Override
    public String getDescription() {
        return getDescription(R.string.description_heal);
    }

    @Override
    public String soundDirName() {
        return "heal";
    }

    @Override
    public int getGroup() {
        return 3;
    }
}
