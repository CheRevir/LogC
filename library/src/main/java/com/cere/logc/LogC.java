package com.cere.logc;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Arrays;

/**
 * Created by CheRevir on 2021/3/10
 */
public class LogC {
    private static LogConfig sConfig;

    public static void init(@NonNull LogConfig config) {
        sConfig = config;
    }

    public static void println(Object msg) {
        println(LogConfig.PRINTLN, getStackTraceTag(), msg);
    }

    public static void v(Object msg) {
        println(LogConfig.VERBOSE, getStackTraceTag(), msg);
    }

    public static void d(Object msg) {
        println(LogConfig.DEBUG, getStackTraceTag(), msg);
    }

    public static void i(Object msg) {
        println(LogConfig.INFO, getStackTraceTag(), msg);
    }

    public static void w(Object msg) {
        println(LogConfig.WARN, getStackTraceTag(), msg);
    }

    public static void e(Object msg) {
        println(LogConfig.ERROR, getStackTraceTag(), msg);
    }

    public static void wft(Object msg) {
        println(LogConfig.ASSERT, getStackTraceTag(), msg);
    }

    private static String getStackTraceTag() {
        if (sConfig == null) {
            throw new NullPointerException("no init");
        }
        StackTraceElement element = getStackTrace()[5];
        return sConfig.getTag() + sConfig.getSeparator() + element;
    }

    private static StackTraceElement[] getStackTrace() {
        return Thread.currentThread().getStackTrace();
    }

    private static void println(int priority, String tag, Object msg) {
        if (sConfig == null) {
            throw new NullPointerException("no init");
        }
        if (sConfig.isPrint() && contains(priority)) {
            switch (priority) {
                case LogConfig.PRINTLN:
                    System.out.println(msg);
                    break;
                case LogConfig.VERBOSE:
                    Log.v(tag, msg.toString());
                    break;
                case LogConfig.DEBUG:
                    Log.d(tag, msg.toString());
                    break;
                case LogConfig.INFO:
                    Log.i(tag, msg.toString());
                    break;
                case LogConfig.WARN:
                    Log.w(tag, msg.toString());
                    break;
                case LogConfig.ERROR:
                    Log.e(tag, msg.toString());
                    break;
                case LogConfig.ASSERT:
                    Log.wtf(tag, msg.toString());
                    break;
            }
        }
    }

    private static boolean contains(int priority) {
        return Arrays.stream(sConfig.getPriority()).anyMatch(e -> e == priority);
    }
}
