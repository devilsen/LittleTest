package com.wuba.view.loading.bubble;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.View;

/**
 * desc : 气泡
 * date : 2019-05-31
 *
 * @author : dongSen
 */
public class BubbleView extends View {

    private Paint mPaint;
    // 气泡颜色
    private int mBubbleColor = Color.GRAY;
    // 气泡半径
    private int mRadius = 100;
    private float mBubbleX;
    private float mBubbleY;

    private int mBubbleHeight;
    private int mBubbleWidth;
    private PointF pointF;

    public BubbleView(Context context) {
        this(context, null);
    }

    public BubbleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BubbleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(mBubbleColor);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(1);
    }

    public void setColor(@ColorInt int color) {
        mBubbleColor = color;
        mPaint.setColor(mBubbleColor);
    }

    public void setRadius(int radius) {
        mRadius = radius;
    }

    public void setBubbleX(float mBubbleX) {
        this.mBubbleX = mBubbleX;
    }

    public void setBubbleY(float mBubbleY) {
        this.mBubbleY = mBubbleY;
    }

    public float getBubbleX() {
        return mBubbleX;
    }

    public float getBubbleY() {
        return mBubbleY;
    }

    public PointF getPointF() {
        if (pointF == null) {
            pointF = new PointF(mBubbleX + mRadius, mBubbleY + mRadius);
        }
        return pointF;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mBubbleHeight = getMeasuredHeight();
        mBubbleWidth = getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mBubbleWidth >> 1, mBubbleHeight >> 1);
        canvas.drawCircle(0, 0, mRadius, mPaint);
    }


}
