package com.wuba.view.viewtest;

import android.content.Context;
import android.graphics.Canvas;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * author : dongSen
 *date : 2017/9/22
 * desc :
 */
public class TestViewGroup extends LinearLayout {
    public TestViewGroup(Context context) {
        super(context);
    }

    public TestViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("group ", "onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e("group ", "onLayout");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("group ", "onDraw");
    }
}
