package com.test.devilsen.test.lifecycler;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.test.devilsen.test.R;


/**
 * author : dongSen
 * date : 2017/6/23 下午3:19
 * desc : 生日提醒dialog
 */
public class BirthdayDialog extends Dialog {

    public BirthdayDialog(Context context) {
        super(context);
    }

    public BirthdayDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BirthdayDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {

        private BirthdayDialog dialog;
        private Context mContext;
        private View mContentView;

        public Builder(Context context) {
            mContext = context;

            mContentView = LayoutInflater.from(mContext).inflate(R.layout.dialog_full_window, null);

        }

        public BirthdayDialog create() {
            dialog = new BirthdayDialog(mContext, R.style.BaseDialog);
            dialog.setContentView(mContentView);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(false);

			/*
             * 获取圣诞框的窗口对象及参数对象以修改对话框的布局设置,
			 * 可以直接调用getWindow(),表示获得这个Activity的Window
			 * 对象,这样这可以以同样的方式改变这个Activity的属性.
			 */
            Window dialogWindow = dialog.getWindow();
            /*
             * 将对话框的大小按屏幕大小的百分比设置
			 */
            assert dialogWindow != null;
            WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
            p.width = (int) (getScreenWidth(mContext)); // 宽度设置为屏幕的0.65
            dialogWindow.setAttributes(p);

            return dialog;
        }

    }

    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }
}
