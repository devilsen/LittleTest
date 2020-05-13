package me.sam.uitest;

import me.sam.uitest.basic.BaseApplication;

/**
 * desc : Robolectric Application
 * 有了这个就相当于有了全局的Application，有了全局的Context，可以完整的覆盖大部分场景并且不需要改动已有代码
 * 记得：在@Config里面设置application = RobolectricApp.class
 * 注意：如果要使用Application的，需要继承BaseApplication
 * date : 2020/5/6 5:19 PM
 *
 * @author : dongSen
 */
public class RobolectricApp extends BaseApplication {
}
