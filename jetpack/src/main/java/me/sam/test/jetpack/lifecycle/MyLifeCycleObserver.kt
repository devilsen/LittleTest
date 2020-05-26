package me.sam.test.jetpack.lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

/**
 * desc : 这样可以优化一些与生命周期相关的组件，使其在自己内部处理，而不是依赖在Activity中调用
 * date : 2019-04-24
 *
 * @author : dongSen
 */
class MyLifeCycleObserver : LifecycleObserver {

    companion object {
        private const val TAG = "MyLifeCycleObserver"
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.w(TAG, "onCreate: ")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Log.w(TAG, "onStart: ")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Log.w(TAG, "onResume: ")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Log.w(TAG, "onPause: ")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Log.w(TAG, "onStop: ")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        Log.w(TAG, "onDestroy: ")
    }

//    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
//        Log.d(TAG, "onStateChanged: $source    event: $event")
//    }

}