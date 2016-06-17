package com.example.s36.gdin01.main;

import android.content.Intent;
import android.util.Log;

import com.example.s36.gdin01.variable.SensorState;
import com.example.s36.gdin01.variable.VariableCollection;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Created by s36 on 14.06.2016.
 */

public abstract class Sensor implements VariableCollection, PropertyChangeListener {

    boolean isOpen;
    private SensorState sensorStateLock;



    void updateState(Intent intent) {
    }

    void updateSetting() {
    }


    void sendEvent() {

    }


    @Override
    public void propertyChange(PropertyChangeEvent event) {

        Log.d(LOG_TAG_SENSOR, "propertyChange " + sensorStateLock.toString());
    }



}
