package me.sam.practice.kotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.sam.practice.R
import me.sam.practice.kotlin.activity.TestActivity

/**
 * desc :
 * date : 2019-12-03 17:54
 * @author : dongSen
 */
class LearnKotlinMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        startActivity(Intent(this, TestActivity::class.java))
    }

}