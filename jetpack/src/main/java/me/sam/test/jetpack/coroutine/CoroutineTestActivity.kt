package me.sam.test.jetpack.coroutine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_coroutine_test.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import me.sam.test.jetpack.R
import me.sam.test.jetpack.room.AppDatabase
import me.sam.test.jetpack.room.UserRepository

/**
 * desc: 协程demo
 * 来源：https://mp.weixin.qq.com/s?__biz=MzAwODY4OTk2Mg==&mid=2652053382&idx=2&sn=3c9ffe976c69675e9c0e08940afd566f
 * 在例子中还有3种并发模式
 * （1）: 取消之前的任务
 * （2）: 让下一个任务排队等待
 * （3）: 复用前一个任务
 *
 * 官方文档：https://developer.android.google.cn/kotlin/coroutines
 * date: 2020/05/14
 * @author: dongsen
 */
class CoroutineTestActivity : AppCompatActivity(), ViewModelProvider.Factory {

    private val userDao by lazy {
        AppDatabase.getInstance(applicationContext).userDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_test)

        val viewModel = ViewModelProvider(this, this).get(UserViewModel::class.java)
        viewModel.users.observe(this, Observer {
            coroutineResultTxt.text = it.toString()
        })

        ascendingTxt.setOnClickListener {
            viewModel.onSortAscending()
        }
        descendingTxt.setOnClickListener {
            viewModel.onSortDescending()
        }

        // async test
        viewModel.getUser()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val repository = UserRepository(dao = userDao)
        return UserViewModel(repository) as T
    }

}