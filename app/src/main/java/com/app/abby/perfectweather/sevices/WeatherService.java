package com.app.abby.perfectweather.sevices;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.app.abby.perfectweather.R;
import com.app.abby.perfectweather.activity.MainActivity;
import com.app.abby.perfectweather.model.api.ApiClient;
import com.app.abby.perfectweather.model.api.WeatherBean;
import com.app.abby.perfectweather.util.RxUtil;
import com.app.abby.perfectweather.util.SharedPreferenceUtil;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Abby on 9/2/2017.
 */

public class WeatherService extends Service {

    private   Notification notification;
    private  NotificationManager manager;
    private Subscription mTasksubscription;
    private Subscription mTimesubscription;

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

            mTimesubscription=Observable.interval(1,TimeUnit.HOURS)
                    .compose(RxUtil.rxSchedulerHelper())
                    .subscribe(new Subscriber<Long>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(Long aLong) {
                            getWeather();
                        }
                    });

        return START_REDELIVER_INTENT;

    }

    public void showNotificationbar(WeatherBean weatherBean){

        Intent intent=new Intent(WeatherService.this, MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(WeatherService.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder=new Notification.Builder(WeatherService.this);
        notification=builder.setContentIntent(pendingIntent)
                .setContentTitle(weatherBean.getHeWeather5().get(0).getBasic().getCity())
                .setContentText(String.format("%s 当前温度: %s℃ ",weatherBean.getHeWeather5().get(0).getNow().getCond().getTxt(),weatherBean.getHeWeather5().get(0).getNow().getTmp()))
                .setSmallIcon(R.drawable.ic_sunny_black_24dp)
                .build();

        notification.flags=Notification.FLAG_ONGOING_EVENT;
        manager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        if(SharedPreferenceUtil.getInstance().getNotificationModel()!=Notification.FLAG_ONGOING_EVENT){
            manager.cancel(1);
        }else {
            manager.notify(1,notification);
        }

    }


    private void getWeather(){

                mTasksubscription=ApiClient.getInstance().fetchWeather(SharedPreferenceUtil.getInstance().getCity())
                .subscribe(new Subscriber<WeatherBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(WeatherBean weatherBean) {

                        showNotificationbar(weatherBean);
                    }
                });

    }

    @Override
    public void onDestroy() {


        if(mTasksubscription!=null&&!mTasksubscription.isUnsubscribed()){
            mTasksubscription.unsubscribe();
        }

        if(mTimesubscription!=null&&!mTimesubscription.isUnsubscribed()){
            mTimesubscription.unsubscribe();
        }
    }



}
