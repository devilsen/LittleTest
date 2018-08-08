package com.test.devilsen.test.multiprocess.sp;

import android.content.UriMatcher;
import android.net.Uri;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * desc :
 * date : 2018/8/8
 *
 * @author : dongSen
 */
@RunWith(AndroidJUnit4.class)
public class UriTest {

    private static final String AUTHORITY = "com.wuba.crm";

    private static UriMatcher matcher;

    static {
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(AUTHORITY, "user", 1);// 配置表
        matcher.addURI(AUTHORITY, "user/#", 2);// 匹配任何数字
        matcher.addURI(AUTHORITY, "user/*", 3);// 匹配任何文本
    }

    @Test
    public void matchTest() {
        Uri uri = Uri.parse("content://com.lenve.cphost.mycontentprovider/user/3");

        long start = System.currentTimeMillis();
        int match = matcher.match(uri);
        long end = System.currentTimeMillis();
        Log.e("UriTest time ", "" + (end - start));

        Uri uri2 = Uri.parse("content://com.lenve.cphost.mycontentprovider/user/3");
        start = System.currentTimeMillis();
        System.out.println(uri2.getPath());
        String[] split = uri2.getPath().split("/");
        end = System.currentTimeMillis();
        Log.e("UriTest time ", "" + (end - start));

        //Split is better
    }


}
