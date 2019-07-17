package com.wuba.view.applist;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.wuba.view.R;


/**
 * author : dongSen
 * date : 2017/8/21
 * desc : 拖拽recyclerView
 */
public class RecyclerTestActivity extends AppCompatActivity implements
        AppListAdapter.OnAppItemStateListener, DragTestAdapter.OnAppDeleteListener {

    private DragTestAdapter dragTestAdapter;
    private AppListAdapter appListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_recycler_view);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_test);

        dragTestAdapter = new DragTestAdapter();
        recyclerView.setAdapter(dragTestAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 5));
        dragTestAdapter.setOnAppDeleteListener(this);

        DragHelper<AppModel> appModelDragHelper = new DragHelper<>(dragTestAdapter, dragTestAdapter.getData());
        final ItemTouchHelper helper = new ItemTouchHelper(appModelDragHelper);

        helper.attachToRecyclerView(recyclerView);

        recyclerView.addOnItemTouchListener(new LongClickListener(recyclerView) {
            @Override
            public void onItemLongClick(RecyclerView.ViewHolder viewHolder) {
                if (viewHolder.getLayoutPosition() != dragTestAdapter.getData().size() - 1) {
                    helper.startDrag(viewHolder);
                    Vibrator vibrator = (Vibrator) RecyclerTestActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(70);
                }
            }
        });

        RecyclerView recyclerViewList = (RecyclerView) findViewById(R.id.recycler_view_test_list);

        appListAdapter = new AppListAdapter();
        recyclerViewList.setAdapter(appListAdapter);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(this));
        appListAdapter.setOnAppItemStateListener(this);

    }

    @Override
    public void onAppItemState(AppModel appModel, boolean isChecked) {
        if (isChecked) {//添加
            int position = AppStateManager.getInstance().addApp(appModel);

            if (position != -1)
                dragTestAdapter.notifyItemInserted(position);
        } else {//删除
            int position = AppStateManager.getInstance().deleteApp(appModel);

            if (position != -1)
                dragTestAdapter.notifyItemRemoved(position);
        }

    }

    @Override
    public void onAppDelete(AppModel appModel, int position) {
        AppStateManager.getInstance().deleteApp(appModel);

        dragTestAdapter.notifyItemRemoved(position);
        appListAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_drag_recycler, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_check) {
            Log.e("addList", AppStateManager.getInstance().toString());
        }
        return super.onOptionsItemSelected(item);
    }
}
