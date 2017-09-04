package com.app.abby.perfectweather.util;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Abby on 8/14/2017.
 */

public class RxUtil {

    public static <T> Observable.Transformer<T, T> rxSchedulerHelper() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static <T> Observable.Transformer<T, T> rxSchedulerHelper(Scheduler scheduler) {
        return tObservable -> tObservable.subscribeOn(scheduler)
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

}