package com.test.devilsen.test.handlertest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.test.devilsen.test.R;

import java.util.ArrayList;

/**
 * author : dongSen
 * date : 2017/10/18
 * desc :
 */
public class HandlerTestActivity extends AppCompatActivity {

    private ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_test);


        Button button = (Button) findViewById(R.id.button_handler_test);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                handler.sendEmptyMessageDelayed(1, 2000);
            }
        });

        for (int i = 0; i < 15; i++) {
            handler2.sendEmptyMessageDelayed(2, 1000);

            handler.removeMessages(1);
            handler.sendEmptyMessageDelayed(1, 1000);
            handler.removeMessages(1);
            handler.sendEmptyMessageDelayed(1, 1000);
        }

    }

    Handler handler2 = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            list.add("111");
            Log.e("aa", list.size() + "");
            return true;
        }
    });

    Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e("size", list.size() + "");
            list.clear();
        }
    };
}
