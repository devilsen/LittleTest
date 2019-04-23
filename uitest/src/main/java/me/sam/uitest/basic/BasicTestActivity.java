package me.sam.uitest.basic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import me.sam.uitest.R;

/**
 * desc : 基础UI测试
 * date : 2019/4/16
 *
 * @author : 来自官方例子
 * https://github.com/googlesamples/android-testing/blob/master/ui/espresso/BasicSample/app/src/main/java/com/example/android/testing/espresso/BasicSample/MainActivity.java
 */
public class BasicTestActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextView;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_test);

        findViewById(R.id.changeTextBt).setOnClickListener(this);
        findViewById(R.id.activityChangeTextBtn).setOnClickListener(this);

        mTextView = findViewById(R.id.textToBeChanged);
        mEditText = findViewById(R.id.editTextUserInput);
    }

    @Override
    public void onClick(View view) {
        // Get the text from the EditText view.
        final String text = mEditText.getText().toString();

        final int changeTextBtId = R.id.changeTextBt;
        final int activityChangeTextBtnId = R.id.activityChangeTextBtn;

        if (view.getId() == changeTextBtId) {
            // 展示EditText的内容
            mTextView.setText(text);
        } else if (view.getId() == activityChangeTextBtnId) {
            // 打开另一个Activity，传递EditText的内容
            Intent intent = ShowTextActivity.newStartIntent(this, text);
            startActivity(intent);
        }
    }
}
