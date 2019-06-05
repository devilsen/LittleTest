package com.wuba.view.loading.bubble2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import com.wuba.view.loading.bubble.BubbleView;

import java.util.ArrayList;

/**
 * desc :
 * date : 2019-06-04
 *
 * @author : dongSen
 */
public class BubbleContainerOld extends LinearLayout {

    private int[] mColors = {Color.parseColor("#ff3925"),
            Color.parseColor("#ff8c14"),
            Color.parseColor("#0091d7"),
            Color.parseColor("#50c414"),
    };

    private int mRadius = dp2px(8);
    private int mBubbleInterval = dp2px(5);
    private ArrayList<BubbleView> mBubbles;
    private BubbleCircular mCircular;
    private Paint bezierPaint;
    private boolean mBackFlag;

    public BubbleContainerOld(Context context) {
        this(context, null);
    }

    public BubbleContainerOld(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BubbleContainerOld(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        setWillNotDraw(false);

        mCircular = new BubbleCircular(mRadius + mBubbleInterval);

        mBubbles = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            BubbleView bubble = new BubbleView(getContext());
            bubble.setColor(mColors[i]);
            bubble.setRadius(mRadius);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(2 * mRadius, 2 * mRadius);
            params.leftMargin = mBubbleInterval;
            params.rightMargin = mBubbleInterval;
            mBubbles.add(bubble);
            addView(bubble, params);
        }

        bezierPaint = new Paint();
        bezierPaint.setColor(mColors[0]);
        bezierPaint.setAntiAlias(true);
        bezierPaint.setStyle(Paint.Style.FILL);

        mCircular.setPaint(bezierPaint);
//        startAnimator(0);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            BubbleView bubble = mBubbles.get(i);
            bubble.setBubbleX(child.getX());
            bubble.setBubbleY(child.getY());

            if (i == 0) {
                mCircular.setCenter(child.getX() + mRadius, child.getY() + mRadius);
                mCircular.initControlPoint();
            }
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        mCircular.drawCircle(canvas, bezierPaint);
        super.onDraw(canvas);
    }

    /**
     * 切换动画
     */
    public void startAnimator(int currentPosition) {
        if (currentPosition == mBubbles.size() - 1) {
            mBackFlag = true;
        } else if (currentPosition == 0) {
            mBackFlag = false;
        }

        int targetPosition = currentPosition;
        if (mBackFlag) {
            targetPosition--;
        } else {
            targetPosition++;
        }

        mCircular.setCurrentAndTarget(mBubbles.get(currentPosition).getPointF(),
                mBubbles.get(targetPosition).getPointF());

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, targetPosition > currentPosition ? 1 : -1);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                mCircular.setProgress(animatedValue);
                postInvalidate();
            }
        });

        final int finalTargetPosition = targetPosition;
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mCircular.setColor(mColors[finalTargetPosition]);
                mCircular.resetCircular(mBubbles.get(finalTargetPosition).getPointF());
                startAnimator(finalTargetPosition);
            }
        });

        valueAnimator.setDuration(1200);
        valueAnimator.start();
    }

    public int dp2px(float dpValue) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5F);
    }
}
