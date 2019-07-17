package me.sam.practice.view.spark;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

import me.sam.practice.R;

/**
 * desc :
 * date : 2019/3/27
 *
 * @author : dongSen
 */
public class SparkView extends View {

    private Paint mPaint;
    private List<Ball> mBalls;
    private ValueAnimator mAnimator;
    private boolean mIsBack;

    public SparkView(Context context) {
        this(context, null);
    }

    public SparkView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SparkView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_favorite_red_600_24dp);
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        mBalls = new ArrayList<>(width * height);
        float d = 3;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Ball ball = new Ball();
                ball.color = mBitmap.getPixel(i, j);
                ball.x = i * d + d / 2;
                ball.y = j * d + d / 2;
                ball.ox = ball.x;
                ball.oy = ball.y;
                ball.radius = d / 2;
                //速度
                ball.xSpeed = (float) (Math.pow(-1, Math.ceil(Math.random() * 1000)) * 20 * Math.random());
                ball.ySpeed = rangInt(-15, 35);
                //加速度
                ball.xAcceleration = 0;
                ball.yAcceleration = 0.98f;

                mBalls.add(ball);
            }
        }

        mAnimator = ValueAnimator.ofFloat(0.1f);
        mAnimator.setDuration(500);
        mAnimator.setRepeatCount(-1);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (mIsBack) {
                    backTime();
                } else {
                    updateBall();
                }
                invalidate();
            }
        });

//        mAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                mIsBack = true;
//                mAnimator.start();
//            }
//        });
    }

    private void backTime() {
        for (Ball mBall : mBalls) {
            if (mBall.ox <= mBall.x) {
                mBall.x -= mBall.xSpeed;
                mBall.xSpeed -= mBall.xAcceleration;
            }

            if (mBall.oy <= mBall.y) {
                mBall.y -= mBall.ySpeed;
                mBall.ySpeed -= mBall.yAcceleration;
            }
        }
    }

    private void updateBall() {
        for (Ball mBall : mBalls) {
            mBall.x += mBall.xSpeed;
            mBall.y += mBall.ySpeed;

            mBall.xSpeed += mBall.xAcceleration;
            mBall.ySpeed += mBall.yAcceleration;
        }
    }

    private int rangInt(int i, int j) {
        int max = Math.max(i, j);
        int min = Math.min(i, j) - 1;
        //在0到(max - min)范围内变化，取大于x的最小整数 再随机
        return (int) (min + Math.ceil(Math.random() * (max - min)));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mAnimator.start();
            mIsBack = false;
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            mIsBack = true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(300, 400);

        for (Ball mBall : mBalls) {
            mPaint.setColor(mBall.color);
            canvas.drawCircle(mBall.x, mBall.y, mBall.radius, mPaint);
        }
    }
}
