package com.wuba.view.textview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.util.Log;
import android.widget.TextView;

import com.wuba.view.R;

/**
 * desc : 拦截html的textView
 * date : 2019-11-12 19:00
 *
 * @author : dongSen
 */
public class TextViewTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_test);

        TextView textView = findViewById(R.id.text_view_test_url);
//        textView.setMovementMethod(BetterLinkMovementMethod.newInstance());

        BetterLinkMovementMethod.linkify(Linkify.ALL, textView)
                .setOnLinkClickListener(new BetterLinkMovementMethod.OnLinkClickListener() {
                    @Override
                    public boolean onClick(TextView textView, String url) {
                        Log.e("URL", url);
                        return true;
                    }
                });
    }
}
