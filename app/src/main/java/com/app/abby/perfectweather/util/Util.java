package com.app.abby.perfectweather.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.app.abby.perfectweather.base.WeatherApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Abby on 8/13/2017.
 */

public class Util {


    //
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }




    //
    public static void addFragmentToActivity(FragmentManager fragmentManager, Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }


    public static boolean isToday(String date){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Calendar today=Calendar.getInstance();
        today.setTime(new Date(System.currentTimeMillis()));
        Calendar day=Calendar.getInstance();
        try {
            day.setTime(format.parse(date));

        }catch (ParseException e){
            e.printStackTrace();
        }

            return (today.get(Calendar.DAY_OF_YEAR)-day.get(Calendar.DAY_OF_YEAR)==0)&&(today.get(Calendar.YEAR)==day.get(Calendar.YEAR));

    }

    public static String getWeekByDateStr(String strDate)
    {
        int year = Integer.parseInt(strDate.substring(0, 4));
        int month = Integer.parseInt(strDate.substring(5, 7));
        int day = Integer.parseInt(strDate.substring(8, 10));

        Calendar c = Calendar.getInstance();

        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, day);

        String week = "";
        int weekIndex = c.get(Calendar.DAY_OF_WEEK);

        switch (weekIndex)
        {
            case 1:
                week = "周日";
                break;
            case 2:
                week = "周一";
                break;
            case 3:
                week = "周二";
                break;
            case 4:
                week = "周三";
                break;
            case 5:
                week = "周四";
                break;
            case 6:
                week = "周五";
                break;
            case 7:
                week = "周六";
                break;
        }
        return week;
    }

    public static void ToastShortTime(String s){
        Toast toast=Toast.makeText(WeatherApplication.getAppContext(),s,Toast.LENGTH_SHORT);
        toast.show();
        Timer timer=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                toast.cancel();

            }
        };
        timer.schedule(timerTask,500);
        timer.cancel();
        timerTask.cancel();
    }

}
