package com.example.travelnepalapp.Fragments;


import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment implements View.OnClickListener {
    Activity context;
    ImageView ivimage;
    Uri imageUri;
    Bitmap bitmap;
    private static final int PICK_IMAGE = 1;
    Integer REQUEST_CAMERA = 1, SELECT_FILE = 0;
    Button fab, register;

    EditText name, email, passwords, imagename, username, passwordds;

    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_signup, container, false);
//        ivimage = view.findViewById(R.id.iv_uploadimage);
//        fab = view.findViewById(R.id.btn_imagebutton);
        register = view.findViewById(R.id.btn_resigerbutton);

        name = view.findViewById(R.id.et_fullname_register);
        email = view.findViewById(R.id.et_email_resgister);
        passwords = view.findViewById(R.id.et_password_resgister);
        passwordds = view.findViewById(R.id.et_password2_resgister);
        username = view.findViewById(R.id.et_username_resgister);
//        imagename=view.findViewById(R.id.et_image_editname);


        register.setOnClickListener(this);
//        fab.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btn_imagebutton:
//                SelectImage();

//                break;
            case R.id.btn_resigerbutton:
                registration();
                break;
        }

    }

//    private void SelectImage() {
//        final CharSequence[] items = {"Camera", "Gallery", "Cancel"};
//        context = getActivity();
//        AlertDialog.Builder builder;
//        builder = new AlertDialog.Builder(context);
//        builder.setTitle("Add Image");
//
//        builder.setItems(items, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int i) {
//                if (items[i].equals("Camera")) {
//                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
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

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == Activity.RESULT_OK) {
//
//            if (requestCode == REQUEST_CAMERA) {
//
//
//                ivimage.setImageURI(imageUri);
//                UploadImage(bitmap);
//
//            }
//            else if ( requestCode == PICK_IMAGE && requestCode == SELECT_FILE) {
//
//                imageUri = data.getData();
//                try {
//                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                ivimage.setImageURI(imageUri);
//                UploadImage(bitmap);
//            }
////             else if (requestCode == PICK_IMAGE && resultCode == SELECT_FILE) {
////                imageUri = data.getData();
////                try {
////                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
////                    ivimage.setImageBitmap(bitmap);
////
////                } catch (IOException e) {
////                    e.printStackTrace();
////
////                }
////
////            }
//
//        }
//
//    }


    private void registration() {
        String fullname = name.getText().toString();
        String emails = email.getText().toString();
        String user = username.getText().toString();
        String pass = passwords.getText().toString();
        String passs = passwordds.getText().toString();

        UserModel userModel = new UserModel(fullname, emails, user, pass, passs);
        UserAPI userAPI = RetrofitHelper.instance().create(UserAPI.class);
        Call<String> usercall = userAPI.addUser(userModel);
        usercall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                String res = response.body();

                Toast.makeText(getActivity(), res, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getActivity(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void UploadImage(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bytes = stream.toByteArray();
        try {
            File file = new File(getActivity().getCacheDir(), "image.jpeg");
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.flush();
            fos.close();

            RequestBody rb = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("imageName", file.getName(), rb);

            UserAPI userAPI = RetrofitHelper.instance().create(UserAPI.class);
            Call<String> imagecall = userAPI.uploadImage(body);
            imagecall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Toast.makeText(getActivity(), response.body() + "Hello", Toast.LENGTH_SHORT).show();
                    imagename.setText(response.body());

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(getActivity(), "ERROR" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
