package com.cere.logc.sample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.cere.logc.LogC;
import com.cere.logc.impl.JsonPrintFormat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private ExecutorService mExecutorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mExecutorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            mExecutorService.submit(() -> {
                LogC.e(this);
            });
        }
        //p();
        //LogC.e("b");
       /* new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                LogC.e(i);
                LogC.e(i);
                LogC.e(i);
                LogC.e(i);
                LogC.e(i);
                LogC.e(i);
                LogC.e(i);
                LogC.e(i);
            }
        }).start();*/
        // p();
    }

    private void p() {
        a();
    }

    private void a() {
        LogC.println("print");
        LogC.v("verbose");
        LogC.d("debug");
        LogC.i("info");
        LogC.w("warn", mgs -> mgs.toString().toUpperCase());
        LogC.e("error", msg -> msg.toString().toUpperCase());
        LogC.wtf("assert", msg -> msg + " print");
        LogC.e("{\"age\":1, \"name\": \"name\", \"list\":[{\"age\":1,\"name\":\"name\"},{\"age\":2,\"name\":\"name2\"}]}", new JsonPrintFormat());
        LogC.e("[{\"age\":1,\"name\":\"name\"},{\"age\":2,\"name\":\"name2\"}]", new JsonPrintFormat());
        LogC.e(LogC.getConfig().getLogDirectory());
        LogC.e(LogC.getConfig().getLogFileName());
    }
}