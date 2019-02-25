package com.test.devilsen.test.aidl.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.test.devilsen.test.R;
import com.test.devilsen.test.aidl.server.IEasyService;

/**
 * @author dongsen
 * date: 2019/02/26 0026.
 */
public class ClientActivity extends AppCompatActivity {

    private IEasyService mIEasyService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl_test_client);

        Intent intent = new Intent("com.devilsen.test.IEasyService");
        // 注意在 Android 5.0以后，不能通过隐式 Intent 启动 service，必须制定包名
        intent.setPackage("com.test.devilsen.test");
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIEasyService = IEasyService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIEasyService = null;
        }
    };
}
