package com.test.devilsen.test.applist;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * author : dongSen
 * date : 2017/8/21
 * desc :
 */
public abstract class LongClickListener implements RecyclerView.OnItemTouchListener {

    private GestureDetectorCompat detectorCompat;

    private RecyclerView recyclerView;

    public LongClickListener(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.detectorCompat = new GestureDetectorCompat(recyclerView.getContext(), new ItemGestureDetectorListener());
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        detectorCompat.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        detectorCompat.onTouchEvent(e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    private class ItemGestureDetectorListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public void onLongPress(MotionEvent e) {
            View childViewUnder = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (childViewUnder != null) {
                RecyclerView.ViewHolder childViewHolder = recyclerView.getChildViewHolder(childViewUnder);
                onItemLongClick(childViewHolder);
            }

        }
    }

    public abstract void onItemLongClick(RecyclerView.ViewHolder viewHolder);

}
