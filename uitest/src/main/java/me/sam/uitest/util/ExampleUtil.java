package me.sam.uitest.util;

import android.util.Log;

import me.sam.uitest.basic.BaseApplication;

/**
 * desc : RobolectricUnitTest 原Class
 * date : 2020/5/6 7:33 PM
 *
 * @author : dongSen
 */
public class ExampleUtil {

    private static final float CHAT_IMAGE_MIN = SizeUtils.dp2px(BaseApplication.getApplication(), 80);

    public static boolean isGif(String filePath) {
        Log.d("Example", String.valueOf(CHAT_IMAGE_MIN));

        if (filePath == null)
            return false;

        String path = filePath.toUpperCase();
        return path.endsWith(".GIF");
    }


}
