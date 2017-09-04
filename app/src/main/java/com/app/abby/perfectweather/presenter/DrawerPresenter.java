package com.app.abby.perfectweather.presenter;

import com.app.abby.perfectweather.contract.DrawerContract;
import com.app.abby.perfectweather.model.api.ApiClient;
import com.app.abby.perfectweather.model.api.WeatherBean;
import com.app.abby.perfectweather.model.database.DrawerItemORM;
import com.app.abby.perfectweather.model.database.OrmLite;
import com.app.abby.perfectweather.util.RxUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Abby on 8/29/2017.
 */

public class DrawerPresenter implements DrawerContract.Presenter{

    private DrawerContract.View mView;
    private List<DrawerItemORM> mData;
    public DrawerPresenter(DrawerContract.View view){
        mData=new ArrayList<>();
        mView=view;
        mView.setPresenter(this);
    }


    @Override
    public void onsubscribe(String city){

    }

    @Override
    public void onunsubscribe(){

    }

    @Override
    public void loadItem(){

        if(mData!=null){
            mData.clear();
        }

        mData=OrmLite.getInstance().query(DrawerItemORM.class);
        mView.showItem(mData);

    }


}
