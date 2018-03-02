package com.test.devilsen.test.bottomsheet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.leakcanary.RefWatcher;
import com.test.devilsen.test.MyApplication;
import com.test.devilsen.test.R;
import com.test.devilsen.test.image.HeadImageView;

/**
 * desc :
 * date : 2017/11/22
 *
 * @author : dongSen
 */
public class BottomSheetActivity extends AppCompatActivity {

    private HeadImageView headImageView;

    private String status = "0";

    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);

        headImageView = findViewById(R.id.head_image);

        button = findViewById(R.id.button_test);

        headImageView.setUserStatus(status);

//        handler.sendEmptyMessageDelayed(1, 10000);


        Glide.with(BottomSheetActivity.this)
                .load("http://img.dongqiudi.com/uploads/avatar/2015/07/25/QM387nh7As_thumb_1437790672318.jpg")
                .into(headImageView);
    }


    public void showDialog(View view) {
        final BottomSheetDialog dialog = new BottomSheetDialog(BottomSheetActivity.this);
        View dialogView = LayoutInflater.from(BottomSheetActivity.this)
                .inflate(R.layout.view_layout_bottom, null);
        TextView tvTakePhoto = (TextView) dialogView.findViewById(R.id.tv_take_photo);
        TextView tvPhotoAlbum = (TextView) dialogView.findViewById(R.id.tv_photo_album);
        TextView tvCancel = (TextView) dialogView.findViewById(R.id.tv_cancel);

        tvTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BottomSheetActivity.this, "拍照", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        tvPhotoAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BottomSheetActivity.this, "拍照", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.setContentView(dialogView);
        dialog.show();
    }

    public void changeStatus(View view) {
        if ("0".equals(status))
            status = "1";
        else
            status = "0";

        headImageView.setUserStatus(status);
    }

//    Handler handler = new Handler(new Handler.Callback() {
//
//        @Override
//        public boolean handleMessage(Message msg) {
//
//            button.setText("test");
//
//            Toast.makeText(BottomSheetActivity.this, "test", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//    });

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = MyApplication.getRefWatcher(this);
        refWatcher.watch(this);
    }
}
