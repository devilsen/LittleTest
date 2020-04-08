package com.test.devilsen.test.shortcut

import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.Bitmap
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.test.devilsen.test.MainActivity
import com.test.devilsen.test.R
import com.wuba.view.bottomsheet.BottomSheetActivity
import com.wuba.view.midautumn.MidAutumnMainActivity

/**
 * desc : ShortCuts
 * date : 2020/4/2 4:43 PM
 * @author : dongSen
 */
class ShortCutTestActivity : AppCompatActivity() {

    companion object {
        val TAG: String = ShortCutTestActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shortcuts_test)
    }

    fun addShortcut(view: View) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N_MR1) {
            return
        }

        val shortcutManager = getSystemService(ShortcutManager::class.java) ?: return
        val maxShortcutCountPerActivity = shortcutManager.maxShortcutCountPerActivity
        Log.e(TAG, maxShortcutCountPerActivity.toString())

        val list = mutableListOf<ShortcutInfo>()
        createShortcut().let {
            list.add(it)
        }
        createShortcuts().let {
            list.add(it)
        }

        val result = shortcutManager.setDynamicShortcuts(list)
        Log.d(TAG, "添加shortcut result $result")
    }

    @RequiresApi(Build.VERSION_CODES.N_MR1)
    private fun createShortcut(): ShortcutInfo {
        return ShortcutInfo.Builder(this, "test1").apply {
            setShortLabel("动态快捷1")   // 作为图标时使用
            setLongLabel("动态快捷1😀")  // 作为长按时使用
            setIcon(Icon.createWithResource(this@ShortCutTestActivity, R.drawable.ic_heat_red_40dp))
//            setIcon(Icon.createWithBitmap(bitmap))
            setIntent(Intent().apply {
                action = Intent.ACTION_MAIN
                setClass(this@ShortCutTestActivity, ShortCutTestActivity::class.java)
                putExtra("extra", "this from shortcut")
            })
        }.build()
    }


    @RequiresApi(Build.VERSION_CODES.N_MR1)
    private fun createShortcuts(): ShortcutInfo {
        return ShortcutInfo.Builder(this, "test2").apply {
            setShortLabel("多动态快捷2")
            setLongLabel("多动态快捷2😂")
            setIcon(Icon.createWithResource(this@ShortCutTestActivity, R.drawable.ic_clubs_black_40dp))
            setIntents(arrayOf(
                    // 这样会连续跳转两个Activity
                    Intent().apply {
                        action = Intent.ACTION_MAIN
                        setClass(this@ShortCutTestActivity, BottomSheetActivity::class.java)
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                    },
                    Intent().apply {
                        action = Intent.ACTION_MAIN
                        setClass(this@ShortCutTestActivity, MidAutumnMainActivity::class.java)
                    }
            ))
        }.build()
    }


    private fun getBitmap(callback: Callback) {
        Thread(Runnable {
            val bitmap = Glide.with(this)
                    .load("http://img.mp.sohu.com/q_mini,c_zoom,w_640/upload/20170731/4c79a1758a3a4c0c92c26f8e21dbd888_th.jpg")
                    .asBitmap()
                    .centerCrop()
                    .into(50, 50)
                    .get()
            callback.onSuccess(bitmap)
        }).start()
    }

    interface Callback {
        fun onSuccess(bitmap: Bitmap)
    }

}