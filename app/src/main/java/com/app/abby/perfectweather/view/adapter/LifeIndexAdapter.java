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
import com.app.abby.perfectweather.model.database.LifeIndexOrm;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Abby on 9/2/2017.
 */

public class LifeIndexAdapter extends BaseRecyclerViewAdapter<LifeIndexAdapter.ViewHolder>{

    private List<LifeIndexOrm> mData;

    public LifeIndexAdapter(List<LifeIndexOrm> data){
        mData=data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lifeindex,parent,false);
        return new ViewHolder(view);

    }


        @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        holder.icon.setImageResource(mData.get(position).getIconRes());
        holder.cond_brif.setText(mData.get(position).getCon_brif());
        holder.cond_txt.setText(mData.get(position).getCon_txt());
    }

    @Override
    public int getItemCount(){
        return mData.size();
    }

    static class ViewHolder extends BaseViewHolder{

        @BindView(R.id.cond_brif)
        TextView cond_brif;

        @BindView(R.id.cond_txt)
        TextView cond_txt;

        @BindView(R.id.icon)
        ImageView icon;


        ViewHolder(View itemView){
            super(itemView);
        }
    }
}
