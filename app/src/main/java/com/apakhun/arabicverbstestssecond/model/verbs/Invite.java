package com.apakhun.arabicverbstestssecond.model.verbs;

import android.os.Parcel;

import com.apakhun.arabicverbstestssecond.App;
import com.apakhun.arabicverbstestssecond.R;
import com.apakhun.arabicverbstestssecond.model.Verb;

public class Invite extends Verb {
    public Invite() {
    }

    public Invite(Parcel parcel) {
        super(parcel);
    }

    @Override
    protected String[] getStringArray() {
        return App.getRes().getStringArray(R.array.invite);
    }

    @Override
    public int pictureResourceId() {
        return R.mipmap.invite;
    }

    @Override
    public String getDescription() {
        return getDescription(R.string.description_invite);
    }

    @Override
    public String soundDirName() {
        return "invite";
    }

    @Override
    public int getGroup() {
        return 4;
    }
}
