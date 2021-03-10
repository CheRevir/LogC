package com.cere.logc.sample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.cere.logc.LogC;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        p();
    }

    private void p() {
        a();
    }

    private void a() {
        LogC.println("print");
        LogC.v("verbose");
        LogC.d("debug");
        LogC.i("info");
        LogC.w("warn");
        LogC.e("error");
        LogC.wft("assert");
    }
}