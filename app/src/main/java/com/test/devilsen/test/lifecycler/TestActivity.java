package com.test.devilsen.test.lifecycler;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.test.devilsen.test.MainActivity;
import com.test.devilsen.test.R;

/**
 * desc :
 * date : 2017/12/21
 *
 * @author : dongSen
 */
public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "state";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycler_test);

        Button openDialogBtn = findViewById(R.id.text_test_open_dialog);
        Button openActivityBtn = findViewById(R.id.text_test_open_activity);
        Button openTransActivityBtn = findViewById(R.id.text_test_open_translate_activity);
        Button breakActivityBtn = findViewById(R.id.text_test_break);

        openDialogBtn.setOnClickListener(this);
        openActivityBtn.setOnClickListener(this);
        openTransActivityBtn.setOnClickListener(this);
        breakActivityBtn.setOnClickListener(this);

        if (savedInstanceState != null)
            Log.e(TAG, "savedInstanceState not null");


        Log.e(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e(TAG, "onRestoreInstanceState");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.text_test_open_dialog:
                openDialog();
                break;
            case R.id.text_test_open_activity:
                openActivity();
                break;
            case R.id.text_test_open_translate_activity:
                openTranslate();
                break;
            case R.id.text_test_break:
                breakActivity();
                break;

        }
    }

    private void breakActivity() {
        throw new RuntimeException("test");
    }

    private void openTranslate() {
        Intent intent = new Intent(this, TranslateActivity.class);
        startActivity(intent);
    }

    private void openActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void openDialog() {
        new AlertDialog.Builder(this)
                .setTitle("test")
                .show();

//        PopupWindow window = new PopupWindow(this);
//        window.setContentView();
//        window.showAsDropDown();

        new BirthdayDialog.Builder(this)
                .create()
                .show();
    }
}
