package com.sachinshankar.utalibrary;

import android.app.Service;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.content.Intent;
import android.os.Bundle;

public class ClosingService extends Service{

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);

        // Handle application closing
        //fireClosingNotification();
        SharedPreferences sharedpreferences = getSharedPreferences(SigninActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();

        // Destroy the service
        stopSelf();
    }
}
