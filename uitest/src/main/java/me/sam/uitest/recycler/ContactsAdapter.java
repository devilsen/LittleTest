package me.sam.uitest.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import me.sam.uitest.R;

/**
 * desc :
 * date : 2019/4/19
 *
 * @author : dongSen
 */
class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private final ArrayList<String> listData = new ArrayList<>(100);

    private Context mContext;

    ContactsAdapter(Context context) {
        mContext = context;
        for (int i = 0; i < 100; i++) {
            listData.add("number " + i);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameTxt.setText(listData.get(position));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nameTxt;
        private ImageButton callBtn;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.text_view_item_name);
            callBtn = itemView.findViewById(R.id.image_button_item_call);

            callBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, listData.get(getAdapterPosition()), Toast.LENGTH_SHORT).show();
        }
    }
}
