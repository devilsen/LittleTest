package com.test.devilsen.test.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.test.devilsen.test.R;
import com.wuba.view.image.HeartTestActivity;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;

/**
 * author : dongSen
 * date : 2017/9/5
 * desc :
 */
public class ProgressActivity extends AppCompatActivity {

    private static final int PROGRESS_NOTIFICATION_ID = 20;

    private NotificationManager mNotifyManager;
    private NotificationCompat.Builder mBuilder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_notify);

        Button startBtn = (Button) findViewById(R.id.button_notify_start);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressNotification(ProgressActivity.this, "Test", "download", 1);
            }
        });
    }

    /**
     * 展示有进度条的通知
     *
     * @param context context
     * @param title   标题
     * @param msg     内容
     */
    public void progressNotification(final Context context, String title, String msg, int progress) {
        mNotifyManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setContentTitle(title)
                .setContentText(msg)
                .setSmallIcon(R.mipmap.ic_launcher);

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        int incr;
                        // Do the "lengthy" operation 20 times
                        for (incr = 0; incr <= 100; incr += 5) {
                            // Sets the progress indicator to a max value, the
                            // current completion percentage, and "determinate"
                            // state
                            mBuilder.setProgress(100, incr, false);
                            // Displays the progress bar for the first time.
                            mNotifyManager.notify(PROGRESS_NOTIFICATION_ID, mBuilder.build());
                            // Sleeps the thread, simulating an operation
                            // that takes time
                            try {
                                // Sleep for 5 seconds
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                Log.d("progress", "sleep failure");
                            }
                        }
                        // When the loop is finished, updates the notification
                        mBuilder.setContentText("Download complete")
                                // Removes the progress bar
                                .setProgress(0, 0, false);

                        Intent intent = new Intent(context, HeartTestActivity.class);

                        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, FLAG_UPDATE_CURRENT);

                        mBuilder.setContentIntent(pendingIntent);
                        mBuilder.setAutoCancel(true);

                        mNotifyManager.notify(PROGRESS_NOTIFICATION_ID, mBuilder.build());
                    }
                }
                // Starts the thread by calling the run() method in its Runnable
        ).start();
    }


}
