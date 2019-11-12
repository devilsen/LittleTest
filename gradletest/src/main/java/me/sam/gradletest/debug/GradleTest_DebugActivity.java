package me.sam.gradletest.debug;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import me.sam.gradletest.R;

/**
 * desc :
 * date : 2019-09-03 11:01
 *
 * @author : dongSen
 */
public class GradleTest_DebugActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gradle_test_activity_debug);
    }
}
