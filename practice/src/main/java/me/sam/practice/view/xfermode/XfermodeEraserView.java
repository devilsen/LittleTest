package me.sam.practice.view.xfermode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import me.sam.practice.R;

/**
 * desc : 刮刮卡
 * date : 2019/3/27
 *
 * @author : dongSen
 */
public class XfermodeEraserView extends View {

    private Paint mPaint;
    private Bitmap mDstBitmap, mSrcBitmap, mTxtBitmap;
    private Path mPath;

    public XfermodeEraserView(Context context) {
        this(context, null);
    }

    public XfermodeEraserView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XfermodeEraserView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //init Paint
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(80);

        //禁用硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        //init bitmap,实际上是三层，最底层是结果，中间是展示图，最上面其实有个透明的图层，用来和中间混合。
        mTxtBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.result);
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cover);
        mDstBitmap = Bitmap.createBitmap(mSrcBitmap.getWidth(), mSrcBitmap.getHeight(), Bitmap.Config.ARGB_8888);

        //init path
        mPath = new Path();
    }

    private float xEvent, yEvent;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xEvent = event.getX();
                yEvent = event.getY();
                mPath.moveTo(xEvent, yEvent);
                Log.e("down", xEvent + "");
                break;
            case MotionEvent.ACTION_MOVE:
                float endX = (event.getX() - xEvent) / 2 + xEvent;
                float endY = (event.getY() - yEvent) / 2 + yEvent;

                mPath.quadTo(xEvent, yEvent, endX, endY);
                xEvent = event.getX();
                yEvent = event.getY();
                Log.e("move", xEvent + "    " + endX);
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //结果
        canvas.drawBitmap(mTxtBitmap, 0, 0, mPaint);

        //使用离屏绘制
        int layer = canvas.saveLayer(0, 0, getWidth(), getHeight(), mPaint, Canvas.ALL_SAVE_FLAG);

        //先将路径绘制到bitmap
        Canvas dstCanvas = new Canvas(mDstBitmap);
        dstCanvas.drawPath(mPath, mPaint);

        //绘制目标图像
        canvas.drawBitmap(mDstBitmap, 0, 0, mPaint);
        //设置模式为 SRC_OUT
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        //绘制源图像
        canvas.drawBitmap(mSrcBitmap, 0, 0, mPaint);

        mPaint.setXfermode(null);

        canvas.restoreToCount(layer);
    }
}
