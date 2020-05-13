package me.sam.test.jetpack.util

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor

/**
 * desc : 主线程线程池
 * date : 2020/5/7 6:19 PM
 * @author : dongSen
 */
class MainThreadExecutor : Executor {

    private val handler: Handler = Handler(Looper.getMainLooper())

    override fun execute(command: Runnable) {
        handler.post(command)
    }

}