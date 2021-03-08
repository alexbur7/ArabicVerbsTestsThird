package com.apakhun.arabicverbstestssecond.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.apakhun.arabicverbstestssecond.App;
import com.apakhun.arabicverbstestssecond.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.List;

public class ProgressPieChart extends PieChart {

    public ProgressPieChart(Context context) {
        super(context);
    }

    public ProgressPieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressPieChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private int succeedCount;
    private int failedCount;
    private int totalQuestions;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private final List<PieEntry> entries = new ArrayList<>(2);

    public void setSucceedCount(int succeedCount) {
        this.succeedCount = succeedCount;
        notifyUpdateData();
    }

    public void setFailedCount(int failedCount) {
        this.failedCount = failedCount;
        notifyUpdateData();
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
        notifyUpdateData();
    }

    public void notifyUpdateData() {
        entries.get(0).setY(succeedCount);
        entries.get(1).setY(failedCount);
        entries.get(2).setY(totalQuestions - (succeedCount + failedCount));
        setCenterText(succeedCount + "/" + totalQuestions);
        setData(getData());
        postInvalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(getCenterCircleBox().getX(), getCenterCircleBox().getY(), getRadius(), paint);
    }

    public void initChart() {
        entries.add(new PieEntry(0)); //succeed questions
        entries.add(new PieEntry(0)); //failed questions
        entries.add(new PieEntry(100)); //total questions
        setUsePercentValues(true);
        getDescription().setEnabled(false);
        getLegend().setEnabled(false);

        setDragDecelerationFrictionCoef(0.95f);
        setCenterText(String.valueOf(0));
        setCenterTextSize(10f);
        setCenterTextColor(App.getRes().getColor(R.color.colorDarkBlue));
        setDrawHoleEnabled(true);
        setHoleColor(Color.TRANSPARENT);
        setHoleRadius(70f);
        setTransparentCircleRadius(70f);

        setDrawCenterText(true);
        setRotationAngle(0);
        setRotationEnabled(false);
        setHighlightPerTapEnabled(false);

        PieDataSet dataSet = new PieDataSet(entries, "Election Results");

        dataSet.setDrawIcons(false);
        dataSet.setDrawValues(false);
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();
        final int[] VORDIPLOM_COLORS = ColorTemplate.VORDIPLOM_COLORS;
        VORDIPLOM_COLORS[2] = App.getRes().getColor(R.color.colorWhite);
        for (int c : VORDIPLOM_COLORS)
            colors.add(c);

        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        setData(data);
        animateY(300);

        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(App.getRes().getColor(R.color.colorDarkBlue));
    }
}

