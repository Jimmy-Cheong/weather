package com.app.abby.perfectweather.contract;

import com.app.abby.perfectweather.base.BasePresenter;
import com.app.abby.perfectweather.base.BaseView;
import com.app.abby.perfectweather.model.data.City;
import com.app.abby.perfectweather.model.data.CityBean;
import com.app.abby.perfectweather.model.data.Province;

import java.util.List;

/**
 * Created by Abby on 8/29/2017.
 */

public interface SelectCityContract {

    interface View extends BaseView<Presenter>{
        void updateCities(List<City> cities, List<CityBean> cityBeen);
        void updateProvinces(List<Province> provinces,List<CityBean> cityBeen);
    }
    interface Presenter extends BasePresenter{
        void loadCities(int portNum);
        void loadProvinces();
    }
}
