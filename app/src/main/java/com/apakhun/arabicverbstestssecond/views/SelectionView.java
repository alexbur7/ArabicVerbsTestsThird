package com.apakhun.arabicverbstestssecond.views;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.apakhun.arabicverbstestssecond.R;
import com.apakhun.arabicverbstestssecond.controllers.Sounder;
import com.apakhun.arabicverbstestssecond.model.Phrase;

public class SelectionView extends ConstraintLayout implements SelectionsView.IAnotherSelected {

    private boolean isSelected;
    private TextView tvAnswerItem;
    private ImageView ivSounding;
    private Phrase phrase;
    private GradientDrawable notPressedStateDrawable;
    private GradientDrawable pressedStateDrawable;
    private StateListDrawable background;

    private boolean isTrueVariant;

    private SelectionsView.IAnotherSelected[] listeners;

    @Override
    public void onAnotherSelected() {
        deselect();
    }

    public SelectionView(Context context) {
        super(context);
    }

    public SelectionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SelectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initSelectionView(Phrase phrase, boolean isTrueVariant, SelectionsView.IAnotherSelected[] listeners) {
        pressedStateDrawable = (GradientDrawable) getResources().getDrawable(R.drawable.item_answer_background_pressed);
        notPressedStateDrawable = (GradientDrawable) getResources().getDrawable(R.drawable.item_answer_background_not_pressed);
        background = new StateListDrawable();
        background.addState(new int[] { android.R.attr.state_pressed }, pressedStateDrawable);
        background.addState(new int[] { android.R.attr.state_window_focused }, notPressedStateDrawable);

        LayoutInflater  mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mInflater.inflate(R.layout.answer_item, this, true);
        tvAnswerItem = findViewById(R.id.tvAnswerItem);
        ivSounding = findViewById(R.id.ivSounding);

        this.phrase = phrase;
        this.isTrueVariant = isTrueVariant;
        this.listeners = listeners;

        setBackgroundDrawable(background);
        draw();
    }

    public GradientDrawable getNotPressedStateDrawable() {return notPressedStateDrawable;}

    public boolean isSelected() {return isSelected;}

    public void select(boolean sounding) {
        if (isTrueVariant && sounding) {
            ivSounding.setVisibility(View.VISIBLE);
            if (isSelected) {
                Sounder.sound(phrase.getSoundPath(), new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        ivSounding.setVisibility(View.INVISIBLE);
                    }
                });
            }
        } else
            ivSounding.setVisibility(View.INVISIBLE);

        isSelected = true;

        for (int i = 0; i < 3; i++) {
            listeners[i].onAnotherSelected();
        }

        draw();
    }

    public void deselect() {
        isSelected = false;
        ivSounding.setVisibility(View.INVISIBLE);
        draw();
    }

    public boolean isTrueVariant() {
        return isTrueVariant;
    }

    public void draw() {
        tvAnswerItem.setText(phrase.getArabic());
        if (isSelected) {
            tvAnswerItem.setTypeface(tvAnswerItem.getTypeface(), Typeface.BOLD);
            tvAnswerItem.setTextSize(40);
        } else {
            tvAnswerItem.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            tvAnswerItem.setTextSize(35);
        }
    }

}
