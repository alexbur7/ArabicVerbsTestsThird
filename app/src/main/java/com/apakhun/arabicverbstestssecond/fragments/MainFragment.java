package com.apakhun.arabicverbstestssecond.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.apakhun.arabicverbstestssecond.R;

public class MainFragment extends ParentFragment {
    private final static String TAG = MainFragment.class.getSimpleName();

    private Button btnToTests;
    private Button btnToTable;
    private Button btnShare;
    private TextView recommendation;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_main, container, false);
        recommendation = view.findViewById(R.id.recommendation);
        btnShare = view.findViewById(R.id.btnToSocials);
        btnToTable = view.findViewById(R.id.btnToTable);
        btnToTests = view.findViewById(R.id.btnToTests);

        recommendation.setOnClickListener(view1 -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            View v = getLayoutInflater().inflate(R.layout.recommendation, null);
            TextView tvRecommendation = v.findViewById(R.id.tvRecommendation);
            tvRecommendation.setText(R.string.recommendation_text);
            builder.setView(v).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();
        });

        btnShare.setOnClickListener(new OnClickBtnShare());
        btnToTests.setOnClickListener(new OnClickBtnBtnToTests());
        btnToTable.setOnClickListener(new OnClickBtnToTable());

        return view;
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
        return false;
    }

    @Override
    protected void onGotArguments(Bundle bundle) {

    }

    class OnClickBtnShare implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.send_friends));
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, getString(R.string.share)));
        }
    }

    class OnClickBtnToTable implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            TableFragment tableFragment = ParentFragment.getInstance((AppCompatActivity) getActivity(), TableFragment.class);
            tableFragment.open();
        }
    }

    class OnClickBtnBtnToTests implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            TestsFragment testsFragment = ParentFragment.getInstance((AppCompatActivity) getActivity(), TestsFragment.class);
            testsFragment.open();
        }
    }
}
