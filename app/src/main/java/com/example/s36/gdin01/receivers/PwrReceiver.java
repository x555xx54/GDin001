package com.example.s36.gdin01.receivers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;


import com.example.s36.gdin01.variable.Category;
import com.example.s36.gdin01.variable.Event;
import com.example.s36.gdin01.variable.Origin;
import com.example.s36.gdin01.variable.VariableCollection;

import java.util.Date;

/**
 * Created by s36 on 09.06.2016.
 */

public class PwrReceiver extends UniversalReceiver implements VariableCollection {


    @Override
    public void onReceive(Context context, Intent intent) {

        Date eventTime = new Date();

        if (intent.getAction().equalsIgnoreCase(Intent.ACTION_POWER_CONNECTED)) {

            Intent intentT = new Intent().setComponent(new ComponentName(context.getPackageName(), CONST_SERVICE_NAME));

            initAndStartService(context, intentT, Category.Sensor, Origin.PWR, Event.PWROn, eventTime);

//            Log.d(LOG_TAG_RECEIVER_PWR, "PwrReceiver - onReceive - ACTION_POWER_CONNECTED");
        }

        if (intent.getAction().equalsIgnoreCase(Intent.ACTION_POWER_DISCONNECTED)) {

            Intent intentT = new Intent().setComponent(new ComponentName(context.getPackageName(), CONST_SERVICE_NAME));

            initAndStartService(context, intentT, Category.Sensor, Origin.PWR, Event.PWROff, eventTime);

//            Log.d(LOG_TAG_RECEIVER_PWR, "PwrReceiver - onReceive - ACTION_POWER_DISCONNECTED");

        }
    }
}
