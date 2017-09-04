package com.app.abby.perfectweather.base;

import android.app.Application;
import android.content.Context;


/**
 * Created by Abby on 8/13/2017.
 */

public class WeatherApplication extends Application {


    private static String mCacheDir;
    private static Context mContext;
    private static WeatherApplication weatherApplicationInstance;

    @Override
    public void onCreate(){
        super.onCreate();
        mContext=getApplicationContext();

        weatherApplicationInstance = this;
        if (getApplicationContext().getExternalCacheDir() != null && ExistSDCard()) {
            mCacheDir = getApplicationContext().getExternalCacheDir().toString();
        } else {
            mCacheDir = getApplicationContext().getCacheDir().toString();
        }
    }

    private boolean ExistSDCard() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }


    public static Context getAppContext() {
        return mContext;
    }

    public static String getAppCacheDir() {
        return mCacheDir;
    }


    public static WeatherApplication getInstance() {

        return weatherApplicationInstance;
    }
}
