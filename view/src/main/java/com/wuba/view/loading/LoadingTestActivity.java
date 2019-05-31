package com.wuba.view.loading;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wuba.view.R;
import com.wuba.view.loading.bubble.BubbleContainer;

/**
 * desc :
 * date : 2019-05-31
 *
 * @author : dongSen
 */
public class LoadingTestActivity extends AppCompatActivity {

    private BubbleContainer bubbleContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_test);

        bubbleContainer = findViewById(R.id.bubble);

    }

    public void start(View view){
        bubbleContainer.startAnimator(0);
    }

    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5F);
    }
}
