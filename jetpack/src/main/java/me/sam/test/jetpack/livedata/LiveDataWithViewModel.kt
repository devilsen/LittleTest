package me.sam.test.jetpack.livedata

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * desc:
 * date: 2020/04/29
 * @author: dongsen
 */
class LiveDataWithViewModel : ViewModel() {

    val number: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>(0)
    }

    fun addNumber(num: Int) {
        number.value = number.value?.plus(num)
    }

}