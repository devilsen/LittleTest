package com.test.devilsen.test.alarm;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.test.devilsen.test.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * author : dongSen
 * date : 2016-07-27 17:30
 * desc :
 */
public class AlarmActivity extends AppCompatActivity {

    private TextView hanlderTxt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        hanlderTxt = (TextView) findViewById(R.id.test_handler);
//        setAlarm();

        hanlderTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post();
            }
        });

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }).start();

    }

    private void post() {
        handler.sendEmptyMessage(1);
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            hanlderTxt.setText("222");
        }
    };

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.getDefault());


    private void setAlarm1() {
        String meetingDate = "2016-07-27 18:49-18:00";
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, 0);
        long firstTime = 5000;
        try {
            firstTime = format.parse(meetingDate.substring(0, 16)).getTime() - System.currentTimeMillis();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.e("firstTime", firstTime / 60000 + "");
        firstTime += SystemClock.elapsedRealtime();

        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, 0, sender);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, sender);
    }

    public static class AlarmReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "clock", Toast.LENGTH_SHORT).show();
        }
    }


    private void setAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);

        /**
         * 第二个参数,requestCode,这个值不一样,可以定义多个闹钟
         * 第四个参数,flags,在getService()和getActivity中也有,详情请参看Android API,这里不是我们的重点,但是很重要的flag
         */
        PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, 0);
        /**
         * 注意这里的时间一定要加上开机之后的时间
         */
        long firstTime = 5000 + SystemClock.elapsedRealtime();

        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, 60000, sender);
    }

    public static void main(String[] args) {
        String test = "http://192.168.125.171:7878/meetingserver/scanning?meetingid=42&dadda";

        int start = test.indexOf("meetingid") + 10;
        int end = test.length();

        if (test.contains("&"))
            end = test.indexOf("&");

        String substring = test.substring(start, end);

        System.out.println(substring);

    }
}
