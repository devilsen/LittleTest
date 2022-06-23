package com.test.devilsen.test.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.test.devilsen.test.R;
import com.test.devilsen.test.util.BitmapUtil;

import java.io.IOException;
import java.io.InputStream;


public class P3ColorTestActivity extends AppCompatActivity {

    private static final String TAG = P3ColorTestActivity.class.getSimpleName();

    private Bitmap mBitmap;
    private ImageView afterIV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p3_color_test);

        ImageView beforeIV = findViewById(R.id.p3_test_image_before);
        afterIV = findViewById(R.id.p3_test_image_after);

        try {
            InputStream open = getResources().getAssets().open("image/Webkit-logo-P3.png");
            mBitmap = BitmapFactory.decodeStream(open);
            beforeIV.setImageBitmap(mBitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void change(View view) {
        String path = getExternalCacheDir().getAbsolutePath() + "/Webkit-logo-P3-transfer.png";
        boolean saveImage = BitmapUtil.saveImage(mBitmap, path);
        Log.d(TAG, "saved = " + saveImage + " path = " + path);

        Bitmap bitmap = BitmapFactory.decodeFile(path);
        afterIV.setImageBitmap(bitmap);
    }
}
