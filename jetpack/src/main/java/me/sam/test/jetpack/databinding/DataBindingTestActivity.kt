package me.sam.test.jetpack.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_view_model_test.*
import me.sam.test.jetpack.R

/**
 * desc : https://developer.android.com/topic/libraries/data-binding
 *        https://www.bilibili.com/video/BV1w4411t7UQ?p=12
 * date : 2020年5月2日
 *
 * @author : dongSen
 */
class DataBindingTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityDataBindingTestBinding>(this, R.layout.activity_data_binding_test)

        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        binding.data = model
        binding.lifecycleOwner = this
    }
}