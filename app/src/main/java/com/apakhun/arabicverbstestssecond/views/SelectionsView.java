package com.apakhun.arabicverbstestssecond.views;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.util.Consumer;

import com.apakhun.arabicverbstestssecond.App;
import com.apakhun.arabicverbstestssecond.R;
import com.apakhun.arabicverbstestssecond.model.Phrase;
import com.apakhun.arabicverbstestssecond.model.Question;

public class SelectionsView extends ConstraintLayout {
    private SelectionView[] answs;
    private TextView tvQuestion;
    private Question question;
    private boolean isAnswered;
    private ImageView ivPicQuestion;
    private ImageView ivPalm;
    private ImageView ivNextMarked;

    private ITrueAnswered listener;

    public interface IAnotherSelected {
        void onAnotherSelected();
    }

    public interface ITrueAnswered {
        void onTrueAnswered();
        void onFalseAnswered();
        void onNextQuestionRequired();
        void onFinishedTest();
    }

    public SelectionsView(Context context) {
        super(context);
        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mInflater.inflate(R.layout.page_test, this, true);

        answs = new SelectionView[4];
        answs[0] = findViewById(R.id.answ1);
        answs[1] = findViewById(R.id.answ2);
        answs[2] = findViewById(R.id.answ3);
        answs[3] = findViewById(R.id.answ4);

        tvQuestion = findViewById(R.id.tvQuestion);
        ivNextMarked = findViewById(R.id.ivNextMarked);
        ivPicQuestion = findViewById(R.id.ivPicQuestion);
        ivPalm = findViewById(R.id.ivPalm);

//        ivNextMarked.getDrawable().setColorFilter(App.getRes().getColor(R.color.colorDarkBlue), PorterDuff.Mode.SRC_ATOP);
        ivPalm.getDrawable().setColorFilter(App.getRes().getColor(R.color.colorDarkBlue), PorterDuff.Mode.SRC_ATOP);
    }

    public void release() {
        ((BitmapDrawable) ivPalm.getDrawable()).getBitmap().recycle();
        ((BitmapDrawable) ivPicQuestion.getDrawable()).getBitmap().recycle();
        ((BitmapDrawable) ivNextMarked.getDrawable()).getBitmap().recycle();
    }

    public void initSelectionsView(Question question, ITrueAnswered listener) {
        this.question = question;
        this.listener = listener;

        for (int i = 0; i < 4; i++) {
            IAnotherSelected[] listeners = new IAnotherSelected[3];
            int counter = 0;
            for (int j = 0; j < 4; j++) {
                if (i == j) continue;
                listeners[counter++] = answs[j];
            }

            answs[i].initSelectionView(question.getVariants()[i], question.isTrueVariant(i), listeners);

            int finalI = i;
            answs[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ivNextMarked.setVisibility(View.VISIBLE);

                    answs[finalI].select(true);
                    if (!question.isTried())
                        question.tryAnswer(finalI);

                    if (question.isTrueVariant(finalI)) {
                        listener.onTrueAnswered();
                        animateColorAnswerItem(answs[finalI], App.getRes().getColor(R.color.colorGreen), new Consumer<Void>() {
                            @Override
                            public void accept(Void y) {

                            }
                        });
                    } else {
                        listener.onFalseAnswered();
                        animateColorAnswerItem(answs[finalI], Color.RED, new Consumer<Void>() {
                            @Override
                            public void accept(Void y) {

                            }
                        });
                    }
                }
            });
        }
        draw();
    }

    private int getPicDrawableId(Question question) {
        Phrase truePhrase = question.getTrueVariant();
        String english = truePhrase.getEnglish();
        if (english.indexOf("two me") > 0)
            return R.drawable.two_men;
        if (english.indexOf("two wo") > 0)
            return R.drawable.two_women;
        if (english.indexOf("all me") > 0)
            return R.drawable.men;
        if (english.indexOf("all wo") > 0)
            return R.drawable.women;
        if (english.indexOf("woman") > 0 || english.indexOf("She ") != -1 || english.indexOf(" her ") != -1)
            return R.drawable.woman;
        if (english.indexOf("man") > 0 || english.indexOf("He ") != -1 || english.indexOf(" him ") != -1)
            return R.drawable.man;
        if (english.indexOf("We ") != -1 || english.indexOf("toget") != -1)
            return R.drawable.we;
        if (english.indexOf("I ") != -1 || english.indexOf("me ") != -1)
            Log.e("EXCEPTION","Error file format: " + english);
            return R.drawable.i;
//        return R.drawable.brand;
    }

    private void animateColorAnswerItem(SelectionView selectionView, int color, Consumer<Void> onAnimationEnd) {
        ValueAnimator anim = new ValueAnimator();

        GradientDrawable notPressedStateDrawable = selectionView.getNotPressedStateDrawable();

        int oldColor = getResources().getColor(R.color.colorItemAnsNotPressed);

        anim.setIntValues(oldColor, color);
        anim.setEvaluator(new ArgbEvaluator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                notPressedStateDrawable.setColor((Integer)valueAnimator.getAnimatedValue());
            }
        });

        anim.setRepeatCount(1);
//        anim.setRepeatMode(ValueAnimator.REVERSE);

        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (onAnimationEnd != null)
                    onAnimationEnd.accept(null);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        anim.setDuration(300);
        anim.start();
    }

    public void setLastQuestionAnsweredState() {

    }

    private void draw() {
        isAnswered = question.isAnswered();
        tvQuestion.setText(question.getTrueVariant().getText());
        ivPicQuestion.setImageDrawable(getResources().getDrawable(getPicDrawableId(question)));

        if (isAnswered) {
            for (SelectionView ans : answs) {
                ans.setClickable(false);
                ans.setBackgroundDrawable(null);
            }
            answs[question.getTrueVariantIndex()].select(false);

            ivNextMarked.setClickable(false);

            if (!question.isLast()) {
                ivNextMarked.setImageDrawable(App.getRes().getDrawable(R.drawable.hand));
                if (question.isSucceed())
                    ivNextMarked.getDrawable().setColorFilter(App.getRes().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
                else
                    ivNextMarked.getDrawable().setColorFilter(App.getRes().getColor(R.color.colorRed), PorterDuff.Mode.SRC_ATOP);
            } else {
                ivNextMarked.setClickable(true);
                ivNextMarked.setImageDrawable(App.getRes().getDrawable(R.drawable.all_passed));
                ivNextMarked.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        listener.onFinishedTest();
                    }
                });
            }


        } else {
            ivNextMarked.setVisibility(View.INVISIBLE);
            ivNextMarked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int selectedAnswer = -1;
                    for (int i = 0; i < 4; i++) {
                        if (answs[i].isSelected()) {
                            selectedAnswer = i;
                            break;
                        }
                    }

                    if (!question.isTried())
                        Log.e("EXCEPTION","Question is not tried to answer but next button is appeared");

                    if (question.isTrueVariant(selectedAnswer)) {
                        question.answer();
                        draw(); // redraw answered situation
                        listener.onNextQuestionRequired();
                    } else {
                        animateColorAnswerItem(answs[selectedAnswer], Color.RED, null);
                    }
                }
            });
        }
    }
}
