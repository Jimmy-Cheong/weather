package com.app.abby.perfectweather.model.api;

import com.app.abby.perfectweather.base.Const;
import com.app.abby.perfectweather.base.WeatherApplication;
import com.app.abby.perfectweather.util.RxUtil;
import com.app.abby.perfectweather.util.Util;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Abby on 7/23/2017.
 */

public class ApiClient {

    private static ApiInterface sApiService=null;
    private static retrofit2.Retrofit sRetrofit=null;
    private static OkHttpClient sOkHttpClient=null;

    private void init() {
        initOkHttp();
        initRetrofit();
        sApiService = sRetrofit.create(ApiInterface.class);
    }

    private ApiClient(){init();}


    public static ApiClient getInstance()
    {
        return RetrofitHolder.INSTANE;
    }


    //retrofit singleton
    private static class RetrofitHolder
    {
        private static final ApiClient INSTANE=new ApiClient();
    }


    private static void initOkHttp()
    {
        OkHttpClient.Builder builder=new OkHttpClient.Builder();

        File cacheFile=new File(WeatherApplication.getAppCacheDir(),"/Cache");
        Cache cache=new Cache(cacheFile,1024*1024*50);
        //cache interceptor
        Interceptor cacheInterceptor= chain -> {

            Request request=chain.request();

            if(!Util.isNetworkConnected(WeatherApplication.getAppContext())) {
                request=request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }

            Response response=chain.proceed(request);
            Response.Builder newBuilder=response.newBuilder();

            if(Util.isNetworkConnected(WeatherApplication.getAppContext())) {
                int maxAge=0;
                newBuilder.header("Cache-Control", "public, max-age=" + maxAge);
            }

            else {
                int maxStale = 60 * 60 * 24 * 28;
                newBuilder.header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale);
            }

            return newBuilder.build();
        };
        builder.cache(cache).addInterceptor(cacheInterceptor);

        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);

        builder.retryOnConnectionFailure(true);
        sOkHttpClient = builder.build();

    }

    private static void initRetrofit() {
        sRetrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(ApiInterface.HOST)
                .client(sOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public ApiInterface getApiService() {
        return sApiService;
    }


    public  Observable<WeatherBean> fetchWeather(String city)
    {
        return
                sApiService.mWeatherAPI(city, Const.API_KEY)
                        .flatMap(weatherAPI -> {String status=weatherAPI.getHeWeather5().get(0).getStatus();
                            if ("no more requests".equals(status)) {
                                return Observable.error(new RuntimeException("No api"));
                            } else if ("unknown city".equals(status)) {
                                return Observable.error(new RuntimeException(String.format("%s is not in the api list", city)));
                            }
                            return Observable.just(weatherAPI);
                        }).map(weatherAPI -> {return weatherAPI;})
                        .compose(RxUtil.rxSchedulerHelper());
    }




}
