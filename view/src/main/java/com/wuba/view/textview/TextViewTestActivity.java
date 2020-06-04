package com.wuba.view.textview;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.util.LinkifyCompat;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.util.Linkify;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.wuba.view.R;

import java.util.regex.Pattern;

/**
 * desc :
 * 1. 拦截html的textView
 * 2. 自定义Linkify
 * date : 2019-11-12 19:00
 *
 * @author : dongSen
 */
public class TextViewTestActivity extends AppCompatActivity implements TextWatcher {

    private TextView mPhoneText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_test);

        EditText editText = findViewById(R.id.test_edit_text);
        editText.addTextChangedListener(this);

        TextView textView = findViewById(R.id.text_view_test_url);
        mPhoneText = findViewById(R.id.text_view_test_phone);
//        textView.setMovementMethod(BetterLinkMovementMethod.newInstance());

        BetterLinkMovementMethod.linkify(Linkify.ALL, textView)
                .setOnLinkClickListener(new BetterLinkMovementMethod.OnLinkClickListener() {
                    @Override
                    public boolean onClick(TextView textView, String url) {
                        Log.e("URL", url);
                        return true;
                    }
                });

        BetterLinkMovementMethod.linkify(Linkify.ALL, mPhoneText)
                .setOnLinkClickListener(new BetterLinkMovementMethod.OnLinkClickListener() {
                    @Override
                    public boolean onClick(TextView textView, String url) {
                        Log.e("URL", url);
                        return true;
                    }
                });

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mPhoneText.setText(s);
        // 在设置text之后，要重新设置
        // 不能在xml中设置autoLink
        Linkify.addLinks(mPhoneText, Linkify.ALL);
        Pattern pattern = Pattern.compile("^(1[0-9][0-9])\\d{8}$");
        Linkify.addLinks(mPhoneText, pattern, "tel://");
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
