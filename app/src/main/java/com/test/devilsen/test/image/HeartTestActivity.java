package com.test.devilsen.test.image;

import android.animation.Animator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.test.devilsen.test.R;

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

        heartImage = (HeartImage) findViewById(R.id.heart_image);
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


}
