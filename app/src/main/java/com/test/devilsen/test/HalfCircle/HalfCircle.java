package com.test.devilsen.test.HalfCircle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * author : dongSen
 * date : 2016-07-01 16:12
 * desc : 半圆倒计时控件
 */
public class HalfCircle extends View {

    private Context mContext;
    private Paint mPaint;
    private Paint mTxtPaint;
    private int width;
    private int height;

    private RectF rectF;
    private float percent;
    private String text = "00:00:00";
    private long totalTime;
    private int angle;

    public HalfCircle(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public HalfCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public HalfCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(Dp2Px(15));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(Color.WHITE);

        mTxtPaint = new Paint();
        mTxtPaint.setTextSize(Dp2Px(40));
        mTxtPaint.setColor(Color.WHITE);
        mTxtPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mTxtPaint.setTextAlign(Paint.Align.CENTER);

        rectF = new RectF();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = right;
        height = bottom;


        float r = Math.min(width, height) / 2;

        rectF.set(-r, -r, r, r);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(width / 2, height / 2 + 50);

        drawCircleBackground(canvas);
        drawCircle(canvas);
        drawText(canvas);
        drawTitle(canvas);
    }

    /**
     * author : dongSen
     * date : 16/7/1 下午5:00
     * desc : 画标题
     */
    private void drawTitle(Canvas canvas) {
        mTxtPaint.setColor(Color.argb(130, 255, 255, 255));
        mTxtPaint.setTextSize(Dp2Px(16));
        canvas.drawText("剩余时间", 0, -Dp2Px(40), mTxtPaint);
    }

    /**
     * author : dongSen
     * date : 16/7/1 下午4:51
     * desc : 画文字
     */
    private void drawText(Canvas canvas) {
        mTxtPaint.setTextSize(Dp2Px(35));
        mTxtPaint.setColor(Color.WHITE);
        canvas.drawText(text, 0, 0, mTxtPaint);
    }

    /**
     * author : dongSen
     * date : 16/7/1 下午4:15
     * desc : 画半圆
     */

    private void drawCircle(Canvas canvas) {
        mPaint.setColor(Color.WHITE);
        canvas.drawArc(rectF, 180, angle, false, mPaint);
    }

    /**
     * author : dongSen
     * date : 16/7/1 下午4:39
     * desc : 半圆背景
     */
    private void drawCircleBackground(Canvas canvas) {
        mPaint.setColor(Color.argb(30, 0, 131, 146));
        canvas.drawArc(rectF, 180, 180, false, mPaint);

    }

    public int Dp2Px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * author : dongSen
     * date : 16/7/1 下午6:31
     * desc : 设置百分百
     */
    public void setPercent(float percent) {
        this.percent = percent;

        if (percent > 100)
            percent = 100;

        if (percent < 0)
            percent = 0;

        angle = (int) (percent / 100 * 180);

        invalidate();
    }

    /**
     * author : dongSen
     * date : 16/7/1 下午6:32
     * desc : 设置显示文字
     */
    public void setText(String text) {
        if (text != null)
            this.text = text;

        invalidate();
    }
}
