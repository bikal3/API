package com.example.travelnepalapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.session.MediaSession;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travelnepalapp.API.UserAPI;
import com.example.travelnepalapp.Models.Authtoken;
import com.example.travelnepalapp.Models.UserModel;
import com.example.travelnepalapp.Retrofit.RetrofitHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfile extends AppCompatActivity implements View.OnClickListener {

    EditText fullname,email,user,password,passwordd;
    Button btnupdate;
    String id;
    Context context;
    Activity activity;
    String token;
    UserModel userModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);



        fullname=findViewById(R.id.et_fullname_update);
        email=findViewById(R.id.et_email_update);
        user=findViewById(R.id.et_username_update);
        password=findViewById(R.id.et_password_update);
        passwordd=findViewById(R.id.et_password2_update);

        btnupdate=findViewById(R.id.btn_update);

        btnupdate.setOnClickListener(this);
//        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();

        Load();

    }

    private void Load() {
        UserAPI userAPI= RetrofitHelper.instance().create(UserAPI.class);
        SharedPreferences preferences=getSharedPreferences("localstorage",0);
         String id=preferences.getString("_id",null);
         final String token=preferences.getString("token",null);
         String username=preferences.getString("username",null);

        Call<UserModel> userModelCall=userAPI.loadprofile(id,token,username);

         userModelCall.enqueue(new Callback<UserModel>() {
             @Override
             public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                 UserModel userModel=response.body();
//
                 fullname.setText(response.body().getName());
                 email.setText(userModel.getEmail());
                 user.setText(userModel.getUsername());
                 password.setText(userModel.getPassword());
                 passwordd.setText(userModel.getPasswordConf());


                 Log.d("myTag", userModel.getName());
                 Log.d("ids",token );
//
                 Toast.makeText(UpdateProfile.this, userModel.getId(), Toast.LENGTH_SHORT).show();


             }

             @Override
             public void onFailure(Call<UserModel> call, Throwable t) {
                 Toast.makeText(UpdateProfile.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

             }
         });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_update:
                updateprofile();
                break;
        }

    }
    private void updateprofile() {
        String name = fullname.getText().toString();
        String emails = email.getText().toString();
        String username = user.getText().toString();
        String pass = password.getText().toString();
        String passs = passwordd.getText().toString();

        UserModel userModel = new UserModel(name, emails, username, pass, passs);
        UserAPI userAPI= RetrofitHelper.instance().create(UserAPI.class);
        Call<UserModel> updateprofilecall=userAPI.updateprofle(userModel);
        updateprofilecall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(UpdateProfile.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(UpdateProfile.this, response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
