package com.wuba.view.image;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.DecelerateInterpolator;

import com.wuba.view.R;


/**
 * author : dongSen
 * date : 2017/9/4
 * desc :
 */
public class HeartImage extends androidx.appcompat.widget.AppCompatImageView {
    public HeartImage(Context context) {
        super(context);
        init(context);
    }

    public HeartImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HeartImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setImageResource(R.mipmap.ic_heart_red);
        initAnim();
    }

    private void initAnim() {
        ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(this, "scaleX", 0.7f, 1f);
        animatorScaleX.setRepeatCount(ValueAnimator.INFINITE);
        animatorScaleX.setRepeatMode(ValueAnimator.REVERSE);

        ObjectAnimator animatorScaleY = ObjectAnimator.ofFloat(this, "scaleY", 0.7f, 1f);
        animatorScaleY.setRepeatCount(ValueAnimator.INFINITE);
        animatorScaleY.setRepeatMode(ValueAnimator.REVERSE);

        ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(this, "alpha", 0.7f, 1f);
        animatorAlpha.setRepeatCount(ValueAnimator.INFINITE);
        animatorAlpha.setRepeatMode(ValueAnimator.REVERSE);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorScaleX, animatorScaleY, animatorAlpha);
        animatorSet.setDuration(600);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.start();
    }


}
