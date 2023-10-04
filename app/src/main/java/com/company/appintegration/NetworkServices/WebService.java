package com.company.appintegration.NetworkServices;


import com.company.appintegration.Models.GameModel;
import com.company.appintegration.Models.BillerResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebService {


    @GET("/v3/44389778-349f-4b86-afe8-6a861cd48f46")
    Call<BillerResponseModel> getBillerList(@Query("mocky-delay") String value);

    @GET("marvel")
    Call<List<GameModel>> getSuperHeros();
}
