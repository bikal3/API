package com.example.travelnepalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity implements View.OnClickListener {
Button btnlogin,btnsignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnlogin=findViewById(R.id.btn_login);
        btnsignup=findViewById(R.id.btn_signup);

        btnlogin.setOnClickListener(this);
        btnsignup.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:

                break;
            case R.id.btn_signup:
                Intent intent=new Intent(Login.this,Signup.class);
                startActivity(intent);
                break;

        }
    }
}
