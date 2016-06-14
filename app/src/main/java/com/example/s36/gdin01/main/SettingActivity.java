package com.example.s36.gdin01.main;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceGroup;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.widget.EditText;

import com.example.s36.gdin01.R;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by kir on 12.06.2016.
 */

public class SettingActivity extends PreferenceActivity {

    SharedPreferences preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preference = PreferenceManager.getDefaultSharedPreferences(this);
        PreferenceScreen rootScreen = getPreferenceManager().createPreferenceScreen(this);
        setPreferenceScreen(rootScreen);

        CheckBoxPreference chbPrefSmsEnable = new CheckBoxPreference(this);
        chbPrefSmsEnable.setKey("chbPrefSmsEnable");
        chbPrefSmsEnable.setTitle("СМС оповещение");
        chbPrefSmsEnable.setSummaryOn("Охранная система будет оповещать о смене состояния по СМС");
        chbPrefSmsEnable.setSummaryOff("Охранная система не будет оповещать о смене состояния по СМС");
        chbPrefSmsEnable.setIcon(R.drawable.message_1_32);
        rootScreen.addPreference(chbPrefSmsEnable);


        ListPreference lpSecurityMode = new ListPreference(this);

        lpSecurityMode.setKey("lpSecurityMode");
        lpSecurityMode.setTitle("Режим контроля");
        lpSecurityMode.setSummary(preference.getString("lpSecurityMode", ""));
        lpSecurityMode.setIcon(R.drawable.usb_1_32);
        lpSecurityMode.setEntries(R.array.list_security_mode_entries);
        lpSecurityMode.setEntryValues(R.array.list_security_mode_entryValue);
        rootScreen.addPreference(lpSecurityMode);

        SwitchPreference switchPreference = new SwitchPreference(this);

        rootScreen.addPreference(switchPreference);


        PreferenceScreen psOwner = getPreferenceManager().createPreferenceScreen(this);
        psOwner.setTitle("title");
        psOwner.setIcon(R.drawable.user_1_32);


        //SharedPreferences phonePref = getPreferences(MODE_PRIVATE);
        LinkedList<EditTextPreference> etpPhoneLinkedList = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            EditTextPreference etpPhone = new EditTextPreference(this);
            etpPhone.setKey("phone" + i);
            etpPhone.setIcon(R.drawable.telephone_1_32);
            etpPhone.setDialogTitle("Номер телефона без 8");
            etpPhone.setDialogIcon(R.drawable.telephone_1_32);
            etpPhone.setTitle(preference.getString("phone" + i, ""));
            etpPhone.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    preference.setTitle((CharSequence) newValue);

                    return true;
                }
            });
            psOwner.addPreference(etpPhone);
        }

        rootScreen.addPreference(psOwner);

        //// lockSensorScreen
        PreferenceScreen lockSensorScreen = getPreferenceManager().createPreferenceScreen(this);
        lockSensorScreen.setTitle("Датчик замка");

        EditTextPreference etpLock = new EditTextPreference(this);
        etpLock.setKey("etpLock");
        etpLock.setTitle("Название замка");
        lockSensorScreen.addPreference(etpLock);

        SwitchPreference swpTypeSensor = new SwitchPreference(this);
        swpTypeSensor.setKey("swpTypeSensor");
        swpTypeSensor.setTitle("Нормально открытый");
        lockSensorScreen.addPreference(swpTypeSensor);

        rootScreen.addPreference(lockSensorScreen);
    }
}
