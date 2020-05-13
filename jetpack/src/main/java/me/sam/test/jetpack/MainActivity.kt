package me.sam.test.jetpack

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import me.sam.test.jetpack.databinding.DataBindingTestActivity
import me.sam.test.jetpack.lifecycle.MyLifeCycleObserver
import me.sam.test.jetpack.livedata.LiveDataTestActivity
import me.sam.test.jetpack.navigation.NavigationTestActivity
import me.sam.test.jetpack.paging.PagingTestActivity
import me.sam.test.jetpack.room.RoomTestActivity
import me.sam.test.jetpack.viewmodel.ViewModelTestActivity
import me.sam.test.jetpack.viewmodel.savedstate.ViewModelSavedStateTestActivity

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
        lifecycle.addObserver(MyLifeCycleObserver())
    }

    fun viewModel(view: View) {
        startActivity(Intent(this, ViewModelTestActivity::class.java))
    }

    fun viewModelSavedState(view: View) {
        startActivity(Intent(this, ViewModelSavedStateTestActivity::class.java))
    }

    fun liveData(view: View) {
        startActivity(Intent(this, LiveDataTestActivity::class.java))
    }

    fun dataBinding(view: View) {
        startActivity(Intent(this, DataBindingTestActivity::class.java))
    }

    fun navigation(view: View) {
        startActivity(Intent(this, NavigationTestActivity::class.java))
    }

    fun room(view: View) {
        startActivity(Intent(this, RoomTestActivity::class.java))
    }

    fun paging(view: View) {
        startActivity(Intent(this, PagingTestActivity::class.java))
    }

}