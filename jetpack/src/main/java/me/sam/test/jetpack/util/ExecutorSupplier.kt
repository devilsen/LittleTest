package me.sam.test.jetpack.util

import java.util.concurrent.*
import java.util.concurrent.atomic.AtomicInteger

/**
 * desc : 线程池
 * https://blog.mindorks.com/threadpoolexecutor-in-android-8e9d22330ee3
 * date : 2020/5/7 5:58 PM
 * @author : dongSen
 */
class ExecutorSupplier {

    companion object {
        val instance by lazy {
            ExecutorSupplier()
        }
    }

    private val mForCalculationTasks: ThreadPoolExecutor
    private val mForIOTasks: ThreadPoolExecutor
    private val mForMainTasks: MainThreadExecutor

    init {
        val availableProcessors = Runtime.getRuntime().availableProcessors()

        val calculateThreadFactory = threadFactory("jetpack_calculate")
        val ioThreadFactory = threadFactory("jetpack_io")

        mForCalculationTasks = ThreadPoolExecutor(
                availableProcessors + 1,
                availableProcessors + 1,
                60,
                TimeUnit.SECONDS,
                LinkedBlockingDeque(),
                calculateThreadFactory
        )

        mForIOTasks = ThreadPoolExecutor(
                availableProcessors * 2 + 1,
                availableProcessors * 2 + 1,
                60,
                TimeUnit.SECONDS,
                LinkedBlockingDeque(),
                ioThreadFactory
        )

        mForMainTasks = MainThreadExecutor()
    }

    private fun threadFactory(threadName: String): ThreadFactory {
        val threadId = AtomicInteger(0)
        return ThreadFactory {
            val t = Thread(it)
            t.name = String.format("$threadName _%d", threadId.getAndIncrement())
            return@ThreadFactory t
        }
    }

    public fun getCalculateExecutor(): ThreadPoolExecutor {
        return mForCalculationTasks
    }

    public fun getIOExecutor(): ThreadPoolExecutor {
        return mForIOTasks
    }

    public fun getMainExecutor(): Executor {
        return mForMainTasks
    }

}