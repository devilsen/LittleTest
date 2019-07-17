package com.wuba.view.badgeicon;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.CheckedTextView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuba.view.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * author : dongSen
 * date : 217/4/24
 * desc :
 */
public class BadgeTextView extends RelativeLayout {

    @IntDef({NUMBER, NEW, LOCK_APP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface BadgeType {
    }

    public static final int NUMBER = 0;
    public static final int NEW = 1;
    public static final int LOCK_APP = 2;

    //应用图标
    private CheckedTextView icon;
    //自定义应用图标
    private int iconDrawable;
    //应用标题
    private String title;
    //文字离图标间距
    private float drawablePadding;
    //下方文字颜色
    private ColorStateList textColor = null;
    //下方文字大小
    private int textSize;

    private BadgeHelper badgeHelper;

    public BadgeTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        inflate(getContext(), R.layout.view_badge_icon, this);

        icon = (CheckedTextView) findViewById(R.id.text_badge_icon);
        TextView badge = (TextView) findViewById(R.id.text_badge_number);

        badgeHelper = new BadgeHelper(badge);

        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
//        setGravity(Gravity.CENTER);
        setBackgroundResource(getDrawableRes(context, R.attr.selectableItemBackgroundBorderless));

        populateAttributes(context, attrs);
        initializeViews();

    }

    private void populateAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.BadgeTextView, 0, 0);

        try {
            iconDrawable = typedArray.getResourceId(R.styleable.BadgeTextView_icon, 0);

            title = typedArray.getString(R.styleable.BadgeTextView_title);

            drawablePadding = typedArray.getDimension(R.styleable.BadgeTextView_drawablePadding, 0);

            textColor = typedArray.getColorStateList(R.styleable.BadgeTextView_textColor);

            textSize = typedArray.getDimensionPixelSize(R.styleable.BadgeTextView_textSize, 0);

        } finally {
            typedArray.recycle();
        }
    }

    private void initializeViews() {
        setCompoundDrawable(iconDrawable);

        icon.setText(title);

        icon.setCompoundDrawablePadding((int) drawablePadding);

        icon.setTextColor(textColor != null ? textColor : ColorStateList.valueOf(0xFF1A1F25));

        int size = textSize == 0 ? 12 : pixelToDp(getContext(), textSize);

        icon.setTextSize(size);
    }

    /**
     * 设置图标
     */
    public void setIconDrawable(@DrawableRes int drawable) {
        setCompoundDrawable(drawable);
    }

    private void setCompoundDrawable(@DrawableRes int drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            icon.setCompoundDrawablesRelativeWithIntrinsicBounds(null,
                    ContextCompat.getDrawable(getContext(), drawable),
                    null,
                    null);
        } else {
            icon.setCompoundDrawables(null,
                    ContextCompat.getDrawable(getContext(), drawable),
                    null,
                    null);
        }
    }

    /**
     * 设置标题
     */
    public void setTitle(String title) {
        icon.setText(title);
    }

    /**
     * 设置被选中
     */
    public void setChecked(boolean checked) {
        icon.setChecked(checked);
    }

    /**
     * 是否被选中
     */
    public boolean isChecked() {
        return icon.isChecked();
    }

    /**
     * 设置角标数量
     */
    public void setBadgeNumber(int number) {
        badgeHelper.setBadgeNumber(number);
    }

    /**
     * 设置角标样式
     */
    public void setIconType(@BadgeType int type) {
        badgeHelper.setIconType(type);
    }

    /**
     * Shows the badge with a neat little scale animation.
     */
    public void showBadge() {
        badgeHelper.showBadge();
    }

    /**
     * Hides the badge with a neat little scale animation.
     */
    public void hideBadge() {
        badgeHelper.hideBadge();
    }

    @DrawableRes
    protected static int getDrawableRes(@NonNull Context context, @AttrRes int drawable) {
        return getTypedValue(context, drawable).resourceId;
    }

    @NonNull
    protected static TypedValue getTypedValue(@NonNull Context context, @AttrRes int resId) {
        TypedValue tv = new TypedValue();
        context.getTheme().resolveAttribute(resId, tv, true);
        return tv;
    }

    protected static int pixelToDp(@NonNull Context context, int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(px / displayMetrics.density);
    }

}
