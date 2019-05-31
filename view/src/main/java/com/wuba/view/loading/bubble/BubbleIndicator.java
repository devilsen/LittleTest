package com.wuba.view.loading.bubble;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.wuba.view.R;

import java.util.ArrayList;
import java.util.List;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/7/17 下午7:34
 * Desc:
 * *****************************************************************
 */

public class BubbleIndicator extends ViewGroup {

    private static final String TAG = "BezierBottomIndicator";

    private boolean isAnimatorStart = false;
    private boolean isViewPagerScoll = false;

    private int width = 0;
    private int height = 0;

    private int childSideLength = 0;  //子view外框的边长
    private float childPadding = 20;  //子View的Padding值

    private float defaultLeftRightGap = 10; //左右两边默认的距离

    private int bgCircularColor = Color.parseColor("#aaaaaa");  //背景圆环颜色
    private int circularColor = Color.parseColor("#3f51b5");  //贝塞尔球的默认颜色
    private List<Integer> circularColors;

    private List<PointF> anchorList;

    private Paint bezierPaint;
    private BubbleCircular bezierCircular;
    private int currentPosition = 0;
    int targetPosition = 0;

    public BubbleIndicator(Context context) {
        this(context, null);
    }

    public BubbleIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BubbleIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.BubbleIndicator, defStyleAttr, 0);
        int indexCount = typedArray.getIndexCount();

        for (int i = 0; i < indexCount; i++) {
            int attr = typedArray.getIndex(i);

            if (attr == R.styleable.BubbleIndicator_leftRightGap) {
                defaultLeftRightGap = typedArray.getDimension(attr, 10);
            } else if (attr == R.styleable.BubbleIndicator_childPadding) {
                childPadding = typedArray.getDimension(attr, 20);
            } else if (attr == R.styleable.BubbleIndicator_bgCircularColor) {
                bgCircularColor = typedArray.getColor(attr, bgCircularColor);
            } else if (attr == R.styleable.BubbleIndicator_defCircularColor) {
                circularColor = typedArray.getColor(attr, circularColor);
            }
        }
        typedArray.recycle();
    }

    private void init() {
        //自定义ViewGroup默认不会调用OnDraw()方法
        setWillNotDraw(false);

        anchorList = new ArrayList<>();
        circularColors = new ArrayList<>();

        bezierPaint = new Paint();
        bezierPaint.setColor(circularColor);
        bezierPaint.setAntiAlias(true);
        bezierPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        /*
         * 获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式
         */
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            width = sizeWidth;
        } else {
            width = wm.getDefaultDisplay().getWidth();
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = sizeHeight;
        } else {
            height = wm.getDefaultDisplay().getHeight();
        }

        if (getChildCount() != 0) {
            childSideLength = (width - getPaddingRight() - getPaddingLeft()) / getChildCount() > height - getPaddingBottom() - getPaddingTop() ? height - getPaddingBottom() - getPaddingTop() : (width - getPaddingLeft() - getPaddingRight()) / getChildCount();
            // 计算出所有的ChildView的宽和高
            bezierCircular = new BubbleCircular(childSideLength / 2);
        }

        setMeasuredDimension(width, height);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        if (childCount == 0) {
            return;
        }
        float childDis = (width - getPaddingLeft() - getPaddingRight() - 2 * defaultLeftRightGap - childSideLength) / (childCount - 1);
        float cWidth = childSideLength - 2 * childPadding;
        float cHeight = cWidth;

        anchorList.clear();
        //计算子控件的位置，强制将子View控制绘制在均分的几个锚点上
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            PointF anchorPoint = new PointF((childDis * i + defaultLeftRightGap + childSideLength / 2 + getPaddingLeft()), getPaddingTop() + childSideLength / 2);
            anchorList.add(anchorPoint);
            childView.layout((int) (anchorPoint.x - cWidth / 2), (int) (anchorPoint.y - cHeight / 2), (int) (anchorPoint.x + cWidth / 2), (int) (anchorPoint.y + cHeight / 2));
        }
        PointF pointF = anchorList.get(0);
        bezierCircular.setCenter(pointF.x, pointF.y);
        bezierCircular.initControlPoint();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        bezierCircular.drawCircle(canvas, bezierPaint);
        super.onDraw(canvas);
    }


    float touchX = 0;
    float touchY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchX = event.getX();
                touchY = event.getY();
                break;

            case MotionEvent.ACTION_UP:
                Log.i(TAG, "touchX: " + touchX + "  touchY: " + touchY);
                for (int i = 0; i < anchorList.size(); i++) {
                    PointF pointF = anchorList.get(i);
                    if (touchX > (pointF.x - childSideLength / 2) && touchX < (pointF.x + childSideLength / 2) && touchY > (pointF.y - childSideLength / 2) && touchY < (pointF.y + childSideLength / 2)) {
                        onClickIndex(i);
                    }
                }
                break;
        }
        return true;
    }

    private void onClickIndex(int position) {
        if (!isAnimatorStart && !isViewPagerScoll && position != currentPosition) {
            targetPosition = position;
            isAnimatorStart = true;
            startAnimator(); //开始动画
        }
    }

    /**
     * 切换动画
     */
    private void startAnimator() {
        bezierCircular.setCurrentAndTarget(anchorList.get(currentPosition), anchorList.get(targetPosition));
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, targetPosition > currentPosition ? 1 : -1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                bezierCircular.setProgress((Float) animation.getAnimatedValue());
                bezierPaint.setColor(circularColors.size() > 0 ? setCircularColor(Math.abs((Float) animation.getAnimatedValue())) : circularColor);
                postInvalidate();
            }
        });

        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                currentPosition = targetPosition;
                bezierPaint.setColor(circularColors.size() > 0 ? circularColors.get(currentPosition) : circularColor);
                bezierCircular.resetCircular(anchorList.get(currentPosition));
                isAnimatorStart = false;
                postInvalidate();
                super.onAnimationEnd(animation);
            }
        });

        int count = Math.abs(targetPosition - currentPosition);
        if (count == 0) {
            return;
        }
        int duration = 450;
        valueAnimator.setDuration(duration);
        valueAnimator.start();
    }

    //颜色变换
    private int setCircularColor(float progress) {
        int startColor = circularColors.get(currentPosition);
        int endColor = circularColors.get(targetPosition);

        int redStart = Color.red(startColor);
        int blueStart = Color.blue(startColor);
        int greenStart = Color.green(startColor);
        int redEnd = Color.red(endColor);
        int blueEnd = Color.blue(endColor);
        int greenEnd = Color.green(endColor);

        int red = (int) (redStart + ((redEnd - redStart) * progress + 0.5));
        int greed = (int) (greenStart + ((greenEnd - greenStart) * progress + 0.5));
        int blue = (int) (blueStart + ((blueEnd - blueStart) * progress + 0.5));
        return Color.argb(255, red, greed, blue);
    }
}
