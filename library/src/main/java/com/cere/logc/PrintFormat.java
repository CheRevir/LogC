package com.cere.logc;

import androidx.annotation.NonNull;

/**
 * Created by CheRevir on 2021/3/10
 */
public interface PrintFormat {
    @NonNull
    String format(@NonNull Object msg);
}
