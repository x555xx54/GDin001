package com.example.s36.gdin01.receivers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

import com.example.s36.gdin01.main.ServiceGD;
import com.example.s36.gdin01.variable.GDinCategory;
import com.example.s36.gdin01.variable.GDinEvent;
import com.example.s36.gdin01.variable.GDinOrigin;
import com.example.s36.gdin01.variable.VariableCollection;

import java.util.Date;

/**
 * Created by s36 on 09.06.2016.
 */

public class SmsReceiver extends UniversalReceiver implements VariableCollection {

    final static String PDUS = "pdus";

    @Override
    public void onReceive(Context context, Intent intent) {

        Date eventTime = new Date();

        if (intent.getAction().equalsIgnoreCase(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {

            String smsNumber = new String();
            StringBuilder smsText = new StringBuilder();
            Bundle bundle = intent.getExtras();

            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get(PDUS);
                for (Object pdu : pdus) {
                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);

                    smsNumber = smsMessage.getOriginatingAddress();
                    smsText.append(smsMessage.getMessageBody());
                }
                Intent intentT = new Intent(context, ServiceGD.class);
                intentT.putExtra(CONST_NUMBER_PHONE, smsNumber);
                intentT.putExtra(CONST_SMS_TEXT, smsText.toString());


                initAndStartService(context,intentT, GDinCategory.User, GDinOrigin.SMS, GDinEvent.SMSIncome, eventTime);
                Log.d(LOG_TAG_RECEIVER_SMS, "SmsReceiver - onReceive");
//                intentT.putExtra(CONST_CATEGORY, GDinCategory.User);
//                intentT.putExtra(CONST_ORIGIN, GDinOrigin.SMS);
//                intentT.putExtra(CONST_EVENT, GDinEvent.SMSIncome);
//                context.startService(intentT);
            }
        }
//        abortBroadcast();

    }
}
