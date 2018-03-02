package com.test.devilsen.test.lifecycler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.test.devilsen.test.R;

/**
 * desc :
 * date : 2017/12/21
 *
 * @author : dongSen
 */
public class TranslateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_recycler_view);
    }
}
