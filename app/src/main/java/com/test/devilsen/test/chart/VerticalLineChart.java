package com.test.devilsen.test.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * author : dongSen
 * date : 2016-06-07 16:23
 * desc : 纵向线性占比
 */
public class VerticalLineChart extends Chart {

    private static final float LINE_WIDTH = 50;

    private Paint mPaint;
    private int degree;
    private int xPosition;

    public VerticalLineChart(Context context) {
        super(context);
        init();
    }

    public VerticalLineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VerticalLineChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(GRAY);
        mPaint.setStrokeWidth(LINE_WIDTH);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        xPosition = mWidth / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(0, mHeight);

        drawGrayLine(canvas);
        drawColorLine(canvas);
    }

    private void drawGrayLine(Canvas canvas) {
        mPaint.setColor(GRAY);
        canvas.drawLine(xPosition, xPosition, xPosition, -mHeight, mPaint);
    }

    private void drawColorLine(Canvas canvas) {
        mPaint.setColor(color);
        float height = (float) degree / 100 * mHeight;
        canvas.drawLine(xPosition, xPosition, xPosition, -height, mPaint);
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
