package com.wuba.view.imkeyboard;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.wuba.view.R;


/**
 * desc :
 * date : 2018/7/10
 *
 * @author : dongSen
 */
public class ImKeyboardActivity extends AppCompatActivity {

    private EditText inputText;
    private RecyclerView recyclerView;
    private Button showLayout;
    private FrameLayout layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im_keyboard);

        recyclerView = findViewById(R.id.recycler_view_im);
        inputText = findViewById(R.id.edit_input);
        showLayout = findViewById(R.id.btn_im);
        layout = findViewById(R.id.layout_im);

        recyclerView.setAdapter(new ImAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        showLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layout.getVisibility() == View.VISIBLE) {
                    layout.setVisibility(View.GONE);
                } else {
                    KeyboardUtils.hideSoftInput(ImKeyboardActivity.this, inputText);
                }
            }
        });

        inputText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    layout.setVisibility(View.GONE);

//                    recyclerView.smoothScrollToPosition(99);
                }
            }
        });

        SoftKeyBoardListener.setListener(this, new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                layout.setVisibility(View.GONE);

            }

            @Override
            public void keyBoardHide(int height) {
                handler.sendEmptyMessageDelayed(1, 100);
            }
        });
    }

    Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                layout.setVisibility(View.VISIBLE);
            } else if (msg.what == 2) {
                layout.setVisibility(View.GONE);
            }

        }
    };

}
