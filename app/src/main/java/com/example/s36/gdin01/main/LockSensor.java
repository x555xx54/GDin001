package com.example.s36.gdin01.main;

import android.content.Intent;
import android.os.Bundle;

import com.example.s36.gdin01.variable.GDinEvent;
import com.example.s36.gdin01.variable.SensorState;

import static com.example.s36.gdin01.variable.VariableCollection.CONST_EVENT;

/**
 * Created by s36 on 14.06.2016.
 */

public class LockSensor extends Sensor {

    private SensorState sensorStateLock;
    private String nameSensor;
    private boolean isNormalOpen;// if pwrOn then lock closed, if pwrOff then lock open //замыкающий при закрытии замка

    public LockSensor(boolean isNormalOpen, SensorState sensorStateLock, String nameSensor) {
        this.sensorStateLock = sensorStateLock;
        this.nameSensor = nameSensor;
        this.isNormalOpen = isNormalOpen;

    }

    @Override
    void updateSetting() {

    }

    @Override
    void updateState(Intent intent) {

        Bundle bundle = intent.getExtras();
        GDinEvent GDinEvent = (GDinEvent) bundle.get(CONST_EVENT);

        if (GDinEvent == GDinEvent.PWROn) {
            sensorStateLock = SensorState.Closed;
        }
        if (GDinEvent == GDinEvent.PWROff) {
            sensorStateLock = SensorState.Open;
        }
    }



    @Override
    void indicator() {

    }

    @Override
    SensorState getState() {
        return sensorStateLock;
    }
}
