package com.ulul.medbuddies.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class ScheduleReceiver extends BroadcastReceiver {


    public ScheduleReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {

    }

    @Override
    public IBinder peekService(Context myContext, Intent service) {
        return super.peekService(myContext, service);
    }
}
