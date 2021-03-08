package com.apakhun.arabicverbstestssecond.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;

import java.util.Locale;


public class Phrase implements Parcelable {
    private String russian;
    private String english;
    private String arabic;
    private int img;
    private String soundPath;

    public Phrase() {}

    protected Phrase(Parcel in) {
        russian = in.readString();
        english = in.readString();
        arabic = in.readString();
        img = in.readInt();
        soundPath = in.readString();
    }

    public static final Creator<Phrase> CREATOR = new Creator<Phrase>() {
        @Override
        public Phrase createFromParcel(Parcel in) {
            return new Phrase(in);
        }

        @Override
        public Phrase[] newArray(int size) {
            return new Phrase[size];
        }
    };

    public String getText() {
        return Locale.getDefault().getLanguage().equals("ru") ? russian : english;
    }

    public String getEnglish() {return english;}

    public void setRussian(String russian) {
        this.russian = russian;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getArabic() {
        return arabic;
    }

    public void setArabic(String arabic) {
        this.arabic = arabic;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getSoundPath() {
        return soundPath;
    }

    public void setSoundPath(String soundPath) {
        this.soundPath = soundPath;
    }
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Phrase))
            return false;
        Phrase rhs = (Phrase) obj;
        return arabic.equals(rhs.arabic);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(russian);
        dest.writeString(english);
        dest.writeString(arabic);
        dest.writeInt(img);
        dest.writeString(soundPath);
    }
}
