package com.wuba.view.scrollnumber;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

/**
 * author : dongSen
 * date : 2016-07-22 16:43
 * desc :
 */
public class ScrollNumber extends View {

    private Paint mPaint;
    private Interpolator mInterpolator = new FastOutSlowInInterpolator();


    private int mTextCenterX = 200;
    private int mTextHeight;
    private Rect mTextBounds = new Rect();
    private int mTextSize = sp2px(130);
    private int mTextColor = 0xFF000000;

    public ScrollNumber(Context context) {
        this(context, null);
    }

    public ScrollNumber(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollNumber(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);

        measureTextHeight();

//        setNumber(0, 6, 1000);

//        startAnim();

    }

    public void setTextSize(int textSize) {
        this.mTextSize = sp2px(textSize);
        mPaint.setTextSize(mTextSize);
        measureTextHeight();
        requestLayout();
        invalidate();
    }

    public void setTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
        mPaint.setColor(mTextColor);
        invalidate();
    }

    public void setInterpolator(Interpolator interpolator) {
        mInterpolator = interpolator;
    }

    private void measureTextHeight() {
        mPaint.getTextBounds("0", 0, 1, mTextBounds);
        mTextHeight = mTextBounds.height();
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int width = measureWidth(widthMeasureSpec);
//        int height = measureHeight(heightMeasureSpec);
//        setMeasuredDimension(width, height);
//
//        mTextCenterX = (getMeasuredWidth() - getPaddingLeft() - getPaddingRight()) / 2;
//    }
//
//    private int measureHeight(int measureSpec) {
//        int mode = MeasureSpec.getMode(measureSpec);
//        int val = MeasureSpec.getSize(measureSpec);
//        int result = 0;
//        switch (mode) {
//            case MeasureSpec.EXACTLY:
//                result = val;
//                break;
//            case MeasureSpec.AT_MOST:
//            case MeasureSpec.UNSPECIFIED:
//                mPaint.getTextBounds("0", 0, 1, mTextBounds);
//                result = mTextBounds.height();
//                break;
//        }
//        result = mode == MeasureSpec.AT_MOST ? Math.min(result, val) : result;
//        return result + getPaddingTop() + getPaddingBottom() + dp2px(40);
//    }
//
//    private int measureWidth(int measureSpec) {
//        int mode = MeasureSpec.getMode(measureSpec);
//        int val = MeasureSpec.getSize(measureSpec);
//        int result = 0;
//        switch (mode) {
//            case MeasureSpec.EXACTLY:
//                result = val;
//                break;
//            case MeasureSpec.AT_MOST:
//            case MeasureSpec.UNSPECIFIED:
//                mPaint.getTextBounds("0", 0, 1, mTextBounds);
//                result = mTextBounds.width();
//                break;
//        }
//        result = mode == MeasureSpec.AT_MOST ? Math.min(result, val) : result;
//        return result + getPaddingLeft() + getPaddingRight() + 15;
//    }

    int self;
    int next;

    @Override
    protected void onDraw(Canvas canvas) {

        drawSelf(canvas);

        if (mOffset > 400) {
//            drawNext(canvas);
        }

        if (mOffset >= 1200) {
            self++;
            startAnim();
        }
        if (self == 10) {
            self = 0;
        }
    }

    private void drawSelf(Canvas canvas) {
        canvas.drawText(self + "", mTextCenterX, mOffset- mTextHeight, mPaint);

    }

    private void drawNext(Canvas canvas) {
        canvas.drawText((self) + "", mTextCenterX, mOffset - mTextHeight - 100, mPaint);
    }


    private float mOffset = 250;

    public void setTarget() {
        startAnim();
    }

    private void startAnim() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1200).setDuration(800);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mOffset = (float) animation.getAnimatedValue();
                Log.e("mOffset", mOffset + "");
                invalidate();
            }
        });
        animator.start();
    }

    private int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }

    private int sp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                dpVal, getResources().getDisplayMetrics());
    }

}
