package me.sam.practice.view.touch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import me.sam.practice.R;

/**
 * desc :
 * date : 2019-05-07
 *
 * @author : dongSen
 */
public class TouchViewActivity extends AppCompatActivity {

    private TextView mNoticeTxt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_view);

        mNoticeTxt = findViewById(R.id.text_view_touch_notice);
        TouchView touchView = findViewById(R.id.touch_view);

        touchView.setTouchRangeListener(new TouchView.TouchRangeListener() {
            @Override
            public void onTouch(String range) {
                mNoticeTxt.setText(range);
            }
        });
    }
}
