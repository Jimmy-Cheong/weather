package com.app.abby.perfectweather.util;

import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;

import com.app.abby.perfectweather.base.WeatherApplication;

/**
 * Created by Abby on 8/14/2017.
 */

public  class SharedPreferenceUtil  {

    private SharedPreferences mPrefs;
    private static final String CITY="city";
    public static final String NOTIFICATION_MODEL = "notification_model";

    private static class SPHolder{
        private static final SharedPreferenceUtil sInstance=new SharedPreferenceUtil();
    }

    private SharedPreferenceUtil(){
        mPrefs= WeatherApplication.getAppContext().getSharedPreferences("setting", Context.MODE_PRIVATE);
    }

    public static SharedPreferenceUtil getInstance(){
        return SPHolder.sInstance;
    }

    public SharedPreferenceUtil putInt(String key,int value){
        mPrefs.edit().putInt(key,value).apply();
        return this;
    }

    public int getInt(String key, int defValue) {
        return mPrefs.getInt(key, defValue);
    }

    public SharedPreferenceUtil putString(String key, String value) {
        mPrefs.edit().putString(key, value).apply();
        return this;
    }

    public String getString(String key, String defValue) {
        return mPrefs.getString(key, defValue);
    }

    public SharedPreferenceUtil putBoolean(String key, boolean value) {
        mPrefs.edit().putBoolean(key, value).apply();
        return this;
    }

    public boolean getBoolean(String key, boolean defValue) {
        return mPrefs.getBoolean(key, defValue);
    }

    public String getCity(){
        return mPrefs.getString(CITY,"beijing");
    }

    public void setCity(String name){
        mPrefs.edit().putString(CITY,name).apply();
    }

    public void setNotificationModel(int t) {
        mPrefs.edit().putInt(NOTIFICATION_MODEL, t).apply();
    }

    public int getNotificationModel() {
        return mPrefs.getInt(NOTIFICATION_MODEL, Notification.FLAG_AUTO_CANCEL);
    }

}
