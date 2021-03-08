package com.apakhun.arabicverbstestssecond.model.verbs;

import android.os.Parcel;

import com.apakhun.arabicverbstestssecond.R;
import com.apakhun.arabicverbstestssecond.model.TimeVerb;

public class SlackenAndPhilosophize extends TimeVerb {
    public SlackenAndPhilosophize() {
    }

    public SlackenAndPhilosophize(Parcel parcel) {
        super(parcel);
    }

    @Override
    public int pictureResourceId() {
        return R.mipmap.slacken_philosophize;
    }

    @Override
    public String getDescription() {
        return getDescription(R.string.description_slacken_philosophize);
    }

    @Override
    protected String soundUnderFolderDir() {
        return "slacken_philosophize";
    }

    @Override
    protected int getPastArrayId() {
        return R.array.past_present_slacken;
    }

    @Override
    protected int getPresentArrayId() {
        return R.array.past_present_philosophize;
    }

    @Override
    protected int getFutureArrayId() {
        return R.array.imperative_slacken_philosophize;
    }

    @Override
    public int getGroup(){
        return 4;
    }

    @Override
    public String getSubtitlesFirst(){
        return getSubtitles(R.array.subtitles_slacken);
    }

    @Override
    public String getSubtitlesSecond(){
        return getSubtitles(R.array.subtitles_philosophize);
    }
}
