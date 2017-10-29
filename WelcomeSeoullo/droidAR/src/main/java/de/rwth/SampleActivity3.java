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

public class SampleActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        changeStatusBarColor();
        ImageView photoEditImageView = (ImageView) findViewById(R.id.imageView);
        photoEditImageView.setImageResource(R.drawable.img_20171029_1);

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
