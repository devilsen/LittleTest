package com.wuba.view.watermark;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.wuba.view.R;

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

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("aaaa","onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("aaaa","onDestroy");
    }
}
