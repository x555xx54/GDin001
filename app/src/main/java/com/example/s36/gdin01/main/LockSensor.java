package com.example.s36.gdin01.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import com.example.s36.gdin01.variable.GDinEvent;
import com.example.s36.gdin01.variable.SensorState;


/**
 * Created by s36 on 14.06.2016.
 */

public class LockSensor extends Sensor {

    private SensorState sensorStateLock;
    private ObservableSensorState sensorStateLockListenered  ;

    private String nameSensor;
    private boolean isNormalOpen;// if pwrOn then lock closed, if pwrOff then lock open //замыкающий при закрытии замка

    public LockSensor(boolean isNormalOpen, SensorState sensorStateLock, String nameSensor) {
        this.sensorStateLock = sensorStateLock;
        this.nameSensor = nameSensor;
        this.isNormalOpen = isNormalOpen;
        Log.d(LOG_TAG_SENSOR, this.getClass().getName() + " LockSensor ");

        sensorStateLockListenered = new ObservableSensorState();
        sensorStateLockListenered.setOnSensorStatChangeListener(new OnSensorStateChangeListener() {
            @Override
            public void onSensorStateChange(SensorState newValue) {
                Log.d(LOG_TAG_SENSOR, " onSensorStateChange " + newValue.toString());
            }
        });
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
            this.value = value;
            if (listener != null) {
                listener.onSensorStateChange(value);
            }
        }
    }

    

    @Override
    void updateSetting() {

    }

    @Override
    void updateState(Intent intent) {

        Bundle bundle = intent.getExtras();
        GDinEvent GDinEvent = (GDinEvent) bundle.get(CONST_EVENT);

        if (isNormalOpen) {

            if (GDinEvent == GDinEvent.PWROn) {
                sensorStateLock = SensorState.Closed;
                sensorStateLockListenered.set(SensorState.Closed);
            }
            if (GDinEvent == GDinEvent.PWROff) {
                sensorStateLock = SensorState.Open;
                sensorStateLockListenered.set(SensorState.Open);
            }

        } else {

            if (GDinEvent == GDinEvent.PWROn) {
                sensorStateLock = SensorState.Open;
                sensorStateLockListenered.set(SensorState.Open);
            }
            if (GDinEvent == GDinEvent.PWROff) {
                sensorStateLock = SensorState.Closed;
                sensorStateLockListenered.set(SensorState.Closed);
            }
        }
        Log.d(LOG_TAG_SENSOR, this.getClass().getName() + " updateState " + sensorStateLock.toString());
    }

    @Override
    void indicator() {

    }

    @Override
    SensorState getState() {
        return sensorStateLock;
    }

}
