package com.example.travelnepalapp.Post;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelnepalapp.API.PostAPI;
import com.example.travelnepalapp.Models.PostModel;
import com.example.travelnepalapp.R;
import com.example.travelnepalapp.Retrofit.RetrofitHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDashboard extends AppCompatActivity {

    TextView posttitle,postlocation,postdesc;
    ImageView postimageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_dashboard);


        posttitle=findViewById(R.id.tv_pc_title);
        postdesc=findViewById(R.id.tv_pc_desc);
        postlocation=findViewById(R.id.tv_pc_location);

        postimageview=findViewById(R.id.iv_pc_post_image);
//        init();
    }

//    private void init() {
//        String id=getIntent().getStringExtra("post_id");
//        PostAPI postAPI= RetrofitHelper.instance().create(PostAPI.class);
//        Call<PostModel> postModelCall=postAPI.getpostid(id);
//        postModelCall.enqueue(new Callback<PostModel>() {
//            @Override
//            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
////                posttitle.setText(response.body().getTitle());
//
//
//            }
//
//            @Override
//            public void onFailure(Call<PostModel> call, Throwable t) {
//                Toast.makeText(PostDashboard.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }
}
