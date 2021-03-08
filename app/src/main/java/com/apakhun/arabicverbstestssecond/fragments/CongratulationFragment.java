package com.apakhun.arabicverbstestssecond.fragments;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.apakhun.arabicverbstestssecond.R;

public class CongratulationFragment extends ParentFragment {
    private Bundle bundle;
    public static final String CONGRATULATION_IMAGE_ID_KEY = "CONGRATULATION_IMAGE_ID_KEY";
    private int imgId;
    private Activity activity;
    @Override
    protected String getFragmentTag() {
        return CongratulationFragment.class.getCanonicalName();
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
        this.bundle = bundle;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.postcard, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgId = bundle.getInt(CONGRATULATION_IMAGE_ID_KEY);
        activity = getActivity();
        ImageView ivCongratulation = view.findViewById(R.id.ivCongratulation);
        ivCongratulation.setImageDrawable(getActivity().getResources().getDrawable(imgId));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (imgId == R.drawable.congr_first_en || imgId == R.drawable.congr_first_ru) {
            rateTheApp();
        }
    }

    private void rateTheApp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle(R.string.title_rate).setMessage(R.string.message_rate).setPositiveButton(R.string.great, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Uri uri = Uri.parse("market://details?id=" + activity.getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    activity.startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    activity.startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + activity.getPackageName())));
                }
            }
        }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }
}
