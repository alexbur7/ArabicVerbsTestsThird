package com.apakhun.arabicverbstestssecond.model.verbs;

import android.os.Parcel;

import com.apakhun.arabicverbstestssecond.R;
import com.apakhun.arabicverbstestssecond.model.TimeVerb;

public class DryAndWake extends TimeVerb {
    public DryAndWake() {
    }

    public DryAndWake(Parcel parcel) {
        super(parcel);
    }

    @Override
    public int pictureResourceId() {
        return R.mipmap.dry_wake;
    }

    @Override
    public String getDescription() {
        return getDescription(R.string.description_dry_wake);
    }

    @Override
    protected String soundUnderFolderDir() {
        return "dry_wake";
    }

    @Override
    protected int getPastArrayId() {
        return R.array.past_present_dry;
    }

    @Override
    protected int getPresentArrayId() {
        return R.array.past_present_wake;
    }

    @Override
    protected int getFutureArrayId() {
        return R.array.imperative_dry_wake;
    }

    @Override
    public int getGroup(){
        return 1;
    }

    @Override
    public String getSubtitlesFirst(){
        return getSubtitles(R.array.subtitles_dry);
    }

    @Override
    public String getSubtitlesSecond(){
        return getSubtitles(R.array.subtitles_wake);
    }
}
