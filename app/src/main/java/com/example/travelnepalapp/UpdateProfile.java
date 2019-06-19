package com.example.travelnepalapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.session.MediaSession;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        email=findViewById(R.id.et_fullname_update);
        user=findViewById(R.id.et_fullname_update);
        password=findViewById(R.id.et_fullname_update);
        passwordd=findViewById(R.id.et_fullname_update);

        btnupdate=findViewById(R.id.btn_update);
        btnupdate.setOnClickListener(this);
//        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();

        Load();

    }

    private void Load() {
        UserAPI userAPI= RetrofitHelper.instance().create(UserAPI.class);
        Call<UserModel> userModelCall=userAPI.loadprofile(id);
         userModelCall.enqueue(new Callback<UserModel>() {
             @Override
             public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                 fullname.setText(response.body().getName());
                 email.setText(response.body().getEmail());
                 user.setText(response.body().getUsername());
                 password.setText(response.body().getPassword());
                 passwordd.setText(response.body().getPasswordConf());

                 UserModel userModel=response.body();

//                 new SharedPref(getActivity()).SessionStart(res.getMessage(),res.getToken());
//                 new SharedPref(activity).UserID();
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
