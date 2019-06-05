package com.wuba.view.loading;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;

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

//        float headOffsetX = (float) (headPoint.getRadius() *
//                Math.sin(Math.atan());


        SeekBar seekBar = findViewById(R.id.seek_bar_loading_test);
        bubbleContainer = findViewById(R.id.bubble);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                bubbleContainer.setBubbleInterval(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar.setProgress(35);

    }

    public void start(View view) {
        bubbleContainer.startBubbleAnimation();
    }

    public void stop(View view) {
        bubbleContainer.stopAnimation();
    }

    public void reset(View view) {
        bubbleContainer.reset();
    }

}
