package com.example.travelnepalapp.API;

import com.example.travelnepalapp.Models.UserModel;
import com.example.travelnepalapp.Models.Authtoken;
import com.example.travelnepalapp.Retrofit.RetrofitHelper;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UserAPI {

    @POST("registration")
        Call<String> addUser(@Body UserModel userModel);

    @FormUrlEncoded
    @POST("login")
    Call<Authtoken> login(@Field("email") String emails, @Field("password") String pass);


    @Multipart
    @POST("upload")
    Call<String> uploadImage(@Part MultipartBody.Part body);

    @POST("updateprofile")
    Call<UserModel> updateprofle(@Body UserModel userModel);




}
