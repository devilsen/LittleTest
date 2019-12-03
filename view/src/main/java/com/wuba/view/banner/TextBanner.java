package com.wuba.view.banner;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;

/**
 * desc : 类似于广告的文字banner
 * date : 2019-06-03
 *
 * @author : dongSen
 * <p>
 * 这得多蠢的人才想出在手机上采用这个功能的。
 */
public class TextBanner extends androidx.appcompat.widget.AppCompatTextView {

    private Paint mPaint;
    private float textX;
    private ValueAnimator animator;
    private String mContent;
    private int mTxtHeight;
    private int mTxtWidth;

    public TextBanner(Context context) {
        this(context, null);
    }

    public TextBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = getPaint();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mContent == null) {
            return;
        }
        getSpacing();

        canvas.drawText(mContent, textX, mTxtHeight + getPaddingTop(), mPaint);

        startAnimation(getWidth() - mTxtWidth);
    }

    public void setContent(String content) {
        this.mContent = content;
        invalidate();
    }

    public void showBanner() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "alpha", 0, 1);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(1500);
        animator.start();
    }

    private void getSpacing() {
        if (mContent == null || mTxtHeight != 0)
            return;

        Rect rect = new Rect();
        mPaint.getTextBounds(mContent, 0, mContent.length(), rect);
        mTxtHeight = rect.height();
        mTxtWidth = rect.width();
    }

    private void startAnimation(float spacing) {
        if (animator != null) {
            return;
        }
        // 如果没有超出范围，不进行平移
        if (spacing > 0 || mContent == null) {
            return;
        }
        int start = getPaddingLeft();
        spacing -= getPaddingRight() * 2;
        animator = ValueAnimator.ofFloat(start, start, spacing, spacing);
        final float finalSpacing = spacing;
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                textX = (float) animation.getAnimatedValue();
                if (textX == 0 || textX == finalSpacing) {
                    return;
                }
                invalidate();
            }
        });
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(mContent.length() * 80);
        animator.start();
    }
}
