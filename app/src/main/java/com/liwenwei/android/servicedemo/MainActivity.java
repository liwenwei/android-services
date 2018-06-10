package com.liwenwei.android.servicedemo;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "SERVICE_TEST";
    private ServiceConnection conn;
    private boolean isBinded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                LocalService.SimpleBinder binder = (LocalService.SimpleBinder) service;
                binder.add(1, 2);
                Log.v(TAG, "3 + 5 = " + binder.add(3, 5));
                Log.v(TAG, binder.getService().toString());
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        findViewById(R.id.btnStart).setOnClickListener(v -> {
            startService(new Intent(MainActivity.this, LocalService1.class));
        });

        findViewById(R.id.btnStop).setOnClickListener(v -> {
            stopService(new Intent(MainActivity.this, LocalService1.class));
        });

        findViewById(R.id.btnBind).setOnClickListener(v  -> {
            bindService(new Intent(MainActivity.this, LocalService.class),
                    conn,
                    Context.BIND_AUTO_CREATE);
            isBinded = true;
        });

        findViewById(R.id.btnUnbind).setOnClickListener(v -> {
            if (isBinded) {
                unbindService(conn);
                isBinded = false;
            }
        });

        findViewById(R.id.btnStartFore).setOnClickListener(v -> {
            startService(new Intent(MainActivity.this, ForegroundService.class));
        });

        findViewById(R.id.btnStopFore).setOnClickListener(v -> {
            stopService(new Intent(MainActivity.this, ForegroundService.class));
        });
    }

    public void startMyService(View v) {
        Intent intent = new Intent(this, HelloService.class);
        startService(intent);
    }

    public void startMyNotification(View v) {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, notificationIntent, 0);

        NotificationCompat.Builder notification =
                new NotificationCompat.Builder(this)
                .setContentTitle("Some Message")
                .setContentText("You've received new messages!")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent);
    }
}
