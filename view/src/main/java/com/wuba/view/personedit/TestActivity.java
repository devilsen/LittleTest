package com.wuba.view.personedit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.wuba.view.R;

/**
 * author : dongSen
 * date : 2017/8/3
 * desc :
 */
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_edit_text);

        EditText textView = findViewById(R.id.edit_emojicon);

//        textView.setText(MisTextHandler.makeData());
////        textView.setMovementMethod(LinkMovementMethod.getInstance());
//
//        textView.setTextClickListener(new MisTextClickListener() {
//            @Override
//            public void onTextClick(String key, String content, int type) {
//                Log.e("ddd", "key " + key + "  content " + content);
//            }
//        });

        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                Log.e("text", s.toString() + "  " + start + "    " + after + "   " + count);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                int length = s.length();

//                if (length > 10) {
//                    Toast.makeText(TestActivity.this, "超过10", Toast.LENGTH_SHORT).show();
//
//                    return;
//                }

                Log.e("text", s.toString() + "  " + start + "    " + before + "   " + count);

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e("text", s.toString() + "   " + s.length());
            }
        });

    }
}
