package com.test.devilsen.test.chart.mpandroid;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.test.devilsen.test.R;

import java.util.ArrayList;

/**
 * author : dongSen
 * date : 2016-06-01 15:13
 * desc :
 */
public class PieChatActivity extends AppCompatActivity implements OnChartValueSelectedListener {

    private PieChart mChart;


    protected String[] mParties = new String[]{
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chat);

        mChart = (PieChart) findViewById(R.id.pie_chart);

        initChart();
    }

    private void initChart() {
//        mChart.setUsePercentValues(true);
//        mChart.setDescription("");
//        mChart.setExtraOffsets(5, 10, 5, 5);
//
//        mChart.setDragDecelerationFrictionCoef(0.95f);

        mChart.setCenterText("50%");
        mChart.setCenterTextSize(30);
        mChart.setCenterTextRadiusPercent(50);
//
//        mChart.setDrawHoleEnabled(true);
//        mChart.setHoleColor(Color.WHITE);

//        mChart.setTransparentCircleColor(Color.WHITE);
//        mChart.setTransparentCircleAlpha(110);
//
        //设置中心的圆
        mChart.setHoleRadius(60f);
        mChart.setTransparentCircleRadius(61f);
//
//        mChart.setDrawCenterText(true);

//        mChart.setRotationAngle(0);
//        // enable rotation of the chart by touch
//        mChart.setRotationEnabled(true);
//        mChart.setHighlightPerTapEnabled(true);
//
//        // mChart.setUnit(" €");
//        // mChart.setDrawUnitsInChart(true);
//
        // add a selection listener
        mChart.setOnChartValueSelectedListener(this);

        setData(2, 100);

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);

//        Legend l = mChart.getLegend();
//        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
//        l.setXEntrySpace(7f);
//        l.setYEntrySpace(0f);
//        l.setYOffset(0f);
    }


    private void setData(int count, float range) {

        float mult = range;

        ArrayList<Entry> yVals1 = new ArrayList<>();

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        for (int i = 0; i < count + 1; i++) {
            yVals1.add(new Entry((float) (Math.random() * mult) + mult / 5, i));
        }

        //图标注
        ArrayList<String> xVals = new ArrayList<>();

        for (int i = 0; i < count + 1; i++)
            xVals.add(mParties[i % mParties.length]);

        PieDataSet dataSet = new PieDataSet(yVals1, "Election Results");
//        dataSet.setSliceSpace(3f);
//        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        mChart.setData(data);

        // undo all highlights
//        mChart.highlightValues(null);

        mChart.invalidate();
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getVal() + ", xIndex: " + e.getXIndex()
                        + ", DataSet index: " + dataSetIndex);

        mChart.setCenterText(e.getVal() + "");
    }

    @Override
    public void onNothingSelected() {

    }
}
