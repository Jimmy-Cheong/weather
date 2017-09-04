package com.app.abby.perfectweather.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.app.abby.perfectweather.R;
import com.app.abby.perfectweather.base.BaseActivity;
import com.app.abby.perfectweather.util.Util;
import com.app.abby.perfectweather.view.fragment.SettingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Abby on 9/1/2017.
 */

public class SettingActivity extends BaseActivity {

    private Unbinder unbinder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        unbinder = ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }



        SettingFragment fragment = new SettingFragment();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container_setting, fragment).commit();


    }

    @Override
    public void onBackPressed() {
        finish();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
