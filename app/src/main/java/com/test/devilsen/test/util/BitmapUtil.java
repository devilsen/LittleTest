package com.test.devilsen.test.util;

import android.graphics.Bitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by dongSen on 2021/12/3
 */
public class BitmapUtil {

    /**
     * 保存 bitmap 到本地文件
     *
     * @param bitmap  bitmap
     * @param path    保存路径
     *
     * @return 创建是否成功
     */
    public static boolean saveImage(Bitmap bitmap, String path) {
        return saveImage(bitmap, path, 100);
    }

    /**
     * 保存 bitmap 到本地文件
     *
     * @param bitmap  bitmap
     * @param path    保存路径
     * @param quality 压缩比
     *
     * @return 创建是否成功
     */
    public static boolean saveImage(Bitmap bitmap, String path, int quality) {
        File file = new File(path);
        try {
            if (!file.exists()) {
                boolean newFile = file.createNewFile();
                if (!newFile) return false;
            }
            FileOutputStream out = new FileOutputStream(file);
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)) {
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
