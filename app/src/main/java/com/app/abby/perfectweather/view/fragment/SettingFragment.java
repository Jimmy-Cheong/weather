package com.app.abby.perfectweather.view.fragment;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;

import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.app.abby.perfectweather.R;
import com.app.abby.perfectweather.base.WeatherApplication;
import com.app.abby.perfectweather.sevices.WeatherService;
import com.app.abby.perfectweather.util.FileSizeUtil;
import com.app.abby.perfectweather.util.FileUtil;
import com.app.abby.perfectweather.util.SharedPreferenceUtil;

import java.io.File;

import butterknife.BindView;
import butterknife.Unbinder;


/**
 * Created by Abby on 8/21/2017.
 */

public class SettingFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener,
        Preference.OnPreferenceChangeListener{

    private Preference mClearCache;
    CheckBoxPreference mNoticicatin;


    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);
        mNoticicatin=(CheckBoxPreference)findPreference("noti_bar");
        if (SharedPreferenceUtil.getInstance().getNotificationModel() != Notification.FLAG_ONGOING_EVENT) {
            mNoticicatin.setChecked(false);
        }else {
            mNoticicatin.setChecked(true);
        }

        mNoticicatin.setOnPreferenceChangeListener(this);

        mClearCache=findPreference("clear_cache");
        mClearCache.setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference){

        if(preference==mClearCache){
            if(FileUtil.delete(new File(WeatherApplication.getAppCacheDir()+"/Cache"))){
                Toast.makeText(WeatherApplication.getAppContext(),"缓存已清除",Toast.LENGTH_SHORT).show();
            }
        }

        return true;
    }

    @Override
    public boolean onPreferenceChange(Preference preference,Object newValue){

        if(preference==mNoticicatin){
            SharedPreferenceUtil.getInstance().setNotificationModel((boolean) newValue ? Notification.FLAG_ONGOING_EVENT : Notification.FLAG_AUTO_CANCEL);
            Toast.makeText(WeatherApplication.getAppContext(),"更改成功,重启应用后生效",Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();


    }


}
