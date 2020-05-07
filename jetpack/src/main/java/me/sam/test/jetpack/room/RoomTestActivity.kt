package me.sam.test.jetpack.room

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_room_test.*
import me.sam.test.jetpack.R
import me.sam.test.jetpack.util.ExecutorSupplier

/**
 * desc : room test
 * https://developer.android.com/training/data-storage/room
 * https://www.bilibili.com/video/BV1w4411t7UQ?p=22
 * date : 2020/5/7 3:39 PM
 * @author : dongSen
 */
class RoomTestActivity : AppCompatActivity() {

    companion object {
        val TAG = RoomTestActivity::class.java.simpleName
    }

    private val userDao by lazy {
        AppDatabase.getInstance(applicationContext).userDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_test)

        insertBtn.setOnClickListener {
            ExecutorSupplier.instance.getIOExecutor().execute { userDao.insert(User(name = "sam", height = 175)) }
        }

        ExecutorSupplier.instance.getIOExecutor().execute {
            val list = userDao.getAll()
            Log.e(TAG, list.toString())
        }

//        contentTxt.text = users.toString()
    }

}