package com.test.devilsen.test;

import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * desc :
 * date : 2018/6/28
 *
 * @author : dongSen
 */
public class Test3 {
    /**
     * 父类中定义的静态语句块要优于子类的变量赋值操作
     * JVM保证一个类的clinit方法在多线程中被正确加锁、同步
     */
    static class Parent {
        public static int A = 1;
        static {
            A = 2;
        }
    }

    static class Sub extends Parent {
        public static int B = A;
    }

    public static void main(String[] args) {
        System.out.println(Sub.B);
    }


public boolean onTouchEvent(MotionEvent event) {
    // 这里主要看viewFlags这个参数，如果设置了Clicklistener或者LongClickener都会把这个值置位相应的标志位，也就是说是可点击的。
    final boolean clickable = ((viewFlags & CLICKABLE) == CLICKABLE
            || (viewFlags & LONG_CLICKABLE) == LONG_CLICKABLE)
            || (viewFlags & CONTEXT_CLICKABLE) == CONTEXT_CLICKABLE;

    // 这里注意，虽然有的组件设置为了不可用，但是也是会『吃掉』点击事件的，只不过没有回应。
    if ((viewFlags & ENABLED_MASK) == DISABLED) {
        return clickable;
    }

    if (clickable || (viewFlags & TOOLTIP) == TOOLTIP) {
        switch (action) {
            case MotionEvent.ACTION_UP:
                // 把各种回调都移除，因为一次点击已经结束
                if (!clickable) {
                    removeTapCallback();
                    removeLongPressCallback();
                    mInContextButtonPress = false;
                    mHasPerformedLongPress = false;
                    break;
                }
                boolean prepressed = (mPrivateFlags & PFLAG_PREPRESSED) != 0;
                if ((mPrivateFlags & PFLAG_PRESSED) != 0 || prepressed) {
                    // View是否获取了焦点
                    boolean focusTaken = false;
                    if (isFocusable() && isFocusableInTouchMode() && !isFocused()) {
                        focusTaken = requestFocus();
                    }
                    // 这里其实判断OnClick事件的一个重要分支，mHasPerformedLongPress顾名思义就是是否触发的长按事件，但是如果
                    // OnLongClick方法返回false，依然是会触发OnClick事件
                    if (!mHasPerformedLongPress && !mIgnoreNextUpEvent) {
                        // Only perform take click actions if we were in the pressed state
                        if (!focusTaken) {
                            // 这里其实就是触发OnClick方法的触发点，但是触发的时候不是直接调用，而是用post的方式，
                            // 这样可以在点击事件的反馈效果之前，让其他的视觉效果也能相继触发
                            if (mPerformClick == null) {
                                mPerformClick = new PerformClick();
                            }
                            if (!post(mPerformClick)) {
                                performClickInternal();
                            }
                        }
                    }
                }
                break;

            case MotionEvent.ACTION_DOWN:
                if (!clickable) {
                    checkForLongClick(0, x, y);
                    break;
                }
                // For views inside a scrolling container, delay the pressed feedback for
                // a short period in case this is a scroll.
                if (isInScrollingContainer) {
                    postDelayed(mPendingCheckForTap, ViewConfiguration.getTapTimeout());
                } else {
                    // Not inside a scrolling container, so show the feedback right away
                    checkForLongClick(0, x, y);
                }
                break;

            case MotionEvent.ACTION_CANCEL:
                removeTapCallback();
                removeLongPressCallback();
                mInContextButtonPress = false;
                mHasPerformedLongPress = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (clickable) {
                    drawableHotspotChanged(x, y);
                }
                // Be lenient about moving outside of buttons
                if (!pointInView(x, y, mTouchSlop)) {
                    // Outside button
                    // Remove any future long press/tap checks
                    removeTapCallback();
                    removeLongPressCallback();
                    if ((mPrivateFlags & PFLAG_PRESSED) != 0) {
                        setPressed(false);
                    }
                    mPrivateFlags3 &= ~PFLAG3_FINGER_DOWN;
                }
                break;

        }
        return true;
    }
    return false;
}
}
