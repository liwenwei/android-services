package com.liwenwei.android.servicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;


/**
 * Extending the IntentService class
 */
public class HelloIntentService extends IntentService {

    public HelloIntentService() {
        super("HelloIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // Normally we could do some work here, like download a file
        // For example, we just sleep for 5 seconds
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
