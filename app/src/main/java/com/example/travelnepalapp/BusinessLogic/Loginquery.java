package com.example.travelnepalapp.BusinessLogic;

import android.util.Log;

import com.example.travelnepalapp.API.UserAPI;
import com.example.travelnepalapp.Models.Authtoken;
import com.example.travelnepalapp.Retrofit.RetrofitHelper;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class Loginquery {
    String email;
    String password;
    boolean isloggedin = false;
    Authtoken authtoken;
    String success;

    public Loginquery(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Authtoken checkUser() {
        UserAPI userAPI = RetrofitHelper.instance().create(UserAPI.class);
        Call<Authtoken> usercall = userAPI.login(email, password);
        try {
            Response<Authtoken> loginresponse = usercall.execute();
            authtoken = loginresponse.body();
            success=authtoken.getSuccess();

            Log.d("authstoken",authtoken.getSuccess());

        } catch (IOException e) {
            e.printStackTrace();

        }
        return authtoken;
    }
}
