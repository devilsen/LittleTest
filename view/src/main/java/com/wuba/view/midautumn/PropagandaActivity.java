package com.wuba.view.midautumn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.wuba.view.R;


/**
 * author : dongSen
 * date : 2016-08-24 11:06
 * desc : 中秋宣传首页
 */
public class PropagandaActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton intoBtn;
    private ImageView exitImg;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welfare_mid_autumn_propaganda);

        intoBtn = (ImageButton) findViewById(R.id.welfare_propaganda_into_btn);
        exitImg = (ImageView) findViewById(R.id.welfare_propaganda_exit_img);

        intoBtn.setOnClickListener(this);
        exitImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.welfare_propaganda_into_btn) {
            Intent intent = new Intent(this, MidAutumnMainActivity.class);
            startActivity(intent);
            finish();
        } else if (i == R.id.welfare_propaganda_exit_img) {
            finish();
        }
    }
}
