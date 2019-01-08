package com.wuba.acm.test;

import android.text.SpannableString;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * desc :
 * date : 2018/8/14
 *
 * @author : dongSen
 */
public class Recursive {

    private static final int MAX = 10000;
    private static final int timeSnap = 1000 * 60 * 60 * 24;

    private static final SimpleDateFormat yearHourMin = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

    public static void main(String[] args) {
//        testString();
//        testSpnanle();

//        vivo              1577008889862
//        currentTimeMillis 1545472978754
//        conversation      1545466901184
        int a = (int) (1577008889862L - System.currentTimeMillis());
        System.out.println(a);
//        long day = () / timeSnap;


//        System.out.println(day);
//        System.out.println(System.currentTimeMillis());
    }

    private static void test(int num) {
        if (num == 1)
            return;

        System.out.println(num);
        test(--num);
        System.out.println(num + " ----- ");

    }

    private static void testString() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < MAX; i++) {
            String a = i + "";
        }
        long end = System.currentTimeMillis();
        System.out.println("String :  " + (end - start));
    }

    private static void testSpnanle() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < MAX; i++) {
            new SpannableString(i + "");
        }
        long end = System.currentTimeMillis();
        System.out.println("spannable :  " + (end - start));
    }


}
