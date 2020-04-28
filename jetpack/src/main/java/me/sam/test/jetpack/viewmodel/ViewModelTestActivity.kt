package me.sam.test.jetpack.viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_view_model_test.*
import me.sam.test.jetpack.R

/**
 * desc : https://developer.android.com/topic/libraries/architecture/viewmodel#kotlin
 *        https://www.bilibili.com/video/BV1w4411t7UQ?p=10
 *        1）ViewModel是用来存储数据的，它将数据与界面完全分离，在Activity被销毁后重建时仍能保留数据。
 *        2）当所有者 Activity 完成时，框架会调用 ViewModel 对象的 onCleared() 方法，清理它的资源。
 *
 *        注意：ViewModel 绝不能引用视图、Lifecycle 或可能存储对 Activity 上下文的引用的任何类。
 *        ViewModel是独立于Activity之外的，如果有这些变量，会致使Activity不能正确的被回收，从而造成内存泄漏
 * date : 2019-05-07
 *
 * @author : dongSen
 */
class ViewModelTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model_test)

        val model = ViewModelProvider(this).get(MyViewModel::class.java)

        contentTxt.text = model.number.toString()
        addBtn.setOnClickListener {
            model.number++
            contentTxt.text = model.number.toString()
        }

    }
}