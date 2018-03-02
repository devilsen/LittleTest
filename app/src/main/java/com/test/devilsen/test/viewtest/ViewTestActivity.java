package com.test.devilsen.test.viewtest;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import com.test.devilsen.test.R;

/**
 * author : dongSen
 * date : 2017/9/22
 * desc :
 */
public class ViewTestActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test);

//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_customer_view);
//
//        recyclerView.setAdapter(new TestAdapter());
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        try {
//            InputStream fileHolder = getAssets().open("fileHolder");
//
//            File file = new File(fileHolder);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String path = Environment.getExternalStorageDirectory().getPath() + File.separator + "fileHolder";
//
//        File holder = new File(path);
//        if (!holder.exists()) {
//            try {
//                holder.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        File file = new File(path);
//        boolean exists = file.exists();
//
//        Log.e("exists", exists + "");

    }
}
