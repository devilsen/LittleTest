package com.wuba.view.loading.bubble;

import android.animation.TypeEvaluator;
import android.graphics.Color;

/**
 * desc : 颜色变化evaluator
 * date : 2019-06-04
 * <p>
 * https://hencoder.com/ui-1-7/
 */
public class HsvEvaluator implements TypeEvaluator<Integer> {
    private float[] startHsv = new float[3];
    private float[] endHsv = new float[3];
    private float[] outHsv = new float[3];

    private HsvEvaluator() {
    }

    public static HsvEvaluator getInstance() {
        return Holder.instance;
    }

    private static class Holder {
        private static final HsvEvaluator instance = new HsvEvaluator();
    }

    @Override
    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        // 把 ARGB 转换成 HSV
        Color.colorToHSV(startValue, startHsv);
        Color.colorToHSV(endValue, endHsv);

        // 计算当前动画完成度（fraction）所对应的颜色值
        if (endHsv[0] - startHsv[0] > 180) {
            endHsv[0] -= 360;
        } else if (endHsv[0] - startHsv[0] < -180) {
            endHsv[0] += 360;
        }
        outHsv[0] = startHsv[0] + (endHsv[0] - startHsv[0]) * fraction;
        if (outHsv[0] > 360) {
            outHsv[0] -= 360;
        } else if (outHsv[0] < 0) {
            outHsv[0] += 360;
        }
        outHsv[1] = startHsv[1] + (endHsv[1] - startHsv[1]) * fraction;
        outHsv[2] = startHsv[2] + (endHsv[2] - startHsv[2]) * fraction;

        // 计算当前动画完成度（fraction）所对应的透明度
        int alpha = startValue >> 24 + (int) ((endValue >> 24 - startValue >> 24) * fraction);

        // 把 HSV 转换回 ARGB 返回
        return Color.HSVToColor(alpha, outHsv);
    }
}