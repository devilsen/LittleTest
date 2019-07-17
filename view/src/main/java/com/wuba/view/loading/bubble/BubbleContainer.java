package com.wuba.view.loading.bubble;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * desc : 包裹气泡的ViewGroup
 * date : 2019-05-31
 *
 * @author : dongSen
 */
public class BubbleContainer extends LinearLayout {

    private static final int ANIMATION_DURATION = 16000;
    private static final int REPEAT_COUNT = Animation.INFINITE;

    private int[] mColors = {Color.parseColor("#ff3925"),
            Color.parseColor("#ff8c14"),
            Color.parseColor("#0091d7"),
            Color.parseColor("#50c414"),
    };

    private int mRadius = dp2px(3);
    private int mBigBubbleRadius = dp2px(5);
    private int mMaxInterval = dp2px(8);

    private ArrayList<BubbleView> mBubbles;
    private SpringView springView;
    private final int defaultInterval = -mRadius;
    private int mBubbleInterval = defaultInterval;
    private boolean mStopFlag;
    private int mWidth;
    private BubbleState mBubbleState;
    private ValueAnimator valueAnimator;

    public BubbleContainer(Context context) {
        this(context, null);
    }

    public BubbleContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BubbleContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(HORIZONTAL);
        setWillNotDraw(false);

        mBubbles = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            BubbleView bubble = new BubbleView(getContext());
            bubble.setColor(mColors[i]);
            bubble.setRadius(mRadius);

            LinearLayout.LayoutParams params = new LayoutParams(2 * mRadius, 2 * mRadius);
            mBubbles.add(bubble);
            addView(bubble, params);
        }
    }

    private void addPointView(int width, int height) {
        springView = new SpringView(getContext());
        springView.setIndicatorColor(mColors[0]);
        mWidth = width;

        resetView();

        LinearLayout.LayoutParams params = new LayoutParams(width, height);
        addView(springView, params);
    }

    private void resetView() {
        Point headPoint = springView.getHeadPoint();
        Point footPoint = springView.getFootPoint();
        Point pullPoint = springView.getPullPoint();

        int x = (mWidth >> 1) - 3 * (mBubbleInterval + mRadius);
        int y = mBigBubbleRadius;

        headPoint.setX(x);
        headPoint.setY(y);
        headPoint.setRadius(mBigBubbleRadius);

        footPoint.setX(x);
        footPoint.setY(y);
        footPoint.setRadius(mRadius);

        pullPoint.setX(x + 2 * (mRadius + mBubbleInterval));
        pullPoint.setY(y);
        pullPoint.setRadius(mRadius);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int middleWidth = (r - l) / 2;
        int middleHeight = (b - t) / 2;

        if (springView == null) {
            addPointView(r - l, b - t);
        }

        int childCount = mBubbles.size();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            BubbleView bubble = mBubbles.get(i);
            layoutChildBubble(bubble, i, middleWidth, middleHeight);

            bubble.setBubbleX(child.getX() + mRadius);
            bubble.setBubbleY(child.getY() + mRadius);
        }

        springView.layout(l, middleHeight - mBigBubbleRadius, r, middleHeight + mBigBubbleRadius);

        if (mBubbleState == null) {
            mBubbleState = new BubbleState(springView);
            mBubbleState.setBubbles(mBubbles);
        }
    }

    private void animationController() {
        if (mStopFlag) {
            return;
        }
        springView.setVisibility(VISIBLE);

        setPointLocation();

        headPointRadiusAnimation();
        if (!valueAnimator.isRunning()) {
            valueAnimator.start();
        }
    }

    private void setPointLocation() {
        Point headPoint = springView.getHeadPoint();
        headPoint.setColor(mColors[0]);
        springView.setIndicatorColor(mColors[0]);

        mBubbleState.initState();
    }

    private void headPointRadiusAnimation() {
        if (valueAnimator != null) {
            return;
        }
        float starX = mBubbles.get(0).getBubbleX();
        float endX = mBubbles.get(3).getBubbleX();

        valueAnimator = ValueAnimator.ofFloat(starX, endX, endX + mRadius, starX, starX - mRadius, starX);
        valueAnimator.setDuration(ANIMATION_DURATION);
        valueAnimator.setRepeatCount(REPEAT_COUNT);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                Point headPoint = springView.getHeadPoint();
                headPoint.setX(animatedValue);

                mBubbleState.setValue(animatedValue);
                springView.invalidate();
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mStopFlag = true;
            }
        });
    }

    private void layoutChildBubble(BubbleView bubble, int position, int middleWidth, int middleHeight) {
        int distance = mBubbleInterval + mRadius;
        switch (position) {
            case 0:
                bubble.layout(middleWidth - 3 * distance - mRadius,
                        middleHeight - mRadius,
                        middleWidth - 3 * distance + mRadius,
                        middleHeight + mRadius);
                break;
            case 1:
                bubble.layout(middleWidth - distance - mRadius,
                        middleHeight - mRadius,
                        middleWidth - distance + mRadius,
                        middleHeight + mRadius);
                break;
            case 2:
                bubble.layout(middleWidth + mBubbleInterval,
                        middleHeight - mRadius,
                        middleWidth + distance + mRadius,
                        middleHeight + mRadius);
                break;
            case 3:
                bubble.layout(middleWidth + 3 * mBubbleInterval + 2 * mRadius,
                        middleHeight - mRadius,
                        middleWidth + 3 * mBubbleInterval + 4 * mRadius,
                        middleHeight + mRadius);
                break;
        }
    }

    public void setBubbleInterval(int interval) {
        if (interval > mMaxInterval || mBubbleInterval == interval) {
            return;
        }
        if (interval < 0) {
            mBubbleInterval = defaultInterval;
            requestLayout();
            return;
        }
        stopAnimation();
        mBubbleInterval = interval - mRadius;
        requestLayout();
    }

    public int dp2px(float dpValue) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5F);
    }

    public void stopAnimation() {
        mStopFlag = true;
        if (valueAnimator != null) {
            valueAnimator.end();
        }
        if (springView != null) {
            springView.setVisibility(GONE);
        }
    }

    public void startBubbleAnimation() {
        mStopFlag = false;
        animationController();
    }

    public void reset() {
        resetView();
    }
}
