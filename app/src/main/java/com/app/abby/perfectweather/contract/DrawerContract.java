package com.app.abby.perfectweather.contract;

import com.app.abby.perfectweather.base.BasePresenter;
import com.app.abby.perfectweather.base.BaseView;
import com.app.abby.perfectweather.model.database.DrawerItemORM;

import java.util.List;

/**
 * Created by Abby on 8/31/2017.
 */

public interface DrawerContract {

     interface View extends BaseView<Presenter>{
        void showItem(List<DrawerItemORM> datas);
     }

     interface Presenter extends BasePresenter{
        void loadItem();
    }
}
