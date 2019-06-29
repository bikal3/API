package com.example.travelnepalapp.API;

import com.example.travelnepalapp.Models.PostModel;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface PostAPI {


    @Multipart
    @POST("uploadimage")
    Call<String> uploadImage(@Part MultipartBody.Part body);

    @FormUrlEncoded
    @POST("users/post")
    Call<String>addpost(@Field("title") String title,
                        @Field("location")String location,
                        @Field("image") String image,
                        @Field("description") String description,
                        @Field("user") String user,
                        @Field("token") String token,
                        @Field("_id") String id
                        );
    @FormUrlEncoded
    @POST("users/postlist")
    Call<List<PostModel>>getpost(@Field("token")String token,
                                 @Field("username")String username,
                                 @Field("_id")String _id
    );

}
