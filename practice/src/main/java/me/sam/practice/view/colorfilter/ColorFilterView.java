package me.sam.practice.view.colorfilter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import me.sam.practice.R;

/**
 * desc :
 * date : 2019-05-07
 *
 * @author : dongSen
 */
public class ColorFilterView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;
    private ColorMatrixColorFilter mColorMatrixColorFilter;

    public ColorFilterView(Context context) {
        this(context, null);
    }

    public ColorFilterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorFilterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.xiaoke);

        float[] colorMatrix = {
                0.8f, 1.6f, 0.2f, 0, -163.9f,
                0.8f, 1.6f, 0.2f, 0, -163.9f,
                0.8f, 1.6f, 0.2f, 0, -163.9f,
                0, 0, 0, 1.0f, 0,
        };

        mColorMatrixColorFilter = new ColorMatrixColorFilter(colorMatrix);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, 50, 0, mPaint);

        mPaint.setColorFilter(mColorMatrixColorFilter);
        canvas.drawBitmap(mBitmap, 50, 800, mPaint);
    }


}
