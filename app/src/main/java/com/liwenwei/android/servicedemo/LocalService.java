package com.liwenwei.android.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by liwenwei on 2018/6/10.
 */

public class LocalService extends Service {

    public class SimpleBinder extends Binder {

        public LocalService getService() {
            return LocalService.this;
        }

        public int add(int a, int b) {
            return a + b;
        }
    }

    private SimpleBinder mBinder;
    @Override
    public void onCreate() {
        super.onCreate();
        mBinder = new SimpleBinder();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
