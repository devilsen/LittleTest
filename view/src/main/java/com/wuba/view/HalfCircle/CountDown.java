package com.wuba.view.HalfCircle;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

/**
 * author : dongSen
 * date : 2016-06-24 22:27
 * desc : 倒计时
 */
public class CountDown {

    private static final int ONE_MINUTE = 1000 * 60;

    private static final int ONE_HOUR = ONE_MINUTE * 60;

    private static final int SUBTRACT = 1;

    private int totalTime = ONE_MINUTE;

    private int nowTime = totalTime;

    private OnCountDownListener listener;

    private static CountDown instance;

    public static CountDown getInstance() {
        if (instance == null) {
            synchronized (CountDown.class) {
                if (instance == null) {
                    instance = new CountDown();
                }
            }
        }
        return instance;
    }

    public void start() {
        handler.removeMessages(SUBTRACT);
        handler.sendEmptyMessage(SUBTRACT);
    }

    public void pause() {
        handler.removeMessages(SUBTRACT);
    }

    String secondString;
    String minuteString;
    int hour;
    int minute;
    int second;

    public String getTime() {
        hour = nowTime / ONE_HOUR;
        minute = nowTime % ONE_HOUR / ONE_MINUTE;
        second = nowTime % ONE_MINUTE / 1000;

        if (second < 10) {
            secondString = "0" + second;
        } else {
            secondString = second + "";
        }

        if (minute < 10) {
            minuteString = "0" + minute;
        } else {
            minuteString = minute + "";
        }

        if (hour > 0)
            return hour + ":" + minuteString + ":" + secondString;

        return minuteString + ":" + secondString;
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SUBTRACT:
                    nowTime -= 1000;
                    if (listener != null) {
                        listener.lastTime(getTime());
                        listener.timePercent((float) nowTime / totalTime * 100);
                    }
                    if (nowTime > 0)
                        handler.sendEmptyMessageDelayed(SUBTRACT, 1000);

                    if (nowTime <= 0) {
                        nowTime = totalTime;
                        handler.removeMessages(SUBTRACT);
                    }
                    break;
            }
        }
    };

    public void setTotalTime(int time) {
        totalTime = time;
    }

    public void restart() {
        pause();
        nowTime = totalTime;
    }

    public void setCountDownListener(OnCountDownListener listener) {
        this.listener = listener;
    }

    public interface OnCountDownListener {
        void lastTime(String time);

        void timePercent(float percent);
    }

}
