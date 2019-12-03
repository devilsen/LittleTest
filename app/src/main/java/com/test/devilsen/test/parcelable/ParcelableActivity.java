package com.test.devilsen.test.parcelable;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.test.devilsen.test.R;

/**
 * desc :
 * date : 2018/6/26
 *
 * @author : dongSen
 */
public class ParcelableActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcelable_test);

        Intent data = getIntent();
        TestBean bean = data.getParcelableExtra("data");

        Log.e("data", bean.toString() + "   ");
    }
}
