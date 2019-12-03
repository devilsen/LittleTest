package com.test.devilsen.test.multiprocess.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.annotation.CheckResult;
import androidx.annotation.Nullable;
import android.text.TextUtils;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.test.devilsen.test.multiprocess.sp.ConstantUtil.TYPE_BOOLEAN;
import static com.test.devilsen.test.multiprocess.sp.ConstantUtil.TYPE_FLOAT;
import static com.test.devilsen.test.multiprocess.sp.ConstantUtil.TYPE_INT;
import static com.test.devilsen.test.multiprocess.sp.ConstantUtil.TYPE_LONG;
import static com.test.devilsen.test.multiprocess.sp.ConstantUtil.TYPE_STRING;
import static com.test.devilsen.test.multiprocess.sp.ConstantUtil.TYPE_STRING_SET;

class SPHelperImpl {

    private SoftReference<ConcurrentHashMap<String, Object>> sCacheMap;

    private SharedPreferences mSharedPreferences;

    private SPHelperImpl() {
    }

    static SPHelperImpl getInstance() {
        return Holder.instance;
    }

    private static class Holder {
        private static final SPHelperImpl instance = new SPHelperImpl();
    }

    @Nullable
    @CheckResult
    SharedPreferences getSP(Context context) {
        if (context == null) {
            return null;
        }

        if (mSharedPreferences == null) {
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        }

        return mSharedPreferences;
    }

    @CheckResult
    public SharedPreferences getSP(Context context, String name, int mod) {
        if (TextUtils.isEmpty(name)) {
            return getSP(context);
        } else {
            if (context != null) {
                return context.getSharedPreferences(name, mod);
            } else {
                throw new RuntimeException("get SharedPreferences false!");
            }
        }
    }

    private Object getCachedValue(String name) {
        if (sCacheMap != null) {
            Map<String, Object> map = sCacheMap.get();
            if (map != null) {
                return map.get(name);
            }
        }
        return null;
    }

    private void setValueToCached(String name, Object value) {
        ConcurrentHashMap<String, Object> map;
        if (sCacheMap == null) {
            map = new ConcurrentHashMap<>();
            sCacheMap = new SoftReference<>(map);
        } else {
            map = sCacheMap.get();
            if (map == null) {
                map = new ConcurrentHashMap<>();
                sCacheMap = new SoftReference<>(map);
            }
        }
        map.put(name, value);
    }

    private void cleanCachedValue() {
        Map<String, Object> map;
        if (sCacheMap != null) {
            map = sCacheMap.get();
            if (map != null) {
                map.clear();
            }
        }
    }


    synchronized <T> void save(Context context, String name, T t, boolean isKeyValue) {
        SharedPreferences sp = getSP(context);
        if (sp == null) return;

        if (t.equals(getCachedValue(name))) {
            return;
        }
        SharedPreferences.Editor editor = sp.edit();
        if (t instanceof Boolean) {
            editor.putBoolean(name, (Boolean) t);
        }
        if (t instanceof String) {
            editor.putString(name, (String) t);
        }
        if (t instanceof Integer) {
            editor.putInt(name, (Integer) t);
        }
        if (t instanceof Long) {
            editor.putLong(name, (Long) t);
        }
        if (t instanceof Float) {
            editor.putFloat(name, (Float) t);
        }

        if (isKeyValue) {
            editor.commit();
        } else {
            editor.apply();
        }
        setValueToCached(name, t);
    }

    String get(Context context, String name, String type) {
        Object value = getCachedValue(name);
        if (value != null) {
            return String.valueOf(value);
        } else {
            value = getImpl(context, name, type);
            setValueToCached(name, value);
            return String.valueOf(value);
        }
    }

    private Object getImpl(Context context, String name, String type) {
        if (!contains(context, name)) {
            return null;
        } else {
            switch (type) {
                case TYPE_STRING:
                    return getString(context, name, null);
                case TYPE_BOOLEAN:
                    return getBoolean(context, name, false);
                case TYPE_INT:
                    return getInt(context, name, 0);
                case TYPE_LONG:
                    return getLong(context, name, 0L);
                case TYPE_FLOAT:
                    return getFloat(context, name, 0F);
                case TYPE_STRING_SET:
                    return getString(context, name, null);
            }
            return null;
        }
    }

    String getString(Context context, String name, String defaultValue) {
        SharedPreferences sp = getSP(context);
        if (sp == null) return defaultValue;
        return sp.getString(name, defaultValue);
    }

    int getInt(Context context, String name, int defaultValue) {
        SharedPreferences sp = getSP(context);
        if (sp == null) return defaultValue;
        return sp.getInt(name, defaultValue);
    }

    float getFloat(Context context, String name, float defaultValue) {
        SharedPreferences sp = getSP(context);
        if (sp == null) return defaultValue;
        return sp.getFloat(name, defaultValue);
    }

    boolean getBoolean(Context context, String name, boolean defaultValue) {
        SharedPreferences sp = getSP(context);
        if (sp == null) return defaultValue;
        return sp.getBoolean(name, defaultValue);
    }

    long getLong(Context context, String name, long defaultValue) {
        SharedPreferences sp = getSP(context);
        if (sp == null) return defaultValue;
        return sp.getLong(name, defaultValue);
    }

    boolean contains(Context context, String name) {
        SharedPreferences sp = getSP(context);
        return sp != null && sp.contains(name);
    }

    void remove(Context context, String name) {
        SharedPreferences sp = getSP(context);
        if (sp == null) return;
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(name);
        editor.apply();
    }

    void clear(Context context) {
        SharedPreferences sp = getSP(context);
        if (sp == null) return;
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
        cleanCachedValue();
    }

    Map<String, ?> getAll(Context context) {
        SharedPreferences sp = getSP(context);
        if (sp == null) return new HashMap<>();
        return sp.getAll();
    }
}