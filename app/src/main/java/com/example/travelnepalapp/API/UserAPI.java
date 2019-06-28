package com.example.travelnepalapp.API;

import com.example.travelnepalapp.Models.UserModel;
import com.example.travelnepalapp.Models.Authtoken;
import com.example.travelnepalapp.Retrofit.RetrofitHelper;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface UserAPI {

    @POST("users/registration")
        Call<String> addUser(@Body UserModel userModel);

    @FormUrlEncoded
    @POST("users/login")
    Call<Authtoken> login(@Field("email") String emails, @Field("password") String pass);


    @Multipart
    @POST("users/upload")
    Call<String> uploadImage(@Part MultipartBody.Part body);

    @PUT("users/updateprofile")
    Call<UserModel> updateprofle(@Body UserModel userModel);


    @GET("users/profile/{id}")
    Call<UserModel> loadprofile(@Path ("id") String id);

}
