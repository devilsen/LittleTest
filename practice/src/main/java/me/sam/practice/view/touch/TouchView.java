package me.sam.practice.view.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * desc :
 * date : 2019-05-07
 *
 * @author : dongSen
 */
public class TouchView extends View {

    private int x, y;
    private TouchRangeListener mTouchRangeListener;

    public TouchView(Context context) {
        this(context, null);
    }

    public TouchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        init();
    }

    private void init() {
        View parent = (View) getParent();
        parent.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mTouchRangeListener != null) {
                    mTouchRangeListener.onTouch("out");
                }
                return false;
            }
        });
    }

    public void setTouchRangeListener(TouchRangeListener mTouchRangeListener) {
        this.mTouchRangeListener = mTouchRangeListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (mTouchRangeListener != null) {
                    mTouchRangeListener.onTouch("in");
                }
                x = (int) event.getRawX();
                y = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) event.getRawX() - x;
                int dy = (int) event.getRawY() - y;

                int mL = getLeft() + dx;
                int mB = getBottom() + dy;
                int mR = getRight() + dx;
                int mT = getTop() + dy;

                layout(mL, mT, mR, mB);

                x = (int) event.getRawX();
                y = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    private void log(String text) {
        Log.e("TouchView", text);
    }

    public interface TouchRangeListener {
        void onTouch(String range);
    }
}
