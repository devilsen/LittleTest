package com.wuba.view.loading.bubble;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * desc : 包裹气泡的ViewGroup
 * date : 2019-05-31
 *
 * @author : dongSen
 */
public class BubbleContainer extends LinearLayout {

    private static final int ANIMATION_DURATION = 700;
    private static final long BOUNDARY_DURATION = 1500;
    private static final int REPEAT_COUNT = 0;
    private static final int RIGHT = 1;
    private static final int LEFT = -1;

    private int[] mColors = {Color.parseColor("#ff3925"),
            Color.parseColor("#ff8c14"),
            Color.parseColor("#0091d7"),
            Color.parseColor("#50c414"),
    };

    private int mRadius = dp2px(3);
    private int mBigBubbleRadius = dp2px(5);
    private int mMinRadius = dp2px(2);
    private int mMaxInterval = dp2px(10);

    private ArrayList<BubbleView> mBubbles;
    private SpringView springView;
    private final int defaultInterval = -mRadius;
    private int mBubbleInterval = defaultInterval;
    private int animationPosition;
    // 边界判断
    private int mBoundaryFlag;
    private boolean mRightFlag = true;
    private boolean mStopFlag;
    private int mWidth;

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
    }

    private void animationController() {
        if (mStopFlag) {
            return;
        }
        springView.setVisibility(VISIBLE);

        int start = animationPosition;
        int target = animationPosition;
        // 向右
        if (mRightFlag) {
            target++;
        } else {// 向左
            target--;
        }
        // 到顶点
        if (target > mBubbles.size() - 1) {
            mBoundaryFlag = RIGHT;
            target--;
            mRightFlag = false;
        } else if (target < 0) {
            mBoundaryFlag = LEFT;
            target++;
            mRightFlag = true;
        } else {
            mBoundaryFlag = 0;
        }
        animationPosition = target;

        setPointLocation(start, target);

        colorAnimation();
        headPointRadiusAnimation(start, target);
        footPointRadiusAnimation();
        pullPointRadiusAnimation();
    }

    private void setPointLocation(int start, int target) {
        BubbleView foot = mBubbles.get(start);
        BubbleView pull = mBubbles.get(target);

        Point footPoint = springView.getFootPoint();
        footPoint.setX(foot.getX() + mRadius);
        footPoint.setColor(mColors[start]);

        Point headPoint = springView.getHeadPoint();
        headPoint.setColor(mColors[target]);
        springView.setIndicatorColor(mColors[target]);

        Point pullPoint = springView.getPullPoint();
        pullPoint.setX(pull.getX() + mRadius);
        pullPoint.setRadius(mRadius);
        pullPoint.setColor(mColors[target]);
    }

    private void headPointRadiusAnimation(int start, int target) {
        BubbleView foot = mBubbles.get(start);
        BubbleView head = mBubbles.get(target);

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(foot.getBubbleX(), head.getBubbleX());
        valueAnimator.setDuration(ANIMATION_DURATION);
        valueAnimator.setRepeatCount(REPEAT_COUNT);

        if (start == mBubbles.size() - 1 || start == 0) {
            valueAnimator.setInterpolator(new AnticipateInterpolator());
        } else {
            valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        }
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                Point headPoint = springView.getHeadPoint();
                headPoint.setX(animatedValue);
                springView.invalidate();
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animationController();
            }
        });
        valueAnimator.start();
    }

    private void footPointRadiusAnimation() {
        ValueAnimator radiusValueAnimator = ValueAnimator.ofFloat(mBigBubbleRadius, mMinRadius);
        radiusValueAnimator.setDuration(ANIMATION_DURATION);
        radiusValueAnimator.setRepeatCount(REPEAT_COUNT);

        radiusValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                Point footPoint = springView.getFootPoint();
                footPoint.setRadius(animatedValue);
            }
        });
        radiusValueAnimator.start();
    }

    private void pullPointRadiusAnimation() {
        ValueAnimator radiusValueAnimator = ValueAnimator.ofFloat(mMinRadius, mRadius);
        radiusValueAnimator.setDuration(ANIMATION_DURATION);
        radiusValueAnimator.setRepeatCount(REPEAT_COUNT);

        radiusValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                Point pullPoint = springView.getPullPoint();
                pullPoint.setRadius(animatedValue);
            }
        });
        radiusValueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Point pullPoint = springView.getPullPoint();
                pullPoint.setRadius(mRadius);
            }
        });
        radiusValueAnimator.start();
    }

    /**
     * 颜色动画
     */
    private void colorAnimation() {
        Point start = springView.getFootPoint();
        Point target = springView.getPullPoint();

        ValueAnimator valueAnimator = ValueAnimator.ofInt(start.getColor(), target.getColor());
        valueAnimator.setDuration(ANIMATION_DURATION);
        valueAnimator.setRepeatCount(REPEAT_COUNT);
        valueAnimator.setEvaluator(HsvEvaluator.getInstance());

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int color = (int) animation.getAnimatedValue();
                springView.getHeadPoint().setColor(color);
            }
        });
        valueAnimator.start();
    }

    /**
     * 到达边界时的动画
     */
    private void boundaryAnimation() {
        int targetDistance;

        // 到达右边界
        if (mBoundaryFlag == RIGHT) {
            targetDistance = mMinRadius;
//            target--;
            mRightFlag = false;
        } else {
            targetDistance = -mMinRadius;
//            target++;
            mRightFlag = true;
        }

        ObjectAnimator valueAnimator = ObjectAnimator.ofFloat(springView, "translationX", 0, targetDistance, 0);
        valueAnimator.setDuration(BOUNDARY_DURATION);
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animationController();
            }
        });
        valueAnimator.start();
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
        springView.setVisibility(GONE);
    }

    public void startBubbleAnimation() {
        mStopFlag = false;
        animationController();
    }

    public void reset() {
        resetView();
        animationPosition = 0;
    }
}
