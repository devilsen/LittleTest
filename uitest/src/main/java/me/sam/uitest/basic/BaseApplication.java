package me.sam.uitest.basic;

import android.app.Application;

/**
 * desc :
 * date : 2020/5/6 7:35 PM
 *
 * @author : dongSen
 */
public class BaseApplication extends Application {

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
