package com.apakhun.arabicverbstestssecond.model;

import android.util.Log;

import java.util.LinkedList;
import java.util.Random;

public class Question {

    private Phrase[] variants = new Phrase[4];
    private Phrase trueVariant;
    private boolean succeed;
    private boolean failed;
    private boolean answered;
    private boolean isLast;

    public Question(Phrase trueVariant, LinkedList<Phrase> falseVariants) {
        this.trueVariant = trueVariant;
        this.succeed = false;
        this.isLast = false;
        init(trueVariant, falseVariants);
    }


    public void setLast() {isLast = true;}
    public boolean isLast() {return isLast;}

    public boolean tryAnswer(int index) {
        if (isTrueVariant(index)) {
            succeed = true;
            failed = false;
        }
        else {
            succeed = false;
            failed = true;
        }
        return succeed;
    }

    public void answer() {
        if (!succeed && !failed)
            Log.e("EXCEPTION","Question is not tried answer");
        answered = true;
    }

    public boolean isTried() {return succeed || failed;}
    public boolean isSucceed() {return succeed;}
    public boolean isFailed() {return failed;}

    public boolean isAnswered() {return answered;}

    private void init(Phrase trueVariant, LinkedList<Phrase> falseVariants) {
        Random random = new Random();
        int indexOfTrueVariant = random.nextInt(4);
        variants[indexOfTrueVariant] = trueVariant;
        for (int i = 0; i < 4; i++) {
            if (variants[i] == null)
                variants[i] = falseVariants.pop();
        }
    }

    public Phrase[] getVariants() {return variants;}
    public Phrase getTrueVariant() {return trueVariant;}

    public int getTrueVariantIndex() {
        for (int i = 0; i < 4; i++) {
            if (isTrueVariant(i))
                return i;
        }
        return -1;
    }

    public boolean isTrueVariant(int index) {
        return variants[index].equals(trueVariant);
    }
    public boolean isTrueVariant(Phrase phrase) {
        return trueVariant.equals(phrase);
    }
}
