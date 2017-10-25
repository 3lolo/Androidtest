package com.pozniak.anatolii.androidtest.net;


import com.pozniak.anatolii.androidtest.models.AnimalModel;
import com.pozniak.anatolii.androidtest.models.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Владелец on 24.10.2017.
 */

public interface AnimalApi {
    @GET("api.php")
    Call<ResponseModel<AnimalModel>> getAnimalList(@Query("query") String animalType);
}
