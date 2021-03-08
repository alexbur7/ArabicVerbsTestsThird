package com.apakhun.arabicverbstestssecond.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.apakhun.arabicverbstestssecond.App;
import com.apakhun.arabicverbstestssecond.R;
import com.apakhun.arabicverbstestssecond.TablePreview;

import java.util.Locale;

public class TableFragment extends ParentFragment {

    @Override
    protected String getFragmentTag() {
        return TableLayout.class.getSimpleName();
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_table, container, false);
        LinearLayout table1 = view.findViewById(R.id.table1);
        LinearLayout table2 = view.findViewById(R.id.table2);
        LinearLayout table3 = view.findViewById(R.id.table3);
        LinearLayout table4 = view.findViewById(R.id.table4);
        LinearLayout table5 = view.findViewById(R.id.table5);

        table1.setOnClickListener(view1 -> startActivity(new Intent(App.getAppContext(), TablePreview.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .putExtra("table", Locale.getDefault().getLanguage().equals("ru") ? R.mipmap.full_table1_ru : R.mipmap.full_table1_en)));

        table2.setOnClickListener(view1 -> startActivity(new Intent(App.getAppContext(), TablePreview.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .putExtra("table", Locale.getDefault().getLanguage().equals("ru") ? R.mipmap.full_table2_ru : R.mipmap.full_table2_en)));

        table3.setOnClickListener(view1 -> startActivity(new Intent(App.getAppContext(), TablePreview.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .putExtra("table", Locale.getDefault().getLanguage().equals("ru") ? R.mipmap.full_table3_ru : R.mipmap.full_table3_en)));

        table4.setOnClickListener(view1 -> startActivity(new Intent(App.getAppContext(), TablePreview.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .putExtra("table", Locale.getDefault().getLanguage().equals("ru") ? R.mipmap.full_table4_ru : R.mipmap.full_table4_en)));

        table5.setOnClickListener(view1 -> startActivity(new Intent(App.getAppContext(), TablePreview.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .putExtra("table", Locale.getDefault().getLanguage().equals("ru") ? R.mipmap.full_table5_ru : R.mipmap.full_table5_en)));
        return view;
    }
}