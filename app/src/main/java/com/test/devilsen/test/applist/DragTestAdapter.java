package com.test.devilsen.test.applist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.devilsen.test.R;

import java.util.ArrayList;

/**
 * author : dongSen
 * date : 2017/8/21
 * desc :
 */
class DragTestAdapter extends RecyclerView.Adapter<DragTestAdapter.ViewHolder> {

    private ArrayList<AppModel> list;

    private OnAppDeleteListener onAppDeleteListener;

    DragTestAdapter() {
        this.list = AppStateManager.getInstance().getAddList();
//        for (int i = 0; i < 20; i++) {
//            list.add(i + "");
//        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_drag, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AppModel appModel = list.get(position);
//        holder.iconImg
        holder.nameTxt.setText(appModel.name);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public ArrayList<AppModel> getData() {
        return list;
    }

    public void setOnAppDeleteListener(OnAppDeleteListener listener) {
        onAppDeleteListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView iconImg;
        private TextView nameTxt;
        private ImageView deleteImg;

        public ViewHolder(View itemView) {
            super(itemView);

            iconImg = (ImageView) itemView.findViewById(R.id.image_drag_test);
            nameTxt = (TextView) itemView.findViewById(R.id.button_drag_name_test);
            deleteImg = (ImageView) itemView.findViewById(R.id.button_drag_delete_test);

            deleteImg.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onAppDeleteListener != null) {
                onAppDeleteListener.onAppDelete(list.get(getAdapterPosition()), getAdapterPosition());
            }
        }
    }

    interface OnAppDeleteListener {
        void onAppDelete(AppModel appModel, int position);
    }
}
