package com.test.devilsen.test.screen.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.test.devilsen.test.R;

/**
 * desc : 一些关于全屏Activity的设置
 * date : 2019/4/18
 *
 * @author : dongSen
 */
public class FullScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        //因为Style里有Toolbar，这里不能再设置
        initToolbar(this);
//        //设置全屏
//        setFullScreen();
//        //如果有ToolBar的话，设置ToolBar的偏移，能漏出状态栏
//        setHeightAndPadding(this, findViewById(R.id.toolbar));
//
//        //设置状态栏全透明
//        StatusBarUtil.transparencyBar(this);
////        //设置状态栏白底黑字
//        StatusBarUtil.StatusBarLightMode(this);

        StatusBarUtil2.setStatusColor(this, false, true, R.color.white);
        setImage();
    }

    private void setImage() {
        ImageView imageView = findViewById(R.id.image_view_full_screen);
        Glide.with(this)
                .load("https://img1.gamersky.com/image2019/01/20190119_ddw_459_10/gamersky_13origin_25_20191191155B54.jpg")
                .centerCrop()
                .into(imageView);
    }

    private Toolbar initToolbar(@NonNull AppCompatActivity activity) {
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        return toolbar;
    }

    private void setFullScreen() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }

        Window window = getWindow();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            //TRANSLUCENT 半透明
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置为透明
            window.setStatusBarColor(Color.TRANSPARENT);

            int visibility = window.getDecorView().getVisibility();
            //全屏展示
            visibility |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            //隐藏导航栏
//            visibility |= View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            //防止内容区域大小发生变化
            visibility |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

            window.getDecorView().setSystemUiVisibility(visibility);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 获取状态栏的高度
     */
    private int getStatusBarHeight(Context context) {
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            return context.getResources().getDimensionPixelSize(resId);
        }
        return 0;
    }

    /**
     * 为ToolBar设置间距
     *
     * @param view toolbar或其他位于顶端的view
     */
    private void setHeightAndPadding(Context context, View view) {
        int statusBarHeight = getStatusBarHeight(context);

        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height += statusBarHeight;
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + statusBarHeight, view.getPaddingRight(), view.getPaddingBottom());
    }
}
