package me.sam.uitest.example;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import me.sam.uitest.BaseRobolectricUnitTest;
import me.sam.uitest.util.ExampleUtil;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * desc : RobolectricUnitTest 示例
 * date : 2020/5/6 7:30 PM
 *
 * @author : dongSen
 */
public class ExampleRobolectricUnitTest extends BaseRobolectricUnitTest {

    private Context mContext = ApplicationProvider.getApplicationContext();

    @Test
    public void isGif() {
        System.out.println(mContext);
        System.out.println(mContext.getApplicationContext());

        String imagePath1 = "";
        String imagePath2 = "111";
        String imagePath3 = "a.png";
        String imagePath4 = "b.gif";

        assertFalse(ExampleUtil.isGif(null));
        assertFalse(ExampleUtil.isGif(imagePath1));
        assertFalse(ExampleUtil.isGif(imagePath2));
        assertFalse(ExampleUtil.isGif(imagePath3));
        assertTrue(ExampleUtil.isGif(imagePath4));
    }

}
