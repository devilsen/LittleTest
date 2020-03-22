package com.wuba.view.badgeicon;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.wuba.view.R;

/**
 * author : dongSen
 * date : 2017/4/24
 * desc :
 */
public class BadgeTestActivity extends AppCompatActivity implements View.OnClickListener {

    private BadgeTextView badge;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badge);

        badge = (BadgeTextView) findViewById(R.id.badge_text_test);

        badge.setOnClickListener(this);

    }

    boolean test = true;

    @Override
    public void onClick(View v) {
        if (test) {
            badge.setBadgeNumber(10);
        } else {
            badge.setBadgeNumber(0);
        }
        badge.setChecked(test);

        test = !test;
    }
}
