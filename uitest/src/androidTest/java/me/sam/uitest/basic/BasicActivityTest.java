package me.sam.uitest.basic;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

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
 * desc : 主界面测试
 * date : 2019/4/16
 *
 * @author : 官方例子
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class BasicActivityTest {


    public static final String STRING_TO_BE_TYPED = "Hello";

    /**
     * Use {@link ActivityScenarioRule} to create and launch the activity under test, and close it
     * after test completes. This is a replacement for {@link androidx.test.rule.ActivityTestRule}.
     */
    @Rule
    public ActivityScenarioRule<BasicTestActivity> activityScenarioRule
            = new ActivityScenarioRule<>(BasicTestActivity.class);

//    @Before
//    public void launchActivity() {
//        ActivityScenario.launch(MainActivity.class);
//    }

    private void openActivity() {
//        onView(withId(R.id.button_test_basic)).perform(click());
    }

    @Test
    public void changeText_sameActivity() {
        openActivity();
        onView(ViewMatchers.withId(R.id.editTextUserInput)).perform(click());
        // 输入一段文字
        onView(ViewMatchers.withId(R.id.editTextUserInput))
                .perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());
//        onView(withId(R.id.changeTextBt)).perform(click()); 与下面是等价的
        onView(withText(R.string.change_text)).perform(click());

        // 检查是否相同
        onView(withId(R.id.textToBeChanged)).check(matches(withText(STRING_TO_BE_TYPED)));
    }

    @Test
    public void changeText_newActivity() {
        openActivity();
        // Type text and then press the button.
        onView(ViewMatchers.withId(R.id.editTextUserInput)).perform(click());
        onView(withId(R.id.editTextUserInput)).perform(typeText(STRING_TO_BE_TYPED),
                closeSoftKeyboard());
        onView(withId(R.id.activityChangeTextBtn)).perform(click());

        // This view is in a different Activity, no need to tell Espresso.
        onView(withId(R.id.show_text_view)).check(matches(withText(STRING_TO_BE_TYPED)));
    }
}

