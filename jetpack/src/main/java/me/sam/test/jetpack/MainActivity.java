package me.sam.test.jetpack;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import me.sam.test.jetpack.lifecycle.MyObserver;

/**
 * desc :
 * date : 2019-04-24
 *
 * @author : dongSen
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //添加一个生命周期观察者    getLifecycle()是FragmentActivity中的方法
        MyObserver observer = new MyObserver();
        getLifecycle().addObserver(observer);
    }
}
