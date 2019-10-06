package com.ulul.carebuddies.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.HashMap;

public class FirebaseService extends Service {

    private HashMap<String, String> firebaseBattery;

    @Override
    public void onCreate() {
        super.onCreate();
        firebaseBattery = new HashMap<>();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
