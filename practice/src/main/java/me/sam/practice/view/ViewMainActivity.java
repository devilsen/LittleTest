package me.sam.practice.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import me.sam.practice.R;
import me.sam.practice.view.spark.SparkTestActivity;
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
}
