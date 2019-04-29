package com.wuba.view.viewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * author : dongSen
 * ate : 2017/9/22
 * desc :
 */
public class TestViewGroup2 extends LinearLayout {
    public TestViewGroup2(Context context) {
        super(context);
    }

    public TestViewGroup2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestViewGroup2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("group2 ", "onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e("group2 ", "onLayout");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("group2 ", "onDraw");
    }
}
