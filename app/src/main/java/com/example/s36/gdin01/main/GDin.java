package com.example.s36.gdin01.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.s36.gdin01.R;
import com.example.s36.gdin01.variable.Event;
import com.example.s36.gdin01.variable.State;
import com.example.s36.gdin01.variable.VariableCollection;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import static com.example.s36.gdin01.variable.Event.PWROff;
import static com.example.s36.gdin01.variable.Event.PWROn;


/**
 * Created by kir on 11.06.2016.
 */

public class GDin extends BroadcastReceiver implements VariableCollection {

    private State gdinState;
    private Context context;
    private ArrayList<String> ownerList;
    private boolean enableSms;

    Long tpmLongStart = 0l;
    Long tpmLongStop = 0l;
    Long tpmLongStart2 = 0l;

    ArrayList<Long> arrayList = new ArrayList();
    HashSet<State> stateHashSet = new HashSet<>();

    public GDin(Context context) {
        this.context = context;
        ownerList = new ArrayList<>();
        enableSms = false;
    }

    public void setGdinState(State gdinState) {
        this.gdinState = gdinState;
    }

    public State getGdinState() {
        return gdinState;
    }

    void updateState(Intent intent) {
        Bundle bundle = intent.getExtras();
        Event event = (Event) bundle.get(CONST_EVENT);
        if (event == PWROn) {

            stateHashSet.add(State.Start);
            tpmLongStart = tpmLongStart2;
            tpmLongStart2 = new Date().getTime();
        }
        if (event == PWROff) {

            stateHashSet.add(State.Stop);
            tpmLongStop = new Date().getTime();
        }
        long tmpDim = tpmLongStart2 - tpmLongStart;
        arrayList.add(tmpDim);
        if ((tmpDim > 1) && (tmpDim) < 1000) {

            stateHashSet.add(State.DoorOpen);
            Log.d(LOG_TAG_SERVICE, "GDinService - onStartCommand - (tpmLongStart2 - tpmLongStart) " + (tmpDim));
        }
        if ((tmpDim > 1000) && (tmpDim < 1500)) {

            stateHashSet.add(State.LockClose);
            Log.d(LOG_TAG_SERVICE, "GDinService - onStartCommand - (tpmLongStart2 - tpmLongStart) " + (tmpDim));
        }
        if ((tmpDim > 1500) && (tmpDim < 2000)) {

            stateHashSet.add(State.LockOpen);
            Log.d(LOG_TAG_SERVICE, "GDinService - onStartCommand - (tpmLongStart2 - tpmLongStart) " + (tmpDim));
        }

        switch (event) {
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

        enableSms = preferences.getBoolean("chbPrefSmsEnable", false);

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
        Event event = (Event) bundle.get(CONST_EVENT);
        


    }
    @Override
    public void onReceive(Context context, Intent intent) {

    }


}
