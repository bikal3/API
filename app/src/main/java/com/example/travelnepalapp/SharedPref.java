package com.example.travelnepalapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.session.MediaSession;

public class SharedPref {
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    // All Shared Preferences Keys
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";

    // User name (make variable public to access from outside)
    public static final String USER_ID = "USER_ID";

    // Email address (make variable public to access from outside)
    public static final String TOKEN = "TOKEN";
    public  SharedPref(Activity activity){
        sharedPreferences =activity.getSharedPreferences("APP",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }
    public void SessionStart(String userid,String token){
        editor.putBoolean(IS_USER_LOGIN,true);
        editor.putString(USER_ID,userid);
        editor.putString(TOKEN,token);
        editor.commit();
    }
    public boolean Session(){
        return sharedPreferences.getBoolean(IS_USER_LOGIN,false);
    }
    public String UserID(){
        return sharedPreferences.getString(USER_ID,"");
    }
    public String TOKEN(){
        return sharedPreferences.getString(TOKEN(),"");
    }
    public void SessionEnd(){
        editor.putBoolean(IS_USER_LOGIN,false);
        editor.remove(USER_ID);
        editor.remove(TOKEN);
        editor.commit();

    }
}
