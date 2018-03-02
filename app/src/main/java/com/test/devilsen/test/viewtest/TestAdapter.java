package com.test.devilsen.test.viewtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.devilsen.test.R;

import java.util.ArrayList;

/**
 * author : dongSen
 * date : 2017/9/25
 * desc :
 */
class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {


    private ArrayList<String> list = new ArrayList<>();

    public TestAdapter() {
        for (int i = 0; i < 1; i++) {
            list.add("position " + i);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_test, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.testView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TestView testView;

        public ViewHolder(View itemView) {
            super(itemView);
            testView = (TestView) itemView.findViewById(R.id.text_test_view);
        }
    }
}
