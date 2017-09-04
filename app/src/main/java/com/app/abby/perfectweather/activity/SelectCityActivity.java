package com.app.abby.perfectweather.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.app.abby.perfectweather.R;
import com.app.abby.perfectweather.base.BaseActivity;
import com.app.abby.perfectweather.contract.SelectCityContract;
import com.app.abby.perfectweather.presenter.SelectCItyPresenter;
import com.app.abby.perfectweather.util.Util;
import com.app.abby.perfectweather.view.fragment.SelectCityFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Abby on 8/29/2017.
 */

public class SelectCityActivity extends BaseActivity {

    private SelectCityContract.Presenter presenter;
    private Unbinder unbinder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    SelectCityFragment fragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);
        unbinder=ButterKnife.bind(this);

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("请选择省份或城市");
        setSupportActionBar(toolbar);

        fragment = new SelectCityFragment();
        Util.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fragment_container);

        presenter = new SelectCItyPresenter(fragment);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        unbinder.unbind();
    }

}
