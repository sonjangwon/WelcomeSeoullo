package com.example.jangwon.welcomeseoullo.ARMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by younghov on 2017. 10. 20..
 */

public class ARMainActivity extends MediaActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        startCameraActivity();
    }

//    public void openUserGallery(View view) {
//        openGallery();
//    }

    public void openUserCamera(View view) {
        startCameraActivity();
    }

    @Override
    protected void onPhotoTaken() {
        Intent intent = new Intent(ARMainActivity.this, PhotoEditorActivity.class);
        intent.putExtra("selectedImagePath", selectedImagePath);
        startActivity(intent);
    }
}