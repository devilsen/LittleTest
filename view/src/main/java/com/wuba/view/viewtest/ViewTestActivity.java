package com.wuba.view.viewtest;

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.wuba.view.R;


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
