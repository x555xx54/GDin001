package com.example.s36.gdin01.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.s36.gdin01.variable.GDinCategory;
import com.example.s36.gdin01.variable.GDinEvent;
import com.example.s36.gdin01.variable.GDinOrigin;
import com.example.s36.gdin01.variable.VariableCollection;

import java.util.Date;


/**
 * Created by s36 on 08.06.2016.
 */

public abstract class UniversalReceiver extends BroadcastReceiver implements VariableCollection {


    protected void initAndStartService(Context context, Intent intent, GDinCategory GDinCategory, GDinOrigin GDinOrigin, GDinEvent GDinEvent, Date eventTime) {

        intent.putExtra(CONST_CATEGORY, GDinCategory);
        intent.putExtra(CONST_ORIGIN, GDinOrigin);
        intent.putExtra(CONST_EVENT, GDinEvent);
        intent.putExtra(CONST_EVENT_TIME, eventTime);

        context.startService(intent);
    }
}
