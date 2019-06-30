package com.example.travelnepalapp.Feedback;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travelnepalapp.API.FeedbackAPI;
import com.example.travelnepalapp.R;
import com.example.travelnepalapp.Retrofit.RetrofitHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Feedback extends AppCompatActivity implements View.OnClickListener {


    EditText fname, lname, email, message;
    Button btnfeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        fname = findViewById(R.id.et_feedback_fname);
        lname = findViewById(R.id.et_feedback_lname);
        email = findViewById(R.id.et_feedback_email);
        message = findViewById(R.id.et_feedback_feecback);

        btnfeedback = findViewById(R.id.btn_feedback_add);

        btnfeedback.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_feedback_add:
                addfeedback();
                break;
        }
    }

    private void addfeedback() {
        String ffname=fname.getText().toString();
        String llname=lname.getText().toString();
        String emails=email.getText().toString();
        String messages=message.getText().toString();


        FeedbackAPI feedbackAPI= RetrofitHelper.instance().create(FeedbackAPI.class);
        Call<String> feedbackcall=feedbackAPI.addpost(ffname,llname,emails,messages);
        feedbackcall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(Feedback.this, "Successfully Added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(Feedback.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }


}
