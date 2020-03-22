package com.test.devilsen.test.lifecycler;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
