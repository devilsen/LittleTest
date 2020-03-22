package com.wuba.view.image;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wuba.view.DataSource.ImageDataSource;
import com.wuba.view.R;

/**
 * desc : 图片测试
 * date : 2019-06-06
 *
 * @author : dongSen
 */
public class ImageTestActivity extends AppCompatActivity {

    private HeadImageView headImageView;

    private String status = "0";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_test);

        headImageView = findViewById(R.id.head_image);
        ImageView imageView = findViewById(R.id.head_image_2);

        headImageView.setBorderColor(Color.GRAY);
        headImageView.setBorderWidth(10);
        Glide.with(ImageTestActivity.this)
                .load(ImageDataSource.getInstance().getIcon())
                .into(headImageView);


        Glide.with(ImageTestActivity.this)
                .load(ImageDataSource.getInstance().getIcon())
                .into(imageView);
    }

    public void changeStatus(View view) {
        if ("0".equals(status))
            status = "1";
        else
            status = "0";

        headImageView.setUserStatus(status);
    }
}
