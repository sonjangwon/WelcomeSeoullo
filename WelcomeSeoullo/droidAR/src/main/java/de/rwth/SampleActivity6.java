package de.rwth;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.ahmedadeltito.photoeditorsdk.PhotoEditorSDK;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SampleActivity6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        ImageView photoEditImageView = (ImageView) findViewById(R.id.imageView);
        photoEditImageView.setImageResource(R.drawable.img_20171029_4);

//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageName = "IMG_.jpg";
//        String selectedImagePath = photoEditorSDK.saveImage("PhotoEditorSDK", imageName);

//        Intent intentToGetImagePath = getIntent();
//        String imagePath = intentToGetImagePath.getExtras().getString("imagePathForAR");

    }
}
