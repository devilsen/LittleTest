package com.wuba.view.midautumn;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.wuba.view.R;
import com.wuba.view.midautumn.dialog.CheckDialog;

/**
 * author : dongSen
 * date : 2016-08-23 10:51
 * desc :
 */
public class MidAutumnMainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView leftTopCloud;
    private ImageView rightTopCloud;
    private ImageView leftBottomCloud;
    private ImageView rightBottomCloud;

    private ImageButton iWantGiftBtn;

    private CheckDialog checkDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //隐藏标题栏
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        //定义全屏参数
//        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
//        //获得当前窗体对象
//        Window window = getWindow();
//        //设置当前窗体为全屏显示
//        window.setFlags(flag, flag);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welfare_mid_autumn);

        initView();
    }

    private void initView() {
        leftTopCloud = (ImageView) findViewById(R.id.welfare_cloud_left_top);
        rightTopCloud = (ImageView) findViewById(R.id.welfare_cloud_right_top);
        leftBottomCloud = (ImageView) findViewById(R.id.welfare_cloud_left_bottom);
        rightBottomCloud = (ImageView) findViewById(R.id.welfare_cloud_right_bottom);

        iWantGiftBtn = (ImageButton) findViewById(R.id.welfare_i_want_gift_btn);

        iWantGiftBtn.setOnClickListener(this);

        checkDialog = new CheckDialog.Builder(this)
                .setTextListener(new CheckDialog.TextListener() {
                    @Override
                    public void check(String text) {
                        Toast.makeText(MidAutumnMainActivity.this, text, Toast.LENGTH_SHORT).show();
                        checkDialog.dismiss();

                        Intent intent = new Intent(MidAutumnMainActivity.this, WelfareResultActivity.class);
                        startActivity(intent);
                    }
                })
                .create();
        initCloudAnim();
    }

    private void initCloudAnim() {
        ObjectAnimator leftTopCloudAnim = ObjectAnimator.ofFloat(leftTopCloud, "translationX", 0, -100).setDuration(2000);
        leftTopCloudAnim.setRepeatCount(ObjectAnimator.INFINITE);
        leftTopCloudAnim.setRepeatMode(ObjectAnimator.REVERSE);
        leftTopCloudAnim.start();
        ObjectAnimator rightTopCloudAnim = ObjectAnimator.ofFloat(rightTopCloud, "translationX", 0, 80).setDuration(3000);
        rightTopCloudAnim.setRepeatCount(ObjectAnimator.INFINITE);
        rightTopCloudAnim.setRepeatMode(ObjectAnimator.REVERSE);
        rightTopCloudAnim.start();
        ObjectAnimator leftBottomCloudAnim = ObjectAnimator.ofFloat(leftBottomCloud, "translationX", 0, -200).setDuration(4000);
        leftBottomCloudAnim.setRepeatCount(ObjectAnimator.INFINITE);
        leftBottomCloudAnim.setRepeatMode(ObjectAnimator.REVERSE);
        leftBottomCloudAnim.start();
        ObjectAnimator rightBottomCloudAnim = ObjectAnimator.ofFloat(rightBottomCloud, "translationX", 0, 150).setDuration(3000);
        rightBottomCloudAnim.setRepeatCount(ObjectAnimator.INFINITE);
        rightBottomCloudAnim.setRepeatMode(ObjectAnimator.REVERSE);
        rightBottomCloudAnim.start();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.welfare_i_want_gift_btn) {
            checkDialog.show();
        }
    }
}
