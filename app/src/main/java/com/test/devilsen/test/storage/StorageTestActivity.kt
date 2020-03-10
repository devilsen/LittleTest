package com.test.devilsen.test.storage

import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.test.devilsen.test.R
import java.io.File

/**
 * desc : 存储
 * date : 2020/3/10 5:40 PM
 * @author : dongSen
 */
class StorageTestActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage_test)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getAllStoragePath()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            get21Storage()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            get24Storage()
        }
    }

    private fun getAllStoragePath() {
        val externalCacheDir: File? = externalCacheDir
        val cachedirNotification: File? = getExternalFilesDir(Environment.DIRECTORY_NOTIFICATIONS)
        val externalcachedirMovies: File? = getExternalFilesDir(Environment.DIRECTORY_MOVIES)

        showLog("externalCacheDir", externalCacheDir?.absolutePath)
        showLog("externalCacheDir_notification", cachedirNotification?.absolutePath)
        showLog("externalCacheDir_movies", externalcachedirMovies?.absolutePath)
        showLog("-----------------------------------------------------","")


        val cacheDir: File = cacheDir
        val filesDir = filesDir
        val obbDir = obbDir

        showLog("cacheDir", cacheDir.absolutePath)
        showLog("filesDir", filesDir.absolutePath)
        showLog("obbDir", obbDir.absolutePath)
        showLog("-----------------------------------------------------","")
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun get21Storage() {
        val codeCacheDir = codeCacheDir
        val noBackupFilesDir = noBackupFilesDir

        showLog("21 codeCacheDir", codeCacheDir.absolutePath)
        showLog("21 noBackupFilesDir", noBackupFilesDir.absolutePath)
        showLog("-----------------------------------------------------","")
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun get24Storage() {
        val dataDir = dataDir

        showLog("24 dataDir", dataDir.absolutePath)
    }

    private fun showLog(type: String, text: String?) {
        Log.e("storage", "$type:  $text" ?: " 没有获取到")
    }


}