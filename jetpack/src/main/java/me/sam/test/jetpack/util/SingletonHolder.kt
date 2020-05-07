package me.sam.test.jetpack.util

/**
 * desc : https://medium.com/@BladeCoder/kotlin-singletons-with-argument-194ef06edd9e
 * date : 2020/5/7 5:31 PM
 * @author : dongSen
 */
open class SingletonHolder<out T : Any, in A>(creator: (A) -> T) {
    private var creator: ((A) -> T)? = creator
    @Volatile
    private var instance: T? = null

    fun getInstance(arg: A): T {
        val i = instance
        if (i != null) {
            return i
        }

        return synchronized(this) {
            val i2 = instance
            if (i2 != null) {
                i2
            } else {
                val created = creator!!(arg)
                instance = created
                creator = null
                created
            }
        }
    }
}