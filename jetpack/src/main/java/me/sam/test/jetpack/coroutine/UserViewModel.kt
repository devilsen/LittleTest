package me.sam.test.jetpack.coroutine

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import me.sam.test.jetpack.room.User
import me.sam.test.jetpack.room.UserRepository

/**
 * desc:
 * date: 2020/05/14 0014
 * @author: dongsen
 */
class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    fun onSortAscending() = getUsers(ascending = true)
    fun onSortDescending() = getUsers(ascending = false)

    private fun getUsers(ascending: Boolean) {
        // launch 可启动新协程而不将结果返回给调用方。任何被视为“一劳永逸”的工作都可以使用 launch 来启动。
        // async 可启动新协程并允许您使用一个名为 await 的暂停函数返回 result。
        viewModelScope.launch {
            _users.value = repository.loadUsers(ascending)
        }
    }

    // https://proandroiddev.com/async-code-using-kotlin-coroutines-233d201099ff
    // 这里只是一个async的示例，并不建议这么做
    private suspend fun getUserByName(name: String): Deferred<User> {
        return viewModelScope.async(Dispatchers.IO) {
            repository.getUser(name)
        }
    }

    fun getUser(name: String = "sam") {
        viewModelScope.launch {
            val await = getUserByName(name).await()
            Log.d("test", await.toString())
        }
    }


    /**
     * Dispatchers.Main - 使用此调度程序可在 Android 主线程上运行协程。此调度程序只能用于与界面交互和执行快速工作。示例包括调用 suspend 函数、运行 Android 界面框架操作，以及更新 LiveData 对象。
     * Dispatchers.IO - 此调度程序经过了专门优化，适合在主线程之外执行磁盘或网络 I/O。示例包括使用 Room 组件、从文件中读取数据或向文件中写入数据，以及运行任何网络操作。
     * Dispatchers.Default - 此调度程序经过了专门优化，适合在主线程之外执行占用大量 CPU 资源的工作。用法示例包括对列表排序和解析 JSON。
     */
    suspend fun get(url: String) =                 // Dispatchers.Main
            withContext(Dispatchers.IO) {              // Dispatchers.IO (main-safety block)
                /* perform network IO here */          // Dispatchers.IO (main-safety block)
            }

}