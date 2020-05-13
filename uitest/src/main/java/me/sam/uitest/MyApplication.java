package me.sam.uitest;

import android.app.Application;

import me.sam.uitest.basic.BaseApplication;

/**
 * desc :
 * date : 2020/5/6 7:35 PM
 *
 * @author : dongSen
 */
public class MyApplication extends BaseApplication {

    private static Application sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }

    public static Application getApplication() {
        return sApplication;
    }

}
