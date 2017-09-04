package com.app.abby.perfectweather.presenter;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.app.abby.perfectweather.base.WeatherApplication;
import com.app.abby.perfectweather.contract.HomePageContract;
import com.app.abby.perfectweather.model.api.ApiClient;
import com.app.abby.perfectweather.model.api.WeatherBean;
import com.app.abby.perfectweather.util.Util;
import java.lang.ref.WeakReference;
import rx.Subscriber;
import rx.Subscription;


/**
 * Created by Abby on 8/14/2017.
 */
public class HomePagePresenter implements HomePageContract.Presenter{

    private WeakReference<HomePageContract.View> mWeakView;
    private Subscription subscription;
     public HomePagePresenter(HomePageContract.View view){
        mWeakView=new WeakReference<>(view);
        view.setPresenter(this);
    }

    @Override
    public void loadWeather(String city,boolean needToast){

        if(Util.isNetworkConnected(WeatherApplication.getAppContext())){


             subscription = ApiClient.getInstance().fetchWeather(city).doOnRequest(aLong -> {
                 if(needToast)
                 mWeakView.get().toastLoading();
             }).subscribe(new Subscriber<WeatherBean>() {
                @Override
                public void onCompleted() {
                    if(needToast)
                    mWeakView.get().toastComplete();
                }

                @Override
                public void onError(Throwable e) {
                    if(needToast)
                    mWeakView.get().toastError();
                }

                @Override
                public void onNext(WeatherBean weather) {
                    mWeakView.get().showWeather(weather);

                }
            });


        }
    }


    @Override
    public void onsubscribe(String city){
        loadWeather(city,false);
    }


    @Override
    public void onunsubscribe() {

        if(subscription!=null&&!subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }


    }

    @Override
    public WeakReference<android.view.View> getView(){
        return mWeakView.get().provideView();
    }





}


