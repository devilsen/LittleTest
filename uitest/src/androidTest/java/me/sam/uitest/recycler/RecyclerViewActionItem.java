package me.sam.uitest.recycler;

import android.view.View;

import androidx.annotation.IdRes;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import org.hamcrest.Matcher;

/**
 * desc : Item ViewAction
 * date : 2019/4/18
 *
 * @author : dongSen
 */
public class RecyclerViewActionItem implements ViewAction {

    private int mViewId;

    public RecyclerViewActionItem(int mViewId) {
        this.mViewId = mViewId;
    }

    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Click on a child view with specified id.";
    }

    @Override
    public void perform(UiController uiController, View view) {
        view.findViewById(mViewId).performClick();
    }

    public static ViewAction clickRecyclerChildWithId(@IdRes int viewId) {
        return new RecyclerViewActionItem(viewId);
    }
}
