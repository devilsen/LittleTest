package me.sam.uitest

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_ui_test.*

/**
 * desc :
 * date : 2020/3/6 10:46 AM
 * @author : dongSen
 */
class UITestActivity : AppCompatActivity() {

    companion object {
        private val TAG: String = UITestActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui_test)

        // 子线程更新UI
        Thread(Runnable {
            text_view_show.text = "hahaha"
            Log.e(TAG, "change the text in " + Thread.currentThread())
        }).start()
    }

    fun check(view: View) {

    }


}