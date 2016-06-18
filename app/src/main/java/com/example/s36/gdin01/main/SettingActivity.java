package com.example.s36.gdin01.main;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;

import com.example.s36.gdin01.R;
import com.example.s36.gdin01.variable.VariableCollection;

import java.util.LinkedList;

/**
 * Created by kir on 12.06.2016.
 */

public class SettingActivity extends PreferenceActivity implements VariableCollection {

    SharedPreferences preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preference = PreferenceManager.getDefaultSharedPreferences(this);
        PreferenceScreen rootScreen = getPreferenceManager().createPreferenceScreen(this);
        setPreferenceScreen(rootScreen);

        CheckBoxPreference chbPrefSmsEnable = new CheckBoxPreference(this);
        chbPrefSmsEnable.setKey(KEY_PREFERENCE_ENABLE_SMS);
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

        PreferenceScreen sensorListScreen = getPreferenceManager().createPreferenceScreen(this);
        sensorListScreen.setTitle("Датчики");
        sensorListScreen.setIcon(R.drawable.usb_1_32);


        //// sensorScreen1
        PreferenceScreen sensorScreen1 = getPreferenceManager().createPreferenceScreen(this);
        sensorScreen1.setTitle(preference.getString(KEY_PREFERENCE_SENSOR_1, "Укажите наименование датчика"));

        final EditTextPreference etpLock = new EditTextPreference(this);
        etpLock.setKey(KEY_PREFERENCE_SENSOR_1);
        etpLock.setTitle("Изменить название датчика");
        etpLock.setSummary("Текущее значение :" + preference.getString(KEY_PREFERENCE_SENSOR_1, ""));
        etpLock.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                etpLock.setSummary((CharSequence) newValue);
                return true;
            }
        });


        PreferenceScreen sensorEventAction1Screen = getPreferenceManager().createPreferenceScreen(this);
        sensorEventAction1Screen.setTitle("PowerOn");

        CheckBoxPreference cbpEvent11 = new CheckBoxPreference(this);
        cbpEvent11.setKey(KEY_PREFERENCE_SENSOR_EVENT_PWR_ON_ACTION_ALARM_ON_1_1);
        cbpEvent11.setTitle("Включить тревогу");
        sensorEventAction1Screen.addPreference(cbpEvent11);

        CheckBoxPreference cbpEvent12 = new CheckBoxPreference(this);
        cbpEvent12.setKey(KEY_PREFERENCE_SENSOR_EVENT_PWR_ON_ACTION_ALARM_OFF_1_2);
        cbpEvent12.setTitle("Выключить тревогу");
        sensorEventAction1Screen.addPreference(cbpEvent12);

        CheckBoxPreference cbpEvent13 = new CheckBoxPreference(this);
        cbpEvent13.setKey(KEY_PREFERENCE_SENSOR_EVENT_PWR_ON_ACTION_SEND_SMS_1_3);
        cbpEvent13.setTitle("Оповестить по смс");
        sensorEventAction1Screen.addPreference(cbpEvent13);


        PreferenceScreen sensorEventAction2Screen = getPreferenceManager().createPreferenceScreen(this);
        sensorEventAction2Screen.setTitle("PowerOff");

        CheckBoxPreference cbpEvent21 = new CheckBoxPreference(this);
        cbpEvent21.setKey(KEY_PREFERENCE_SENSOR_EVENT_PWR_OFF_ACTION_ALARM_ON_1_1);
        cbpEvent21.setTitle("Включить тревогу");
        sensorEventAction2Screen.addPreference(cbpEvent21);

        CheckBoxPreference cbpEvent22 = new CheckBoxPreference(this);
        cbpEvent22.setKey(KEY_PREFERENCE_SENSOR_EVENT_PWR_OFF_ACTION_ALARM_OFF_1_2);
        cbpEvent22.setTitle("Выключить тревогу");
        sensorEventAction2Screen.addPreference(cbpEvent22);

        CheckBoxPreference cbpEvent23 = new CheckBoxPreference(this);
        cbpEvent23.setKey(KEY_PREFERENCE_SENSOR_EVENT_PWR_OFF_ACTION_SEND_SMS_1_3);
        cbpEvent23.setTitle("Оповестить по смс");
        sensorEventAction2Screen.addPreference(cbpEvent23);



        sensorScreen1.addPreference(etpLock);
        sensorScreen1.addPreference(sensorEventAction1Screen);
        sensorScreen1.addPreference(sensorEventAction2Screen);

        ListPreference lpSensorActionPwrOn = new ListPreference(this);
        lpSensorActionPwrOn.setKey(KEY_PREFERENCE_SENSOR_LIST_ACTION_PWRON_1);
        lpSensorActionPwrOn.setTitle("При появлении питания");


        sensorListScreen.addPreference(sensorScreen1);
        rootScreen.addPreference(sensorListScreen);
    }

    @Override
    protected void onPause() {
        super.onPause();

        Intent intent = new Intent(this, MyService.class);
        intent.putExtra(CONST_STATUS_CODE, CODE_SETTING_UPDATE);
        startService(intent);
    }
}
