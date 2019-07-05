package com.example.travelnepalapp.API;

import com.example.travelnepalapp.Models.CommentModel;
import com.example.travelnepalapp.Models.PostModel;
import com.example.travelnepalapp.Models.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CommentAPI {

    @GET("ccommentbyid/{id}")
    Call<List<CommentModel>> getallcomment(@Path("id") String id);

    @POST("userbyid/{id}")
    Call<UserModel> getuserdetial(@Path("id") String id);
}