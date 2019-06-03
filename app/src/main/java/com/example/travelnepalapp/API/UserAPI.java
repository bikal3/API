package com.example.travelnepalapp.API;

import com.example.travelnepalapp.Models.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserAPI {

    @POST("registration")
    Call<Void> addUser(@Body UserModel userModel);

    @FormUrlEncoded
    @POST("login")
    Call<String> login(@Field("email") String email, @Field("password") String pass);



}
