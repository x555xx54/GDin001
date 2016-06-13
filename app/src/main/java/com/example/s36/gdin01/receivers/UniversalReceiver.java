package com.example.s36.gdin01.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.s36.gdin01.variable.Category;
import com.example.s36.gdin01.variable.Event;
import com.example.s36.gdin01.variable.Origin;
import com.example.s36.gdin01.variable.VariableCollection;

import java.util.Date;


/**
 * Created by s36 on 08.06.2016.
 */

public abstract class UniversalReceiver extends BroadcastReceiver implements VariableCollection {


    protected void initAndStartService(Context context, Intent intent, Category category, Origin origin, Event event, Date eventTime) {

        intent.putExtra(CONST_CATEGORY, category);
        intent.putExtra(CONST_ORIGIN, origin);
        intent.putExtra(CONST_EVENT, event);
        intent.putExtra(CONST_EVENT_TIME, eventTime);

        context.startService(intent);
    }
}
