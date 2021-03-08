package com.apakhun.arabicverbstestssecond.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apakhun.arabicverbstestssecond.R;
import com.apakhun.arabicverbstestssecond.activities.MainActivity;
import com.apakhun.arabicverbstestssecond.model.Tests;
import com.apakhun.arabicverbstestssecond.viewmodel.TestsViewModel;
import com.apakhun.arabicverbstestssecond.views.adapters.TestsRecyclerViewAdapter;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Locale;

public class TestsFragment extends ParentFragment {
    private final static String TAG = TestsFragment.class.getCanonicalName();
    private TestsViewModel testsViewModel;
    private TestsRecyclerViewAdapter adapter;
    private InterstitialAd mInterstitial;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testsViewModel = ViewModelProviders.of(getActivity()).get(TestsViewModel.class);
        adapter = new TestsRecyclerViewAdapter((MainActivity) getActivity());

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tests, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rvTests);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        recyclerView.getRecycledViewPool().setMaxRecycledViews(TestsRecyclerViewAdapter.EXPANDABLE_VIEW_TYPE, 5);
        recyclerView.getRecycledViewPool().setMaxRecycledViews(TestsRecyclerViewAdapter.DEFAULT_VIEW_TYPE, 10);
        recyclerView.setItemViewCacheSize(15);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));



        testsViewModel.getTests().observe(this, tests -> {
            adapter.setData(tests);

            Tests.Progress progress = tests.getProgress();
            int order = 0;
            if (progress.getPassedCount().length() - progress.getPassedCount().replace("1", "").length() == 4
                    && !progress.isCongrShowed(4)) {
                order = 4;
                showCongratulations(getImageId(order));
                progress.addCongrShowed(order);

            } else if (progress.getPassedCount().length() - progress.getPassedCount().replace("2", "").length() == 5
                    && !progress.isCongrShowed(5)) {
                order = 5;
                showCongratulations(getImageId(order));
                progress.addCongrShowed(order);
            } else if (progress.getPassedCount().length() - progress.getPassedCount().replace("3", "").length() == 3
                    && !progress.isCongrShowed(6)) {
                order = 6;
                showCongratulations(getImageId(order));
                progress.addCongrShowed(order);
            } else if (progress.getPassedCount().length() - progress.getPassedCount().replace("4", "").length() == 3
                    && !progress.isCongrShowed(7)) {
                order = 7;
                showCongratulations(getImageId(order));
                progress.addCongrShowed(order);
            }
        });
        return view;
    }

    private int getImageId(int order) {
        switch (order) {
            case 4:
                return Locale.getDefault().getLanguage().equals("ru") ? R.drawable.congr_first_ru : R.drawable.congr_first_en;
            case 5:
                return Locale.getDefault().getLanguage().equals("ru") ? R.drawable.congr_second_ru : R.drawable.congr_second_en;
            case 6:
                return Locale.getDefault().getLanguage().equals("ru") ? R.drawable.congr_third_ru : R.drawable.congr_third_en;
            case 7:
                return Locale.getDefault().getLanguage().equals("ru") ? R.drawable.congr_fourth_ru : R.drawable.congr_fourth_en;
        }
        return -1;
    }



    private void showCongratulations(int imageId) {

        Bundle bundle = new Bundle();
        bundle.putInt(CongratulationFragment.CONGRATULATION_IMAGE_ID_KEY, imageId);
        CongratulationFragment congratulationFragment = ParentFragment.getInstance((AppCompatActivity) getActivity(), CongratulationFragment.class, bundle);
        congratulationFragment.open();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        testsViewModel.getTests().removeObservers(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected String getFragmentTag() {
        return TAG;
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

    }
}
