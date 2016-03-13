package com.example.glenn.lathalanavigation;

import android.os.Bundle;
import android.view.View;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class PhotoGallery extends Activity {
    private static int RESULT_LOAD_IMG = 1;
    String photoPath;

    Button btnUploadPhoto;
    Button btnSelectPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_gallery);
        btnUploadPhoto = (Button) findViewById(R.id.btnUploadPicture);
        btnSelectPhoto = (Button) findViewById(R.id.btnSelectPicture);

        String genderValue = getIntent().getExtras().getString(Publish.KEY_CURRENT_PHOTO);


        btnSelectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
            }
        });
        btnUploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent();
                result.putExtra(Publish.KEY_CURRENT_PHOTO, photoPath);
                //setResult and exit
                setResult(RESULT_OK, result);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                photoPath = cursor.getString(columnIndex);
                cursor.close();
                ImageView imgView = (ImageView) findViewById(R.id.imgView);
                imgView.setImageBitmap(BitmapFactory
                        .decodeFile(photoPath));
            } else {
                Toast.makeText(this, "Please select a photo", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "May Error", Toast.LENGTH_LONG).show();
        }


    }

}