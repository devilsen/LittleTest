package com.test.devilsen.test.socket;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.WebSocket;
import com.test.devilsen.test.R;
import com.test.devilsen.test.socket.model.PayResult;

/**
 * desc :
 * date : 2019-11-25 17:54
 *
 * @author : dongSen
 */
public class WebSocketTestActivity extends AppCompatActivity {

    private static final String TAG = WebSocketTestActivity.class.getSimpleName();
    private WebSocket webSocket;

    private int mReconnectTimes;
    private int delayMillis = 1000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_socket_test);

        startWebSocket();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webSocket != null) {
            webSocket.close();
        }
        mHandler.removeCallbacksAndMessages(null);
    }

    private void startWebSocket() {

        AsyncHttpClient.getDefaultInstance().websocket("", null, new AsyncHttpClient.WebSocketConnectCallback() {
            @Override
            public void onCompleted(Exception ex, WebSocket webSocket) {
                if (ex != null) {
                    ex.printStackTrace();
                    return;
                }
                Log.e(TAG, "web socket connected.");

                WebSocketTestActivity.this.webSocket = webSocket;
                webSocket.send("a string");
                webSocket.send(new byte[10]);
                webSocket.setStringCallback(new WebSocket.StringCallback() {
                    public void onStringAvailable(String s) {
                        Log.e(TAG, "I got a string: " + s);

                        PayResult payResult = new Gson().fromJson(s, PayResult.class);

                        //发送通知，告诉服务已经收到支付结果
                        webSocket.send("PAYNOTIFYCALLBACK##" + "suliangjian" + "##" + payResult.tradeNo);
                        Log.e(TAG, "pay result" + payResult.toString());

                    }
                });
                webSocket.setDataCallback(new DataCallback() {
                    public void onDataAvailable(DataEmitter emitter, ByteBufferList byteBufferList) {
                        Log.e(TAG, "I got some bytes!");
                        // note that this data has been read
                        byteBufferList.recycle();
                    }
                });
                webSocket.setPingCallback(new WebSocket.PingCallback() {
                    @Override
                    public void onPingReceived(String s) {
                        Log.e(TAG, "ping call back");
                    }
                });
                webSocket.setPongCallback(new WebSocket.PongCallback() {
                    @Override
                    public void onPongReceived(String s) {
                        Log.e(TAG, "pong call back");
                    }
                });
                webSocket.setClosedCallback(new CompletedCallback() {
                    @Override
                    public void onCompleted(Exception ex) {
                        if (ex != null)
                            ex.printStackTrace();
                        Log.e(TAG, "on close");

                        reconnect();
                    }
                });
                webSocket.setEndCallback(new CompletedCallback() {
                    @Override
                    public void onCompleted(Exception ex) {
                        if (ex != null)
                            ex.printStackTrace();
                        Log.e(TAG, "on end");
                    }
                });
                webSocket.setDataCallback(new DataCallback() {
                    @Override
                    public void onDataAvailable(DataEmitter emitter, ByteBufferList bb) {
                        Log.e(TAG, "on data available");
                    }
                });
            }
        });
    }

    private void reconnect() {
        mReconnectTimes++;
        if (mReconnectTimes > 5) {
            delayMillis = 1000;
            return;
        }

        delayMillis = delayMillis << 1;
        Log.e("delayMillis", delayMillis + "");
        mHandler.sendEmptyMessageDelayed(1, delayMillis);
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            startWebSocket();
            return false;
        }
    });

}
