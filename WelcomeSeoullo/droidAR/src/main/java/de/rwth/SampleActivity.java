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

public class SampleActivity extends AppCompatActivity {
    private PhotoEditorSDK photoEditorSDK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageName = "IMG_.jpg";
//        String selectedImagePath = photoEditorSDK.saveImage("PhotoEditorSDK", imageName);
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String imagePathFinal = pref.getString("hi", "");
        Intent intentToGetImagePath = getIntent();
//        String imagePath = intentToGetImagePath.getExtras().getString("imagePathForAR");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        Bitmap bitmap = BitmapFactory.decodeFile(imagePathFinal, options);
        ImageView photoEditImageView = (ImageView) findViewById(R.id.imageView);
        photoEditImageView.setImageBitmap(bitmap);
    }
}
