package com.app.abby.perfectweather.model.database;

import com.app.abby.perfectweather.BuildConfig;
import com.app.abby.perfectweather.base.Const;
import com.app.abby.perfectweather.base.WeatherApplication;
import com.litesuits.orm.LiteOrm;

/**
 * Created by Abby on 8/17/2017.
 */

public class OrmLite {

    private static LiteOrm litOrm;

    private OrmLite(){
        if(litOrm==null){
            litOrm=LiteOrm.newSingleInstance(WeatherApplication.getAppContext(), Const.DB_NAME);
        }
        litOrm.setDebugged(BuildConfig.DEBUG);
    }


    private static class OrmHolder{
        private static final OrmLite mInstance=new OrmLite();
    }

    private static OrmLite getOrmHolder(){
        return OrmHolder.mInstance;
    }


    public static LiteOrm getInstance(){
        getOrmHolder();
        return litOrm;
    }

}
