package com.apakhun.arabicverbstestssecond;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

public class TablePreview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_preview);
        getSupportActionBar().hide();

        SubsamplingScaleImageView imageView = findViewById(R.id.table);
        imageView.setImage(ImageSource.resource(getIntent().getIntExtra("table", 1)));
    }
}
