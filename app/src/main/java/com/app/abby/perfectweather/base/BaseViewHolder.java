package com.app.abby.perfectweather.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Abby on 9/2/2017.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView){
        super(itemView);
        ButterKnife.bind(this,itemView);
    }


}
