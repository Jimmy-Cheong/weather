package com.app.abby.perfectweather.view.fragment;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.abby.perfectweather.R;
import com.app.abby.perfectweather.activity.MainActivity;
import com.app.abby.perfectweather.base.BaseFragment;
import com.app.abby.perfectweather.base.WeatherApplication;
import com.app.abby.perfectweather.contract.HomePageContract;
import com.app.abby.perfectweather.model.api.WeatherBean;
import com.app.abby.perfectweather.model.database.DetailORM;
import com.app.abby.perfectweather.model.database.ForecastORM;
import com.app.abby.perfectweather.model.database.LifeIndexOrm;
import com.app.abby.perfectweather.model.database.OrmLite;
import com.app.abby.perfectweather.util.SharedPreferenceUtil;
import com.app.abby.perfectweather.util.Util;
import com.app.abby.perfectweather.view.adapter.DetailAdapter;
import com.app.abby.perfectweather.view.adapter.ForecastAdapter;
import com.app.abby.perfectweather.view.adapter.LifeIndexAdapter;
import com.app.abby.tsnackbar.TSnackbar;



import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Abby on 8/13/2017.
 */

public class HomePageFragment extends BaseFragment implements HomePageContract.View {

    private Unbinder unbinder;
    @BindView(R.id.detail_recyclerview)
    RecyclerView detailRecy;

    @BindView(R.id.forecast_recyclerview)
    RecyclerView forecastRecy;

    @BindView(R.id.lifeindex_recyclerview)
    RecyclerView lifeRecy;


    private DetailAdapter detailAdapter;
    private ForecastAdapter forecastAdapter;
    private LifeIndexAdapter lifeIndexAdapter;


    private List<DetailORM> detailORMs;
    private List<ForecastORM> forecastORMs;
    private List<LifeIndexOrm> lifeIndexOrms;


    private HomePageContract.Presenter presenter;

    private OnFragmentInteractionListener onFragmentInteractionListener;

    private TSnackbar tSnackbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_homepage, container, false);
        unbinder=ButterKnife.bind(this,rootView);


        detailRecy.setNestedScrollingEnabled(false);
        detailRecy.setLayoutManager(new GridLayoutManager(WeatherApplication.getAppContext(), 3));
        detailORMs=new ArrayList<>();
        detailAdapter=new DetailAdapter(detailORMs);
        detailRecy.setAdapter(detailAdapter);



        forecastRecy.setNestedScrollingEnabled(false);
        forecastRecy.setLayoutManager(new LinearLayoutManager(WeatherApplication.getAppContext()));
        forecastORMs=new ArrayList<>();
        forecastAdapter=new ForecastAdapter(forecastORMs);
        forecastRecy.setAdapter(forecastAdapter);


        lifeRecy.setNestedScrollingEnabled(false);
        lifeRecy.setLayoutManager(new LinearLayoutManager(WeatherApplication.getAppContext()));
        lifeIndexOrms=new ArrayList<>();
        lifeIndexAdapter=new LifeIndexAdapter(lifeIndexOrms);
        lifeRecy.setAdapter(lifeIndexAdapter);

        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            onFragmentInteractionListener = (OnFragmentInteractionListener) context;
        } else
            throw new RuntimeException(context.toString() + "must implement onFragmentInteractionListener");
    }


    public static HomePageFragment newInstance(){
        return new HomePageFragment();
    }


    @Override
    public void setPresenter(HomePageContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void showWeather(WeatherBean weather) {


        detailORMs.clear();
        detailORMs.addAll(createDetail(weather));
        detailAdapter.notifyDataSetChanged();


        forecastORMs.clear();
        forecastORMs.addAll(createForecasts(weather));
        forecastAdapter.notifyDataSetChanged();

        lifeIndexOrms.clear();
        lifeIndexOrms.addAll(createLifeIndex(weather));
        lifeIndexAdapter.notifyDataSetChanged();


        showNotificationbar(weather);
        onFragmentInteractionListener.updateHeader(weather);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if(presenter!=null)
        presenter.onunsubscribe();
        //释放资源
        tSnackbar=null;

    }


    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    public interface OnFragmentInteractionListener {
        void updateHeader(WeatherBean weather);
    }


    @Override
    protected void lazyload(){

    }

    @Override
    public WeakReference<View> provideView(){

        return new WeakReference<>(getView());
    }


    private List<DetailORM> createDetail(WeatherBean weather){
        List<DetailORM> details=new ArrayList<>();
        details.add(new DetailORM(R.drawable.ic_temp,"体感温度",weather.getHeWeather5().get(0).getNow().getFl()+"℃"));
        details.add(new DetailORM(R.drawable.ic_shidu,"相对湿度",weather.getHeWeather5().get(0).getNow().getHum()+"%"));
        details.add(new DetailORM(R.drawable.ic_pres,"气压",weather.getHeWeather5().get(0).getNow().getPres()+"Pa"));
        details.add(new DetailORM(R.drawable.ic_rain,"降水量",weather.getHeWeather5().get(0).getNow().getPcpn()+"mm"));
        details.add(new DetailORM(R.drawable.ic_wind,"风速",weather.getHeWeather5().get(0).getNow().getWind().getSpd()+"km/h"));
        details.add(new DetailORM(R.drawable.ic_vis,"能见度",weather.getHeWeather5().get(0).getNow().getVis()+"km"));
        return details;
    }

    private List<ForecastORM> createForecasts(WeatherBean weather){
        List<ForecastORM> forecasts=new ArrayList<>();
        for(int i=0;i<weather.getHeWeather5().get(0).getDaily_forecast().size();i++){

                if(!Util.isToday(weather.getHeWeather5().get(0).getDaily_forecast().get(i).getDate()))

                forecasts.add(new ForecastORM(Util.getWeekByDateStr(weather.getHeWeather5().get(0).getDaily_forecast().get(i).getDate()),weather.getHeWeather5().get(0).getDaily_forecast().get(i).getDate(),weather.getHeWeather5().get(0).getDaily_forecast().get(i).getCond().getTxt_d(),weather.getHeWeather5().get(0).getDaily_forecast().get(i).getTmp().getMax(),weather.getHeWeather5().get(0).getDaily_forecast().get(i).getTmp().getMin(),weather.getHeWeather5().get(0).getDaily_forecast().get(i).getCond().getCode_d()));

                else {

                    forecasts.add(new ForecastORM("今天",weather.getHeWeather5().get(0).getDaily_forecast().get(i).getDate(),weather.getHeWeather5().get(0).getDaily_forecast().get(i).getCond().getTxt_d(),weather.getHeWeather5().get(0).getDaily_forecast().get(i).getTmp().getMax(),weather.getHeWeather5().get(0).getDaily_forecast().get(i).getTmp().getMin(),weather.getHeWeather5().get(0).getDaily_forecast().get(i).getCond().getCode_d()));

                }
        }
        return forecasts;
    }

    private List<LifeIndexOrm>createLifeIndex(WeatherBean weather){
        List<LifeIndexOrm> lifeIndex=new ArrayList<>();
        lifeIndex.add(new LifeIndexOrm(weather.getHeWeather5().get(0).getSuggestion().getCw().getBrf(),weather.getHeWeather5().get(0).getSuggestion().getCw().getTxt(),R.drawable.ic_car));
        lifeIndex.add(new LifeIndexOrm(weather.getHeWeather5().get(0).getSuggestion().getSport().getBrf(),weather.getHeWeather5().get(0).getSuggestion().getSport().getTxt(),R.drawable.ic_sport));
        lifeIndex.add(new LifeIndexOrm(weather.getHeWeather5().get(0).getSuggestion().getDrsg().getBrf(),weather.getHeWeather5().get(0).getSuggestion().getDrsg().getTxt(),R.drawable.ic_cloth));
        lifeIndex.add(new LifeIndexOrm(weather.getHeWeather5().get(0).getSuggestion().getUv().getBrf(),weather.getHeWeather5().get(0).getSuggestion().getUv().getTxt(),R.drawable.ic_uv));

        return lifeIndex;
    }


    @Override
    public void toastLoading(){
        TSnackbar.make(getView(),"(＝^ω^＝)正在加载天气，请稍候...",TSnackbar.LENGTH_INDEFINITE)
                .setFadeOrTranslateStyle(TSnackbar.STYLE_FADE_OUT)
                .setActionTextColor(Color.WHITE)
                .setPreDefinedStyle(TSnackbar.STYLE_LOADING)
               .show();
    }

    @Override
    public void toastError(){
        TSnackbar.make(getView(),"（╯＾╰）无法加载当前城市的天气，请重试...",TSnackbar.LENGTH_LONG)
                .setFadeOrTranslateStyle(TSnackbar.STYLE_FADE_OUT)
                .setPreDefinedStyle(TSnackbar.STYLE_ERROR)
                .show();
    }

    @Override
    public void toastComplete(){

             tSnackbar=TSnackbar.make(getView(),"加载完毕~(@^_^@)~",TSnackbar.LENGTH_LONG)
                    .setPreDefinedStyle(TSnackbar.STYLE_COMPLETE)
                    .setFadeOrTranslateStyle(TSnackbar.STYLE_FADE_OUT)
                    .setAction("取消", v -> {
                        tSnackbar.dismiss();
                    });
                tSnackbar.show();
    }



    public void showNotificationbar(WeatherBean weather){

        Intent intent=new Intent(getActivity(),MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent=
                PendingIntent.getActivity(getActivity(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder = new Notification.Builder(getActivity());
        Notification notification=builder.setContentIntent(pendingIntent)
                .setContentTitle(weather.getHeWeather5().get(0).getBasic().getCity())
                .setContentText(String.format("%s 当前温度: %s℃ ",weather.getHeWeather5().get(0).getNow().getCond().getTxt(),weather.getHeWeather5().get(0).getNow().getTmp()))
                .setSmallIcon(R.drawable.ic_sunny_black_24dp)
                .build();
        NotificationManager manager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notification.flags= Notification.FLAG_ONGOING_EVENT;
        if(SharedPreferenceUtil.getInstance().getNotificationModel()!=Notification.FLAG_ONGOING_EVENT){

            manager.cancel(1);
        }else {
            manager.notify(1, notification);

        }

    }


}












