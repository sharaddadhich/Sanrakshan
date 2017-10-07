package com.example.android.sanrakshan.Fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.sanrakshan.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;


public class ReportFragment extends Fragment {

    Context ctx;



    public ReportFragment() {
        // Required empty public constructor
    }

    public ReportFragment(Context ctx) {
        this.ctx = ctx;
    }

    private static final int CAMERA_REQUEST = 1888;
    ByteArrayOutputStream bytearrayoutputstream;
    File file;
    FileOutputStream fileoutputstream;
    ImageView ivCamera;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_report,container,false);


        ivCamera = (ImageView) rootView.findViewById(R.id.iv_Camera);

        ivCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"fname_" +
//                        String.valueOf(System.currentTimeMillis()) + ".jpg"));
                cameraIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, file);
//                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });




        return rootView;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
//            image.setImageBitmap(photo);

//            photo.compress(Bitmap.CompressFormat.PNG, 60, bytearrayoutputstream);

            file = new File( Environment.getExternalStorageDirectory() + "/SampleImage.png");

            try

            {

                fileoutputstream = new FileOutputStream(file);

                fileoutputstream.write(bytearrayoutputstream.toByteArray());

                fileoutputstream.close();

            }

            catch (Exception e)

            {

                e.printStackTrace();

            }

//            Toast.makeText(getContext(), "Image Saved Successfully", Toast.LENGTH_LONG).show();

            Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setType("application/image");
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"sharaddadhich1997@gmail.com"});
            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"ALERT");
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Its a worst Situation");
            emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
//            emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///mnt/sdcard/Myimage.jpeg"));
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        }
    }

}

