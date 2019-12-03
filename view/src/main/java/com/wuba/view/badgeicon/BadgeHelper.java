package com.wuba.view.badgeicon;

import androidx.core.view.ViewCompat;
import android.widget.TextView;

/**
 * author : dongSen
 * date : 2017/4/24
 * desc : 角标操作逻辑
 */
class BadgeHelper {

    private TextView badge;

    private boolean badgeIsVisible;

    BadgeHelper(TextView badge) {
        this.badge = badge;
        hideBadge();
    }


    /**
     * 设置角标数量
     */
    void setBadgeNumber(int number) {
        if (number == 0) {
            hideBadge();
            return;
        }
        if (!badgeIsVisible)
            showBadge();

        String numberString = String.valueOf(number);
        if (number > 99)
            numberString = "99+";

        badge.setText(numberString);
    }

    /**
     * 设置角标样式
     */
    void setIconType(@BadgeTextView.BadgeType int type) {
        if (type == BadgeTextView.NUMBER) {

        } else if (type == BadgeTextView.NEW) {

        } else if (type == BadgeTextView.LOCK_APP) {

        }
    }

    /**
     * Shows the badge with a neat little scale animation.
     */
    void showBadge() {
        badgeIsVisible = true;
        ViewCompat.animate(badge)
                .setDuration(150)
                .alpha(1)
                .scaleX(1)
                .scaleY(1)
                .start();
    }

    /**
     * Hides the badge with a neat little scale animation.
     */
    void hideBadge() {
        badgeIsVisible = false;
        ViewCompat.animate(badge)
                .setDuration(150)
                .alpha(0)
                .scaleX(0)
                .scaleY(0)
                .start();
    }
}
