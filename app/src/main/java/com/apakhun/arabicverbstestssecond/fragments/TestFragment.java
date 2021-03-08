package com.apakhun.arabicverbstestssecond.fragments;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.apakhun.arabicverbstestssecond.R;
import com.apakhun.arabicverbstestssecond.model.Question;
import com.apakhun.arabicverbstestssecond.model.Test;
import com.apakhun.arabicverbstestssecond.model.Tests;
import com.apakhun.arabicverbstestssecond.model.Verb;
import com.apakhun.arabicverbstestssecond.model.TimeVerb;
import com.apakhun.arabicverbstestssecond.viewmodel.TestViewModel;
import com.apakhun.arabicverbstestssecond.viewmodel.TestsViewModel;
import com.apakhun.arabicverbstestssecond.views.SelectionsView;
import com.apakhun.arabicverbstestssecond.views.ViewPagerCustomDuration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class TestFragment extends ParentFragment {

    private TestViewModel testViewModel;
    private TestsViewModel testsViewModel;
    private Tests.Progress progress;
    private Verb verb;
    private TimeVerb.Time time;
    private ViewPagerCustomDuration viewPager;
    private TextView tvDescription;
    private TextView tvError;
    private TextView tvCorrect;
    private TextView tvTotal;
    private ImageView repeat;
    private InterstitialAd mInterstitial;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);mInterstitial = new InterstitialAd(getActivity());
        mInterstitial.setAdUnitId("ca-app-pub-6149544244855072/7072143750");

        testsViewModel = ViewModelProviders.of(getActivity()).get(TestsViewModel.class);

        testsViewModel.getTests().observe(getActivity(), new Observer<Tests>() {
            @Override
            public void onChanged(@Nullable Tests tests) {
                progress = tests.getProgress();

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_test, container, false);

        viewPager = view.findViewById(R.id.viewpager);
        tvDescription = view.findViewById(R.id.tvDescriptionVerb);
        tvError = view.findViewById(R.id.tvError);
        tvCorrect = view.findViewById(R.id.tvCorrect);
        tvTotal = view.findViewById(R.id.tvTotal);
        repeat = view.findViewById(R.id.repeat);
        AdView adView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        testViewModel = ViewModelProviders.of(getActivity()).get(TestViewModel.class);
//        verb = testViewModel.getSelectedVerb().first;
//        time = testViewModel.getSelectedVerb().second;

        if (verb == null) {
            Log.e("EXCEPTION","Verb is null");
        }

        tvDescription.setText(verb.getDescription());

        Test oldTest = testViewModel.getTest().getValue();
        testViewModel.getTest().observe(this, test -> {
            //костыль: при обзервинге приходит вначале старое значение(после изменения состояния Activity), потом новое
            if (oldTest == test)
                return;
            tvError.setText(String.valueOf(test.getNumFailedQuestions()));
            tvCorrect.setText(String.valueOf(test.getNumSucceedQuestions()));
            tvTotal.setText(String.valueOf(test.getNumQuestions()));
            ViewPagerAdapter adapter = new ViewPagerAdapter();
            viewPager.setAdapter(adapter);
            adapter.setData(test);
            updatePage(test);
        });

        viewPager.setPageTransformer(true,  new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@androidx.annotation.NonNull View page, float position) {
                if (position >= -1.0F || position <= 1.0F) {
                    float height = (float)page.getHeight();
                    float scaleFactor = Math.max(0.85F, 1.0F - Math.abs(position));
                    float vertMargin = height * (1.0F - scaleFactor) / 2.0F;
                    float horzMargin = (float)page.getWidth() * (1.0F - scaleFactor) / 2.0F;
                    page.setPivotY(0.5F * height);
                    if (position < 0.0F) {
                        page.setTranslationX(horzMargin - vertMargin / 2.0F);
                    } else {
                        page.setTranslationX(-horzMargin + vertMargin / 2.0F);
                    }

                    page.setScaleX(scaleFactor);
                    page.setScaleY(scaleFactor);
                    page.setAlpha(0.5F + (scaleFactor - 0.85F) / 0.14999998F * 0.5F);
                }
            }
        });
        testViewModel.requestTest();

        setHasOptionsMenu(true);
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdRequest adRequest = new AdRequest.Builder().build();
                mInterstitial.loadAd(adRequest);
                mInterstitial.setAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        if (mInterstitial.isLoaded()) {
                            mInterstitial.show();
                        }
                    }
                });
                testViewModel.restartTest();
            }

        });

        return view;
    }

    private void updatePage(Test test) {
        if (test.getNumAnsweredQuestions() < test.getNumQuestions()) {
            viewPager.slower();
            viewPager.setCurrentItem(test.getNumAnsweredQuestions(), true);
            viewPager.faster();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        testsViewModel.notifyChanges(); //update progress
        testViewModel.saveTest();
        testsViewModel.saveProgresses();
        testViewModel.getTest().removeObservers(this);
    }

    @Override
    protected String getFragmentTag() {
        return TestFragment.class.getSimpleName();
    }

    @Override
    protected int getLayoutId() {
        return R.id.frgMain;
    }

    @Override
    protected boolean allowToBackStack() {
        return true;
    }

    @Override
    protected void onGotArguments(Bundle bundle) {
        verb = bundle.getParcelable(Verb.class.getCanonicalName());
        time = TimeVerb.Time.values()[bundle.getInt(TimeVerb.Time.class.getCanonicalName())];
    }

    class ViewPagerAdapter extends PagerAdapter implements SelectionsView.ITrueAnswered {
        private Test test;

        private final String TAG = ViewPagerAdapter.class.getCanonicalName();


        public void setData(Test test) {
            this.test = test;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            if (test == null)
                return 0;
            int answeredCount = test.getNumAnsweredQuestions();
            if (answeredCount == test.getNumQuestions())
                return answeredCount;
            else
                return 1 + test.getNumAnsweredQuestions();
        }



        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            SelectionsView view = new SelectionsView(getContext());

            Question question = test.getQuestion(position);
            view.initSelectionsView(question, this);

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
//            ((SelectionsView) object).release();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }


        int lastNumSucceedQuestions = 0;
        int lastNumFailedQuestions = 0;

        @Override
        public void onTrueAnswered() {
            //TODO для открытия тестов
            progress.putSucceed(verb, time, test.getNumSucceedQuestions());
            //to test congratulation postcard
            //progress.putSucceed(verb, time, test.getNumQuestions());

            tvCorrect.setText(String.valueOf(test.getNumSucceedQuestions()));
            tvTotal.setText(String.valueOf(test.getNumQuestions()));

            //only first pressing animate
            if (lastNumSucceedQuestions != test.getNumSucceedQuestions()) {
                lastNumSucceedQuestions = test.getNumSucceedQuestions();
                animateColorStatistic(tvCorrect, getResources().getColor(R.color.colorGreen));
            }

            if (progress.getSucceed(verb, TimeVerb.Time.COMMON) == verb.getPhrases().size()) {
                progress.addPassed(verb);
            }
        }

        @Override
        public void onFalseAnswered() {
            progress.putFailed(verb, time, test.getNumFailedQuestions());
            tvError.setText(String.valueOf(test.getNumFailedQuestions()));
            tvTotal.setText(String.valueOf(test.getNumQuestions()));

            //only first pressing animate
            if (lastNumFailedQuestions != test.getNumFailedQuestions()) {
                lastNumFailedQuestions = test.getNumFailedQuestions();
                animateColorStatistic(tvError, getResources().getColor(R.color.colorRed));
            }
        }



        private void animateColorStatistic(TextView textView, int color) {
            ValueAnimator animColor = new ValueAnimator();
            int oldColor = getResources().getColor(R.color.colorDarkBlue);
            animColor.setIntValues(oldColor, color);
            animColor.setEvaluator(new ArgbEvaluator());
            animColor.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    textView.setTextColor((Integer)valueAnimator.getAnimatedValue());
                }
            });
            animColor.setRepeatCount(1);
            animColor.setRepeatMode(ValueAnimator.REVERSE);
            animColor.setDuration(300);

            ValueAnimator animScale = new ValueAnimator();
            animScale.setFloatValues(1, 2);
            animScale.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    textView.setScaleX((Float)valueAnimator.getAnimatedValue());
                    textView.setScaleY((Float)valueAnimator.getAnimatedValue());
                }
            });
            animScale.setRepeatCount(1);
            animScale.setRepeatMode(ValueAnimator.REVERSE);
            animScale.setDuration(300);

//            animColor.start();
            animScale.start();
        }

        @Override
        public void onNextQuestionRequired() {
            notifyDataSetChanged();
            updatePage(test);
        }

        @Override
        public void onFinishedTest() {
            //save data here
            TestFragment.this.close();
        }
    }
}
