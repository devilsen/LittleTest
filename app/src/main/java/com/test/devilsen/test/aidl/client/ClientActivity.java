package com.test.devilsen.test.aidl.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.test.devilsen.test.R;
import com.test.devilsen.test.aidl.server.EasyService;
import com.test.devilsen.test.aidl.server.IEasyService;

/**
 * @author dongsen
 * date: 2019/02/26 0026.
 */
public class ClientActivity extends AppCompatActivity {

    private IEasyService mIEasyService;
    private int count;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl_test_client);
    }

    public void start(View view) {
        Intent intent = new Intent(this, EasyService.class);
        // 注意在 Android 5.0以后，不能通过隐式 Intent 启动 service，必须制定包名
//        intent.setPackage("com.test.devilsen.test");
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    public void sendMessage(View view) {
        if (mIEasyService != null) {
            try {
                mIEasyService.connect("send " + count++);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIEasyService = IEasyService.Stub.asInterface(service);
            try {
                mIEasyService.connect("hello Server");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIEasyService = null;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
    }
}
