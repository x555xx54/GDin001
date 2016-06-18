package com.example.s36.gdin01.main;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by kir on 11.06.2016.
 */

public class SmsSender {

    Context context;

    public SmsSender(Context context) {
        this.context = context;
    }

    void sendSMS(String number, String msg) {
        //Intent intent = new Intent().setComponent(new ComponentName("com.example.kiril.micvol", "com.example.kiril.micvol.MyService"));
        Intent intent = new Intent(context, ServiceGD.class);
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent, 0);

        SmsManager sms = SmsManager.getDefault();

        if (sms != null) {
            sms.sendTextMessage(number, null, msg, pi, null);
        }
    }

    void sendSMS(ArrayList<String > ownersAL, String msg){
        Intent intent = new Intent(context, ServiceGD.class);
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent, 0);

        SmsManager sms = SmsManager.getDefault();
        if (sms != null) {

            for (int i = 0; i < ownersAL.size(); i++) {

                String number = ownersAL.get(i);
                sms.sendTextMessage(number, null, msg, pi, null);

                Log.d("LOG_TAG", "----------sms send " + number);
            }
        }
    }

}
