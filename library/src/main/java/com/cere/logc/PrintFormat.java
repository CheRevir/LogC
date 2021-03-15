package com.cere.logc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by CheRevir on 2021/3/10
 */
public interface PrintFormat {
    @NonNull
    String format(@Nullable Object msg);
}
