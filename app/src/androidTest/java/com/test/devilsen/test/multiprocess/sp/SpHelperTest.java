package com.test.devilsen.test.multiprocess.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Random;

/**
 * desc :
 * date : 2018/8/7
 *
 * @author : dongSen
 */
@RunWith(AndroidJUnit4.class)
public class SpHelperTest {

    @Test
    public void useAppContext() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
//        SPHelper.init((Application) appContext.getApplicationContext());

        Random random = new Random();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(random.nextInt(200));
        }

        long start = System.currentTimeMillis();
//        for (int i = 0; i < 100; i++) {
//            SPHelper.save("key" + list.get(i), i);
//        }
        long end = System.currentTimeMillis();
        Log.e("ExampleInstrumentedTest", "SPHelper takes " + (end - start) + " millis");

        start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            SharedPreferences sp = appContext.getSharedPreferences("text", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("key" + list.get(i), i);
            editor.apply();
        }
        end = System.currentTimeMillis();
        Log.e("ExampleInstrumentedTest", "SharedPreferences takes " + (end - start) + " millis");

        Log.e("ExampleInstrumentedTest", "-----------------------------");
    }
}