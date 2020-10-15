package com.test.devilsen.test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * desc : log test
 * date : 2020/10/15 11:25 AM
 *
 * @author : dongSen
 */
public class LogTestActivity extends AppCompatActivity {

    public static final String TAG = "Log Test";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_test);

        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public void order(View view) {
        testOrder();
    }

    /**
     * 按照严重程度从小到大排序：Verbose<Debug<Information<Warning<Error<Assert
     * 即VDIWEA(我是这样记的：”我滴胃啊“。）
     * 选择等级低的可以显示出所有比其等级高的日志信息。
     * Assert是最严重的错误，很少出现，用Log.wtf去写。wtf的意思是：What a Terrible Failure.
     */
    public static void testOrder() {
        Log.v(TAG, "-------------------------------------");
        Log.v(TAG, "Verbose level message");
        Log.d(TAG, "Debug level message");
        Log.i(TAG, "Information level message");
        Log.w(TAG, "Warning level message");
        Log.e(TAG, "Error level message");
        Log.wtf(TAG, "Assert level message");
        Log.v(TAG, "-------------------------------------");
    }

}
