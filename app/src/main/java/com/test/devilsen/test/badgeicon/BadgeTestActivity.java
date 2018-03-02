package com.test.devilsen.test.badgeicon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.test.devilsen.test.R;

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
