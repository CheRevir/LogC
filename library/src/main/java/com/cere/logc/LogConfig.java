package com.cere.logc;

import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.NonNull;

/**
 * Created by CheRevir on 2021/3/10
 */
public class LogConfig {
    public static final int PRINTLN = 1;
    public static final int VERBOSE = 2;
    public static final int DEBUG = 3;
    public static final int INFO = 4;
    public static final int WARN = 5;
    public static final int ERROR = 6;
    public static final int ASSERT = 7;

    private final Config mConfig;

    private LogConfig(Config config) {
        this.mConfig = config;
    }

    @NonNull
    public Context getContext() {
        return mConfig.mContext;
    }

    @NonNull
    public String getTag() {
        return mConfig.getTag();
    }

    public boolean isPrint() {
        return mConfig.isPrint;
    }

    public int[] getPriority() {
        return mConfig.priority;
    }

    public String getSeparator() {
        return mConfig.separator;
    }

    private static class Config {
        private final Context mContext;
        private String tag;
        private boolean isPrint = true;
        private int[] priority = {PRINTLN, VERBOSE, DEBUG, INFO, WARN, ERROR, ASSERT};
        private String separator = " -> ";

        private Config(Context context) {
            this.mContext = context;
        }

        public String getTag() {
            return TextUtils.isEmpty(tag) ? mContext.getPackageName() : tag;
        }
    }

    public static class Builder {
        private final Config mConfig;

        public Builder(@NonNull Context context) {
            this.mConfig = new Config(context);
        }

        /**
         * 设置Log的标签
         */
        public Builder setTag(@NonNull String tag) {
            mConfig.tag = tag;
            return this;
        }

        /**
         * 是否打印日志
         */
        public Builder setPrint(boolean isPrint) {
            mConfig.isPrint = isPrint;
            return this;
        }

        /**
         * 设置打印什么级别的Log
         *
         * @param priority {@link #PRINTLN}, {@link #VERBOSE}, {@link #DEBUG},
         *                 {@link #INFO}, {@link #WARN}, {@link #ERROR}, {@link #ASSERT}
         */
        public Builder setPriority(int... priority) {
            mConfig.priority = priority;
            return this;
        }

        /**
         * 设置日志分隔符
         */
        public Builder setSeparator(String separator) {
            mConfig.separator = separator;
            return this;
        }

        public LogConfig build() {
            return new LogConfig(mConfig);
        }
    }
}
