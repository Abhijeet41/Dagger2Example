package com.abhi41.dagger2examle.Interfaces;

import com.abhi41.dagger2examle.Model.HeroModel;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://simplifiedcoding.net/demos/";

    @GET("marvel")
    Call<List<HeroModel>> getHeroes();


}
