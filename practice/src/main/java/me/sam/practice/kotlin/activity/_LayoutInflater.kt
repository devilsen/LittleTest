package me.sam.practice.kotlin.activity

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast

/**
 * desc :
 * date : 2019-12-03 18:24
 * @author : dongSen
 */
val Context.layoutInflater get() = LayoutInflater.from(this)

fun String.lastChar(): Char = this[this.length - 1]

fun Activity.toast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()