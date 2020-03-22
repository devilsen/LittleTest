package com.test.devilsen.test.chart;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;

/**
 * author : dongSen
 * date : 2016-06-06 18:58
 * desc : 环形图
 */
public class CircleChart extends Chart {

    private Paint mPaint;
    private Paint mTxtPaint;
    private RectF rectF;

    private int colorWidth = 35;

    private float yellowDegree;
    private float blueDegree;
    private int yellowInt;
    private int blueInt;

    public CircleChart(Context context) {
        super(context);
        init();
    }

    public CircleChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setColor(GRAY);

        mTxtPaint = new Paint();
        mTxtPaint.setTextSize(60);
        mTxtPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mTxtPaint.setTextAlign(Paint.Align.CENTER);

        float r = 100;
        rectF = new RectF(-r, -r, r, r);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        float r = getMeasuredWidth() / 2 - 15;
        rectF.set(-r + getPaddingLeft(), -r + getPaddingTop(), r - getPaddingRight(), r - getPaddingBottom());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);

        drawGrayCircle(canvas);
        drawYellowCircle(canvas);
        drawBlueCircle(canvas);

        drawYellowText(canvas);
        if (blueInt > 0)
            drawBlueText(canvas);
    }

    private void drawGrayCircle(Canvas canvas) {
        mPaint.setStrokeWidth(10);
        mPaint.setColor(GRAY);

        canvas.drawArc(rectF, 0, 360, false, mPaint);
    }

    private void drawYellowCircle(Canvas canvas) {
        mPaint.setStrokeWidth(colorWidth);
        mPaint.setColor(YELLOW);

        canvas.drawArc(rectF, -90, yellowDegree, false, mPaint);
    }

    private void drawBlueCircle(Canvas canvas) {
        mPaint.setStrokeWidth(colorWidth);
        mPaint.setColor(BLUE);

        canvas.drawArc(rectF, yellowDegree - 90, blueDegree, false, mPaint);
    }

    private void drawYellowText(Canvas canvas) {
        mTxtPaint.setColor(YELLOW);
        Paint.FontMetrics fontMetrics = mTxtPaint.getFontMetrics();
        float bottom = fontMetrics.bottom;

        if (blueInt == 0)
            bottom = 0;

        canvas.drawText(yellowInt + "%", 0, -bottom * 2, mTxtPaint);

    }

    private void drawBlueText(Canvas canvas) {
        mPaint.setColor(BLUE);
        mPaint.setStrokeWidth(5);
        canvas.drawLine(-80, 0, 80, 0, mPaint);

        mTxtPaint.setColor(BLUE);
        Paint.FontMetrics fontMetrics = mTxtPaint.getFontMetrics();
        float bottom = fontMetrics.bottom;
        canvas.drawText(blueInt + "%", 0, bottom * 5, mTxtPaint);
    }

    /**
     * author : dongSen
     * date : 16/6/7 下午2:46
     * desc : 设置占比大小
     */
    public void setPercentage(int yellowDegree, int blueDegree) {
        yellowPredetermined = yellowDegree;
        value = yellowDegree + blueDegree;

        startAnim();
    }

    /**
     * author : dongSen
     * date : 16/6/7 下午3:04
     * desc : 设置占比大小
     */
    public void setPercentage(int yellowDegree) {
        blueInt = 0;
        blueDegree = 0;

        yellowPredetermined = yellowDegree;
        value = yellowDegree;
        startAnim();
    }

    float value;
    int yellowPredetermined;
    float temp;

    private void startAnim() {
        ValueAnimator numberAnimator = ValueAnimator.ofFloat(0, value).setDuration(2500);
        numberAnimator.setInterpolator(new FastOutSlowInInterpolator());
        numberAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                temp = (float) animation.getAnimatedValue();
                if (temp > yellowPredetermined) {
                    yellowInt = yellowPredetermined;//二次修正,避免误差
                    blueInt = (int) (temp - yellowPredetermined);
                    blueDegree = blueInt / 100f * 360;
                } else {
                    yellowInt = (int) temp;
                    yellowDegree = yellowInt / 100f * 360;
                }
                invalidate();
            }
        });
        numberAnimator.start();
    }

}
