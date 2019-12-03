package com.test.devilsen.test.multiprocess.sp;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import androidx.annotation.NonNull;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.test.devilsen.test.multiprocess.sp.ConstantUtil.COMMA_REPLACEMENT;
import static com.test.devilsen.test.multiprocess.sp.ConstantUtil.KEY_VALUE;
import static com.test.devilsen.test.multiprocess.sp.ConstantUtil.NULL_STRING;
import static com.test.devilsen.test.multiprocess.sp.ConstantUtil.VALUE;

/**
 * description sp 帮助类，操作本地SP文件
 * <p>
 * author: LiQiuzuo
 * CreatTime: 2016/9/10 16:12
 * LastModify: LiQiuzuo
 * LastModifyTime 2018年08月07日18:29:07
 * LastCheckedBy: dongSen
 */
public class SpHelper {
    // single class
    private static SpHelper mInstance;

    private Context mContext;

    private SpHelper() {
    }

    public static SpHelper getInstance(Context context) {
        if (mInstance == null) {
            synchronized (SpHelper.class) {
                if (mInstance == null) {
                    mInstance = new SpHelper();
                }
            }
        }
        mInstance.initContext(context);
        return mInstance;
    }

    public static SpHelper getInstance() {
        if (mInstance == null) {
            synchronized (SpHelper.class) {
                if (mInstance == null) {
                    mInstance = new SpHelper();
                }
            }
        }
        return mInstance;
    }

    public void initContext(Context context) {
        mContext = context.getApplicationContext();
    }

    private SharedPreferences getSP() {
        return SPHelperImpl.getInstance().getSP(mContext);
    }

//    public SharedPreferences getSP(String name, int mod) {
//        return SPHelperImpl.getInstance().getSP(mContext, name, mod);
//    }

//    public boolean put(String name, int mod, Map<String, Object> map, boolean isKeyValue) throws Exception {
//        if (map == null || map.size() == 0) {
//            return false;
//        }
//        SharedPreferences.Editor editor = getSP(name, mod).edit();
//        Iterator iterator = map.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry entry = (Map.Entry) iterator.next();
//            String key = (String) entry.getKey();
//            Object val = entry.getValue();
//            put(editor, key, val);
//        }
//        if (isKeyValue) {//关键数据，确保提交结果及后续操作数据正确
//            return editor.commit();
//        } else {
//            editor.apply();
//            return true;
//        }
//    }

    public boolean put(Map<String, Object> map, boolean isKeyValue) {

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue(), isKeyValue);
        }

        return true;
    }
//
//    public boolean put(String name, int mod, String key, Object val, boolean isKeyValue) {
//        if (val instanceof Integer) {
//            put(key, (Integer) val, isKeyValue);
//        } else if (val instanceof String) {
//            put(key, (String) val, isKeyValue);
//        } else if (val instanceof Boolean) {
//            put(key, (Boolean) val, isKeyValue);
//        } else if (val instanceof Float) {
//            put(key, (Float) val, isKeyValue);
//        } else if (val instanceof Long) {
//            put(key, (Long) val, isKeyValue);
//        }
//
//        return true;
//    }

    /**
     * @param key        键值
     * @param val        数据
     * @param isKeyValue 是否会出现线程问题
     * @return 返回保存结果
     */
    public boolean put(String key, Object val, boolean isKeyValue) {
        if (val instanceof Integer) {
            put(key, (Integer) val, isKeyValue);
        } else if (val instanceof String) {
            put(key, (String) val, isKeyValue);
        } else if (val instanceof Boolean) {
            put(key, (Boolean) val, isKeyValue);
        } else if (val instanceof Float) {
            put(key, (Float) val, isKeyValue);
        } else if (val instanceof Long) {
            put(key, (Long) val, isKeyValue);
        }

        return true;
    }

    public synchronized void put(String name, Boolean t, boolean isKeyValue) {
        checkContext();

        Uri uri = UriMaker.obtainBoolean(name);
        ContentValues cv = new ContentValues(2);
        cv.put(VALUE, t);
        cv.put(KEY_VALUE, isKeyValue);

        update(uri, cv);
    }

    public synchronized void put(String name, String t, boolean isKeyValue) {
        checkContext();

        Uri uri = UriMaker.obtainString(name);
        ContentValues cv = new ContentValues(2);
        cv.put(VALUE, t);
        cv.put(KEY_VALUE, isKeyValue);

        update(uri, cv);
    }

    public synchronized void put(String name, Integer t, boolean isKeyValue) {
        checkContext();

        Uri uri = UriMaker.obtainInt(name);
        ContentValues cv = new ContentValues(2);
        cv.put(VALUE, t);
        cv.put(KEY_VALUE, isKeyValue);

        update(uri, cv);
    }

    public synchronized void put(String name, Long t, boolean isKeyValue) {
        checkContext();

        Uri uri = UriMaker.obtainLong(name);
        ContentValues cv = new ContentValues(2);
        cv.put(VALUE, t);
        cv.put(KEY_VALUE, isKeyValue);

        update(uri, cv);
    }

    public synchronized void put(String name, Float t, boolean isKeyValue) {
        checkContext();

        Uri uri = UriMaker.obtainFloat(name);
        ContentValues cv = new ContentValues(2);
        cv.put(VALUE, t);
        cv.put(KEY_VALUE, isKeyValue);

        update(uri, cv);
    }


    public synchronized void put(String name, Set<String> t, boolean isKeyValue) {
        checkContext();

        Uri uri = UriMaker.obtainSet(name);
        Set<String> convert = new HashSet<>(t.size());
        for (String string : t) {
            convert.add(string.replace(",", COMMA_REPLACEMENT));
        }
        ContentValues cv = new ContentValues(2);
        cv.put(VALUE, convert.toString());
        cv.put(KEY_VALUE, isKeyValue);

        update(uri, cv);
    }

    public String getString(String key, String defaultS) {
        checkContext();

        Uri uri = UriMaker.obtainString(key);

        ContentResolver cr = mContext.getContentResolver();
        String rtn = cr.getType(uri);
        if (rtn == null || rtn.equals(NULL_STRING)) {
            return defaultS;
        }

        return rtn;
    }

    public String getString(String key) {
        return getString(key, "");
    }

    public Boolean getBoolean(String key, Boolean defaultS) {
        checkContext();

        Uri uri = UriMaker.obtainBoolean(key);

        ContentResolver cr = mContext.getContentResolver();
        String rtn = cr.getType(uri);
        if (rtn == null || rtn.equals(NULL_STRING)) {
            return defaultS;
        }
        return Boolean.parseBoolean(rtn);
    }

    public Long getLong(String key, Long defaultS) {
        checkContext();

        Uri uri = UriMaker.obtainLong(key);

        ContentResolver cr = mContext.getContentResolver();
        String rtn = cr.getType(uri);
        if (rtn == null || rtn.equals(NULL_STRING)) {
            return defaultS;
        }
        return Long.parseLong(rtn);
    }

    public Long getLong(String key) {
        try {
            return getLong(key, 0L);
        } catch (Exception e) {
            return 0L;
        }
    }

    public Integer getInt(String key, Integer defaultS) {
        checkContext();

        Uri uri = UriMaker.obtainInt(key);

        ContentResolver cr = mContext.getContentResolver();
        String rtn = cr.getType(uri);
        if (rtn == null || rtn.equals(NULL_STRING)) {
            return defaultS;
        }
        return Integer.parseInt(rtn);
    }

    public Integer getInt(String key) {
        try {
            return getInt(key, 0);
        } catch (Exception e) {
            return 0;
        }
    }

    private void update(@NonNull Uri uri, @NonNull ContentValues cv) {
        ContentResolver cr = mContext.getContentResolver();
        cr.update(uri, cv, null, null);
    }

    private void checkContext() {
        if (mContext == null) {
            throw new IllegalStateException("context has not been initialed");
        }
    }
}