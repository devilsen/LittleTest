package me.sam.uitest.recycler;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.sam.uitest.R;

/**
 * desc : RecyclerView测试
 * date : 2019/4/19
 *
 * @author : dongSen
 */
public class RecyclerViewTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_test);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_test);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ContactsAdapter(this));
    }
}
