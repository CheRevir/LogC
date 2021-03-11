package com.cere.logc;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.cere.logc.impl.GeneralPrintFormat;

import java.io.File;
import java.util.Arrays;

/**
 * Created by CheRevir on 2021/3/10
 */
public class LogC {
    private static LogConfig sConfig;
    private static LogFile sLogFile;

    public static void init(@NonNull LogConfig config) {
        sConfig = config;
        if (config.enableSave()) {
            sLogFile = new LogFile(config);
        }
    }

    public static LogConfig getConfig() {
        return sConfig;
    }

    public static void println(Object msg) {
        print(LogConfig.PRINTLN, msg, new GeneralPrintFormat());
    }

    public static void println(Object msg, @NonNull PrintFormat format) {
        print(LogConfig.PRINTLN, msg, format);
    }

    public static void v(Object msg) {
        print(LogConfig.VERBOSE, msg, new GeneralPrintFormat());
    }

    public static void v(Object msg, @NonNull PrintFormat format) {
        print(LogConfig.VERBOSE, msg, format);
    }

    public static void d(Object msg) {
        print(LogConfig.DEBUG, msg, new GeneralPrintFormat());
    }

    public static void d(Object msg, @NonNull PrintFormat format) {
        print(LogConfig.DEBUG, msg, format);
    }

    public static void i(Object msg) {
        print(LogConfig.INFO, msg, new GeneralPrintFormat());
    }

    public static void i(Object msg, @NonNull PrintFormat format) {
        print(LogConfig.INFO, msg, format);
    }

    public static void w(Object msg) {
        print(LogConfig.WARN, msg, new GeneralPrintFormat());
    }

    public static void w(Object msg, @NonNull PrintFormat format) {
        print(LogConfig.WARN, msg, format);
    }

    public static void e(Object msg) {
        print(LogConfig.ERROR, msg, new GeneralPrintFormat());
    }

    public static void e(Object msg, @NonNull PrintFormat format) {
        print(LogConfig.ERROR, msg, format);
    }

    public static void wtf(Object msg) {
        print(LogConfig.ASSERT, msg, new GeneralPrintFormat());
    }

    public static void wtf(Object msg, @NonNull PrintFormat format) {
        print(LogConfig.ASSERT, msg, format);
    }

    public static boolean deleteAllLog() {
        if (sConfig == null) {
            throw new NullPointerException("no init");
        }
        String directory = sConfig.getLogDirectory();
        if (!TextUtils.isEmpty(directory)) {
            File file = new File(directory);
            if (file.exists()) {
                return file.delete();
            } else {
                return true;
            }
        }
        return false;
    }

    private static void print(int priority, Object msg, PrintFormat format) {
        if (sConfig == null) {
            throw new NullPointerException("no init");
        }
        if (sConfig.isPrint() && contains(priority)) {
            String tag = getStackTraceTag(6);
            String value = format.format(msg);
            switch (priority) {
                case LogConfig.PRINTLN:
                    System.out.println(tag + ": " + value);
                    break;
                case LogConfig.VERBOSE:
                    Log.v(tag, value);
                    break;
                case LogConfig.DEBUG:
                    Log.d(tag, value);
                    break;
                case LogConfig.INFO:
                    Log.i(tag, value);
                    break;
                case LogConfig.WARN:
                    Log.w(tag, value);
                    break;
                case LogConfig.ERROR:
                    Log.e(tag, value);
                    break;
                case LogConfig.ASSERT:
                    Log.wtf(tag, value);
                    break;
            }
            if (sConfig.enableSave()) {
                if (sLogFile == null) {
                    throw new NullPointerException("no init");
                }
                sLogFile.add(tag + ": " + value);
            }
        }
    }

    @NonNull
    private static String getStackTraceTag(int index) {
        StackTraceElement element = getStackTrace()[index];
        return sConfig.getTag() + sConfig.getSeparator() + element;
    }

    @NonNull
    private static StackTraceElement[] getStackTrace() {
        return Thread.currentThread().getStackTrace();
    }

    private static boolean contains(int priority) {
        return Arrays.stream(sConfig.getPriority()).anyMatch(e -> e == priority);
    }
}
