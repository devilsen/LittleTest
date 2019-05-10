package me.sam.practice.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import me.sam.practice.R;
import me.sam.practice.anim.simple.ImageAnimationActivity;
import me.sam.practice.view.colorfilter.ColorFilterActivity;
import me.sam.practice.view.edittext.PayTestActivity;
import me.sam.practice.view.spark.SparkTestActivity;
import me.sam.practice.view.touch.TouchViewActivity;
import me.sam.practice.view.xfermode.XfermodeTestActivity;

/**
 * desc :
 * date : 2019/3/27
 *
 * @author : dongSen
 */
public class ViewMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_main);
    }

    public void Xfermode(View view) {
        Intent intent = new Intent(this, XfermodeTestActivity.class);
        startActivity(intent);
    }

    public void Spark(View view) {
        Intent intent = new Intent(this, SparkTestActivity.class);
        startActivity(intent);
    }

    public void ColorFilter(View view) {
        Intent intent = new Intent(this, ColorFilterActivity.class);
        startActivity(intent);
    }

    public void TouchView(View view) {
        Intent intent = new Intent(this, TouchViewActivity.class);
        startActivity(intent);
    }


    public void PayView(View view) {
        Intent intent = new Intent(this, PayTestActivity.class);
        startActivity(intent);
    }

    public void imageAnimation(View view) {
        Intent intent = new Intent(this, ImageAnimationActivity.class);
        startActivity(intent);
    }


}
