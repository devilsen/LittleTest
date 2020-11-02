package com.test.devilsen.test.log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * desc : log 封装
 * date : 2020/10/15 2:43 PM
 *
 * @author : from orhanobut
 */
public class ELog {

    public static final int VERBOSE = 2;
    public static final int DEBUG = 3;
    public static final int INFO = 4;
    public static final int WARN = 5;
    public static final int ERROR = 6;
    public static final int ASSERT = 7;

    static final String E_TAG = "DEVIL_SEN";
    static boolean enableLog = true;

    private static final LogPrinter printer = new LogPrinter();

    public static void log(int priority, @Nullable String tag, @Nullable String message, @Nullable Throwable throwable) {
        printer.log(priority, tag, message, throwable);
    }

    public static void d(@NonNull String message, @Nullable Object... args) {
        printer.d(message, args);
    }

    public static void d(@NonNull String message) {
        printer.d(message);
    }

    public static void d(@Nullable Object object) {
        printer.d(object);
    }

    public static void e(@NonNull String message, @Nullable Object... args) {
        printer.e(null, message, args);
    }

    public static void e(@Nullable Throwable throwable, @NonNull String message, @Nullable Object... args) {
        printer.e(throwable, message, args);
    }

    public static void i(@NonNull String message, @Nullable Object... args) {
        printer.i(message, args);
    }

    public static void v(@NonNull String message, @Nullable Object... args) {
        printer.v(message, args);
    }

    public static void w(@NonNull String message, @Nullable Object... args) {
        printer.w(message, args);
    }

    /**
     * Tip: Use this for exceptional situations to log
     * ie: Unexpected errors etc
     */
    public static void wtf(@NonNull String message, @Nullable Object... args) {
        printer.wtf(message, args);
    }

    /**
     * Formats the given json content and print it
     */
    public static void json(@Nullable String json) {
        printer.json(json);
    }

    /**
     * Formats the given xml content and print it
     */
    public static void xml(@Nullable String xml) {
        printer.xml(xml);
    }

    /**
     * Enable or disable log print
     *
     * @param enable print log
     */
    public static void enableLog(boolean enable) {
        enableLog = enable;
    }

    /**
     * Open show Thread info
     */
    public static void showThreadInfo() {
        printer.showThreadInfo();
    }

    /**
     * Open show method info
     */
    public static void showMethodInfo() {
        printer.showMethodInfo();
    }

    /**
     * ELog is enable in app
     */
    static boolean isEnable() {
        return enableLog;
    }
}
