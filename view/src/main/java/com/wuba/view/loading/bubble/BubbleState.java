package com.wuba.view.loading.bubble;

import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;

/**
 * desc :
 * date : 2019-06-06
 *
 * @author : dongSen
 */
class BubbleState {

    private ArrayList<BubbleView> bubbleViews;
    private Point footPoint;
    private Point pullPoint;
    private Point headPoint;

    private float x0;
    private float x1;
    private float x2;
    private float x3;

    private boolean back;
    private ValueAnimator colorValueAnimator;

    BubbleState(SpringView springView) {
        footPoint = springView.getFootPoint();
        headPoint = springView.getHeadPoint();
        pullPoint = springView.getPullPoint();
    }

    void setBubbles(ArrayList<BubbleView> bubbles) {
        bubbleViews = bubbles;

        x0 = bubbles.get(0).getBubbleX();
        x1 = bubbles.get(1).getBubbleX();
        x2 = bubbles.get(2).getBubbleX();
        x3 = bubbles.get(3).getBubbleX();
    }

    void setValue(float animatedValue) {
        if (back) {
            back(animatedValue);
        } else {
            forward(animatedValue);
        }
    }

    private void forward(float animatedValue) {
        if (x0 < animatedValue && animatedValue < x1) {
            state1();
        } else if (x1 <= animatedValue && animatedValue < x2) {
            state2();
        } else if (x2 <= animatedValue && animatedValue < x3) {
            state3();
        } else if (animatedValue >= x3) {
            state4();
            back = true;
        }
    }

    private void back(float animatedValue) {
        if (x0 < animatedValue && animatedValue < x1) {
            state7();
        } else if (x1 <= animatedValue && animatedValue < x2) {
            state6();
        } else if (x2 <= animatedValue && animatedValue < x3) {
            state5();
        } else if (animatedValue <= x0) {
            state8();
            back = false;
        }
    }

    void initState() {
        state1();
    }

    /**
     * 0-1
     */
    private void state1() {
        BubbleView foot = bubbleViews.get(0);
        BubbleView pull = bubbleViews.get(1);

        setState(pull, foot);
    }

    /**
     * 1-2
     */
    private void state2() {
        BubbleView foot = bubbleViews.get(1);
        BubbleView pull = bubbleViews.get(2);

        setState(pull, foot);
    }

    /**
     * 2-3
     */
    private void state3() {
        BubbleView foot = bubbleViews.get(2);
        BubbleView pull = bubbleViews.get(3);

        setState(pull, foot);
    }

    /**
     * 到最右
     */
    private void state4() {
        BubbleView foot = bubbleViews.get(3);
        BubbleView pull = bubbleViews.get(3);

        setState(pull, foot);
    }

    /**
     * 返回
     * 3->2
     */
    private void state5() {
        BubbleView pull = bubbleViews.get(2);
        BubbleView foot = bubbleViews.get(3);

        setState(pull, foot);
    }

    /**
     * 2->1
     */
    private void state6() {
        BubbleView pull = bubbleViews.get(1);
        BubbleView foot = bubbleViews.get(2);

        setState(pull, foot);
    }

    /**
     * 1->0
     */
    private void state7() {
        BubbleView pull = bubbleViews.get(0);
        BubbleView foot = bubbleViews.get(1);

        setState(pull, foot);
    }

    /**
     * 最左
     */
    private void state8() {
        BubbleView pull = bubbleViews.get(0);
        BubbleView foot = bubbleViews.get(0);

        setState(pull, foot);
    }

    private void setState(BubbleView pull, BubbleView foot) {
        setX(pull, foot);
        setColor(pull, foot);
    }

    private void setX(BubbleView pull, BubbleView foot) {
        if (pull.getBubbleX() == pullPoint.getX()) {
            return;
        }
        pullPoint.setX(pull.getBubbleX());
        footPoint.setX(foot.getBubbleX());
    }

    private void setColor(BubbleView pull, BubbleView foot) {
        setHeadColor(pull, foot);

        if (pull.getBubbleColor() == pullPoint.getColor()) {
            return;
        }
        pullPoint.setColor(pull.getBubbleColor());
        footPoint.setColor(foot.getBubbleColor());
    }

    private void setHeadColor(BubbleView pull, BubbleView foot) {
        if (Math.abs(headPoint.getX() - foot.getBubbleX()) > headPoint.getRadius()) {
            headPoint.setColor(pull.getBubbleColor());
        } else {
            headPoint.setColor(foot.getBubbleColor());
        }
    }

    private void colorAnimation(int start, int end) {
        if (colorValueAnimator != null && colorValueAnimator.isRunning()) {
            return;
        }
        colorValueAnimator = ValueAnimator.ofInt(start, end);
        colorValueAnimator.setDuration(1800);
        colorValueAnimator.setInterpolator(new LinearInterpolator());
        colorValueAnimator.setEvaluator(HsvEvaluator.getInstance());

        colorValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int color = (int) animation.getAnimatedValue();
                headPoint.setColor(color);
            }
        });
        colorValueAnimator.start();
    }

}
