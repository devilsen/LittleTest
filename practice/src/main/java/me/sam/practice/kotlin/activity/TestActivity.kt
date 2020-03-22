package me.sam.practice.kotlin.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_test.*
import me.sam.practice.R

/**
 * desc :
 * date : 2019-12-03 17:42
 * @author : dongSen
 */
class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        TestBtn.setOnClickListener {
            changeTxt()
        }

        "Test".lastChar()

        toast("hello")

        val note = Note(text = "this is a text")
        note == note.copy()

        val other = note.copy(text = "something else ...")

    }

    private fun changeTxt() {
        TestTxt.text = "change the text"
    }

}