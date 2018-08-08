package com.test.devilsen.test.multiprocess.sp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Map;
import java.util.Set;

import static com.test.devilsen.test.multiprocess.sp.ConstantUtil.CURSOR_COLUMN_NAME;
import static com.test.devilsen.test.multiprocess.sp.ConstantUtil.CURSOR_COLUMN_TYPE;
import static com.test.devilsen.test.multiprocess.sp.ConstantUtil.CURSOR_COLUMN_VALUE;
import static com.test.devilsen.test.multiprocess.sp.ConstantUtil.KEY_VALUE;
import static com.test.devilsen.test.multiprocess.sp.ConstantUtil.SEPARATOR;
import static com.test.devilsen.test.multiprocess.sp.ConstantUtil.TYPE_BOOLEAN;
import static com.test.devilsen.test.multiprocess.sp.ConstantUtil.TYPE_CLEAN;
import static com.test.devilsen.test.multiprocess.sp.ConstantUtil.TYPE_CONTAIN;
import static com.test.devilsen.test.multiprocess.sp.ConstantUtil.TYPE_FLOAT;
import static com.test.devilsen.test.multiprocess.sp.ConstantUtil.TYPE_GET_ALL;
import static com.test.devilsen.test.multiprocess.sp.ConstantUtil.TYPE_INT;
import static com.test.devilsen.test.multiprocess.sp.ConstantUtil.TYPE_LONG;
import static com.test.devilsen.test.multiprocess.sp.ConstantUtil.TYPE_STRING;
import static com.test.devilsen.test.multiprocess.sp.ConstantUtil.VALUE;

public class SPContentProvider extends ContentProvider {

    private final SPHelperImpl spHelperImpl = SPHelperImpl.getInstance();

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String[] path = uri.getPath().split(SEPARATOR);
        String type = path[1];
        if (type.equals(TYPE_GET_ALL)) {
            Map<String, ?> all = spHelperImpl.getAll(getContext());
            if (all != null) {
                MatrixCursor cursor = new MatrixCursor(new String[]{CURSOR_COLUMN_NAME, CURSOR_COLUMN_TYPE, CURSOR_COLUMN_VALUE});
                Set<String> keySet = all.keySet();
                for (String key : keySet) {
                    Object[] rows = new Object[3];
                    rows[0] = key;
                    rows[2] = all.get(key);
                    if (rows[2] instanceof Boolean) {
                        rows[1] = TYPE_BOOLEAN;
                    } else if (rows[2] instanceof String) {
                        rows[1] = TYPE_STRING;
                    } else if (rows[2] instanceof Integer) {
                        rows[1] = TYPE_INT;
                    } else if (rows[2] instanceof Long) {
                        rows[1] = TYPE_LONG;
                    } else if (rows[2] instanceof Float) {
                        rows[1] = TYPE_FLOAT;
                    }
                    cursor.addRow(rows);
                }
                return cursor;
            }
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        // 用这个来取数值
        String[] path = uri.getPath().split(SEPARATOR);
        String type = path[1];
        String key = path[2];
        if (type.equals(TYPE_CONTAIN)) {
            return String.valueOf(spHelperImpl.contains(getContext(), key));
        }
        return spHelperImpl.get(getContext(), key, type);
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        String[] path = uri.getPath().split(SEPARATOR);
        String key = path[2];
        Object obj = values.get(VALUE);
        boolean isKeyValue = (boolean) values.get(KEY_VALUE);
        if (obj != null)
            spHelperImpl.save(getContext(), key, obj, isKeyValue);
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        String[] path = uri.getPath().split(SEPARATOR);
        String type = path[1];
        if (type.equals(TYPE_CLEAN)) {
            spHelperImpl.clear(getContext());
            return 0;
        }
        String key = path[2];
        if (spHelperImpl.contains(getContext(), key)) {
            spHelperImpl.remove(getContext(), key);
        }
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        insert(uri, values);
        return 0;
    }
}