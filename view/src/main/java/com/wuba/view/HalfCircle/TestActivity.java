package com.wuba.view.HalfCircle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wuba.view.R;


/**
 * author : dongSen
 * date : 2016-07-01 16:24
 * desc :
 */
public class TestActivity extends AppCompatActivity {

    private HalfCircle circle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_test);

        circle = (HalfCircle) findViewById(R.id.test_circle);

        CountDown countDown = CountDown.getInstance();
        countDown.setCountDownListener(new CountDown.OnCountDownListener() {
            @Override
            public void lastTime(String time) {
                circle.setText(time);
            }

            @Override
            public void timePercent(float percent) {
                circle.setPercent(percent);
            }
        });
//        countDown.setTotalTime();
        countDown.start();
    }
}
