package com.example.jangwon.welcomeseoullo.ARMenu;

        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.widget.ImageView;

        import com.ahmedadeltito.photoeditorsdk.PhotoEditorSDK;
        import com.example.jangwon.welcomeseoullo.R;

        import java.text.SimpleDateFormat;
        import java.util.Date;

/**
 * Created by younghov on 2017. 10. 23..
 */

public class SetImageView extends AppCompatActivity {
    private PhotoEditorSDK photoEditorSDK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_image_view);

//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageName = "IMG_" + timeStamp + ".jpg";
//        String selectedImagePath = photoEditorSDK.saveImage("PhotoEditorSDK", imageName);
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inSampleSize = 1;
//        Bitmap bitmap = BitmapFactory.decodeFile(selectedImagePath, options);
//        ImageView photoEditImageView = (ImageView) findViewById(R.id.imageView);
//        photoEditImageView.setImageBitmap(bitmap);

    }
}
