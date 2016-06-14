package com.example.s36.gdin01.main;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.s36.gdin01.variable.Event;
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
        Event event = (Event) bundle.get(CONST_EVENT);

        if (event == Event.Start){
            Log.d(LOG_TAG_SERVICE, "event == Event.Start");
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
