package com.app.abby.perfectweather.view.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.abby.perfectweather.R;
import com.app.abby.perfectweather.base.BaseRecyclerViewAdapter;
import com.app.abby.perfectweather.base.BaseViewHolder;
import com.app.abby.perfectweather.model.database.DrawerItemORM;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Abby on 8/29/2017.
 */

public class DrawerItemAdapter extends RecyclerView.Adapter<DrawerItemAdapter.ViewHolder> {

    private List<DrawerItemORM> mDraweritem;
    private OnHolderItemClickListener onHolderItemClickListener;
    private OnDeleteClickListener onDeleteClickListener;
    public DrawerItemAdapter(List<DrawerItemORM> drawerItemORM){
        mDraweritem=drawerItemORM;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder,int position){

            holder.city.setText(mDraweritem.get(position).getCity());
            holder.temp.setText(mDraweritem.get(position).getTemp());
            holder.item_view.setOnClickListener(v -> onHolderItemClickListener.onHolderItemClick(holder.getAdapterPosition()));
            holder.delete_view.setOnClickListener(v -> onDeleteClickListener.onDeleteBtnClick(position));
            int code=Integer.parseInt(mDraweritem.get(position).getCode());
            if(code==100){
                holder.iconView.setImageResource(R.drawable.sunny);
                holder.item_view.setBackgroundResource(R.drawable.city_bg_sunny);
            }else if(code>100&&code<=213){
                holder.iconView.setImageResource(R.drawable.cloudy);
                holder.item_view.setBackgroundResource(R.drawable.city_bg_cloudy);
            }else if(code>213&&code<=301){
                holder.iconView.setImageResource(R.drawable.light_rain);
                holder.item_view.setBackgroundResource(R.drawable.city_bg_rainy);
            }else if (code>301&&code<=304){
                holder.iconView.setImageResource(R.drawable.thunder_rain);
                holder.item_view.setBackgroundResource(R.drawable.city_bg_rainy);
            }else if(code>304&&code<=306){
                holder.iconView.setImageResource(R.drawable.light_rain);
                holder.item_view.setBackgroundResource(R.drawable.city_bg_rainy);
            }else if (code>306&&code<=312){
                holder.iconView.setImageResource(R.drawable.heavy_rain);
                holder.item_view.setBackgroundResource(R.drawable.city_bg_rainy);
            }else if(code>=313&&code<=407){
                holder.iconView.setImageResource(R.drawable.snow);
                holder.item_view.setBackgroundResource(R.drawable.city_bg_rainy);
            }else if(code>407){
                holder.iconView.setImageResource(R.drawable.fog);
                holder.item_view.setBackgroundResource(R.drawable.city_bg_cloudy);
            }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_drawer,parent,false);

        return new ViewHolder(view);

    }


    @Override
    public int getItemCount(){
        return mDraweritem.size();
    }


    static class ViewHolder extends BaseViewHolder{

        @BindView(R.id.icon_delete)
        ImageView delete_view;

        @BindView(R.id.dialog_icon)
        ImageView iconView;

        @BindView(R.id.dialog_city)
        TextView city;

        @BindView(R.id.dialog_temp)
        TextView temp;

        @BindView(R.id.card_view)
        CardView item_view;

        public ViewHolder (View itemView){
            super(itemView);

        }
    }

    public interface OnHolderItemClickListener{
        void onHolderItemClick(int position);
    }

    public void setOnHolderItemClickListener(OnHolderItemClickListener listener){
        onHolderItemClickListener=listener;
    }

    public interface OnDeleteClickListener{
        void onDeleteBtnClick(int position);
    }

    public void setOnDeleteBtnClickListener(OnDeleteClickListener listener){

        onDeleteClickListener=listener;
    }
}
