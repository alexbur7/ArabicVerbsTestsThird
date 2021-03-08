package com.apakhun.arabicverbstestssecond.model.verbs;

import android.os.Parcel;

import com.apakhun.arabicverbstestssecond.R;
import com.apakhun.arabicverbstestssecond.model.TimeVerb;

public class Give extends TimeVerb {
    public Give() {
    }

    public Give(Parcel parcel) {
        super(parcel);
    }

    @Override
    public int pictureResourceId() {
        return R.mipmap.give;
    }

    @Override
    public String getDescription() {
        return getDescription(R.string.description_give);
    }

    @Override
    protected String soundUnderFolderDir() {
        return "give";
    }

    @Override
    protected int getPastArrayId() {
        return R.array.past_give;
    }

    @Override
    protected int getPresentArrayId() {
        return R.array.present_give;
    }

    @Override
    protected int getFutureArrayId() {
        return R.array.future_give;
    }

    @Override
    public int getGroup(){
        return 2;
    }
}
