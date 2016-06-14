package com.example.s36.gdin01.main;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.s36.gdin01.variable.GDinEvent;
import com.example.s36.gdin01.variable.VariableCollection;

/**
 * Created by s36 on 09.06.2016.
 */

public class GDinService extends Service implements VariableCollection {
    int count = 0;

    GDin gDin = new GDin(this);

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            return super.onStartCommand(intent, flags, startId);
        }
//        Log.d(LOG_TAG_SERVICE, "GDinService - onStartCommand");



        Bundle bundle = intent.getExtras();
        GDinEvent GDinEvent = (GDinEvent) bundle.get(CONST_EVENT);

        if (GDinEvent == GDinEvent.Start){
            Log.d(LOG_TAG_SERVICE, "GDinEvent == GDinEvent.Start");
        }

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
