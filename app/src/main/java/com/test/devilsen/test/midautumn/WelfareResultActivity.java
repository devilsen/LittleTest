package com.test.devilsen.test.midautumn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.test.devilsen.test.R;

/**
 * author : dongSen
 * date : 2016-08-24 14:45
 * desc :
 */
public class WelfareResultActivity extends AppCompatActivity {

    private TextView testEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welfare_mid_autumn_result);

        testEdit = (TextView) findViewById(R.id.tttt);

//        testEdit.setFocusableInTouchMode(false);
//        testEdit.setFocusable(false);
//        testEdit.requestFocus();

//        testEdit.setInputType(InputType.TYPE_NULL);
    }
}
