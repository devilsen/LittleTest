package me.sam.uitest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import me.sam.uitest.basic.BasicTestActivity;
import me.sam.uitest.idling.IdlingTestActivity;
import me.sam.uitest.intent.IntentTestActivity;
import me.sam.uitest.recycler.RecyclerViewTestActivity;

/**
 * desc : 主界面
 * date : 2019/4/19
 *
 * @author : dongSen
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBasic(View view) {
        Intent intent = new Intent(this, BasicTestActivity.class);
        startActivity(intent);
    }

    public void onIntent(View view) {
        Intent intent = new Intent(this, IntentTestActivity.class);
        startActivity(intent);
    }

    public void onRecycler(View view) {
        Intent intent = new Intent(this, RecyclerViewTestActivity.class);
        startActivity(intent);
    }

    public void onIdling(View view) {
        Intent intent = new Intent(this, IdlingTestActivity.class);
        startActivity(intent);
    }
}
