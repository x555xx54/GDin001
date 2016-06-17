package com.example.s36.gdin01.main;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.s36.gdin01.variable.GDinEvent;
import com.example.s36.gdin01.variable.SensorState;
import com.example.s36.gdin01.variable.VariableCollection;

/**
 * Created by s36 on 09.06.2016.
 */

public class GDinService extends Service implements VariableCollection {
    int count = 0;

    GDin gDin = new GDin(this);
    LockSensor lockSensorTop = new LockSensor("TopLock");

    @Override
    public void onCreate() {
        readSetting();
    }


    void readSetting() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            return super.onStartCommand(intent, flags, startId);
        }
//        Log.d(LOG_TAG_SERVICE, "GDinService - onStartCommand");
        Bundle bundle = intent.getExtras();
        GDinEvent GDinEvent = (GDinEvent) bundle.get(CONST_EVENT);


        lockSensorTop.updateState(intent);


        gDin.updateState(intent);
        gDin.setSecurityMode();
        gDin.actionProcess(intent);


        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
