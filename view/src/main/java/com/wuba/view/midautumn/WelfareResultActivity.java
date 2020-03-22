package com.wuba.view.midautumn;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import com.wuba.view.R;

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
