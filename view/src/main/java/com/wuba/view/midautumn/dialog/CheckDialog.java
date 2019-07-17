package com.wuba.view.midautumn.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.wuba.view.R;

/**
 * author : dongSen
 * date : 2016-08-24 11:03
 * desc :
 */
public class CheckDialog extends Dialog {
    public CheckDialog(Context context) {
        super(context);
    }

    public CheckDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CheckDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder implements View.OnClickListener {

        private CheckDialog dialog;
        private Context mContext;
        private View mContentView;
        private EditText checkEdit;
        private ImageButton checkBtn;
        private ImageView exitImg;

        private TextListener listener;

        public Builder(Context mContext) {
            this.mContext = mContext;
            mContentView = LayoutInflater.from(mContext).inflate(R.layout.view_welfare_check, null);

            checkEdit = (EditText) mContentView.findViewById(R.id.welfare_check_edit);
            checkBtn = (ImageButton) mContentView.findViewById(R.id.welfare_check_btn);
            exitImg = (ImageView) mContentView.findViewById(R.id.welfare_check_exit_img);

            checkBtn.setOnClickListener(this);
            exitImg.setOnClickListener(this);
        }

        public Builder setTextListener(TextListener listener) {
            this.listener = listener;
            return this;
        }

        private String getText() {
            String text = checkEdit.getText().toString().trim();
            if (TextUtils.isEmpty(text)) {
                Toast.makeText(mContext, "请输入密码", Toast.LENGTH_SHORT).show();
            } else {
                return text;
            }
            return null;
        }

        @Override
        public void onClick(View v) {
            int i = v.getId();
            if (i == R.id.welfare_check_btn) {
                if (getText() != null)
                    listener.check(getText());
            } else if (i == R.id.welfare_check_exit_img) {
                if (dialog != null)
                    dialog.dismiss();
            }
        }

        public CheckDialog create() {
            dialog = new CheckDialog(mContext, R.style.BaseDialog);
            dialog.setContentView(mContentView);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(false);

            return dialog;
        }
    }

    public interface TextListener {
        void check(String text);
    }
}
