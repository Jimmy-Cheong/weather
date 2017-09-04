package com.app.abby.perfectweather.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.abby.perfectweather.R;
import com.app.abby.perfectweather.base.BaseRecyclerViewAdapter;
import com.app.abby.perfectweather.base.BaseViewHolder;
import com.app.abby.perfectweather.model.api.WeatherBean;
import com.app.abby.perfectweather.model.database.ForecastORM;
import com.app.abby.perfectweather.util.Util;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Abby on 8/17/2017.
 */

public class ForecastAdapter extends BaseRecyclerViewAdapter<ForecastAdapter.ViewHolder>{


    private List<ForecastORM> forecastORMList;
    public ForecastAdapter(List<ForecastORM> forecastORMs){
        forecastORMList=forecastORMs;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View itemView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_daily_forecast,parent,false);
        return new ViewHolder(itemView,this);
    }

    @Override
    public void onBindViewHolder(ForecastAdapter.ViewHolder holder,int position){

        holder.temMinTextView.setText(forecastORMList.get(position).getTempminText());
        holder.temMaxTextView.setText(forecastORMList.get(position).getTempmaxText());
        holder.weekTextView.setText(forecastORMList.get(position).getWeekText());
        holder.dateTextView.setText(forecastORMList.get(position).getDateText());
        holder.weatherTextView.setText(forecastORMList.get(position).getWeatherText());
        int code=Integer.parseInt(forecastORMList.get(position).getCode());
        if(code==100){
            holder.iconImageView.setImageResource(R.drawable.ic_icon_sunny);
        }else if(code>100&&code<=213) {
            holder.iconImageView.setImageResource(R.drawable.ic_cloudy);
        }else if (code>213&&code<=301){
            holder.iconImageView.setImageResource(R.drawable.ic_icon_rain);
        }else if (code>301&&code<=304){
            holder.iconImageView.setImageResource(R.drawable.ic_icon_thunder_rain);
        }else if(code>304&&code<=313){
            holder.iconImageView.setImageResource(R.drawable.ic_icon_heavy_rain);
        }else if(code>313){
            holder.iconImageView.setImageResource(R.drawable.ic_icon_snow);
        }
    }

    @Override
    public int getItemCount(){
        return forecastORMList.size();
    }
    static class ViewHolder extends BaseViewHolder{

        @BindView(R.id.weekday)
        TextView weekTextView;

        @BindView(R.id.date)
        TextView dateTextView;

        @BindView(R.id.icon)
        ImageView iconImageView;

        @BindView(R.id.condition)
        TextView weatherTextView;

        @BindView(R.id.max_temp)
        TextView temMaxTextView;

        @BindView(R.id.min_temp)
        TextView temMinTextView;

        ViewHolder(View itemView,ForecastAdapter adapter){

            super(itemView);

        }
    }


}
