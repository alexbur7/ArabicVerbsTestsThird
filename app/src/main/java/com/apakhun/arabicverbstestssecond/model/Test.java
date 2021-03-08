package com.apakhun.arabicverbstestssecond.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Test {
    private final List<Question> questions;
    private Test(int size) {
        questions = new ArrayList<>(size);
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public Question getQuestion(int num) {
        return questions.get(num);
    }

    public int getNumQuestions() {return questions.size();}

    public int getNumAnsweredQuestions() {
        int answeredQuestions = 0;
        for (Question q : questions) {
            if (q.isAnswered())
                answeredQuestions++;
        }
        return answeredQuestions;
    }

    public int getNumSucceedQuestions() {
        int succeedQuestions = 0;
        for (Question q : questions) {
            if (q.isSucceed())
                succeedQuestions++;
        }
        return succeedQuestions;
    }

    public int getNumFailedQuestions() {
        int failedQuestions = 0;
        for (Question q : questions) {
            if (q.isFailed())
                failedQuestions++;
        }
        return failedQuestions;
    }

    public static Test generateTest(Verb verb, TimeVerb.Time time) {
        List<Phrase> allPhrases = verb.getPhrases();
        List<Phrase> truePhrases;

        if (time == TimeVerb.Time.COMMON) {
            truePhrases = allPhrases;
        } else {
            if (!(verb instanceof TimeVerb))
                Log.e("EXCEPTION","verb is not TimeVerb while time is not COMMON");
            TimeVerb timeVerb = (TimeVerb) verb;
            truePhrases = timeVerb.getPhrasesByTime(time);
        }

        Test test = new Test(truePhrases.size());

        LinkedList<Phrase> falseVariants = new LinkedList<>();
        Random random = new Random();

//        Integer[] range = Utils.getShuffledArray(0, truePhrases.size());
        int[] range = new int[truePhrases.size()];
        for (int i = 0; i < range.length; i++) {
            range[i] = i;
        }
        for (int i = 0; i < range.length; i++) {
            Phrase trueVariant = truePhrases.get(i);

            for (int j = 0; j < 3; j++) {
                int index;
                do {
                    index = random.nextInt(allPhrases.size());
                    if (!allPhrases.get(index).equals(trueVariant))
                        break;
                } while(true);
                falseVariants.add(allPhrases.get(index));
            }

            Question question = new Question(trueVariant, falseVariants);
            if (i == range.length - 1)
                question.setLast();
            test.addQuestion(question);
            falseVariants.clear();
        }

        return test;
    }
}
