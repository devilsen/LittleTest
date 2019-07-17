package me.sam.uitest.recycler;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.sam.uitest.MainActivity;
import me.sam.uitest.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * desc : 点击Item中的View
 * date : 2019/4/19
 *
 * @author : dongSen
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecyclerViewTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    private void openActivity() {
        onView(withId(R.id.button_test_recyclerView)).perform(click());
    }

    @Test
    public void clickPhone(){
        openActivity();
        onView(withId(R.id.recycler_view_test)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0,RecyclerViewActionItem.clickRecyclerChildWithId(R.id.image_button_item_call)));
    }

    @Test
    public void clickPhoneFindWithText(){
        openActivity();
        onView(withId(R.id.recycler_view_test)).perform(RecyclerViewActions.scrollToPosition(70));
        onView(allOf(withId(R.id.text_view_item_name), withText("number 70"))).check(matches(withText("number 70")));
    }

}
