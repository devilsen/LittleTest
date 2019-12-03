package com.wuba.view.recyclerview;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.wuba.view.R;

/**
 * desc :
 * date : 2019/1/11
 *
 * @author : dongSen
 */
public class ItemAnimationTestActivity extends AppCompatActivity {

    private ItemAnimationAdapter itemAnimationAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_animtion_test);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_animation_test);

        itemAnimationAdapter = new ItemAnimationAdapter();
        itemAnimationAdapter.init();

        recyclerView.setAdapter(itemAnimationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void addData(View view) {
        itemAnimationAdapter.add();
    }
}
