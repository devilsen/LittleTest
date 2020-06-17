package com.test.devilsen.test

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * desc : splash activity
 * date : 2020/6/16 4:33 PM
 * @author : dongSen
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}