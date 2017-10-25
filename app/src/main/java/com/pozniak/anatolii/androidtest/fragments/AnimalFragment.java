package com.pozniak.anatolii.androidtest.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pozniak.anatolii.androidtest.ProfileActivity;
import com.pozniak.anatolii.androidtest.R;
import com.pozniak.anatolii.androidtest.adapter.AnimalAdapter;
import com.pozniak.anatolii.androidtest.adapter.OnRecyclerItemClick;
import com.pozniak.anatolii.androidtest.application.TestApplication;
import com.pozniak.anatolii.androidtest.models.AnimalModel;
import com.pozniak.anatolii.androidtest.models.ResponseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Владелец on 24.10.2017.
 */

public class AnimalFragment extends Fragment implements OnRecyclerItemClick<AnimalModel> {
    public static final String BUNDLE_ANIMAL_TYPE = "animal type";
    private static final String LIST_STATE_KEY = "recycler state";
    public static final String ADAPTER_DATA_KEY = "adapter data";

    private RecyclerView recycler;
    private LinearLayoutManager mLayoutManager;
    private AnimalAdapter animalAdapter;
    private Parcelable mListState;

    public static AnimalFragment newInstance(String type) {

        Bundle args = new Bundle();
        args.putString(BUNDLE_ANIMAL_TYPE, type);

        AnimalFragment fragment = new AnimalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        recycler = view.findViewById(R.id.recycler);
        mLayoutManager = new LinearLayoutManager(getContext());
        if (savedInstanceState != null) {
            List<AnimalModel> animalList = savedInstanceState.getParcelableArrayList(ADAPTER_DATA_KEY);
            animalAdapter = new AnimalAdapter(animalList, this);
            mLayoutManager.onRestoreInstanceState(mListState);
        }
        if (animalAdapter == null) {
            animalAdapter = new AnimalAdapter(null, this);
            String type = getArguments().getString(BUNDLE_ANIMAL_TYPE);
            if (!TextUtils.isEmpty(type)) {
                TestApplication.getAnimalApi().getAnimalList(type).enqueue(new Callback<ResponseModel<AnimalModel>>() {
                    @Override
                    public void onResponse(Call<ResponseModel<AnimalModel>> call, Response<ResponseModel<AnimalModel>> response) {
                        if (response.code() == 200) {
                            if (animalAdapter != null) {
                                animalAdapter.setData(response.body().getData());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModel<AnimalModel>> call, Throwable t) {

                    }
                });
            }

        }
        recycler.setAdapter(animalAdapter);
        recycler.setLayoutManager(mLayoutManager);

        return view;
    }

    @Override
    public void click(AnimalModel model, int position) {
        startActivity(ProfileActivity.getLaunchIntent(getContext(), model));
    }


    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null &&
                savedInstanceState.containsKey(LIST_STATE_KEY)) {
            mListState = savedInstanceState.getParcelable(LIST_STATE_KEY);
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (animalAdapter != null) {
            mListState = mLayoutManager.onSaveInstanceState();
            outState.putParcelable(LIST_STATE_KEY, mListState);
            outState.putParcelableArrayList(ADAPTER_DATA_KEY, (ArrayList<? extends Parcelable>) animalAdapter.getItems());
        }
    }
}
