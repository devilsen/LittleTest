package me.sam.test.jetpack.livedata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_view_model_test.*
import me.sam.test.jetpack.R

/**
 * desc: https://developer.android.com/topic/libraries/architecture/livedata
 *       https://www.bilibili.com/video/BV1w4411t7UQ?p=11
 *
 * date: 2020/04/29 0029
 * @author: dongsen
 */
class LiveDataTestActivity : AppCompatActivity() {

    private lateinit var model: LiveDataWithViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model_test)

        model = ViewModelProvider(this).get(LiveDataWithViewModel::class.java)
        model.number.observe(this, Observer { number -> contentTxt.text = number.toString() })

        addBtn.setOnClickListener { model.addNumber(1) }
    }

}