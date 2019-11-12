package com.test.devilsen.test;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * author : dongSen
 * date : 2016-06-27 18:09
 * desc :
 */
public class VectorTestActivity extends AppCompatActivity {

    private ImageView vectorTest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poker);

        vectorTest = (ImageView) findViewById(R.id.vector_anim_test_img);
        Drawable drawable = vectorTest.getDrawable();
        ((Animatable) drawable).start();
    }
}
