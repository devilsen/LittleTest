package me.sam.test.jetpack

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import me.sam.test.jetpack.lifecycle.MyObserver
import me.sam.test.jetpack.livedata.LiveDataTestActivity
import me.sam.test.jetpack.viewmodel.ViewModelTestActivity

/**
 * desc :
 * date : 2019-04-24
 *
 * @author : dongSen
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //添加一个生命周期观察者    getLifecycle()是FragmentActivity中的方法
        val observer = MyObserver()
        lifecycle.addObserver(observer)
    }

    fun viewModel(view: View) {
        startActivity(Intent(this, ViewModelTestActivity::class.java))
    }

    fun liveData(view: View) {
        startActivity(Intent(this, LiveDataTestActivity::class.java))
    }
}