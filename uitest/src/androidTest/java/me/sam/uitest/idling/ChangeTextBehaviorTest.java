package me.sam.uitest.idling;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.sam.uitest.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * desc :
 * date : 2019-04-23
 *
 * @author : dongSen
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ChangeTextBehaviorTest {

    private static final String STRING_TO_BE_TYPED = "Espresso";

    private IdlingResource mIdlingResource;

    @Rule
    public ActivityScenarioRule<IdlingTestActivity> mActivityRule = new ActivityScenarioRule<>(
            IdlingTestActivity.class);
    /**
     * Use {@link ActivityScenario to launch and get access to the activity.
     * {@link ActivityScenario#onActivity(ActivityScenario.ActivityAction)} provides a thread-safe
     * mechanism to access the activity.
     */
    @Before
    public void registerIdlingResource() {
        mActivityRule.getScenario().onActivity(new ActivityScenario.ActivityAction<IdlingTestActivity>() {
            @Override
            public void perform(IdlingTestActivity activity) {
                mIdlingResource = activity.getIdlingResource();
                // To prove that the test fails, omit this call:
                IdlingRegistry.getInstance().register(mIdlingResource);
            }
        });
    }

    @Test
    public void changeText_sameActivity() {
        // Type text and then press the button.
        onView(withId(R.id.editTextUserInput))
                .perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.changeTextBt)).perform(click());
        // Check that the text was changed.
        onView(withId(R.id.textToBeChanged)).check(matches(withText(STRING_TO_BE_TYPED)));
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }
}