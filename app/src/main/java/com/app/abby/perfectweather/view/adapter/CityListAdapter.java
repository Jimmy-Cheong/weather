package com.app.abby.perfectweather.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.app.abby.perfectweather.R;
import java.util.List;
import com.app.abby.perfectweather.base.BaseViewHolder;
import butterknife.BindView;

/**
 * Created by Abby on 8/29/2017.
 */

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.ViewHolder> {

    private List<String> mcities;
    private OnCityItemClickListener mOnCityClickListener;

    public CityListAdapter(List<String>list){
        mcities=list;

    }


    @Override
    public int getItemCount(){
        return mcities.size();
    }



    @Override
    public void onBindViewHolder(ViewHolder holder,int pos){
        holder.mCity.setText(mcities.get(pos));
        holder.mItem.setOnClickListener(v -> {
          mOnCityClickListener.onItemClick(pos);
        });
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View itemView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_city_name,parent,false);

        return new ViewHolder(itemView);
    }


    static class ViewHolder extends BaseViewHolder{

        @BindView(R.id.item_city)
        LinearLayout mItem;

        @BindView(R.id.city)
        TextView mCity;
        public ViewHolder(View itemView){
            super(itemView);

        }
    }



    public interface OnCityItemClickListener{
        void onItemClick(int position);
    }

    public void setOnClickListener(OnCityItemClickListener listener){
        mOnCityClickListener=listener;
    }




}
