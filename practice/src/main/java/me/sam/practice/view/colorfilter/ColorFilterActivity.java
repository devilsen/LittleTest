package me.sam.practice.view.colorfilter;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * desc :
 * date : 2019-05-07
 *
 * @author : dongSen
 */
public class ColorFilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ColorFilterView(this));
    }
}
