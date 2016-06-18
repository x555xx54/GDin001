package com.example.s36.gdin01.main;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import com.example.s36.gdin01.variable.GDinAction;
import com.example.s36.gdin01.variable.GDinEvent;
import com.example.s36.gdin01.variable.VariableCollection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


/**
 * Created by s36 on 09.06.2016.
 */

public class ServiceGD extends Service implements VariableCollection {
    boolean isEnableSms;

    SensorGD sensorTopLock;
    GDin gDin = new GDin(this);

    @Override
    public void onCreate() {
        sensorTopLock = new SensorGD(this, updateSettingSensorName(), updateSettinEvent());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            return super.onStartCommand(intent, flags, startId);
        }
//        Log.d(LOG_TAG_SERVICE, "ServiceGD - onStartCommand");
        Bundle bundle = intent.getExtras();
        GDinEvent GDinEvent = (GDinEvent) bundle.get(CONST_EVENT);

        GDinAction gDinAction = (GDinAction) bundle.get(CONST_ACTION);
        if (gDinAction == GDinAction.UpdateSetting) {
            sensorTopLock.updateSeting(updateSettingSensorName(), updateSettinEvent());
        }
        sensorTopLock.updateState(intent);


        gDin.updateState(intent);
        gDin.setSecurityMode();
        gDin.actionProcess(intent);


        return START_STICKY;
    }

    HashMap<GDinEvent, HashSet<GDinAction>> updateSettinEvent() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        HashMap<GDinEvent, HashSet<GDinAction>> eventActionMap = new HashMap<>();

        ArrayList<GDinAction> actionArrayList1 = new ArrayList<>();


        HashSet<GDinAction> hashSetAction = new HashSet<>();
        if (actionArrayList1 != null) {
            actionArrayList1.clear();
        }

        if (preferences.getBoolean(KEY_PREFERENCE_SENSOR_EVENT_PWR_ON_ACTION_ALARM_ON_1_1, false)) {
            hashSetAction.add(GDinAction.AlarmOn);
        }
        if (preferences.getBoolean(KEY_PREFERENCE_SENSOR_EVENT_PWR_ON_ACTION_ALARM_OFF_1_2, false)) {
            hashSetAction.add(GDinAction.AlarmOff);
        }
        if (preferences.getBoolean(KEY_PREFERENCE_SENSOR_EVENT_PWR_ON_ACTION_SEND_SMS_1_3, false)) {
            hashSetAction.add(GDinAction.SendSMSOwner);
        }
        eventActionMap.put(GDinEvent.PWROn, hashSetAction);

        hashSetAction.clear();

        if (preferences.getBoolean(KEY_PREFERENCE_SENSOR_EVENT_PWR_OFF_ACTION_ALARM_ON_1_1, false)) {
            hashSetAction.add(GDinAction.AlarmOn);
        }
        if (preferences.getBoolean(KEY_PREFERENCE_SENSOR_EVENT_PWR_OFF_ACTION_ALARM_OFF_1_2, false)) {
            hashSetAction.add(GDinAction.AlarmOff);
        }
        if (preferences.getBoolean(KEY_PREFERENCE_SENSOR_EVENT_PWR_OFF_ACTION_SEND_SMS_1_3, false)) {
            hashSetAction.add(GDinAction.SendSMSOwner);
        }
        eventActionMap.put(GDinEvent.PWROff, hashSetAction);

        return eventActionMap;
    }

    String updateSettingSensorName() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        return preferences.getString(KEY_PREFERENCE_SENSOR_1, "sensor1");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
