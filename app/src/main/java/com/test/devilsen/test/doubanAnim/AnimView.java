package com.test.devilsen.test.doubanAnim;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * author : dongSen
 * date : 2016-05-27 11:23
 * desc :
 */
public class AnimView extends View {

    private ValueAnimator animator;
    private float animatedValue;
    private int duration = 5000;
    private TimeInterpolator interpolator = new DecelerateInterpolator();

    private Paint mPaint;

    public AnimView(Context context) {
        super(context);
        initPaint();
        initAnim();
    }

    public AnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        initAnim();
    }

    public AnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        initAnim();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);//圆角笔触
        mPaint.setColor(Color.rgb(97, 195, 109));
        mPaint.setStrokeWidth(15);

    }

    //宽高
    private float mWidth;
    private float mHeight;


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mWidth = right;
        mHeight = bottom;
    }

    private void initAnim() {
        if (animator != null && animator.isRunning()) {
            animator.cancel();
            animator.start();
        } else {
            animator = ValueAnimator.ofFloat(0, 855).setDuration(duration);
            animator.setInterpolator(interpolator);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    animatedValue = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            animator.start();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2); //将canvas的坐标点移到中心

        doubanAnim(canvas);
    }

    float startAngle = 0, sweepAngle = 0;

    private void doubanAnim(Canvas canvas) {
        float point = 50;
        float r = point * (float) Math.sqrt(2);
        RectF rectF = new RectF(-r, -r, r, r);
        canvas.save();

        // rotate
        if (animatedValue >= 135) {
            canvas.rotate(animatedValue - 135);
        }

        // draw mouth
        if (animatedValue < 135) {
            startAngle = animatedValue + 5;
            sweepAngle = 170 + animatedValue / 3;
        } else if (animatedValue < 270) {
            startAngle = 135 + 5;
            sweepAngle = 170 + animatedValue / 3;
        } else if (animatedValue < 630) {
            startAngle = 135 + 5;
            sweepAngle = 260 - (animatedValue - 270) / 5;
        } else if (animatedValue < 720) {
            startAngle = 135 - (animatedValue - 630) / 2 + 5;
            sweepAngle = 260 - (animatedValue - 270) / 5;
        } else {
            startAngle = 135 - (animatedValue - 630) / 2 - (animatedValue - 720) / 6 + 5;
            sweepAngle = 170;
        }
        canvas.drawArc(rectF, startAngle, sweepAngle, false, mPaint);

        // draw eye
        canvas.drawPoints(new float[]{
                -point, -point
                , point, -point
        }, mPaint);

        canvas.restore();
    }
}
