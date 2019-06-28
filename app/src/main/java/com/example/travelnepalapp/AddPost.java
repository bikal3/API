package com.example.travelnepalapp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.travelnepalapp.API.PostAPI;
import com.example.travelnepalapp.Retrofit.RetrofitHelper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPost extends AppCompatActivity implements View.OnClickListener {
    Activity context;
    EditText addtitle, addlocation, adddesc, imagename;
    Button btnaddpost, btnselectimage, btnimageupload;
    ImageView postimage;

    Uri imageUri;
    Bitmap bitmap;
    private static final int PICK_IMAGE = 1;
    Integer REQUEST_CAMERA = 1, SELECT_FILE = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        //EDIT TEXT
        addtitle = findViewById(R.id.et_post_title);
        addlocation = findViewById(R.id.et_post_location);
        adddesc = findViewById(R.id.et_post_desc);
        imagename = findViewById(R.id.et_post_imagename);

        //buttons
        btnaddpost = findViewById(R.id.btn_post_addpost);
        btnimageupload = findViewById(R.id.btn_post_imageupload);
        btnselectimage = findViewById(R.id.btn_post_selectimage);

        //imageview
        postimage = findViewById(R.id.iv_post_img_);
        btnselectimage.setOnClickListener(this);
        btnimageupload.setOnClickListener(this);
        btnaddpost.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_post_selectimage:
                Opengallery();
                break;

            case R.id.btn_post_addpost:

                break;

            case R.id.btn_post_imageupload:
                uploadImage(bitmap);
                break;

        }

    }
//
//    private void SelectImage() {
//        final CharSequence[] items = {"Camera", "Gallery", "Cancel"};
//        context= this;
//        AlertDialog.Builder builder;
//        builder = new AlertDialog.Builder(context);
//        builder.setTitle("Add Image");
//
//        builder.setItems(items, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int i) {
//                if (items[i].equals("Camera")) {
//                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    if (intent.resolveActivity(context.getPackageManager()) != null) {
//                        startActivityForResult(intent, REQUEST_CAMERA);
//                    }
//
//                } else if (items[i].equals("Gallery")) {
//
//                    Intent gallery = new Intent();
//                    gallery.setType("image/*");
//                    gallery.setAction(Intent.ACTION_GET_CONTENT);
//                    startActivityForResult(Intent.createChooser(gallery, "Choose Image"), PICK_IMAGE);
//
//
//                } else if (items[i].equals("Cancel")) {
//                    dialog.dismiss();
//                }
//            }
//        });
//        builder.show();
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == Activity.RESULT_OK) {
//
//            if (requestCode == REQUEST_CAMERA) {
//
//
//            } else if (requestCode == PICK_IMAGE) {
//
//                imageUri = data.getData();
//                try {
//                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
//                    postimage.setImageBitmap(bitmap);
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//
//                }
//            }
//
//        }
//
//    }
//
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
                postimage.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();

            }

        }

    }
    private void uploadImage(Bitmap bitmap) {
        ByteArrayOutputStream stream= new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, stream);
        byte[] bytes=stream.toByteArray();
        try{

            File file= new File(this.getCacheDir(),"image.jpeg");
            file.createNewFile();
            FileOutputStream fos= new FileOutputStream(file);
            fos.write(bytes);
            fos.flush();
            fos.close();

            RequestBody rb= RequestBody.create(MediaType.parse("multipart/form-data"),file);
            MultipartBody.Part body=MultipartBody.Part.createFormData("imageName",file.getName(),rb);

            PostAPI heroAPI= RetrofitHelper.instance().create(PostAPI.class);
            Call<String> imageModelCall=heroAPI.uploadImage(body);
            imageModelCall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Toast.makeText(AddPost.this, response.body(), Toast.LENGTH_SHORT).show();
                    imagename.setText(response.body());


                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(AddPost.this, "Error"+ t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        }
        catch (IOException e){
            e.printStackTrace();

        }
    }




}