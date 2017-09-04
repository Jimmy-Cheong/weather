package com.app.abby.perfectweather.contract;

import com.app.abby.perfectweather.base.BasePresenter;
import com.app.abby.perfectweather.base.BaseView;
import com.app.abby.perfectweather.model.api.WeatherBean;

import java.lang.ref.WeakReference;

/**
 * Created by Abby on 8/13/2017.
 */

public interface HomePageContract {

    interface View extends BaseView<Presenter>{
        void showWeather(WeatherBean weather);
        void toastLoading();
        void toastError();
        void toastComplete();
        WeakReference<android.view.View> provideView();
    }

    interface Presenter extends BasePresenter{
        void loadWeather(String cityId,boolean needToast);
        WeakReference<android.view.View> getView();
    }
}

