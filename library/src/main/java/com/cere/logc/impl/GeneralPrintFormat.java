package com.cere.logc.impl;

import androidx.annotation.NonNull;

import com.cere.logc.PrintFormat;

/**
 * Created by CheRevir on 2021/3/10
 */
public class GeneralPrintFormat implements PrintFormat {
    @NonNull
    @Override
    public String format(@NonNull Object msg) {
        return msg.toString();
    }
}
