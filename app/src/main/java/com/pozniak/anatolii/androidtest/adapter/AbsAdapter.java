package com.pozniak.anatolii.androidtest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Владелец on 26.07.2017.
 */

public abstract class AbsAdapter<T, VH extends AbsViewHolder<T>> extends RecyclerView.Adapter<VH> {

    protected List<T> models;
    protected OnRecyclerItemClick<T> click;

    protected abstract int getLayoutId();

    protected abstract VH getLayoutHolder(View view);

    public AbsAdapter(List<T> models, OnRecyclerItemClick<T> click) {
        this.models = models;
        this.click = click;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
        return getLayoutHolder(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.onBind(models.get(position), position);
    }

    @Override
    public int getItemCount() {
        return (models != null) ? models.size() : 0;
    }

}
