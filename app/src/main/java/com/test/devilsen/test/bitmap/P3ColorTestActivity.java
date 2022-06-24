package com.test.devilsen.test.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorSpace;
import android.graphics.ImageDecoder;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.test.devilsen.test.R;
import com.test.devilsen.test.util.BitmapUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;


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
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            mBitmap = BitmapFactory.decodeStream(open, null, options);
//            String path = "/storage/emulated/0/DCIM/P3/Webkit-logo-P3.png";
//            mBitmap = BitmapFactory.decodeFile(path, options);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                ColorSpace colorSpace = mBitmap.getColorSpace();
                Log.d(TAG, "bitmap color space = " + colorSpace.getName());

                Bitmap.Config config = mBitmap.getConfig();
                Log.d(TAG, "bitmap config = " + config.toString());
            }
            beforeIV.setImageBitmap(mBitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void change(View view) {
        int imageSize = mBitmap.getRowBytes() * mBitmap.getHeight();
        ByteBuffer buffer = ByteBuffer.allocateDirect(imageSize);
        mBitmap.copyPixelsToBuffer(buffer);
        buffer.position(0);

        // 0. save directly
//        String path = saveBitmap(mBitmap);
//        Bitmap bitmap = BitmapFactory.decodeFile(path);
//        afterIV.setImageBitmap(bitmap);

        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        // 1. canvas draw
//        Canvas canvas = new Canvas(bitmap);
//        Paint paint = new Paint();
//        canvas.drawBitmap(mBitmap, 0, 0, paint);

        // 2. use pixels
        int[] pixels = new int[mBitmap.getWidth() * mBitmap.getHeight()];
        mBitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);

        // 3. use ImageDecoder.Source (need api 28 android 9.0)
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
//            ImageDecoder.Source source = ImageDecoder.createSource(getAssets(), "image/Webkit-logo-P3.png");
//            try {
//                //使用OnHeaderDecodedListener监听解码信息
//                bitmap = ImageDecoder.decodeBitmap(source, (decoder, info, source1) ->
//                        decoder.setTargetColorSpace(ColorSpace.get(ColorSpace.Named.SRGB)));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        // 4. use buffer(not working for P3)
//        bitmap.copyPixelsFromBuffer(buffer);

        afterIV.setImageBitmap(bitmap);

        saveBitmap(bitmap);
    }

    private String saveBitmap(Bitmap bitmap) {
        String path = getExternalCacheDir().getAbsolutePath() + "/Webkit-logo-P3-transfer.png";
        boolean saveImage = BitmapUtil.saveImage(bitmap, path);
        Log.d(TAG, "saved = " + saveImage + " path = " + path);
        return path;
    }
}
