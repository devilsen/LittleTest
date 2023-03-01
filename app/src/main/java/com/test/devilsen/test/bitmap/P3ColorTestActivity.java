package com.test.devilsen.test.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorSpace;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.exifinterface.media.ExifInterface;

import com.test.devilsen.test.R;
import com.test.devilsen.test.util.BitmapUtil;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileWriter;
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
//            InputStream open = getResources().getAssets().open("image/Webkit-logo-P3.png");
            String path = "image/face/face_o3.jpg";
            InputStream open = getResources().getAssets().open(path);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                options.outConfig = Bitmap.Config.ARGB_8888;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                options.inPreferredColorSpace = ColorSpace.get(ColorSpace.Named.SRGB);
            }
            mBitmap = BitmapFactory.decodeStream(open, null, options);
//            mBitmap = BitmapFactory.decodeFile("/storage/emulated/0/Pictures/QQ/sample_image_p3.png");
//            String path = "/storage/emulated/0/DCIM/P3/Webkit-logo-P3.png";
//            mBitmap = BitmapFactory.decodeFile(path, options);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                ColorSpace colorSpace = mBitmap.getColorSpace();
                Log.d(TAG, "bitmap color space = " + colorSpace.getName());
                if (colorSpace.equals(ColorSpace.get(ColorSpace.Named.DISPLAY_P3))) {
                    Log.d(TAG, "color space is the same");
                }

            }

            ExifInterface exifInterface = makeExif(path);
            int rotationDegrees = exifInterface.getRotationDegrees();
            boolean flipped = exifInterface.isFlipped();
            Log.e(TAG, "[SamTest] image path = " + path + ", rotation = " + rotationDegrees + ", flipped = " + flipped);
//
//            Uri uri = Uri.parse("https://webkit.org/blog-files/color-gamut/Webkit-logo-P3.png");
//            SimpleDraweeView draweeView = findViewById(R.id.my_image_view);
////            draweeView.setImageURI(uri);
//
//            ImageRequest request = ImageRequest.fromUri(uri);
//            ImagePipeline imagePipeline = Fresco.getImagePipeline();
//            DataSource dataSource = imagePipeline.fetchDecodedImage(request, this);
//            try {
//                dataSource.subscribe(new BaseBitmapDataSubscriber() {
//                    @Override
//                    public void onNewResultImpl(@Nullable Bitmap bitmap) {
//                        if (bitmap == null) {
//                            return;
//                        }
//                        mBitmap = bitmap;
//                        beforeIV.setImageBitmap(mBitmap);
//                    }
//
//                    @Override
//                    public void onFailureImpl(DataSource dataSource) {
//                        // No cleanup required here
//                    }
//                }, CallerThreadExecutor.getInstance());
//            } finally {
//                if (dataSource != null) {
//                    dataSource.close();
//                }
//            }
//            draweeView.setImageRequest(request);

            Bitmap.Config config = mBitmap.getConfig();
            Log.d(TAG, "bitmap config = " + config.toString());
            beforeIV.setImageBitmap(mBitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void change(View view) {
        // 0. save directly
//        String path = saveBitmap(mBitmap);
//        Bitmap bitmap = BitmapFactory.decodeFile(path);
//        afterIV.setImageBitmap(bitmap);

        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        // 1. canvas draw
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        canvas.drawBitmap(mBitmap, 0, 0, paint);
        canvas.save();

        // 2. use pixels
//        int[] pixels = new int[mBitmap.getWidth() * mBitmap.getHeight()];
//        mBitmap.getPixels(pixels, 0, width, 0, 0, width, height);
//        savePixelsData(pixels, "PixelsFromJava");
//        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);

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
//        int imageSize = mBitmap.getRowBytes() * mBitmap.getHeight();
//        ByteBuffer buffer = ByteBuffer.allocateDirect(imageSize);
//        mBitmap.copyPixelsToBuffer(buffer);
//        buffer.position(0);
//        bitmap.copyPixelsFromBuffer(buffer);

        // 4.2. use buffer(not working for P3)
//        int imageSize = mBitmap.getRowBytes() * mBitmap.getHeight();
//        IntBuffer buffer = IntBuffer.allocate(imageSize / 4);
//        mBitmap.copyPixelsToBuffer(buffer);
//        buffer.position(0);
//        savePixelsData(buffer.array(), "PixelsFromBuffer");
//        bitmap.copyPixelsFromBuffer(buffer);

        afterIV.setImageBitmap(bitmap);

        saveBitmap(bitmap);
    }

    private void savePixelsData(int[] pixels, String fileName) {
        String path = getExternalCacheDir().getAbsolutePath() + "/" + fileName + ".txt";
        Log.e(TAG, "savePixelsData: path = " + path);
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileWriter fileWriter = new FileWriter(file);
            for (int pixel : pixels) {
                fileWriter.write(Integer.toHexString(pixel) + "\t");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ExifInterface makeExif(@NonNull String path) {
        ExifInterface exif = null;
        try {
            FileDescriptor fileDescriptor = getAssets().openFd(path).getFileDescriptor();
            return new ExifInterface(fileDescriptor);
        } catch (Throwable e) {
            Log.e(TAG, "fail exif: " + path, e);
        }

        try {
            exif = new ExifInterface(path);
        } catch (IOException ignored) {
        }
        return exif;
    }

    private String saveBitmap(Bitmap bitmap) {
        String path = getExternalCacheDir().getAbsolutePath() + "/Webkit-logo-P3-transfer.png";
        boolean saveImage = BitmapUtil.saveImage(bitmap, path);
        Bitmap bitmap1 = BitmapFactory.decodeFile(path);
        Log.d(TAG, "saved = " + saveImage + " path = " + path + " bitmap config = " + bitmap1.getConfig());
        return path;
    }
}
