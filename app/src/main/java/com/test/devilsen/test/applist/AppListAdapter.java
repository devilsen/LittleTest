package com.test.devilsen.test.applist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.devilsen.test.R;

import java.util.ArrayList;

/**
 * author : dongSen
 * date : 2017/8/21
 * desc :
 */
class AppListAdapter extends RecyclerView.Adapter<AppListAdapter.ViewHolder> {

    private ArrayList<AppModel> list;

    private OnAppItemStateListener onAppItemStateListener;

    AppListAdapter() {
        this.list = AppStateManager.getInstance().getList();
        for (int i = 0; i < 20; i++) {
            list.add(new AppModel(String.valueOf(i), String.valueOf("name" + i)));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_app, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AppModel appModel = list.get(position);

        holder.nameTxt.setText(appModel.name);
        holder.flagCheckBox.setChecked(appModel.isAdd);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public ArrayList<AppModel> getData() {
        return list;
    }

    void setOnAppItemStateListener(OnAppItemStateListener listener) {
        onAppItemStateListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView iconImg;
        private TextView nameTxt;
        private CheckBox flagCheckBox;

        public ViewHolder(View itemView) {
            super(itemView);

            iconImg = (ImageView) itemView.findViewById(R.id.image_list_test);
            nameTxt = (TextView) itemView.findViewById(R.id.text_app_name);
            flagCheckBox = (CheckBox) itemView.findViewById(R.id.checkbox_app_list);

            flagCheckBox.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onAppItemStateListener != null) {
                CheckBox checkBox = (CheckBox) v;
                onAppItemStateListener.onAppItemState(list.get(getAdapterPosition()), checkBox.isChecked());
            }
        }
    }

    interface OnAppItemStateListener {
        void onAppItemState(AppModel model, boolean isChecked);
    }
}
