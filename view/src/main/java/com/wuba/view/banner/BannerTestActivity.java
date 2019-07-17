package com.wuba.view.banner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wuba.view.R;

/**
 * desc :
 * date : 2019-06-03
 *
 * @author : dongSen
 */
public class BannerTestActivity extends AppCompatActivity {

    private static final String TEXT = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa1234567890qwertyuiopasdfghjklaaa1234567890qwertyuiopasdfghjklaaa1234567890qwertyuiopasdfghjklaaa1234567890qwertyuiopasdfghjklaaa1234567890qwertyuiopasdfghjklaaa1234567890qwertyuiopasdfghjklaaa1234567890qwertyuiopasdfghjkl";

    private TextBanner textBanner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_test);

        textBanner = findViewById(R.id.text_banner);
        textBanner.setContent(TEXT);
    }

    public void start(View view) {

        textBanner.showBanner();

    }
}
