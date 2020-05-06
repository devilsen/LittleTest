package me.sam.uitest;

import android.os.Build;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * desc :
 * date : 2020/5/6 6:56 PM
 *
 * @author : dongSen
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.P, application = RobolectricApp.class)
public class BaseRobolectricUnitTest {
}
