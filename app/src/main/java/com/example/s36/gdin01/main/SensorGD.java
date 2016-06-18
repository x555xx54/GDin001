package com.example.s36.gdin01.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import com.example.s36.gdin01.variable.GDinAction;
import com.example.s36.gdin01.variable.GDinEvent;
import com.example.s36.gdin01.variable.SensorState;
import com.example.s36.gdin01.variable.VariableCollection;

import java.util.HashMap;

import java.util.HashSet;

public class SensorGD implements VariableCollection {

    private ObservableSensorState sensorStateLock;
    private String nameSensor;
    private HashMap<GDinEvent, HashSet<GDinAction>> eventActionMap = new HashMap<>();
    Context context;

    public SensorGD(Context context,
                    String nameSensor,
                    HashMap<GDinEvent, HashSet<GDinAction>> eventActionMap) {
        this.nameSensor = nameSensor;
        this.context = context;
        Log.d(LOG_TAG_SENSOR, this.getClass().getName() + " SensorGD ");

        sensorStateLock = new ObservableSensorState();
        sensorStateLock.setOnSensorStatChangeListener(new OnSensorStateChangeListener() {
            @Override
            public void onSensorStateChange(SensorState newValue) {
                Log.d(LOG_TAG_SENSOR, " onSensorStateChange " + newValue.toString());
            }
        });
    }


    void updateState(Intent intent) {

        Bundle bundle = intent.getExtras();
        GDinEvent gdinEvent = (GDinEvent) bundle.get(CONST_EVENT);

        switch (gdinEvent) {
            case PWROn: Log.d(LOG_TAG_SENSOR, "swich");
                break;
        }

//        if (isNormalOpen) {
//
//            if (GDinEvent == GDinEvent.PWROn) {
//                sensorStateLock.set(SensorState.Closed);
//            }
//            if (GDinEvent == GDinEvent.PWROff) {
//                sensorStateLock.set(SensorState.Open);
//            }
//
//        } else {
//
//            if (GDinEvent == GDinEvent.PWROn) {
//                sensorStateLock.set(SensorState.Open);
//            }
//            if (GDinEvent == GDinEvent.PWROff) {
//                sensorStateLock.set(SensorState.Closed);
//            }
//        }
        Log.d(LOG_TAG_SENSOR, this.getClass().getName() + " updateState " + sensorStateLock.get().toString());
    }

    void updateSeting( String nameSensor,
                       HashMap<GDinEvent, HashSet<GDinAction>> eventActionMap){
        this.nameSensor = nameSensor;
        this.eventActionMap = eventActionMap;

    }
    public interface OnSensorStateChangeListener {
        void onSensorStateChange(SensorState newValue);
    }

    public class ObservableSensorState {
        private OnSensorStateChangeListener listener;
        private SensorState value;

        public void setOnSensorStatChangeListener(OnSensorStateChangeListener listener) {
            this.listener = listener;
        }

        public SensorState get() {
            return value;
        }

        public void set(SensorState value) {
            if (this.value != value) {
                this.value = value;
                if (listener != null) {
                    listener.onSensorStateChange(value);
                }
                Log.d(LOG_TAG_SENSOR, "State chang");

                Intent intentT = new Intent(context, ServiceGD.class);

                //intentT.putExtra(CONST_EVENT, GDinEvent.);

                //context.sendBroadcast();
            }

        }
    }


}
