package com.example.s36.gdin01.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.s36.gdin01.variable.GDinEvent;
import com.example.s36.gdin01.variable.SensorState;
import com.example.s36.gdin01.variable.VariableCollection;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import static com.example.s36.gdin01.variable.GDinEvent.PWROff;
import static com.example.s36.gdin01.variable.GDinEvent.PWROn;

/**
 * Created by kir on 11.06.2016.
 */

public class GDin implements VariableCollection {

    private SensorState gdinSensorState;
    private Context context;
    private ArrayList<String> ownerList;

    boolean isEnableSms;
    boolean isAlarm;
    boolean isGuard;


    Long tpmLongStart = 0l;
    Long tpmLongStop = 0l;
    Long tpmLongStart2 = 0l;

    ArrayList<Long> arrayList = new ArrayList();
    HashSet<SensorState> sensorStateHashSet = new HashSet<>();

    Handler handler;

    public GDin(Context context) {
        this.context = context;
        ownerList = new ArrayList<>();
        isEnableSms = false;
    }

    public void setGdinSensorState(SensorState gdinSensorState) {
        this.gdinSensorState = gdinSensorState;
    }

    public SensorState getGdinSensorState() {
        return gdinSensorState;
    }

    void updateState(Intent intent) {


        Bundle bundle = intent.getExtras();
        GDinEvent GDinEvent = (GDinEvent) bundle.get(CONST_EVENT);
        if (GDinEvent == PWROn) {

            handler.removeMessages(0);
//            Log.d(LOG_TAG_SERVICE, "handler.removeMessages(0);");

            tpmLongStart = tpmLongStart2;
            tpmLongStart2 = new Date().getTime();
        }
        if (GDinEvent == PWROff) {


            handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
//                    Log.d(LOG_TAG_SERVICE, "handler Alarm" + msg.what);
                }
            };
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    handler.sendEmptyMessageDelayed(0, 1500);
//                    Log.d(LOG_TAG_SERVICE, "handler.sendEmptyMessageDelayed(0,1500);");
                }
            });
            t.start();

            tpmLongStop = new Date().getTime();
        }
        long tmpDim = tpmLongStart2 - tpmLongStart;
        arrayList.add(tmpDim);
        if ((tmpDim > 1) && (tmpDim) < 1000) {

//            Log.d(LOG_TAG_SERVICE, "GDinService - onStartCommand - (tpmLongStart2 - tpmLongStart) " + (tmpDim));
        }
        if ((tmpDim > 1000) && (tmpDim < 1500)) {

//            Log.d(LOG_TAG_SERVICE, "GDinService - onStartCommand - (tpmLongStart2 - tpmLongStart) " + (tmpDim));
        }
        if ((tmpDim > 1500) && (tmpDim < 2000)) {

//            Log.d(LOG_TAG_SERVICE, "GDinService - onStartCommand - (tpmLongStart2 - tpmLongStart) " + (tmpDim));
        }

        switch (GDinEvent) {
            case SMSIncome:
                //Log.d(LOG_TAG_SERVICE, "GDinService - onStartCommand -SMSIncome " + count);

                for (int i = 0; i < arrayList.size(); i++) {
                    Log.d("myloo", String.valueOf(arrayList.get(i)) + "\n");
                }

                break;
            case PWROn:
                //count++;
                // Log.d(LOG_TAG_SERVICE, "GDinService - onStartCommand -PWROff " + count);
                break;
            case PWROff:
                break;
        }
    }

    void setSecurityMode() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        isEnableSms = preferences.getBoolean("chbPrefSmsEnable", false);

        for (int i = 0; i < 10; i++) {
            String owner = preferences.getString("phone" + i, "");
            if (!owner.equalsIgnoreCase("")) {
                ownerList.add(owner);
            }
        }

        String mode = preferences.getString("lpSecurityMode", "");

        switch (mode) {
            case "simpleOn":
                break;
            case "simpleOff":
                break;
        }
    }

    public void actionProcess(Intent intent) {
        Bundle bundle = intent.getExtras();
        GDinEvent GDinEvent = (GDinEvent) bundle.get(CONST_EVENT);


    }
}
