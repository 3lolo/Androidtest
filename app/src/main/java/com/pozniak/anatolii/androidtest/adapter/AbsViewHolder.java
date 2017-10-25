package com.pozniak.anatolii.androidtest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 3lolo on 26.04.2017.
 */
public abstract class AbsViewHolder<T> extends RecyclerView.ViewHolder {
    protected T model;
    protected OnRecyclerItemClick<T> click;

    public AbsViewHolder(View itemView, OnRecyclerItemClick<T> click) {
        super(itemView);
        this.click = click;
    }

    public abstract void onBind(T model, int position);

}
