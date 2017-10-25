package com.pozniak.anatolii.androidtest.application;

import android.app.Application;

import com.pozniak.anatolii.androidtest.net.AnimalApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Владелец on 24.10.2017.
 */

public class TestApplication extends Application {

    public static final String BASE_URL = "http://kot3.com/xim/";
    public static AnimalApi animalApi;

    @Override
    public void onCreate() {
        super.onCreate();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        animalApi = retrofit.create(AnimalApi.class);
    }

    public static AnimalApi getAnimalApi() {
        return animalApi;
    }
}
