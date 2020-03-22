package com.wuba.view.midautumn;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;

import com.wuba.view.R;

import java.util.Random;

/**
 * author : dongSen
 * date : 2016-08-23 14:01
 * desc : 流星
 */
public class MeteorImage extends androidx.appcompat.widget.AppCompatImageView {

    Random random = new Random();
    int position;

    public MeteorImage(Context context) {
        super(context);
        init(context);
    }

    public MeteorImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MeteorImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setBackground(context.getResources().getDrawable(R.drawable.welfare_meteor));
        position = random.nextInt(500) + 400;

        final ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(this, "translationX", position, -position * 2);
        final ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(this, "translationY", -position, position * 2);
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(this, "alpha", 1, 0);


        scaleXAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        scaleYAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        alphaAnimator.setRepeatCount(ObjectAnimator.INFINITE);

        final AnimatorSet set = new AnimatorSet();
        set.setDuration(3000);
        set.playTogether(scaleXAnimator, scaleYAnimator, alphaAnimator);
        set.setStartDelay(random.nextInt(2) * 1000);
        set.start();

        scaleXAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                position = random.nextInt(500) + 400;
                scaleXAnimator.setFloatValues(position, -position * 2);
                scaleYAnimator.setFloatValues(-position, position * 2);
                set.end();
                set.setStartDelay(random.nextInt(2) * 1000);
                set.start();
            }
        });



    }

}
