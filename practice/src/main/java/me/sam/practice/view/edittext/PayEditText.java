package me.sam.practice.view.edittext;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import me.sam.practice.R;

/**
 * desc :
 * date : 2019-05-07
 *
 * @author : dongSen
 */
public class PayEditText extends LinearLayout implements View.OnFocusChangeListener, TextWatcher {

    private EditText[] mEditTexts = new EditText[6];
    private int index;

    public PayEditText(Context context) {
        this(context, null);
    }

    public PayEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PayEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_pay_edit_text, this);

        mEditTexts[0] = findViewById(R.id.edit_text_pay_0);
        mEditTexts[1] = findViewById(R.id.edit_text_pay_1);
        mEditTexts[2] = findViewById(R.id.edit_text_pay_2);
        mEditTexts[3] = findViewById(R.id.edit_text_pay_3);
        mEditTexts[4] = findViewById(R.id.edit_text_pay_4);
        mEditTexts[5] = findViewById(R.id.edit_text_pay_5);

        mEditTexts[0].requestFocus();
        for (EditText mEditText : mEditTexts) {
            mEditText.setOnFocusChangeListener(this);
            mEditText.addTextChangedListener(this);
        }
    }

    private int getIndex(View view) {
        for (int i = 0; i < mEditTexts.length; i++) {
            if (view == mEditTexts[i]) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            index = getIndex(v);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() > 1) {
            mEditTexts[index].setText(String.valueOf(s.charAt(start)));
        }

        if (s.length() == 1) {
            if (index == mEditTexts.length - 1) {
                index = mEditTexts.length - 1;
            } else {
                index++;
            }
            mEditTexts[index].requestFocus();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
