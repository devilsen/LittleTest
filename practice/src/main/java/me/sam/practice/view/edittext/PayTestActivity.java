package me.sam.practice.view.edittext;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * desc :
 * date : 2019-05-07
 *
 * @author : dongSen
 */
public class PayTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PayEditText(this));
    }
}
