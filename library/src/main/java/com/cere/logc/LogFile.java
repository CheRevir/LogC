package com.cere.logc;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by CheRevir on 2021/3/11
 */
public class LogFile implements Runnable {
    private final ConcurrentLinkedQueue<String> mQueue = new ConcurrentLinkedQueue<>();
    private final Thread mThread = new Thread(this);
    private final Object lock = new Object();
    private OutputStreamWriter mWriter;

    public LogFile(@NonNull LogConfig config) {
        String directory = config.getLogDirectory();
        if (TextUtils.isEmpty(directory)) {
            return;
        }
        File file = new File(directory + File.separator + config.getLogFileName());
        try {
            mWriter = new OutputStreamWriter(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mThread.start();
    }

    public void add(String msg) {
        mQueue.offer(msg);
        if (mThread.getState() == Thread.State.WAITING) {
            synchronized (lock) {
                lock.notify();
            }
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                if (mQueue.isEmpty()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    while (!mQueue.isEmpty()) {
                        try {
                            mWriter.append(mQueue.poll()).append("\n");
                            mWriter.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
