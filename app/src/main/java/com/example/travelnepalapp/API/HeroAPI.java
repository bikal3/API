package com.example.travelnepalapp.API;

import com.example.travelnepalapp.Models.DashboardModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HeroAPI {
    @POST("heros")
    Call<Void> addHero(@Body DashboardModel dashboardModel);
}
