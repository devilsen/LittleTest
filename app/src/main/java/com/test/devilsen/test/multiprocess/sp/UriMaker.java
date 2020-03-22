package com.test.devilsen.test.multiprocess.sp;

import android.net.Uri;
import androidx.annotation.NonNull;

import static com.test.devilsen.test.multiprocess.sp.ConstantUtil.CONTENT_URI;
import static com.test.devilsen.test.multiprocess.sp.ConstantUtil.SEPARATOR;
import static com.test.devilsen.test.multiprocess.sp.ConstantUtil.TYPE_BOOLEAN;
import static com.test.devilsen.test.multiprocess.sp.ConstantUtil.TYPE_FLOAT;
import static com.test.devilsen.test.multiprocess.sp.ConstantUtil.TYPE_INT;
import static com.test.devilsen.test.multiprocess.sp.ConstantUtil.TYPE_LONG;
import static com.test.devilsen.test.multiprocess.sp.ConstantUtil.TYPE_STRING;
import static com.test.devilsen.test.multiprocess.sp.ConstantUtil.TYPE_STRING_SET;

/**
 * desc : 用于生成uri
 * date : 2018/8/8
 *
 * @author : dongSen
 */
public class UriMaker {

    private static final String CONTENT = CONTENT_URI + SEPARATOR;

    static Uri obtainString(@NonNull String key) {
        return Uri.parse(CONTENT + TYPE_STRING + SEPARATOR + key);
    }

    static Uri obtainBoolean(@NonNull String key) {
        return Uri.parse(CONTENT + TYPE_BOOLEAN + SEPARATOR + key);
    }

    static Uri obtainInt(@NonNull String key) {
        return Uri.parse(CONTENT + TYPE_INT + SEPARATOR + key);
    }

    static Uri obtainLong(@NonNull String key) {
        return Uri.parse(CONTENT + TYPE_LONG + SEPARATOR + key);
    }

    static Uri obtainFloat(@NonNull String key) {
        return Uri.parse(CONTENT + TYPE_FLOAT + SEPARATOR + key);
    }

    static Uri obtainSet(@NonNull String key) {
        return Uri.parse(CONTENT + TYPE_STRING_SET + SEPARATOR + key);
    }


}
