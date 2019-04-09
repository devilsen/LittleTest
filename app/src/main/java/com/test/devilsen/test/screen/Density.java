package com.test.devilsen.test.screen;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

/**
 * desc : 通过Density来适配屏幕
 * date : 2019/4/9
 *
 * @author : dongSen
 */
public class Density {

    private static final float WIDTH = 720;

    private static float appDensity;//屏幕密度
    private static float appScaleDensity;//字体缩放比例

    /**
     * Screen density --- means how many pixels appear within a constant area of the display, dots per inch = dpi(每英寸像素点)
     * Screen size --- means amount of physical space available for displaying an interface, screen's diagonal, inch(屏幕大小)
     * Screen resolution --- means number of pixels available in the display, scale-independent pixel = sp(有多少像素)
     * density-independent pixel = virtual pixel that is independent of the screen density, dp
     */
    public static void setDensity(Activity activity) {
        final Application application = activity.getApplication();
        DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
        //获取目标density
        if (appDensity == 0) {
            appDensity = displayMetrics.density;
            appScaleDensity = displayMetrics.scaledDensity;

            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        appScaleDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        float targetDensity = displayMetrics.widthPixels / WIDTH; // 1080 / 720 = 1.5
        float targetScaleDensity = targetDensity * (appScaleDensity / appDensity);
        int targetDensityDpi = (int) (targetDensity * 160);

        DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity; //缩放比 ，以160为基准
        activityDisplayMetrics.scaledDensity = targetScaleDensity; //与density一样，不过用于字体
        activityDisplayMetrics.densityDpi = targetDensityDpi; //目标DPI
    }

}
