package me.sam.practice.anim.simple;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import me.sam.practice.R;

/**
 * desc :
 * date : 2019-05-08
 *
 * @author : dongSen
 */
public class ImageAnimationActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_image);

        imageView = findViewById(R.id.image_view_anim_test);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translate();
            }
        });
    }

    private void translate() {
        ObjectAnimator translateAnimator = ObjectAnimator.ofFloat(imageView, "translationX", 0, 200, 0);
        translateAnimator.setDuration(1200);
        translateAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        translateAnimator.start();

        translateAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                rotate();
            }
        });
    }

    private void rotate() {
        ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0, 360);
        rotationAnimator.setDuration(1200);
        rotationAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        rotationAnimator.start();

        rotationAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                scale();
            }
        });
    }

    private void scale() {
        ObjectAnimator scaleAnimator = ObjectAnimator.ofFloat(imageView, "scaleX", 1f, 2f, 1f);
        scaleAnimator.setDuration(1200);
        scaleAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleAnimator.start();
    }
}
