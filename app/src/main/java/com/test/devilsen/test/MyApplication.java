package com.test.devilsen.test;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * desc :
 * date : 2017/12/14
 *
 * @author : dongSen
 */
public class MyApplication extends Application {

    RefWatcher watcher;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        watcher = LeakCanary.install(this);
        // Normal app init code...
    }

    public static RefWatcher getRefWatcher(Context context) {
        MyApplication application = (MyApplication) context.getApplicationContext();
        return application.watcher;
    }
}
