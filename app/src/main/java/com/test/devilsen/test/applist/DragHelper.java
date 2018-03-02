package com.test.devilsen.test.applist;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.Collections;

/**
 * author : dongSen
 * date : 2017/8/21
 * desc :
 */
public class DragHelper<T> extends ItemTouchHelper.Callback {

    private DragTestAdapter adapter;

    private ArrayList<T> list;

    public DragHelper(DragTestAdapter adapter, ArrayList<T> list) {
        this.adapter = adapter;
        this.list = list;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        int flags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;

        return makeMovementFlags(flags, 0);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int fromPosition = viewHolder.getAdapterPosition();
        int targetPosition = target.getAdapterPosition();

        if (fromPosition < targetPosition) {
            for (int i = fromPosition; i < targetPosition; i++) {
                Collections.swap(list, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > targetPosition; i--) {
                Collections.swap(list, i, i - 1);
            }
        }

        adapter.notifyItemMoved(fromPosition, targetPosition);

        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }
}
