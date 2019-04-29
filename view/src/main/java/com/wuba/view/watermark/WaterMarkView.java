package com.wuba.view.watermark;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

/**
 * desc : 水印控件
 * date : 2018/3/2
 *
 * @author : dongSen
 */
public class WaterMarkView extends View {

    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private int textWidth;

    private String text;

    private String horizontalText;

    private int verticalRepeatTime;

    //一行的文字
    private StringBuilder lineText;

    //行间距
    private int lineSpace;

    private int XRotationDeviation;

    private int YRotationDeviation;

    public WaterMarkView(Context context) {
        super(context);
        init();
    }

    public WaterMarkView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaterMarkView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint.setColor(Color.parseColor("#EBEBEB"));
        paint.setTextSize(sp2px(getContext(), 18));

        lineText = new StringBuilder();

        lineSpace = dp2px(getContext(), 100);

        XRotationDeviation = -dp2px(getContext(), 30);
        YRotationDeviation = dp2px(getContext(), 60);
    }

    public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5F);
    }

    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5F);
    }

    private void measureText() {
        if (TextUtils.isEmpty(text)) {
            return;
        }

        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        textWidth = bounds.width() + dp2px(getContext(), 20);
    }

    private void makeLineText(int horizontalRepeatTime) {
        for (int i = 0; i < horizontalRepeatTime; i++) {
            lineText.append(text);
            lineText.append("             ");
        }

        horizontalText = lineText.toString();
    }

    public void setText(String text) {
        this.text = text;

        measureText();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (TextUtils.isEmpty(text)) {
            return;
        }

        int horizontalRepeatTime = getMeasuredWidth() / textWidth;
        verticalRepeatTime = getMeasuredHeight() / lineSpace;

        makeLineText(horizontalRepeatTime);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (TextUtils.isEmpty(text)) {
            return;
        }
        canvas.save();

        canvas.rotate(-20, canvas.getWidth() / 2, canvas.getHeight() / 2);

        canvas.translate(XRotationDeviation, YRotationDeviation);

        for (int i = 0, y = 0; i < verticalRepeatTime; i++) {
            canvas.drawText(horizontalText, 0, y, paint);

            y += lineSpace;
        }

        canvas.restore();
    }
}
