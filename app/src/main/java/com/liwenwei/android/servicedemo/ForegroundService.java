package com.liwenwei.android.servicedemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import java.lang.reflect.Method;

/**
 * Created by liwenwei on 2018/6/10.
 */

public class ForegroundService extends Service {

    private NotificationManager mNM;

    private Method mStartForeground;
    private Method mStopForeground;
    private static final Class[] mStartForegroundSignature = new Class[] {
            int.class, Notification.class};
    private static final Class[] mStopForegroundSignature = new Class[] {
            boolean.class};
    private Object[] mStartForegroundArgs = new Object[2];
    private Object[] mStopForegroundArgs = new Object[1];

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mNM = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        try {
            mStartForeground = ForegroundService.class.getMethod("startForeground", mStartForegroundSignature);
            mStopForeground = ForegroundService.class.getMethod("stopForeground", mStopForegroundSignature);
        } catch (NoSuchMethodException e) {
            mStartForeground = mStopForeground = null;
        }

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!")
                        .setContentIntent(contentIntent);

        mNM.notify(1, mBuilder.build());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
