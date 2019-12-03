package com.wuba.view.image;

import android.animation.Animator;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.wuba.view.R;

/**
 * author : dongSen
 * date : 2017/9/4
 * desc :
 */
public class HeartTestActivity extends AppCompatActivity {

    private HeartImage heartImage;
    private View layout;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_heart_image);

        heartImage = findViewById(R.id.heart_image);
        layout = findViewById(R.id.heart_view);

        heartImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revealAnim();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void revealAnim() {

        // get the center for the clipping circle
        int cx = (heartImage.getLeft() + heartImage.getRight()) / 2;
        int cy = (heartImage.getTop() + heartImage.getBottom()) / 2;

        // get the final radius for the clipping circle
        float hypot = (float) Math.hypot(layout.getHeight(), layout.getWidth());
        int finalRadius = (int) hypot;

        // create the animator for this view (the start radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(layout, cx, cy, 0, finalRadius);

        // make the view visible and start the animation
        anim.setDuration(1000);
        layout.setVisibility(View.VISIBLE);
        anim.start();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("aaa","onSaveInstanceState");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("aaa","onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("aaa","onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("aaa","onDestroy");

    }
}
