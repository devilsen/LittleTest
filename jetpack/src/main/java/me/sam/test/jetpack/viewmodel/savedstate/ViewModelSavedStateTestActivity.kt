package me.sam.test.jetpack.viewmodel.savedstate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_view_model_test.*
import me.sam.test.jetpack.R

/**
 * desc : https://developer.android.com/topic/libraries/architecture/viewmodel-savedstate#kotlin
 *        https://www.bilibili.com/video/BV1w4411t7UQ?p=14
 *
 *        主要的区别在ViewModel中
 * date : 2020年5月2日
 *
 * @author : dongSen
 */
class ViewModelSavedStateTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model_test)

        val model = ViewModelProvider(this).get(SavedStateViewModel::class.java)

        model.number.observe(this, Observer {
            contentTxt.text = model.number.value.toString()
        })
        addBtn.setOnClickListener {
            model.add()
        }
    }
}