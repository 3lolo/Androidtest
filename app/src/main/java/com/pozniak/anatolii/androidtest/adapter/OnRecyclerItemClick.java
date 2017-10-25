package com.pozniak.anatolii.androidtest.adapter;

/**
 * Created by Владелец on 24.10.2017.
 */

public interface OnRecyclerItemClick<T> {
    void click(T model, int position);
}
