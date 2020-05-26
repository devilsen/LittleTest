package me.sam.test.jetpack.lifecycle

import android.util.Log
import androidx.lifecycle.*

/**
 * desc : 这样可以优化一些与生命周期相关的组件，使其在自己内部处理，而不是依赖在Activity中调用
 * date : 2019-04-24
 *
 * @author : dongSen
 */
class MyLifeCycleEventObserver : LifecycleEventObserver {

    companion object {
        private const val TAG = "EventObserver"
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        Log.d(TAG, "onStateChanged: $source    event: $event")
    }

}