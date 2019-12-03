package com.wuba.view.midautumn;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;

import com.wuba.view.R;

import java.util.Random;

/**
 * author : dongSen
 * date : 2016-08-23 14:01
 * desc :
 */
public class StarImage extends androidx.appcompat.widget.AppCompatImageView {

    public StarImage(Context context) {
        super(context);
        init(context);
    }

    public StarImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StarImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setBackground(context.getResources().getDrawable(R.drawable.welfare_big_star));
        Random random = new Random();

        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(this, "scaleX", 1, 1.2f, 0);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(this, "scaleY", 1, 1.2f, 0);
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(this, "alpha", 1, 1, 0.5f);
        ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(this, "rotation", random.nextInt(360));

        scaleXAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        scaleYAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        alphaAnimator.setRepeatCount(ObjectAnimator.INFINITE);

        scaleXAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        scaleYAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        alphaAnimator.setRepeatMode(ObjectAnimator.REVERSE);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(3000);
        set.playTogether(scaleXAnimator, scaleYAnimator, alphaAnimator, rotationAnimator);
        set.setStartDelay(random.nextInt(5) * 1000);
        set.start();

    }

}
