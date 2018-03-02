package com.test.devilsen.test.watermark;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.test.devilsen.test.R;

/**
 * desc :
 * date : 2018/3/2
 *
 * @author : dongSen
 */
public class WaterMarkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watermark);

        WaterMarkView waterMarkView = findViewById(R.id.watermark);

        waterMarkView.setText("liuzhenshisb");
    }
}
