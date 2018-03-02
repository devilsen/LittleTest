package com.test.devilsen.test.chart;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

/**
 * author : dongSen
 * date : 2016-06-07 15:25
 * desc :
 */
public class Chart extends View {

    protected static final int GRAY = Color.rgb(230, 230, 230);
    protected static final int YELLOW = Color.rgb(255, 210, 59);
    protected static final int BLUE = Color.rgb(1, 176, 241);
    protected static final int GREEN = Color.rgb(146, 209, 79);

    public int color = YELLOW;

    protected int mWidth = 100;
    protected int mHeight = 100;

    public Chart(Context context) {
        super(context);
    }

    public Chart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Chart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.EXACTLY) {
            heightSpecSize = widthSpecSize = Math.min(widthSpecSize, heightSpecSize);
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
        mWidth = getWidth();
        mHeight = getHeight();
    }

    /**
     * author : dongSen
     * date : 16/6/7 下午4:25
     * desc : 设定柱体颜色
     */
    public void setLineColor(int color) {
        if (color == 0) {
            this.color = YELLOW;
        } else if (color == 1) {
            this.color = BLUE;
        } else if (color == 2) {
            this.color = GREEN;
        } else {
            this.color = color;
        }
    }

}
