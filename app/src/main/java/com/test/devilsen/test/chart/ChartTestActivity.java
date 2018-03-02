package com.test.devilsen.test.chart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.test.devilsen.test.R;

/**
 * author : dongSen
 * date : 2016-06-06 19:11
 * desc :
 */
public class ChartTestActivity extends AppCompatActivity {

    private CircleChart circle;
    private HorizontalLineChart hLine;
    private VerticalLineChart vLine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_test);

        circle = (CircleChart) findViewById(R.id.circle);
        hLine = (HorizontalLineChart) findViewById(R.id.horizontal_line);
        vLine = (VerticalLineChart) findViewById(R.id.vertical_line);

        circle.setPercentage(70 );
        hLine.setPercentage(70);
        vLine.setPercentage(50);
    }
}
