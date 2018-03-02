package com.test.devilsen.test.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * author : dongSen
 * date : 2016-06-07 15:20
 * desc : 横向线性占比
 */
public class HorizontalLineChart extends Chart {

    private static final int LINE_WIDTH = 15;

    private Paint mPaint;
    private int paddingLeft;
    private int paddingRight;
    private int mYpostion;

    private int degree;

    public HorizontalLineChart(Context context) {
        super(context);
        init();
    }

    public HorizontalLineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HorizontalLineChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(LINE_WIDTH);
        mPaint.setColor(GRAY);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.EXACTLY) {
            setMeasuredDimension(widthSpecSize, heightSpecSize);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mWidth, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, mHeight);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mYpostion = mHeight / 2;

        paddingLeft = getPaddingLeft();
        paddingRight = getPaddingRight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawGrayLine(canvas);
        drawColorLine(canvas);
    }

    private void drawGrayLine(Canvas canvas) {
        mPaint.setColor(GRAY);
        canvas.drawLine(paddingLeft, mYpostion, mWidth - paddingRight, mYpostion, mPaint);
    }

    private void drawColorLine(Canvas canvas) {
        mPaint.setColor(color);
        float degreeLength = (float) degree / 100 * (mWidth - paddingRight);
        canvas.drawLine(paddingLeft, mYpostion, degreeLength, mYpostion, mPaint);
    }


    /**
     * author : dongSen
     * date : 16/6/7 下午3:59
     * desc : 设定百分比
     */
    public void setPercentage(int degree) {
        this.degree = degree;
    }

}
