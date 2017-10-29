package de.rwth;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
        changeStatusBarColor();

        ImageView photoEditImageView = (ImageView) findViewById(R.id.imageView);
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String imagePathFinal = pref.getString("hi", "");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        Bitmap bitmap = BitmapFactory.decodeFile(imagePathFinal, options);
        photoEditImageView.setImageBitmap(bitmap);

//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageName = "IMG_.jpg";
//        String selectedImagePath = photoEditorSDK.saveImage("PhotoEditorSDK", imageName);

//        Intent intentToGetImagePath = getIntent();
//        String imagePath = intentToGetImagePath.getExtras().getString("imagePathForAR");

    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }
    }
}
