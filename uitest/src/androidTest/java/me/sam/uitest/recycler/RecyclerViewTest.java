package me.sam.uitest.recycler;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import me.sam.uitest.MainActivity;
import me.sam.uitest.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * desc :
 * date : 2019/4/19
 *
 * @author : dongSen
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class RecyclerViewTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);
    @Before
    private void openActivity() {
        onView(withId(R.id.button_test_recyclerView)).perform(click());
    }

    @Test
    public void clickPhone(){

    }

}
