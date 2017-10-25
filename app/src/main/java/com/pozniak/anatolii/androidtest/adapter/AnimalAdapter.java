package com.pozniak.anatolii.androidtest.adapter;

import android.view.View;

import com.pozniak.anatolii.androidtest.R;
import com.pozniak.anatolii.androidtest.models.AnimalModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Владелец on 24.10.2017.
 */

public class AnimalAdapter extends AbsAdapter<AnimalModel, AbsViewHolder<AnimalModel>> {

    public AnimalAdapter(List<AnimalModel> models, OnRecyclerItemClick<AnimalModel> click) {
        super(models, click);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_recycler;
    }

    @Override
    protected AbsViewHolder<AnimalModel> getLayoutHolder(View view) {
        return new AnimalViewHolder(view, click);
    }

    public void setData(List<AnimalModel> modelList) {
        if (models == null) {
            models = new ArrayList<>();
        } else {
            models.clear();
        }
        models.addAll(modelList);
        notifyDataSetChanged();
    }

    public List<AnimalModel> getItems() {
        return models;
    }
}
