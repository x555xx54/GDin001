package com.example.s36.gdin01.variable;

/**
 * Created by s36 on 09.06.2016.
 */

public interface VariableCollection {
    String CONST_NUMBER_PHONE = "number_phone";
    String CONST_SMS_TEXT = "sms_text";
    String CONST_EVENT = "event";
    String CONST_EVENT_TIME = "event_time";
    String CONST_CATEGORY = "category";
    String CONST_ORIGIN = "origin";
    String CONST_SERVICE_NAME= "com.example.s36.gdin01.main.ServiceGD";


    String LOG_TAG_SERVICE = "mylogserv";
    String LOG_TAG_RECEIVER_PWR = "mylogpwr";
    String LOG_TAG_RECEIVER_SMS = "mylogsms";
    String LOG_TAG_SENSOR = "mylogsensor";

    String KEY_PREFERENCE_ENABLE_SMS = "chbPrefSmsEnable";
    String KEY_PREFERENCE_SENSOR_1 = "etpLock";
    String KEY_PREFERENCE_SENSOR_LIST_ACTION_PWRON_1 = "lpSensorActionPwrOn";
    String KEY_PREFERENCE_SENSOR_EVENT_PWR_ON_ACTION_ALARM_ON_1_1 = "chp_event_action_pwron_1_1";
    String KEY_PREFERENCE_SENSOR_EVENT_PWR_ON_ACTION_ALARM_OFF_1_2 = "chp_event_action_pwron_1_2";
    String KEY_PREFERENCE_SENSOR_EVENT_PWR_ON_ACTION_SEND_SMS_1_3 = "chp_event_action_pwron_1_3";

    String KEY_PREFERENCE_SENSOR_EVENT_PWR_OFF_ACTION_ALARM_ON_1_1 = "chp_event_action_pwroff_1_1";
    String KEY_PREFERENCE_SENSOR_EVENT_PWR_OFF_ACTION_ALARM_OFF_1_2 = "chp_event_action_pwroff_1_2";
    String KEY_PREFERENCE_SENSOR_EVENT_PWR_OFF_ACTION_SEND_SMS_1_3 = "chp_event_action_pwroff_1_3";

}
