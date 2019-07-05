package com.example.travelnepalapp.Comment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.travelnepalapp.API.CommentAPI;
import com.example.travelnepalapp.Adapters.CommentAdapter;
import com.example.travelnepalapp.Models.CommentModel;
import com.example.travelnepalapp.Models.UserModel;
import com.example.travelnepalapp.R;
import com.example.travelnepalapp.Retrofit.RetrofitHelper;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddComment extends AppCompatActivity {
    private RecyclerView recyclerAdapter;
    private List<CommentModel> commentModelList = new ArrayList<>();
    String userid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);
        recyclerAdapter = findViewById(R.id.rv_comment_recylerview);

        init();
    }

    private void init() {
        final CommentAPI commentAPI = RetrofitHelper.instance().create(CommentAPI.class);
        final String id = getIntent().getStringExtra("post_id");


        Call<List<CommentModel>> listCall = commentAPI.getallcomment(id);
        listCall.enqueue(new Callback<List<CommentModel>>() {
            @Override
            public void onResponse(Call<List<CommentModel>> call, final Response<List<CommentModel>> responses) {
                commentModelList = responses.body();
                recyclerAdapter.setAdapter(new CommentAdapter(AddComment.this, commentModelList));
                recyclerAdapter.setLayoutManager(new LinearLayoutManager(AddComment.this));
//                JSONObject reader = new JSONObject();
//                JsonObject jsonObject= reader.getJSONObject("comment");

////                jsonObject=responses.body();
//                String[] user =new String[commentModelList.size()];
//                for(int i=0; i<commentModelList.size(); i++){
//                    user[i]=commentModelList.get(i).getUser();
//                    Log.d("userid",user[i]);
//                    Call<UserModel> userModelCalls=commentAPI.getuserdetial(user[i]);
//                    userModelCalls.enqueue(new Callback<UserModel>() {
//                        @Override
//                        public void onResponse(Call<UserModel> call, Response<UserModel> response) {
//                            UserModel userModel =response.body();
//                            recyclerAdapter.setAdapter(new CommentAdapter(AddComment.this, commentModelList,userModel));
//                            recyclerAdapter.setLayoutManager(new LinearLayoutManager(AddComment.this));
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<UserModel> call, Throwable t) {
//                            Toast.makeText(AddComment.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//
//                        }
//                    });
//                }



            }

            @Override
            public void onFailure(Call<List<CommentModel>> call, Throwable t) {

                Toast.makeText(AddComment.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void userdetial(){
        CommentAPI commentAPI=RetrofitHelper.instance().create(CommentAPI.class);
    }
}
