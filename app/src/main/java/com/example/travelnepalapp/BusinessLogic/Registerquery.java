package com.example.travelnepalapp.BusinessLogic;

import android.util.Log;

import com.example.travelnepalapp.API.UserAPI;
import com.example.travelnepalapp.Models.UserModel;
import com.example.travelnepalapp.Retrofit.RetrofitHelper;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class Registerquery {
    String success;

    UserModel userModel;
    public Registerquery(UserModel userModel){

        this.userModel=userModel;
    }
    public String register(){
        UserAPI userAPI = RetrofitHelper.instance().create(UserAPI.class);
        Call<String> registercall=userAPI.addUser(userModel);
        try{

            Response<String> registerres=registercall.execute();
            success=registerres.body();
            Log.d("authstoken",success);

        }catch (IOException e) {
            e.printStackTrace();

        }
        return success;
    }



}
