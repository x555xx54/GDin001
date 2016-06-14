package com.example.s36.gdin01.main;

import android.content.Intent;

import com.example.s36.gdin01.variable.SensorState;

/**
 * Created by s36 on 14.06.2016.
 */

public abstract class Sensor {

    boolean isOpen;

    void updateState(Intent intent) {
    }
    void updateSetting(){
    }

    SensorState getState() {
        return SensorState.Open;
    }

    void indicator(){

    }
}
