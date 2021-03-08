package com.apakhun.arabicverbstestssecond.model;

import android.os.Parcel;

import com.apakhun.arabicverbstestssecond.App;
import com.apakhun.arabicverbstestssecond.R;
import com.apakhun.arabicverbstestssecond.model.Phrase;
import com.apakhun.arabicverbstestssecond.model.Verb;

import java.util.List;
import java.util.Locale;

public abstract class TimeVerb extends Verb {
    public enum Time {
        PAST,
        PRESENT,
        FUTURE,
        COMMON;

        @Override
        public String toString() {
            if (this == PAST)
                return "PAST";
            if (this == PRESENT)
                return "PRESENT";
            if (this == FUTURE)
                return "FUTURE";

            return "COMMON";
        }
    }

    private Verb pastVerb;
    private Verb presentVerb;
    private Verb futureVerb;

    public TimeVerb() {
        super();
        pastVerb = initPastVerb();
        presentVerb = initPresentVerb();
        futureVerb = initFutureVerb();
        getPhrases().addAll(pastVerb.getPhrases());
        getPhrases().addAll(presentVerb.getPhrases());
        getPhrases().addAll(futureVerb.getPhrases());
    }

    public TimeVerb(Parcel parcel) {
        super(parcel);
        pastVerb = parcel.readParcelable(Verb.class.getClassLoader());
        presentVerb = parcel.readParcelable(Verb.class.getClassLoader());
        futureVerb = parcel.readParcelable(Verb.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(pastVerb, flags);
        dest.writeParcelable(presentVerb, flags);
        dest.writeParcelable(futureVerb, flags);
    }

    protected String getSubtitles(int descriptionId){
        String[] descr = App.getRes().getStringArray(descriptionId);
        return Locale.getDefault().getLanguage().equals("ru") ? descr[0] : descr[1];
    }

    public List<Phrase> getPhrasesByTime(Time time) {
        switch (time) {
            case PAST:
                return pastVerb.getPhrases();
            case PRESENT:
                return presentVerb.getPhrases();
            case FUTURE:
                return futureVerb.getPhrases();
            case COMMON:
                return getPhrases();
        }
        return null;
    }

    protected Verb initPastVerb() {
        return new PastVerb() {
            @Override
            protected String[] getStringArray() {
                return App.getRes().getStringArray(getPastArrayId());
            }
        };
    }

    protected Verb initPresentVerb() {
        return new PresentVerb() {
            @Override
            protected String[] getStringArray() {
                return App.getRes().getStringArray(getPresentArrayId());
            }
        };
    }

    protected Verb initFutureVerb() {
        return new FutureVerb() {
            @Override
            protected String[] getStringArray() {
                return App.getRes().getStringArray(getFutureArrayId());
            }
        };
    }

    protected abstract String soundUnderFolderDir();
    protected abstract int getPastArrayId();
    protected abstract int getPresentArrayId();
    protected abstract int getFutureArrayId();

    @Override
    public String soundDirName() {
        return pastVerb.soundDirName();
    }

    @Override
    protected String[] getStringArray() {
        return App.getRes().getStringArray(R.array.time_verb); //empty list
    }

    public Verb getPastVerb() {
        return pastVerb;
    }
    public Verb getPresentVerb() {
        return presentVerb;
    }
    public Verb getFutureVerb() {
        return futureVerb;
    }

    abstract class PastVerb extends Verb {

        @Override
        public int pictureResourceId() {
            return 0;
        }

        @Override
        public String getDescription() {
            return null;
        }

        @Override
        public String soundDirName() {
            return soundUnderFolderDir() + "/past";
        }

        @Override
        public int getGroup() {
            return 0;
        }
    }

    abstract class PresentVerb extends Verb {
        @Override
        public int pictureResourceId() {
            return 0;
        }

        @Override
        public String getDescription() {
            return null;
        }

        @Override
        public String soundDirName() {
            return soundUnderFolderDir() + "/present";
        }

        @Override
        public int getGroup() {
            return 0;
        }
    }

    abstract class FutureVerb extends Verb {
        @Override
        public int pictureResourceId() {
            return 0;
        }

        @Override
        public String getDescription() {
            return null;
        }

        @Override
        public String soundDirName() {
            return soundUnderFolderDir() + "/future";
        }

        @Override
        public int getGroup() {
            return 0;
        }
    }
}
