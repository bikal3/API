package com.example.travelnepalapp.API;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface PostAPI {


    @Multipart
    @POST("uploadimage")
    Call<String> uploadImage(@Part MultipartBody.Part body);
}
