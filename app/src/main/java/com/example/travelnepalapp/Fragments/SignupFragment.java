package com.example.travelnepalapp.Fragments;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.travelnepalapp.API.UserAPI;
import com.example.travelnepalapp.Models.UserModel;
import com.example.travelnepalapp.R;
import com.example.travelnepalapp.Retrofit.RetrofitHelper;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment implements View.OnClickListener {
    Activity context;
    ImageView ivimage;
    Uri imageUri;
    Integer REQUEST_CAMERA = 1, SELECT_FILE = 0;
    Button fab, register;

    EditText name, email, passwords;

    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        ivimage = view.findViewById(R.id.iv_uploadimage);
        fab = view.findViewById(R.id.btn_imagebutton);
        register = view.findViewById(R.id.btn_resigerbutton);

        name=view.findViewById(R.id.et_fullname_register);
        email=view.findViewById(R.id.et_email_resgister);
        passwords=view.findViewById(R.id.et_password_resgister);


        register.setOnClickListener(this);
        fab.setOnClickListener(this);

        return view;
    }

    private void SelectImage() {
        final CharSequence[] items = {"Camera", "Gallery", "Cancel"};
        context = getActivity();
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setTitle("Add Image");

        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (items[i].equals("Camera")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivityForResult(intent, REQUEST_CAMERA);
                    }

                } else if (items[i].equals("Gallery")) {

                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent, SELECT_FILE);

                } else if (items[i].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == REQUEST_CAMERA) {


                ivimage.setImageURI(imageUri);

            } else if (requestCode == SELECT_FILE) {

                Uri selectedImageUri = data.getData();
                ivimage.setImageURI(selectedImageUri);
            }

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_imagebutton:
                SelectImage();
                break;
            case R.id.btn_resigerbutton:
                registration();
                break;
        }

    }

    private void registration() {
        String fullname = name.getText().toString();
        String emails = email.getText().toString();
        String pass = passwords.getText().toString();

        UserModel userModel = new UserModel(fullname, emails, pass);
        UserAPI userAPI = RetrofitHelper.instance().create(UserAPI.class);
        Call<Void> usercall = userAPI.addUser(userModel);
        usercall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
//                if (!response.isSuccessful()) {
//                    Toast.makeText(context, "Code", Toast.LENGTH_SHORT).show();
//                    return; }
                Toast.makeText(getActivity(), "Successfully Added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getActivity(), "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}
