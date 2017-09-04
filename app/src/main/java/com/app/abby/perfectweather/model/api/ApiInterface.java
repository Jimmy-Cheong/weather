package com.app.abby.perfectweather.model.api;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Abby on 8/13/2017.
 */

public interface ApiInterface {

    String HOST="https://free-api.heweather.com/v5/";

    @GET("weather")
    Observable<WeatherBean> mWeatherAPI(@Query("city") String city, @Query("key") String key);

}
