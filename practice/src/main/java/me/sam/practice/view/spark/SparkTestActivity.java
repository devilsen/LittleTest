package me.sam.practice.view.spark;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * desc : 爆炸效果View
 * date : 2019/3/27
 *
 * @author : dongSen
 */
public class SparkTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SparkView(this));
    }
}
