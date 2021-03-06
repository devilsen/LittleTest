package com.test.devilsen.test.aidl.server;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.HandlerThread;
import android.os.IBinder;
import android.util.Log;

/**
 * @author dongsen
 * date: 2019/02/25 0025.
 */
public class EasyService extends Service {

    private static final String TAG = "EasyService";

    com.test.devilsen.test.aidl.server.IEasyService.Stub mIBinder = new com.test.devilsen.test.aidl.server.IEasyService.Stub() {
        @Override
        public void connect(String mes) {
            Log.e(TAG, "connect:   mes = " + mes);

        }

        @Override
        public void disConnect(String mes) {
            Log.e(TAG, "disConnect:  mes = " + mes);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder;
    }
}
