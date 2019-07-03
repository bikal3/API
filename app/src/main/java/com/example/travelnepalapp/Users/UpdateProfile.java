package com.example.travelnepalapp.Users;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelnepalapp.API.PostAPI;
import com.example.travelnepalapp.API.UserAPI;
import com.example.travelnepalapp.Models.UserModel;
import com.example.travelnepalapp.R;
import com.example.travelnepalapp.Retrofit.RetrofitHelper;
import com.example.travelnepalapp.Retrofit.Url;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfile extends AppCompatActivity implements View.OnClickListener {

    EditText fullname, email, user;
    TextView updateimagename,hiddenimagename;
    Button btnupdate, btnselectimage, btnuploadimage;
    ImageView updateimage;
    String image;


    Context context;

    Uri imageUri;
    Bitmap bitmap;
    private static final int PICK_IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);


        fullname = findViewById(R.id.et_fullname_update);
        email = findViewById(R.id.et_email_update);
        user = findViewById(R.id.et_username_update);
        btnupdate = findViewById(R.id.btn_update);
        btnselectimage = findViewById(R.id.btn_update_selectimage);

        updateimage = findViewById(R.id.iv_update_img);
        updateimagename = findViewById(R.id.et_update_imagename);
        hiddenimagename=findViewById(R.id.hiddenimagename);


        btnupdate.setOnClickListener(this);
        btnselectimage.setOnClickListener(this);
//        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();

        Load();
//        if(updateimagename==null || updateimagename.equals("")){
//            updateimagename.setText("");
//        }
//

    }

    private void StrictMode() {
        android.os.StrictMode.ThreadPolicy policy = new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);
    }

    private void Load() {
        UserAPI userAPI = RetrofitHelper.instance().create(UserAPI.class);
        SharedPreferences preferences = getSharedPreferences("localstorage", 0);
        String id = preferences.getString("_id", null);
        final String token = preferences.getString("token", null);
        String username = preferences.getString("username", null);

        Call<UserModel> userModelCall = userAPI.loadprofile(id, token, username);

        userModelCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                UserModel userModel = response.body();
//
                fullname.setText(response.body().getName());
                email.setText(userModel.getEmail());
                user.setText(userModel.getUsername());
                hiddenimagename.setText(userModel.getImage());

                StrictMode();
                try {

                    String imgurl = Url.URL_image + userModel.getImage();
                    URL url = new URL(imgurl);
                    updateimage.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
                } catch (IOException e) {
                    e.printStackTrace();
                }



                Log.d("ids", token);
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
        switch (v.getId()) {
            case R.id.btn_update:
                updateprofile();
                break;

            case R.id.btn_update_selectimage:
                Opengallery();

                break;


        }

    }

    private void updateprofile() {
        String name = fullname.getText().toString();
        String emails = email.getText().toString();
        String username = user.getText().toString();
        SharedPreferences preferences = getSharedPreferences("localstorage", 0);
        String token = preferences.getString("token", null);
        String _id = preferences.getString("_id", null);

        UserAPI userAPI = RetrofitHelper.instance().create(UserAPI.class);
        if(updateimagename == null){
             image = hiddenimagename.getText().toString();
        }else{
            image = updateimagename.getText().toString();
        }
    Log.d("tag", hiddenimagename.getText().toString());

        Call<String> updateprofilecall = userAPI.updateprofle(_id, token, username, name, image, emails);
        updateprofilecall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(UpdateProfile.this, "Failed", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(UpdateProfile.this, "Sucessfull", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void Opengallery() {
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery, "Choose Image"), PICK_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            imageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                updateimage.setImageBitmap(bitmap);
                uploadImage(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bytes = stream.toByteArray();
        try {

            File file = new File(this.getCacheDir(), "image.jpeg");
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.flush();
            fos.close();

            RequestBody rb = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("imageName", file.getName(), rb);

            PostAPI heroAPI = RetrofitHelper.instance().create(PostAPI.class);
            Call<String> imageModelCall = heroAPI.uploadImage(body);
            imageModelCall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Toast.makeText(UpdateProfile.this, response.body(), Toast.LENGTH_SHORT).show();
                    updateimagename.setText(response.body());
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(UpdateProfile.this, "Error" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}
