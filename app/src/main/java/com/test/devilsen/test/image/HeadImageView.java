package com.test.devilsen.test.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.test.devilsen.test.R;


/**
 * @author pangkai createTime: 2017/8/4 15:04
 * @Description
 */
public class HeadImageView extends CircleImageView {

    private Bitmap mBitmap;
    /**
     * 绘图的Paint
     */
    private Paint mBitmapPaint;

    private double angle;

    public HeadImageView(Context context) {
        this(context, null);
    }

    public HeadImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);
        //angle 45°
        angle = 45.0 * Math.PI / 180;
    }

    /**
     * @param status 状态：0 online；1 offline；2 logout；3 busy；
     */
    public void setUserStatus(String status) {

        if (TextUtils.equals(status, "0")) {
            mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.imkit_icon_online);
        } else if (TextUtils.equals(status, "1")) {
            mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.imkit_icon_busy);
        }

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mBitmap == null || mDrawableRadius == 0)
            return;

        //距离中心的水平距离（因为是正方形，所以用一个就够了）
        double cosLength = Math.cos(angle) * mDrawableRadius;

        int size = mBitmap.getWidth() / 2;

        float position = (float) (mDrawableRadius + cosLength - size);

        canvas.drawBitmap(mBitmap, position, position, mBitmapPaint);

    }

}
