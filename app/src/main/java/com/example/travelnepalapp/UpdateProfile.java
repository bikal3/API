package com.example.travelnepalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.travelnepalapp.API.UserAPI;
import com.example.travelnepalapp.Retrofit.RetrofitHelper;

public class UpdateProfile extends AppCompatActivity implements View.OnClickListener {

    EditText fullname,email,user,password,passwordd;
    Button btnupdate;


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
        UserAPI userAPI= RetrofitHelper.instance().create(UserAPI.class);






    }
}
