package com.apakhun.arabicverbstestssecond.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.apakhun.arabicverbstestssecond.App;
import com.apakhun.arabicverbstestssecond.Utils;
import com.apakhun.arabicverbstestssecond.model.verbs.Clothe;
import com.apakhun.arabicverbstestssecond.model.verbs.Decorate;
import com.apakhun.arabicverbstestssecond.model.verbs.Discover;
import com.apakhun.arabicverbstestssecond.model.verbs.Dread;
import com.apakhun.arabicverbstestssecond.model.verbs.DryAndWake;
import com.apakhun.arabicverbstestssecond.model.verbs.Give;
import com.apakhun.arabicverbstestssecond.model.verbs.Heal;
import com.apakhun.arabicverbstestssecond.model.verbs.Invite;
import com.apakhun.arabicverbstestssecond.model.verbs.Leave;
import com.apakhun.arabicverbstestssecond.model.verbs.Meet;
import com.apakhun.arabicverbstestssecond.model.verbs.Protect;
import com.apakhun.arabicverbstestssecond.model.verbs.Select;
import com.apakhun.arabicverbstestssecond.model.verbs.SlackenAndPhilosophize;
import com.apakhun.arabicverbstestssecond.model.verbs.Trust;
import com.apakhun.arabicverbstestssecond.model.verbs.Visit;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;


public abstract class Verb implements Parcelable {

    private List<Phrase> phrases = new LinkedList<>();

    public Verb() {
        phrases.addAll(initPhrases());
    }

    public Verb(Parcel parcel) {
        parcel.readList(this.phrases, Phrase.class.getClassLoader());
    }

    public List<Phrase> getPhrases() {
        return phrases;
    }

    private List<Phrase> initPhrases() {
        String[] stringArray = getStringArray();
        List<Phrase> phrases = new LinkedList<>();
        int soundId = 1;
        for (String string : stringArray) {
            Phrase phrase = new Phrase();
            String[] splitted = Utils.splitPhraseString(string);
            phrase.setEnglish(splitted[0]);
            phrase.setRussian(splitted[1]);
            phrase.setArabic(splitted[2]);
            phrase.setSoundPath(soundResourcePath() + "/" + soundId + ".mp3");
            phrases.add(phrase);
            soundId++;
        }
        return phrases;
    }

    public static final VerbParcelableCreator CREATOR = new VerbParcelableCreator();

    public static class VerbParcelableCreator implements Creator<Verb> {

        public static int flag = -1;

        @Override
        public Verb createFromParcel(Parcel source) {
            switch (flag) {
                case 0:
                    return new Discover(source);
                case 1:
                    return new Leave(source);
                case 2:
                    return new Trust(source);
                case 3:
                    return new DryAndWake(source);
                case 4:
                    return new Give(source);
                case 5:
                    return new Decorate(source);
                case 6:
                    return new Visit(source);
                case 7:
                    return new Select(source);
                case 8:
                    return new Dread(source);
                case 9:
                    return new Protect(source);
                case 10:
                    return new Heal(source);
                case 11:
                    return new Meet(source);
                case 12:
                    return new Clothe(source);
                case 13:
                    return new Invite(source);
                case 14:
                    return new SlackenAndPhilosophize(source);

                    default: return new Discover(source);
            }
        }

        @Override
        public Verb[] newArray(int size) {
            return new Verb[0];
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(phrases);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @NonNull
    @Override
    public String toString() {
        return getVerbText();
    }

    public String getVerbText() {
        if (phrases.isEmpty())
            return Verb.class.getSimpleName();
        return phrases.get(0).getArabic();
    }

    public String soundResourcePath() {
        return "sound/" + soundDirName();
    }

    protected String getDescription(int descriptionId) {
        String descr = App.getRes().getString(descriptionId);
        String[] ru_en = descr.split(System.getProperty("line.separator"));
        return Locale.getDefault().getLanguage().equals("ru") ? ru_en[0] : ru_en[1];
    }

    protected abstract String[] getStringArray();
    public abstract int pictureResourceId();
    public abstract String getDescription();
    public abstract String soundDirName();
    public abstract int getGroup();

    public String getSubtitlesFirst(){
        return null;
    }

    public String getSubtitlesSecond(){
        return null;
    }
}
