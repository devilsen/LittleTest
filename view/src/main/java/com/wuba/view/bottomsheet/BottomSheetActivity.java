package com.wuba.view.bottomsheet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wuba.view.R;

/**
 * desc :
 * date : 2017/11/22
 *
 * @author : dongSen
 */
public class BottomSheetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
    }

    public void showDialog(View view) {
        final BottomSheetDialog dialog = new BottomSheetDialog(BottomSheetActivity.this);
        View dialogView = LayoutInflater.from(BottomSheetActivity.this)
                .inflate(R.layout.view_layout_bottom, null);
        TextView tvTakePhoto = dialogView.findViewById(R.id.tv_take_photo);
        TextView tvPhotoAlbum = dialogView.findViewById(R.id.tv_photo_album);
        TextView tvCancel = dialogView.findViewById(R.id.tv_cancel);

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
//        RefWatcher refWatcher = MyApplication.getRefWatcher(this);
//        refWatcher.watch(this);
    }
}
