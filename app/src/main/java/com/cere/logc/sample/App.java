package com.cere.logc.sample;

import android.app.Application;

import com.cere.logc.LogC;
import com.cere.logc.LogConfig;

/**
 * Created by CheRevir on 2021/3/10
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogConfig config = new LogConfig.Builder(this).setEnableSave(true).build();
        LogC.init(config);
    }
}
